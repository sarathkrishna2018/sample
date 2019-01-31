package colruyt.rearulmgtdmnejb.service.dl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.SQLQueries;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProductHierarchySetDlService implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ReaRulMgtDmnConstants.PERSISTENCE_UNIT_NAME)
	private transient EntityManager entityManager;
	
	public PriceProductHierarchySet create(PriceProductHierarchySet reaPpdHchyset) {
		PriceProductHierarchySet reaPpdHchy=  entityManager.merge(reaPpdHchyset);
		entityManager.flush();
		return reaPpdHchy;
	}
	
	public PriceProductHierarchySetElmnt create(PriceProductHierarchySetElmnt reaPpdHchysetElmnt) {
		PriceProductHierarchySetElmnt ppdHchysetElmnt=  entityManager.merge(reaPpdHchysetElmnt);
		entityManager.flush();
		return ppdHchysetElmnt;
	}
	
	public PriceProductHierarchySet findByPk(long productHierarchySetId) {
		return entityManager.find(PriceProductHierarchySet.class, productHierarchySetId);
	}

	public void removeProductHierarchyElement(Long productHieracrchySetId) {
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySetElmnt priceProductHierarchySetElmnt where priceProductHierarchySetElmnt.id.ppdHchysetId = ?1");
		query.setParameter(1, productHieracrchySetId).executeUpdate();	
		
	}
	
	public List<PriceProductHierarchySetElmnt> findSetElementByElementIds(List<Long> elementValues){
		Query query = entityManager.createQuery("select distinct priceProductHierarchySetElmnt from  PriceProductHierarchySetElmnt priceProductHierarchySetElmnt where priceProductHierarchySetElmnt.id.ppdHchyElmntId IN ?1");
		return query.setParameter(1, elementValues).getResultList();	
		
	}
	public List<Long> findSetElementBySetIds(List<Long> setIds){
		Query query = entityManager.createQuery("select priceProductHierarchySetElmnt.id.ppdHchysetId from  PriceProductHierarchySetElmnt priceProductHierarchySetElmnt where priceProductHierarchySetElmnt.id.ppdHchysetId IN ?1  group by priceProductHierarchySetElmnt.id.ppdHchysetId having count(priceProductHierarchySetElmnt.id.ppdHchyElmntId)=1");
		return query.setParameter(1, setIds).getResultList();	
		
	}
	
	
	public void deleteSetElements(List<Long> productHieracrchySetList) {
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySetElmnt priceProductHierarchySetElmnt where priceProductHierarchySetElmnt.id.ppdHchyElmntId IN ?1");
		query.setParameter(1, productHieracrchySetList).executeUpdate();	
		entityManager.clear();
	}
	public List<PriceProductHierarchySet> findProductSetByIds(List<Long> productHierarchySetIds){
		Query query = entityManager.createQuery("select distinct priceProductHierarchySet from  PriceProductHierarchySet priceProductHierarchySet where priceProductHierarchySet.ppdHchysetId IN ?1");
		return query.setParameter(1, productHierarchySetIds).getResultList();	
	}
	
	public long getPriceProductHierarchySetElementId(XPSRuleBo xpsRuleBo){
		Query query = entityManager.createNativeQuery("SELECT rhs.PPD_HCHYSET_ID, rhs.REA_RULE_ID from PRCGD001.REA_PPD_HCHYSET rhs "
			+ " where rhs.REA_RULE_ID = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId());
		Long ppdHchysetId = null;
		List<Object[]> results = query.getResultList();
		for(Object[] item : results) {
			ppdHchysetId = ((BigDecimal)item[0]).longValue();
		}
		return ppdHchysetId;
	}
	
	public long deletePriceProductHierarchySetElemnet(Long ppdHchysetId){
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySetElmnt pphse where pphse.id.ppdHchysetId = (?1)");
		query.setParameter(1, ppdHchysetId).executeUpdate();	
		entityManager.clear();
		return 1L;
	}
	
	public long deletePriceProductHierarchySet(XPSRuleBo xpsRuleBo){
		Query query = entityManager.createQuery("Delete from  PriceProductHierarchySet priceProductHierarchySet where priceProductHierarchySet.reaRuleId = (?1)");
		query.setParameter(1, xpsRuleBo.getRuleId()).executeUpdate();	
		entityManager.clear();
		return 1L;
	}

}
