package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import colruyt.rearulmgtdmnejb.entity.RefQuantityCond;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * 
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class RefQuantityConditionTypeDlService implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;
	
	public List<RefQuantityCond> findAllQuantityConditionTypes(){
		TypedQuery<RefQuantityCond> query = entityManager.createQuery(
				"SELECT refQtyCon FROM RefQuantityCond refQtyCon",
				RefQuantityCond.class);
		return query.getResultList();
	}

}