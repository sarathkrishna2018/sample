package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import colruyt.priceproduct.bo.LangHirerachyBo;
import colruyt.priceproduct.bo.MainCategoryBo;
import colruyt.priceproduct.bo.PriceProductHierarchyBo;
import colruyt.priceproduct.bo.ProductCategoryBo;
import colruyt.priceproduct.bo.ProductGroupBo;
import colruyt.priceproduct.bo.ProductSegmentBo;
import colruyt.rearulmgtdmnejb.enums.PriceProductHierarchyType;


public class PriceProductConverter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param userLang
	 * @return
	 */
	public static List<String> getLanguagePreference(String userLang){
		List<String> langOrder = ReactionRuleMgtConstants.LANG_PREFERENCE_NL;
		if (ReactionRuleMgtConstants.LANG_CODE_EN.equals(userLang)) {
			langOrder = ReactionRuleMgtConstants.LANG_PREFERENCE_EN;
		} else if (ReactionRuleMgtConstants.LANG_CODE_NL.equals(userLang)) {
			langOrder = ReactionRuleMgtConstants.LANG_PREFERENCE_NL;
		} else if (ReactionRuleMgtConstants.LANG_CODE_FR.equals(userLang)) {
			langOrder = ReactionRuleMgtConstants.LANG_PREFERENCE_FR;
		}
		return langOrder;
	}
	
	
	
	/**
	 * @param langHierarchyList
	 * @param langPrioriyList
	 * @return
	 */
	private String getNameByLanguagePriority(List<LangHirerachyBo> langHierarchyList, List<String> langPrioriyList) {
		for (String langCode : langPrioriyList) {
			for (LangHirerachyBo langHirerachyBo : langHierarchyList) {
				if (langCode.equals(langHirerachyBo.getRefIsoLanguage().getIsoLangCode())) {
					return langHirerachyBo.getName();
				}
			}
		}
		return null;
	}


	
	

	
	
	
	
	public List<PriceProductHierarchyBo> convertProducts(List<MainCategoryBo> mainCategoryBoList, String langCode) {
		List<PriceProductHierarchyBo> priceProductHierarchyBoList = Lists.newArrayList();
		for (MainCategoryBo mainCategoryBo : mainCategoryBoList) {
			priceProductHierarchyBoList.add(convert((MainCategoryBo) mainCategoryBo, langCode));
		}
		return priceProductHierarchyBoList;
	}
	/**
	 * @param mainCategoryBo
	 * @param segmentPriceProductMap
	 * @param langCode
	 * @return
	 */
	public PriceProductHierarchyBo convert(MainCategoryBo mainCategoryBo, String langCode) {

		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(mainCategoryBo.getMainCategoryLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name, mainCategoryBo.getMainCategoryCode(),
				ReactionRuleMgtConstants.PRICE_PRODUCT_HIERARCHY_ALL_VALUE,
				PriceProductHierarchyType.MAIN_CATEGORY.getTypeId());
		if (mainCategoryBo.getProductCategories() != null) {
			List<PriceProductHierarchyBo> productHierarchy = Lists.newArrayList();
			for (ProductCategoryBo productCategoryBo : mainCategoryBo.getProductCategories()) {
				productHierarchy.add(convert(productCategoryBo, langCode));

			}
			hierarchyBo.setChildHierarchy(productHierarchy);
		}
		return hierarchyBo;
	}
	/**
	 * @param productCategoryBo
	 * @param segmentPriceProductMap
	 * @param langCode
	 * @return
	 */
	private PriceProductHierarchyBo convert(ProductCategoryBo productCategoryBo, String langCode) {
		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(productCategoryBo.getProductCategoryLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name,
				productCategoryBo.getProductCategoryCode(), productCategoryBo.getMainCategoryCode(),
				PriceProductHierarchyType.PRODUCT_CATEGORY.getTypeId());
		if (productCategoryBo.getProductGroups() != null) {
			List<PriceProductHierarchyBo> productHierarchy = Lists.newArrayList();
			for (ProductGroupBo productGroup : productCategoryBo.getProductGroups()) {
				productHierarchy.add(convert(productGroup, langCode));
			}
			hierarchyBo.setChildHierarchy(productHierarchy);
		}
		return hierarchyBo;
	}

	/**
	 * @param productGroup
	 * @param segmentPriceProductMap
	 * @param langCode
	 * @return
	 */
	private PriceProductHierarchyBo convert(ProductGroupBo productGroup, String langCode) {
		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(productGroup.getProductGroupLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name, productGroup.getProductGroupCode(),
				productGroup.getProductCategoryCode(), PriceProductHierarchyType.PRODUCT_GROUP.getTypeId());
		if (productGroup.getProductSegments() != null) {
			List<PriceProductHierarchyBo> productHierarchy = Lists.newArrayList();
			for (ProductSegmentBo productSegmentBo : productGroup.getProductSegments()) {
				productHierarchy.add(convertProductSegment(productSegmentBo, langCode));

			}
			hierarchyBo.setChildHierarchy(productHierarchy);
		}

		return hierarchyBo;
	}
	
	/**
	 * @param productSegmentBO
	 * @param segmentPriceProductMap
	 * @param langCode
	 * @return
	 */
	private PriceProductHierarchyBo convertProductSegment(ProductSegmentBo productSegmentBO, String langCode) {
		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(productSegmentBO.getProductSegmentLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name,
				productSegmentBO.getProductSegmentCode(), productSegmentBO.getProductGroupCode(),
				PriceProductHierarchyType.PRODUCT_SEGMENT.getTypeId());

		return hierarchyBo;
	}

	
}