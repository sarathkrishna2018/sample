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

import colruyt.rearulmgtdmnejb.entity.RefFilterOutRecordingType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityConditionType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityType;
import colruyt.rearulmgtdmnejb.entity.RefReasonType;
import colruyt.rearulmgtdmnejb.entity.RefRuleType;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReferenceDataDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReactionRuleDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public List<RefFilterOutRecordingType> findAllRefFilterOutRecordingTypes() {
		Query query = entityManager.createQuery(
				"select refFilterOutRecordingType  from  RefFilterOutRecordingType refFilterOutRecordingType");
		return query.getResultList();

	}

	public List<RefQuantityConditionType> findAllRefQuantityCondition() {
		Query query = entityManager
				.createQuery("select refQuantityConditionType from RefQuantityConditionType refQuantityConditionType ");
		return query.getResultList();

	}

	public List<RefQuantityType> findAllRefQuantityType() {
		Query query = entityManager.createQuery("select refQuantityType from RefQuantityType refQuantityType ");
		return query.getResultList();

	}

	public List<RefReasonType> findAllRefReasonType() {
		Query query = entityManager.createQuery("select refReasonType from RefReasonType refReasonType ");
		return query.getResultList();

	}

	public List<RefRuleType> findAllRuleType() {
		Query query = entityManager.createQuery("select ruleType from RefRuleType ruleType ");
		return query.getResultList();

	}

}