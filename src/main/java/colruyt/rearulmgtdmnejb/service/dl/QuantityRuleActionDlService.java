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
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class QuantityRuleActionDlService implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public QuantityRuleAction createOrUpdate(QuantityRuleAction quantityRuleAction) {
		QuantityRuleAction qtyRuleAction = entityManager.merge(quantityRuleAction);
		entityManager.flush();
		return qtyRuleAction;

	}

	public QuantityRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(QuantityRuleAction.class, reaRuleId);

	}

	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		Query query = entityManager.createQuery(
				"Delete from  QuantityRuleAction quantityRuleAction where quantityRuleAction.reactionRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();
		entityManager.clear();
	}

}