package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import colruyt.priceproduct.bo.LangHierarchyBo;
import colruyt.priceproduct.bo.MainCategoryBo;
import colruyt.priceproduct.bo.PriceProductHierarchyBo;
import colruyt.priceproduct.bo.ProductCategoryBo;
import colruyt.priceproduct.bo.ProductGroupBo;
import colruyt.priceproduct.bo.ProductSegmentBo;
import colruyt.rearulmgtdmnejb.enums.PriceProductHierarchyType;

public class PriceProductConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<PriceProductHierarchyBo> convertToBo(List<MainCategoryBo> mainCategoryBoList, String langCode) {
		List<PriceProductHierarchyBo> priceProductHierarchyBoList = Lists.newArrayList();
		for (MainCategoryBo mainCategoryBo : mainCategoryBoList) {
			priceProductHierarchyBoList.add(convertToBo((MainCategoryBo) mainCategoryBo, langCode));
		}
		return priceProductHierarchyBoList;
	}

	public static PriceProductHierarchyBo convertToBo(MainCategoryBo mainCategoryBo, String langCode) {

		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(mainCategoryBo.getMainCategoryLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name, mainCategoryBo.getMainCategoryCode(),
				ReactionRuleDmnConstants.PRICE_PRODUCT_HIERARCHY_ALL_VALUE,
				PriceProductHierarchyType.MAIN_CATEGORY.getTypeId());
		if (mainCategoryBo.getProductCategories() != null) {
			List<PriceProductHierarchyBo> productHierarchy = Lists.newArrayList();
			for (ProductCategoryBo productCategoryBo : mainCategoryBo.getProductCategories()) {
				productHierarchy.add(convertToBo(productCategoryBo, langCode));

			}
			hierarchyBo.setChildHierarchy(productHierarchy);
		}
		return hierarchyBo;
	}

	private static PriceProductHierarchyBo convertToBo(ProductCategoryBo productCategoryBo, String langCode) {
		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(productCategoryBo.getProductCategoryLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name,
				productCategoryBo.getProductCategoryCode(), productCategoryBo.getMainCategoryCode(),
				PriceProductHierarchyType.PRODUCT_CATEGORY.getTypeId());
		if (productCategoryBo.getProductGroups() != null) {
			List<PriceProductHierarchyBo> productHierarchy = Lists.newArrayList();
			for (ProductGroupBo productGroup : productCategoryBo.getProductGroups()) {
				productHierarchy.add(convertToBo(productGroup, langCode));
			}
			hierarchyBo.setChildHierarchy(productHierarchy);
		}
		return hierarchyBo;
	}

	private static PriceProductHierarchyBo convertToBo(ProductGroupBo productGroup, String langCode) {
		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(productGroup.getProductGroupLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name, productGroup.getProductGroupCode(),
				productGroup.getProductCategoryCode(), PriceProductHierarchyType.PRODUCT_GROUP.getTypeId());
		if (productGroup.getProductSegments() != null) {
			List<PriceProductHierarchyBo> productHierarchy = Lists.newArrayList();
			for (ProductSegmentBo productSegmentBo : productGroup.getProductSegments()) {
				productHierarchy.add(convertToBo(productSegmentBo, langCode));

			}
			hierarchyBo.setChildHierarchy(productHierarchy);
		}

		return hierarchyBo;
	}

	private static PriceProductHierarchyBo convertToBo(ProductSegmentBo productSegmentBO, String langCode) {
		List<String> langPrioList = getLanguagePreference(langCode);
		String name = getNameByLanguagePriority(productSegmentBO.getProductSegmentLangs(), langPrioList);
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo(name,
				productSegmentBO.getProductSegmentCode(), productSegmentBO.getProductGroupCode(),
				PriceProductHierarchyType.PRODUCT_SEGMENT.getTypeId());

		return hierarchyBo;
	}

	private static List<String> getLanguagePreference(String userLang) {
		List<String> langOrder = ReactionRuleDmnConstants.LANG_PREFERENCE_NL;
		if (ReactionRuleDmnConstants.LANG_CODE_EN.equals(userLang)) {
			langOrder = ReactionRuleDmnConstants.LANG_PREFERENCE_EN;
		} else if (ReactionRuleDmnConstants.LANG_CODE_NL.equals(userLang)) {
			langOrder = ReactionRuleDmnConstants.LANG_PREFERENCE_NL;
		} else if (ReactionRuleDmnConstants.LANG_CODE_FR.equals(userLang)) {
			langOrder = ReactionRuleDmnConstants.LANG_PREFERENCE_FR;
		}
		return langOrder;
	}

	private static String getNameByLanguagePriority(List<LangHierarchyBo> langHierarchyList,
			List<String> langPrioriyList) {
		for (String langCode : langPrioriyList) {
			for (LangHierarchyBo langHierarchyBo : langHierarchyList) {
				if (langCode.equals(langHierarchyBo.getRefIsoLanguage().getIsoLangCode())) {
					return langHierarchyBo.getName();
				}
			}
		}
		return null;
	}

}