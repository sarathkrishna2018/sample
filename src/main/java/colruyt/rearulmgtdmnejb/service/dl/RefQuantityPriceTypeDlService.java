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

import colruyt.rearulmgtdmnejb.entity.RefQuantityType;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;

/**
 * 
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class RefQuantityPriceTypeDlService implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;
	
	public List<RefQuantityType> findAllQuantityConditionTypes(){
		TypedQuery<RefQuantityType> query = entityManager.createQuery(
				"SELECT refQtyType FROM RefQuantityType refQtyType",
				RefQuantityType.class);
		return query.getResultList();
	}
}