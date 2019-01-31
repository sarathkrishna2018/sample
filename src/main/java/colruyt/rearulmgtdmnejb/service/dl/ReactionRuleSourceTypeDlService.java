package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceType;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * Session Bean implementation class ReactionRuleSourceTypeDlService
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactionRuleSourceTypeDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReactionRuleSourceTypeDlService.class);

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	
	/**
	 * 
	 * @param sourceType
	 */
	public void createOrUpdate(List<ReactionRuleSourceType> sourceType) {
		for(ReactionRuleSourceType reaRuleSetSrc : sourceType){
			entityManager.merge(reaRuleSetSrc);
			entityManager.flush();
		}
	}


	/**This method is used to remove entries for a particular rule id
	 * @param ruleId
	 */
	public void remove(Long ruleId) {	
		Query query = entityManager.createQuery("Delete from  ReactionRuleSourceType reactionRuleSourceType where reactionRuleSourceType.id.reaRuleId = ?1");
		query.setParameter(1, ruleId).executeUpdate();		
	}

	public long physicalDeleteSourceType(XPSRuleBo xpsRuleBo){
		Query query = entityManager.createQuery("Delete from  ReactionRuleSourceType reactionRuleSourceType where reactionRuleSourceType.id.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 1L;
	}
	
}
