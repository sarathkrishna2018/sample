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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElementPK;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchyElementDlService;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchySetDlService;
import colruyt.rearulmgtdmnejb.util.ProductHierarchyElementConverter;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;

@Stateless
@LocalBean
public class PriceProductHierarchyService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PriceProductHierarchyService.class);
	
	@EJB
	ProductHierarchyElementDlService productHierarchyElementDlService;

	@EJB
	ProductHierarchySetDlService productHierarchySetDlService;
	@EJB
	PriceProductService priceProductService;

	public GeneralRuleBo createProductHierarchySet(GeneralRuleBo reactionRuleBo) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_PRODUCTHIERARCHYSET);
		List<PriceProductHierarchyElement> reaPpdHchyElmnts = getProductHrchyElmnt(
				reactionRuleBo.getPriceProductHierarchySet(), reactionRuleBo.getLogonId());
		PriceProductHierarchySet reaPpdHchyset = setProdHrchySet(reactionRuleBo);
		reaPpdHchyset = productHierarchySetDlService.create(reaPpdHchyset);
		reactionRuleBo.setProductHierarchySetId(reaPpdHchyset.getProductHierarchySetId());
		createProductHierarchySetElement(reaPpdHchyElmnts, reactionRuleBo.getProductHierarchySetId(),
				reactionRuleBo.getLogonId());
		return reactionRuleBo;
	}

	public List<PriceProductHierarchyElement> getProductHrchyElmnt(
			List<ProductHierarchyElementBo> productHierarchyElementBos, String logonId) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_PRODUCTHIERARCHYELEMENT);
		List<PriceProductHierarchyElement> reaPpdHchyElmnts = ProductHierarchyElementConverter
				.convertFromBo(productHierarchyElementBos, logonId);
		List<PriceProductHierarchyElement> existingProdHrchyList = getExistingProdHrchyElmnts(reaPpdHchyElmnts);
		List<PriceProductHierarchyElement> combinedProdHrchyElmnList = Lists.newArrayList();
		boolean hierarchyFound;
		for (PriceProductHierarchyElement reaPpdHchyElmnt : reaPpdHchyElmnts) {
			hierarchyFound = false;
			for (PriceProductHierarchyElement existingProdHrchyElmnt : existingProdHrchyList) {
				if (reaPpdHchyElmnt.getProdHrchyValue().equals(existingProdHrchyElmnt.getProdHrchyValue())
						&& reaPpdHchyElmnt.getProductHierarchyTypeId()
								.equals(existingProdHrchyElmnt.getProductHierarchyTypeId())) {
					// same hierarchy element already exists in DB
					hierarchyFound = true;
					combinedProdHrchyElmnList.add(existingProdHrchyElmnt);
				}
			}
			if (!hierarchyFound) {
				PriceProductHierarchyElement reaPpdHchyElmntCreated = productHierarchyElementDlService
						.create(reaPpdHchyElmnt);
				// hierarchy is not existing in DB yet
				combinedProdHrchyElmnList.add(reaPpdHchyElmntCreated);
			}
		}
		return combinedProdHrchyElmnList;
	}

	private List<PriceProductHierarchyElement> getExistingProdHrchyElmnts(
			List<PriceProductHierarchyElement> priceProductHierarchyElements) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_PRODUCTHIERARCHYELEMENTEXISTING);
		List<String> productHierarchyElmntValueLst = Lists.newArrayList();
		for (PriceProductHierarchyElement reaPpdHchyElmnt : priceProductHierarchyElements) {
			productHierarchyElmntValueLst.add(reaPpdHchyElmnt.getProdHrchyValue());
		}
		return productHierarchyElementDlService.findByHierarchyValueList(productHierarchyElmntValueLst);

	}

	private PriceProductHierarchySet setProdHrchySet(GeneralRuleBo reactionRuleBo) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_PRODUCTHIERARCHYSETVALUE);
		PriceProductHierarchySet reaPpdHchyset = new PriceProductHierarchySet();
		if (reactionRuleBo.getProductHierarchySetId() != null) {
			reaPpdHchyset = productHierarchySetDlService.findByPk(reactionRuleBo.getProductHierarchySetId());
		}
		reaPpdHchyset.setReactionRuleId(reactionRuleBo.getRuleId());
		reaPpdHchyset.setAssortmentName(reactionRuleBo.getAssortmentName());
		reaPpdHchyset.setOwnBrand(reactionRuleBo.isOwnBrand());
		reaPpdHchyset.setNationalBrand(reactionRuleBo.isNationalBrand());
		reaPpdHchyset.setCheapBrand(reactionRuleBo.isCheapBrand());
		reaPpdHchyset.setLstUpdateBy(reactionRuleBo.getLogonId());
		reaPpdHchyset.setCreatedBy(reactionRuleBo.getLogonId());
		return reaPpdHchyset;
	}

	public void createProductHierarchySetElement(List<PriceProductHierarchyElement> reaPriceProductHierarchyElements,
			Long productHierarchySetId, String logonId) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_PRODUCTHIERARCHYSETELEMENT);
		for (PriceProductHierarchyElement reaPpdHchyElmnt : reaPriceProductHierarchyElements) {
			PriceProductHierarchySetElement reaPpdHchysetElmnt = new PriceProductHierarchySetElement();
			PriceProductHierarchySetElementPK reaPpdHchysetElmntPK = new PriceProductHierarchySetElementPK();
			reaPpdHchysetElmntPK.setProductHierarchyElementId(reaPpdHchyElmnt.getProductHierarchyElementId());
			reaPpdHchysetElmntPK.setProdicyHierarchySetId(productHierarchySetId);
			reaPpdHchysetElmnt.setId(reaPpdHchysetElmntPK);
			reaPpdHchysetElmnt.setLstUpdateBy(logonId);
			productHierarchySetDlService.create(reaPpdHchysetElmnt);
		}
	}

	public void removeProductHierarchyElement(Long productHieracrchySetId) {
		productHierarchySetDlService.removeProductHierarchyElement(productHieracrchySetId);
	}

	public List<Long> getproductHierarchySetIdList(Set<String> externalValueSet) {
		List<Long> productHierarchySetIds = Lists.newArrayList();
		List<PriceProductHierarchyElement> allElements = productHierarchyElementDlService.findAllElements();
		if (!allElements.isEmpty()) {
			Set<String> productHierarchyElementSet = convertHierarchyValueListToSet(allElements);
			Set<String> deletedElements = Sets.difference(productHierarchyElementSet, externalValueSet);
			List<Long> toBeDeletedElements = findByElementValueList(deletedElements, allElements);
			if (!toBeDeletedElements.isEmpty()) {
				List<PriceProductHierarchySetElement> toBeDeletedSetElements = productHierarchySetDlService
						.findSetElementByElementIds(toBeDeletedElements);
				List<Long> setIds = Lists.newArrayList();
				for (PriceProductHierarchySetElement priceProductHierarchySetElement : toBeDeletedSetElements) {
					setIds.add(priceProductHierarchySetElement.getId().getProductHierarchySetId());
				}
				productHierarchySetIds = productHierarchySetDlService.findSetElementBySetIds(setIds);
				if (!toBeDeletedElements.isEmpty()) {
					productHierarchySetDlService.deleteSetElements(toBeDeletedElements);
					productHierarchyElementDlService.deleteElements(toBeDeletedElements);
				}
			}
		}
		return productHierarchySetIds;
	}

	private Set<String> convertHierarchyValueListToSet(List<PriceProductHierarchyElement> hierarchyValuesFromSoi) {
		Set<String> hierarchyValueSet = new HashSet<>();
		for (PriceProductHierarchyElement hierarchyValue : hierarchyValuesFromSoi) {
			if (hierarchyValue.getProductHierarchyTypeId() != 1) {
				hierarchyValueSet.add(hierarchyValue.getProdHrchyValue());
			}
		}
		return hierarchyValueSet;
	}

	public List<Long> findByElementValueList(Set<String> hierarchyValuesToDelete,
			List<PriceProductHierarchyElement> productHierarchyElementList) {
		List<Long> toBeDeletedElements = Lists.newArrayList();
		for (String hierarchyValue : hierarchyValuesToDelete) {
			for (PriceProductHierarchyElement productHierarchyElement : productHierarchyElementList) {
				if (hierarchyValue.equalsIgnoreCase(productHierarchyElement.getProdHrchyValue())) {
					toBeDeletedElements.add(productHierarchyElement.getProductHierarchyElementId());
					break;
				}
			}
		}
		return toBeDeletedElements;
	}

	public List<PriceProductHierarchySet> findProductHierarchySets(List<Long> productHierarchySetIds) {
		return productHierarchySetDlService.findProductSetByIds(productHierarchySetIds);

	}

	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", deleteRuleInfoBo.getRuleId());
		logger.debug(debugInfo);
		Long ppdHchysetId = productHierarchySetDlService.getPriceProductHierarchySetElementId(deleteRuleInfoBo);
		productHierarchySetDlService.deletePriceProductHierarchySetElemnet(ppdHchysetId);
		productHierarchySetDlService.deletePriceProductHierarchySet(deleteRuleInfoBo);
	}
}
