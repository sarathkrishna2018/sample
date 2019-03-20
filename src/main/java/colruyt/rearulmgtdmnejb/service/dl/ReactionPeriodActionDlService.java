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
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactionPeriodActionDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public ReactionPeriodRuleAction createOrUpdate(ReactionPeriodRuleAction reactionPeriodRule) {
		ReactionPeriodRuleAction reactionPeriodRuleAction = entityManager.merge(reactionPeriodRule);
		entityManager.flush();
		return reactionPeriodRuleAction;
	}

	public ReactionPeriodRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(ReactionPeriodRuleAction.class, reaRuleId);
	}

	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		Query query = entityManager.createQuery(
				"Delete from  ReactionPeriodRuleAction reactionPeriodRuleAction where reactionPeriodRuleAction.reaRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();
		entityManager.clear();
	}
}
