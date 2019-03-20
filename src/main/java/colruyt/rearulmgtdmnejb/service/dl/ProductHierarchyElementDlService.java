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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProductHierarchyElementDlService implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReactionRuleDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;

	public List<PriceProductHierarchyElement> findByHierarchyValueList(List<String> productHierarchyElmntValues) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PriceProductHierarchyElement> criteriaQuery = criteriaBuilder
				.createQuery(PriceProductHierarchyElement.class);
		Root<PriceProductHierarchyElement> hierarchyRoot = criteriaQuery.from(PriceProductHierarchyElement.class);
		Path<String> hierarchyValuePath = hierarchyRoot.get("prodHrchyValue");
		Predicate valuePredicate = hierarchyValuePath.in(productHierarchyElmntValues);
		criteriaQuery.where(valuePredicate);
		return entityManager.createQuery(criteriaQuery).getResultList();

	}

	public PriceProductHierarchyElement create(PriceProductHierarchyElement reaPpdHchyElmnt) {
		PriceProductHierarchyElement prodHchyElmnt = entityManager.merge(reaPpdHchyElmnt);
		entityManager.flush();
		return prodHchyElmnt;
	}

	public List<PriceProductHierarchyElement> findAllElements() {
		Query query = entityManager.createQuery(
				"select priceProductHierarchyElement  from  PriceProductHierarchyElement priceProductHierarchyElement");
		List<PriceProductHierarchyElement> priceProductHierarchyElementList = query.getResultList();
		return priceProductHierarchyElementList;
	}

	public void deleteElements(List<Long> elementsIds) {
		Query query = entityManager.createQuery(
				"delete  from  PriceProductHierarchyElement priceProductHierarchyElement where priceProductHierarchyElement.productHierarchyElementId IN ?1 ");
		query.setParameter(1, elementsIds).executeUpdate();
		entityManager.flush();
		entityManager.clear();

	}

}
