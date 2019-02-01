package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmntPK;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchyElementDlService;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchySetDlService;
import colruyt.rearulmgtdmnejb.util.ProductHrchyElmntConverter;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;

/**
 * Session Bean implementation class PriceProductHierarchyBlService
 */
/**
 * @author asi16ad
 *
 */
@Stateless
@LocalBean
public class PriceProductHierarchyService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PriceProductHierarchyService.class);
	@EJB
	ProductHrchyElmntConverter productHrchyElmntConverter;

	@EJB
	ProductHierarchyElementDlService productHierarchyElementDlService;

	@EJB
	ProductHierarchySetDlService productHierarchySetDlService;
	@EJB
	PriceProductService priceProductService;

	/**
	 * This method is to create ProductHierarchySet
	 * 
	 * @param productHierarchyElements
	 * @param ownBrand
	 * @param nationalBrand
	 * @param cheapBrand
	 */
	public GeneralRuleBo createProductHierarchySet(GeneralRuleBo reactionRuleBo) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_PRODUCTHIERARCHYSET);
		List<PriceProductHierarchyElement> reaPpdHchyElmnts = getProductHrchyElmnt(
				reactionRuleBo.getPriceProductHierarchySet(), reactionRuleBo.getLogonId());
		PriceProductHierarchySet reaPpdHchyset = setProdHrchySet(reactionRuleBo);
		reaPpdHchyset = productHierarchySetDlService.create(reaPpdHchyset);
		reactionRuleBo.setProductHierarchySetId(reaPpdHchyset.getPpdHchysetId());
		createProdHrchtSetElmnt(reaPpdHchyElmnts, reactionRuleBo.getProductHierarchySetId(),
				reactionRuleBo.getLogonId());
		return reactionRuleBo;
	}

	/**
	 * This method is to get ProductHierarchy element
	 * 
	 * @param productHierarchyElementBos
	 * @param logonId
	 * @return List<PriceProductHierarchyElement>
	 */
	public List<PriceProductHierarchyElement> getProductHrchyElmnt(
			List<ProductHierarchyElementBo> productHierarchyElementBos, String logonId) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_PRODUCTHIERARCHYELEMENT);
		List<PriceProductHierarchyElement> reaPpdHchyElmnts = productHrchyElmntConverter
				.convertProductHierarchyElement(productHierarchyElementBos, logonId);
		List<PriceProductHierarchyElement> existingProdHrchyList = getExistingProdHrchyElmnts(reaPpdHchyElmnts);
		List<PriceProductHierarchyElement> combinedProdHrchyElmnList = Lists.newArrayList();
		boolean hierarchyFound;
		for (PriceProductHierarchyElement reaPpdHchyElmnt : reaPpdHchyElmnts) {
			hierarchyFound = false;
			for (PriceProductHierarchyElement existingProdHrchyElmnt : existingProdHrchyList) {
				if (reaPpdHchyElmnt.getPpdHchyValue().equals(existingProdHrchyElmnt.getPpdHchyValue())
						&& reaPpdHchyElmnt.getPpdHchyTypeId().equals(existingProdHrchyElmnt.getPpdHchyTypeId())) {
					// same hierarchy element already exists in DB
					hierarchyFound = true;
					combinedProdHrchyElmnList.add(existingProdHrchyElmnt);
				}
			}
			if (!hierarchyFound) {
				PriceProductHierarchyElement reaPpdHchyElmntCreated = productHierarchyElementDlService.create(reaPpdHchyElmnt);
				// hierarchy is not existing in DB yet
				combinedProdHrchyElmnList.add(reaPpdHchyElmntCreated);
			}
		}
		return combinedProdHrchyElmnList;
	}

	/**
	 * This method is to get existing PriceProductHierarchyElement
	 * 
	 * @param reaPpdHchyElmnts
	 * @return List<PriceProductHierarchyElement>
	 */
	private List<PriceProductHierarchyElement> getExistingProdHrchyElmnts(
			List<PriceProductHierarchyElement> reaPpdHchyElmnts) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_PRODUCTHIERARCHYELEMENTEXISTING);
		List<String> productHierarchyElmntValueLst = Lists.newArrayList();
		for (PriceProductHierarchyElement reaPpdHchyElmnt : reaPpdHchyElmnts) {
			productHierarchyElmntValueLst.add(reaPpdHchyElmnt.getPpdHchyValue());
		}
		return productHierarchyElementDlService.findByHierarchyValueList(productHierarchyElmntValueLst);

	}

	/**
	 * This method is used to set the ProductHierarchySet values
	 * 
	 * @param cGChainRuleBoObj
	 * @param combinedProdHrchyElmnList
	 * @param assortmentName
	 * @param logonId
	 * @return ProductHierarchySet
	 */
	private PriceProductHierarchySet setProdHrchySet(GeneralRuleBo reactionRuleBo) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_PRODUCTHIERARCHYSETVALUE);
		PriceProductHierarchySet reaPpdHchyset = new PriceProductHierarchySet();
		if (reactionRuleBo.getProductHierarchySetId() != null) {
			reaPpdHchyset = productHierarchySetDlService.findByPk(reactionRuleBo.getProductHierarchySetId());
		}
		reaPpdHchyset.setReaRuleId(reactionRuleBo.getRuleId());
		reaPpdHchyset.setAssortmentName(reactionRuleBo.getAssortmentName());
		reaPpdHchyset.setOwnBrandYn(reactionRuleBo.isOwnBrand());
		reaPpdHchyset.setNatBrandYn(reactionRuleBo.isNationalBrand());
		reaPpdHchyset.setCheapBrandYn(reactionRuleBo.isCheapBrand());
		reaPpdHchyset.setLstUpdateBy(reactionRuleBo.getLogonId());
		reaPpdHchyset.setCreatedBy(reactionRuleBo.getLogonId());
		return reaPpdHchyset;
	}

	/**
	 * This method is to create ProductHierarchySetElement
	 * 
	 * @param reaPpdHchyElmnts
	 * @param prodHrchySetId
	 * @param logonId
	 */
	public void createProdHrchtSetElmnt(List<PriceProductHierarchyElement> reaPpdHchyElmnts, Long prodHrchySetId,
			String logonId) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_PRODUCTHIERARCHYSETELEMENT);
		for (PriceProductHierarchyElement reaPpdHchyElmnt : reaPpdHchyElmnts) {
			PriceProductHierarchySetElmnt reaPpdHchysetElmnt = new PriceProductHierarchySetElmnt();
			PriceProductHierarchySetElmntPK reaPpdHchysetElmntPK = new PriceProductHierarchySetElmntPK();
			reaPpdHchysetElmntPK.setPpdHchyElmntId(reaPpdHchyElmnt.getPpdHchyElmntId());
			reaPpdHchysetElmntPK.setPpdHchysetId(prodHrchySetId);
			reaPpdHchysetElmnt.setId(reaPpdHchysetElmntPK);
			reaPpdHchysetElmnt.setLstUpdateBy(logonId);
			productHierarchySetDlService.create(reaPpdHchysetElmnt);
		}
	}

	public void removeProductHierarchyElement(Long productHieracrchySetId) {
		productHierarchySetDlService.removeProductHierarchyElement(productHieracrchySetId);
	}

	public List<Long> manageExternalChanges(Set<String> externalValueSet) {
		List<Long> productHierarchySetIds =Lists.newArrayList();
		List<PriceProductHierarchyElement> allElements = productHierarchyElementDlService.findAllElements();
		if(!allElements.isEmpty()){
		Set<String> productHierarchyElementSet = converthierarchyValueListtoSet(allElements);
		Set<String> deletedElements = Sets.difference(productHierarchyElementSet, externalValueSet);
		List<Long> toBeDeletedElements = findByElementValueList(deletedElements, allElements);
		if(!toBeDeletedElements.isEmpty()){
		List<PriceProductHierarchySetElmnt> toBeDeletedSetElements = productHierarchySetDlService
				.findSetElementByElementIds(toBeDeletedElements);
		List<Long> setIds = Lists.newArrayList();
		for (PriceProductHierarchySetElmnt priceProductHierarchySetElement : toBeDeletedSetElements) {
			setIds.add(priceProductHierarchySetElement.getId().getPpdHchysetId());
		}
		productHierarchySetIds = productHierarchySetDlService.findSetElementBySetIds(setIds);
			productHierarchySetDlService.deleteSetElements(toBeDeletedElements);
			productHierarchyElementDlService.deleteElements(toBeDeletedElements);
		}
		}
		return productHierarchySetIds;
	}

	/**
	 * 
	 * @param hierarchyValuesFromSoi
	 * @return
	 */
	private Set<String> converthierarchyValueListtoSet(List<PriceProductHierarchyElement> hierarchyValuesFromSoi) {
		Set<String> hierarchyValueSet = new HashSet<>();
		for (PriceProductHierarchyElement hierarchyValue : hierarchyValuesFromSoi) {
			if (hierarchyValue.getPpdHchyTypeId() != 1) {
				hierarchyValueSet.add(hierarchyValue.getPpdHchyValue());
			}
		}
		return hierarchyValueSet;
	}

	public List<Long> findByElementValueList(Set<String> hierarchyValuesToDelete,
			List<PriceProductHierarchyElement> productHierarchyElementList) {
		List<Long> toBeDeletedElements = Lists.newArrayList();
		for (String hierarchyValue : hierarchyValuesToDelete) {
			for (PriceProductHierarchyElement productHierarchyElement : productHierarchyElementList) {
				if (hierarchyValue.equalsIgnoreCase(productHierarchyElement.getPpdHchyValue())) {
					toBeDeletedElements.add(productHierarchyElement.getPpdHchyElmntId());
					break;
				}
			}
		}
		return toBeDeletedElements;
	}

	public List<PriceProductHierarchySet> findProductHierarchySets(List<Long> productHierarchySetIds) {
		return productHierarchySetDlService
				.findProductSetByIds(productHierarchySetIds);
		
	}
	
	public void physicalDeleteElements(XPSRuleBo xpsRuleBo){
		String debugInfo = String.format("physicalDeleteElements %1$d", xpsRuleBo.getRuleId());
		logger.debug(debugInfo);
		Long ppdHchysetId =  productHierarchySetDlService.getPriceProductHierarchySetElementId(xpsRuleBo);
		productHierarchySetDlService.deletePriceProductHierarchySetElemnet(ppdHchysetId);
		productHierarchySetDlService.deletePriceProductHierarchySet(xpsRuleBo);
	}
}
