package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

/**
 * Session Bean implementation class ReactionRuleSourceTypeDlService
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactionRuleSourceTypeDlService implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = ReactionRuleDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public void physicalDeleteSourceType(DeleteRuleInfoBo deleteRuleInfoBo){
		Query query = entityManager.createQuery("Delete from  ReactionRuleSourceType reactionRuleSourceType where reactionRuleSourceType.id.reaRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();	
		entityManager.clear();
	}
	
}
