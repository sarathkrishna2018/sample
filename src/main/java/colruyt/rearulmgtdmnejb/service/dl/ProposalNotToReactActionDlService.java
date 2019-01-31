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

import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleActionRsn;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * @version 1.0
 * @created 04-dec-2018 15:05:16
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProposalNotToReactActionDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public ProposalNotToReactRuleAction createOrUpdate(ProposalNotToReactRuleAction notToReactRuleAction) {
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = entityManager.merge(notToReactRuleAction);
		entityManager.flush();
		return proposalNotToReactRuleAction;

	}
	
	public void createOrUpdate(List<ProposalNotToReactRuleActionRsn> reaNreactSetRsns) {
		for(ProposalNotToReactRuleActionRsn reaNreactSetRsn: reaNreactSetRsns){
			entityManager.merge(reaNreactSetRsn);
			entityManager.flush();
		}
		
	}

	public ProposalNotToReactRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(ProposalNotToReactRuleAction.class, reaRuleId);
		
	}

	public void remove(Long ruleId) {
		Query query = entityManager.createQuery("Delete from  ProposalNotToReactRuleActionRsn proposalNotToReactRuleActionRsn where proposalNotToReactRuleActionRsn.id.reaRuleId = ?1");		
		query.setParameter(1, ruleId).executeUpdate();
		entityManager.clear();
	}
	
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		Query query = entityManager.createQuery("Delete from  ProposalNotToReactRuleAction proposalNotToReactRuleAction where proposalNotToReactRuleAction.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 0L;
	}
	
	public long physicalDeleteElementsRsn(XPSRuleBo xpsRuleBo) {
		Query query = entityManager.createQuery("Delete from  ProposalNotToReactRuleActionRsn prsn where prsn.id.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 0L;
	}
	

}