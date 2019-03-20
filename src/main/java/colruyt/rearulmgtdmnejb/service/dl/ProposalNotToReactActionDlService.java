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
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProposalNotToReactActionDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReactionRuleDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public ProposalNotToReactRuleAction createOrUpdate(ProposalNotToReactRuleAction notToReactRuleAction) {
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = entityManager.merge(notToReactRuleAction);
		entityManager.flush();
		return proposalNotToReactRuleAction;

	}

	public ProposalNotToReactRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(ProposalNotToReactRuleAction.class, reaRuleId);

	}

	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		Query query = entityManager.createQuery(
				"Delete from  ProposalNotToReactRuleAction proposalNotToReactRuleAction where proposalNotToReactRuleAction.reactionRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();
		entityManager.clear();
	}

	public void physicalDeleteElementsRsn(DeleteRuleInfoBo deleteRuleInfoBo) {
		Query query = entityManager
				.createQuery("Delete from  ProposalNotToReactRuleActionRsn prsn where prsn.id.reactionRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();
		entityManager.clear();
	}

}