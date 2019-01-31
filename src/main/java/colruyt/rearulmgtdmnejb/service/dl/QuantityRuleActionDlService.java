package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * @version 1.0
 * @created 28-nov-2018 9:02:58
 */

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class QuantityRuleActionDlService implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	

	/**
	 * 
	 * @param quantityRuleAction
	 */
	public QuantityRuleAction createOrUpdate(QuantityRuleAction quantityRuleAction){
		QuantityRuleAction qtyRuleAction = entityManager.merge(quantityRuleAction);
		entityManager.flush();
		return qtyRuleAction;
		
	}



	public QuantityRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(QuantityRuleAction.class, reaRuleId);
		
	}
	
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		Query query = entityManager.createQuery("Delete from  QuantityRuleAction quantityRuleAction where quantityRuleAction.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 1L;
	}

}