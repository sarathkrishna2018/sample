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

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactionRuleActionTypeDlService implements Serializable {
	private static final long serialVersionUID = 1L;


	@PersistenceContext(unitName = ReactionRuleDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public void physicalDeleteActionForRules(DeleteRuleInfoBo deleteRuleInfoBo) {
		Query query = entityManager.createQuery(
				"Delete from  ReactionRuleActionType reactionRuleActionType where reactionRuleActionType.id.reaRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();
		entityManager.clear();
	}

}
