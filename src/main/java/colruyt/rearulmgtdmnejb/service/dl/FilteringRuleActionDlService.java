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
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * @version 1.0
 * @created 28-nov-2018 8:20:19
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class FilteringRuleActionDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;


	/**
	 * 
	 * @param filteringRuleAction
	 */
	public FilteringRuleAction createOrUpdate(FilteringRuleAction reaFltRule) {
		FilteringRuleAction fltRule = entityManager.merge(reaFltRule);
		entityManager.flush();
		return fltRule;
	}


	public FilteringRuleAction findByRuleId(Long ruleId) {
		return entityManager.find(FilteringRuleAction.class, ruleId);
				
	}
	
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		Query query = entityManager.createQuery("Delete from  FilteringRuleAction filteringRuleAction where filteringRuleAction.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 0L;
	}

}