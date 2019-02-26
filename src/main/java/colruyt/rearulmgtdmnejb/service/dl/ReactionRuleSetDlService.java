package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleSetInfoBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;


/**
 * @version 1.0
 * @created 28-nov-2018 8:31:07
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactionRuleSetDlService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ReactionRuleSetDlService.class);

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	/**
	 * 
	 * @param reactionPricingRuleSet
	 */
	public ReactionRuleSet createOrUpdate(ReactionRuleSet reaRuleset) {
		ReactionRuleSet ruleset = entityManager.merge(reaRuleset);
		entityManager.flush();
		return ruleset;
	}

	/**
	 * 
	 * @param cgChainId
	 * @param pcChainId
	 */
	public List<ReactionRuleSet> findByAttributes(long cgChainId, long pcChainId, long ruleTypeId) {
		List<ReactionRuleSet> reactionRulesets = null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReactionRuleSet> criteriaQuery = criteriaBuilder.createQuery(ReactionRuleSet.class);
		Root<ReactionRuleSet> reactionRuleSetRoot = criteriaQuery.from(ReactionRuleSet.class);
		Path<Long> cgChainPath = reactionRuleSetRoot.get("colruytGroupChainId");
		Predicate cgChainPredicate = criteriaBuilder.equal(cgChainPath, cgChainId);
		Path<Long> pcChainPath = reactionRuleSetRoot.get("priceCompetitorChainId");
		Predicate pcChainPredicate = criteriaBuilder.equal(pcChainPath, pcChainId);
		Path<Long> ruleTypePath = reactionRuleSetRoot.get("ruleTypeId");
		Predicate ruleTypePredicate = criteriaBuilder.equal(ruleTypePath, ruleTypeId);
		Predicate logicallyDeletePredicate = criteriaBuilder.isNull(reactionRuleSetRoot.<Date>get("logicallyDeletedDate"));
		Predicate combinedPredicate = criteriaBuilder.and(cgChainPredicate, pcChainPredicate, ruleTypePredicate, logicallyDeletePredicate);
		criteriaQuery.where(combinedPredicate);
		try {
			reactionRulesets = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (NoResultException exception) {
			logger.error("No Result Found while finding by attributes", exception);
		}
		return reactionRulesets;
	}

	/**
	 * 
	 * @param id
	 */
	public ReactionRuleSet findByPk(long reaRulesetId) {
		ReactionRuleSet reaRuleset = entityManager.find(ReactionRuleSet.class, reaRulesetId);
		entityManager.flush();
		return reaRuleset;
	}

	/**
	 * 
	 * @param cgChainId
	 * @param pcChainId
	 */
	public List<ReactionRuleSet> findByCgChainAndPCChain(long cgChainId, long pcChainId) {
		List<ReactionRuleSet> reactionRulesets = null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReactionRuleSet> criteriaQuery = criteriaBuilder.createQuery(ReactionRuleSet.class);
		Root<ReactionRuleSet> reactionRuleSetRoot = criteriaQuery.from(ReactionRuleSet.class);
		Path<Long> cgChainPath = reactionRuleSetRoot.get("colruytGroupChainId");
		Predicate cgChainPredicate = criteriaBuilder.equal(cgChainPath, cgChainId);
		Path<Long> pcChainPath = reactionRuleSetRoot.get("priceCompetitorChainId");
		Predicate pcChainPredicate = criteriaBuilder.equal(pcChainPath, pcChainId);
		Predicate logicallyDeletePredicate = criteriaBuilder.isNull(reactionRuleSetRoot.<Date>get("logicallyDeletedDate"));
		Predicate combinedPredicate = criteriaBuilder.and(cgChainPredicate, pcChainPredicate, logicallyDeletePredicate);
		criteriaQuery.where(combinedPredicate);
		try {
			reactionRulesets = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (NoResultException exception) {
			logger.error("No Result Found", exception);
		}
		return reactionRulesets;
	}
	/**
	 * This method is to delete reaction ruleset
	 * @param reactionRuleSet
	 */
	public void logicallyDeleteRuleSet(ReactionRuleSet reactionRuleSet) {
		Date updatedDate = new Date();
		Query query = entityManager.createQuery(
				"update ReactionRuleSet reactionRuleSet set reactionRuleSet.logicallyDeletedDate=?1 where reactionRuleSet.reaRulesetId=?2",
				ReactionRuleSet.class);
		query.setParameter(1, updatedDate);
		query.setParameter(2, reactionRuleSet.getReaRulesetId());
		query.executeUpdate();
	}
	
	public List<DeleteRuleSetInfoBo> findAllLogicallyDeletedRuleSet(Date dateForRulesDelete) {		
		List<DeleteRuleSetInfoBo> ruleSet = new ArrayList<DeleteRuleSetInfoBo>();
		Query query = entityManager.createNativeQuery("SELECT ruleSet.REA_RULESET_ID, ruleSet.RULETYPE_ID from"
				+ " REA_RULESET ruleSet LEFT JOIN SOI_PPT_RULE soippt ON ruleSet.REA_RULESET_ID=soippt.REACT_RULESET_ID "
				+ " LEFT JOIN SOI_CG_CHN_RULE soicg ON ruleSet.REA_RULESET_ID=soicg.REACT_RULESET_ID"
				+ " where ruleSet.DATE_LOGICALLY_DELETED IS NOT NULL AND ruleSet.DATE_LOGICALLY_DELETED < (?1) AND soippt.REACT_RULESET_ID IS NULL AND soicg.REACT_RULESET_ID IS NULL");
		query.setParameter(1, dateForRulesDelete, TemporalType.DATE);
		List<Object[]> results = query.getResultList();
		for(Object[] item : results) {
			Long ruleSetId = ((BigDecimal)item[0]).longValue();
			Long ruleType = ((BigDecimal)item[1]).longValue();
			DeleteRuleSetInfoBo deleteRuleInfoBo= new DeleteRuleSetInfoBo(ruleSetId, ruleType);
	        ruleSet.add(deleteRuleInfoBo);
		}
		logger.info("findAllLogicallyDeletedRuleSet completed");
		return ruleSet;
	}
	
	public void physicalDeleteAllRuleSet(Set<Long> ruleSetIds){
		Query query = entityManager.createQuery("Delete from  ReactionRuleSet reactionRuleSet where reactionRuleSet.reaRulesetId IN (?1)");
		query.setParameter(1, ruleSetIds).executeUpdate();	
		entityManager.clear();
	}
}
