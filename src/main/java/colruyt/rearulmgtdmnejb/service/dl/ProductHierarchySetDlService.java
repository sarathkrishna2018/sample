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
import javax.persistence.TypedQuery;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElement;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProductHierarchySetDlService implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReactionRuleDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;
	
	public PriceProductHierarchySet create(PriceProductHierarchySet reaPpdHchyset) {
		PriceProductHierarchySet reaPpdHchy=  entityManager.merge(reaPpdHchyset);
		entityManager.flush();
		return reaPpdHchy;
	}
	
	public PriceProductHierarchySetElement create(PriceProductHierarchySetElement reaPpdHchysetElmnt) {
		PriceProductHierarchySetElement ppdHchysetElmnt=  entityManager.merge(reaPpdHchysetElmnt);
		entityManager.flush();
		return ppdHchysetElmnt;
	}
	
	public PriceProductHierarchySet findByPk(long productHierarchySetId) {
		return entityManager.find(PriceProductHierarchySet.class, productHierarchySetId);
	}

	public void removeProductHierarchyElement(Long productHieracrchySetId) {
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySetElement priceProductHierarchySetElement where priceProductHierarchySetElement.id.productHierarchySetId = ?1");
		query.setParameter(1, productHieracrchySetId).executeUpdate();	
		
	}
	
	public List<PriceProductHierarchySetElement> findSetElementByElementIds(List<Long> elementValues){
		Query query = entityManager.createQuery("select distinct priceProductHierarchySetElement from  PriceProductHierarchySetElement priceProductHierarchySetElement where priceProductHierarchySetElement.id.productHierarchyElementId IN ?1");
		return query.setParameter(1, elementValues).getResultList();	
		
	}
	public List<Long> findSetElementBySetIds(List<Long> setIds){
		Query query = entityManager.createQuery("select priceProductHierarchySetElement.id.productHierarchySetId from  PriceProductHierarchySetElement priceProductHierarchySetElement where priceProductHierarchySetElement.id.productHierarchySetId IN ?1  group by priceProductHierarchySetElement.id.productHierarchySetId having count(priceProductHierarchySetElement.id.productHierarchyElementId)=1");
		return query.setParameter(1, setIds).getResultList();	
		
	}
	
	
	public void deleteSetElements(List<Long> productHieracrchySetList) {
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySetElement priceProductHierarchySetElement where priceProductHierarchySetElement.id.productHierarchyElementId IN ?1");
		query.setParameter(1, productHieracrchySetList).executeUpdate();	
		entityManager.clear();
	}
	public List<PriceProductHierarchySet> findProductSetByIds(List<Long> productHierarchySetIds){
		Query query = entityManager.createQuery("select distinct priceProductHierarchySet from  PriceProductHierarchySet priceProductHierarchySet where priceProductHierarchySet.productHierarchySetId IN ?1");
		return query.setParameter(1, productHierarchySetIds).getResultList();	
	}
	
	public Long getPriceProductHierarchySetElementId(DeleteRuleInfoBo deleteRuleInfoBo){
		TypedQuery<Long> query = entityManager.createQuery("SELECT rhs.productHierarchySetId from PriceProductHierarchySet rhs "
			+ " where rhs.reactionRuleId = (?1)",Long.class);
		query.setParameter(1, deleteRuleInfoBo.getRuleId());
		return query.getSingleResult();
	}
	
	public void deletePriceProductHierarchySetElemnet(Long ppdHchysetId){
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySetElement pphse where pphse.id.productHierarchySetId = (?1)");
		query.setParameter(1, ppdHchysetId).executeUpdate();	
		entityManager.clear();
	}
	
	public void deletePriceProductHierarchySet(DeleteRuleInfoBo deleteRuleInfoBo){
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySet priceProductHierarchySet where priceProductHierarchySet.reactionRuleId = (?1)");
		query.setParameter(1, deleteRuleInfoBo.getRuleId()).executeUpdate();	
		entityManager.clear();
	}

}
