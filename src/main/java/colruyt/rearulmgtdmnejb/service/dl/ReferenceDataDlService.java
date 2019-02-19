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
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * @version 1.0
 * @created 28-nov-2018 8:20:19
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ReferenceDataDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;


	/**
	 * @param ruleId
	 * @return
	 */
	public List<RefFilterOutRecordingType> findAllRefFilterOutRecordingTypes() {
		Query query = entityManager.createQuery("select refFilterOutRecordingType  from  RefFilterOutRecordingType refFilterOutRecordingType");
	    List<RefFilterOutRecordingType> refFilterOutRecordingTypes  = query.getResultList();	
		return refFilterOutRecordingTypes;
				
	}
	
}