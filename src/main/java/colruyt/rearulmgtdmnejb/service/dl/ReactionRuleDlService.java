package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReactionRuleMgtConstants;
import colruyt.rearulmgtdmnejb.util.SQLQueries;

/**
 * @version 1.0
 * @created 28-nov-2018 8:25:18
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactionRuleDlService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ReactionRuleDlService.class);

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	/**
	 * 
	 * @param reactionPricingRule
	 * @return
	 */
	public ReactionRule createOrUpdate(ReactionRule reactionPricingRule) {
		ReactionRule reactionRule = entityManager.merge(reactionPricingRule);
		entityManager.flush();
		return reactionRule;
	}

	public ReactionRule findByPk(String ruleType) {
		ReactionRule reactionRule = entityManager.find(ReactionRule.class, ruleType);
		entityManager.flush();
		return reactionRule;
	}

	public ReactionRule findByPk(Long ruleId) {
		return entityManager.find(ReactionRule.class, ruleId);
	}

	public List<ReactionRule> findByRuleSetId(long ruleSetId) {
		Date currentDate = new Date();
		List<ReactionRule> ruleList = Lists.newArrayList();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReactionRule> criteriaQuery = criteriaBuilder.createQuery(ReactionRule.class);
		Root<ReactionRule> root = criteriaQuery.from(ReactionRule.class);
		List<Predicate> predicateList = Lists.newArrayList();
		Predicate ruleSetPredicate = criteriaBuilder.equal(root.<Long>get("reaRulesetId"), ruleSetId);
		predicateList.add(ruleSetPredicate);
		Predicate logicallyDeletePredicate = criteriaBuilder.isNull(root.<Date>get("logicallyDeletedDate"));
		predicateList.add(logicallyDeletePredicate);
		Path<Date> validDatePath = root.<Date>get("validUpto");
		Predicate validToPredicate = criteriaBuilder.greaterThanOrEqualTo(validDatePath, currentDate);
		Predicate validToInfinityPredicate = criteriaBuilder.isNull(validDatePath);
		Predicate validToOrPredicate = criteriaBuilder.or(validToPredicate, validToInfinityPredicate);
		predicateList.add(validToOrPredicate);
		Predicate[] predicatesArray = predicateList.toArray(new Predicate[predicateList.size()]);
		criteriaQuery.where(predicatesArray);
		try {
			ruleList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (NoResultException exception) {
			logger.info("No results", exception);
			ruleList = null;
		}
		return ruleList;
	}

	/**
	 * This method is used to find Parent rule by using child rule id
	 * 
	 * @param childRuleId
	 * @return
	 */
	public ReactionRule findParentRule(Long childRuleId) {
		ReactionRule parentRule;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReactionRule> criteriaQuery = criteriaBuilder.createQuery(ReactionRule.class);
		Root<ReactionRule> root = criteriaQuery.from(ReactionRule.class);
		List<Predicate> predicateList = Lists.newArrayList();
		Predicate ruleSetPredicate = criteriaBuilder.equal(root.<Long>get("childRuleId"), childRuleId);
		predicateList.add(ruleSetPredicate);
		Predicate[] predicatesArray = predicateList.toArray(new Predicate[predicateList.size()]);
		criteriaQuery.where(predicatesArray);
		try {
			parentRule = entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException exception) {
			logger.info("No results for parent rule", exception);
			parentRule = null;
		}
		return parentRule;
	}

	public void updateLogicallyDeletedDate(ReactionRule reactionRule) {
		Date updatedDate = new Date();
		Query query = entityManager.createQuery(
				"update ReactionRule reactionRule set reactionRule.logicallyDeletedDate=?1 where reactionRule.reaRuleId=?2",
				ReactionRule.class);
		query.setParameter(1, updatedDate);
		query.setParameter(2, reactionRule.getReaRuleId());
		query.executeUpdate();
	}

	
	public Long findAllByRuleSetId(Long rulesetId) {
		TypedQuery<Long> query = entityManager.createQuery(
				"select max(reactionRule.rulePriority) from ReactionRule reactionRule where reactionRule.reaRulesetId = ?1",
				Long.class);
		query.setParameter(1, rulesetId);
		return query.getSingleResult();
	}

	public void logicallyDeleteRules(List<Long> reactionRuleIds, String logonId) {
		Date updatedDate = new Date();
		Query query = entityManager.createQuery(
				"update ReactionRule reactionRule set reactionRule.logicallyDeletedDate=?1,reactionRule.lstUpdateBy=?2 where reactionRule.reaRuleId IN ?3",
				ReactionRule.class);
		query.setParameter(1, updatedDate);
		query.setParameter(2, logonId);
		query.setParameter(3, reactionRuleIds);
		query.executeUpdate();
	}

	public List<XPSRuleBo> findAllLogicallyDeletedRules(Date dateForRulesDelete) {
		List<XPSRuleBo> rules = new ArrayList<XPSRuleBo>();
		String schemaName = SQLQueries.getSchemaName(entityManager);
		Query query = entityManager.createNativeQuery(
				SQLQueries.FIND_ALL_LOGICALLY_DELETE_RULE.replaceAll(ReactionRuleMgtConstants.SCHEMA, schemaName));
		query.setParameter(1, dateForRulesDelete, TemporalType.DATE);
		List<Object[]> results = query.getResultList();
		for (Object[] item : results) {
			Long ruleId = ((BigDecimal) item[0]).longValue();
			Long ruleType = ((BigDecimal) item[1]).longValue();
			XPSRuleBo xpsRuleBo = new XPSRuleBo(ruleId, ruleType);
			rules.add(xpsRuleBo);
		}
		logger.info("findAllLogicallyDeletedRules completed");
		return rules;
	}

	public List<XPSRuleBo> findAllExpiredRules(Date dateForRulesDelete) {
		List<XPSRuleBo> rules = new ArrayList<XPSRuleBo>();
		String schemaName = SQLQueries.getSchemaName(entityManager);
		Query query = entityManager.createNativeQuery(
				SQLQueries.FIND_ALL_EXPIRED_RULE.replaceAll(ReactionRuleMgtConstants.SCHEMA, schemaName));
		query.setParameter(1, dateForRulesDelete, TemporalType.DATE);
		List<Object[]> results = query.getResultList();
		for (Object[] item : results) {
			Long ruleId = ((BigDecimal) item[0]).longValue();
			Long ruleType = ((BigDecimal) item[1]).longValue();
			XPSRuleBo xpsRuleBo = new XPSRuleBo(ruleId, ruleType);
			rules.add(xpsRuleBo);
		}
		logger.info("findAllLogicallyDeletedRules findAllExpiredRules");
		return rules;
	}

	public long physicalDeleteRule(XPSRuleBo xpsRuleBo) {
		Query query = entityManager
				.createQuery("Delete from  ReactionRule reactionRule where reactionRule.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();
		entityManager.clear();
		return 1L;
	}

}