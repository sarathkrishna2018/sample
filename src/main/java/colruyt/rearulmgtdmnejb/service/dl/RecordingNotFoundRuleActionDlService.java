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
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class RecordingNotFoundRuleActionDlService  implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public RecordingNotFoundRuleAction createOrUpdate(RecordingNotFoundRuleAction recordNotFoundRuleAction) {
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = entityManager.merge(recordNotFoundRuleAction);
		entityManager.flush();
		return recordingNotFoundRuleAction;

	}

	public RecordingNotFoundRuleAction findByRuleId(long reaRuleId) {
		return entityManager.find(RecordingNotFoundRuleAction.class, reaRuleId);
		
	}
	
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		Query query = entityManager.createQuery("Delete from  RecordingNotFoundRuleAction recordingNotFoundRuleAction where recordingNotFoundRuleAction.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 1L;
	}

}
