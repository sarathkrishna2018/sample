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
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReactingRuleActionDlService implements Serializable {
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;
	private static final long serialVersionUID = 1L;

	public ReactingRuleAction createOrUpdate(ReactingRuleAction reaReactingAct) {
		ReactingRuleAction reactingRuleAction =entityManager.merge(reaReactingAct);
		entityManager.flush();
		return reactingRuleAction;

	}

	public ReactingRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(ReactingRuleAction.class, reaRuleId);
		
	}
	
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		Query query = entityManager.createQuery("Delete from  ReactingRuleAction reactingRuleAction where reactingRuleAction.reaRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();	
		entityManager.clear();
	}

}
