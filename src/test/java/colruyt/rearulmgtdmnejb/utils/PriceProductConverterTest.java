package colruyt.rearulmgtdmnejb.utils;

import java.util.List;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.priceproduct.bo.LangHirerachyBo;
import colruyt.priceproduct.bo.MainCategoryBo;
import colruyt.priceproduct.bo.PriceProductHierarchyBo;
import colruyt.priceproduct.bo.ProductCategoryBo;
import colruyt.priceproduct.bo.ProductGroupBo;
import colruyt.priceproduct.bo.ProductSegmentBo;
import colruyt.priceproduct.bo.RefIsoLangBO;
import colruyt.rearulmgtdmnejb.util.PriceProductConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class PriceProductConverterTest {
	@TestedObject
	private PriceProductConverter priceProductConverter;
	
	@Test
	public void getLanguagePreferenceTest(){
		String userLang="EN";
		List<String> expectedLanguagePreference=priceProductConverter.getLanguagePreference(userLang);
		Assert.assertNotNull(expectedLanguagePreference);
	}
	@Test
	public void getLanguagePreferenceFailTest(){
		String userLang="EN";
		List<String> expectedLanguagePreference=priceProductConverter.getLanguagePreference(userLang);
		Assert.assertNotSame(new Long(5l), expectedLanguagePreference.size());
	}
	@Test
	public void convertProductsTest(){
		String langCode="EN";
		List<PriceProductHierarchyBo> expectedProducts=priceProductConverter.convertProducts(getMainCategoryBoList(), langCode);
		Assert.assertEquals(getMainCategoryBoList().size(), expectedProducts.size());
	}
	@Test
	public void convertProductsFailTest(){
		String langCode="EN";
		List<PriceProductHierarchyBo> expectedProducts=priceProductConverter.convertProducts(getMainCategoryBoList(), langCode);
		Assert.assertNotSame(new Long(5l), expectedProducts.size());
	}
	@Test
	public void convertTest(){
		String langCode="EN";
		PriceProductHierarchyBo expectedPriceProductHierarchy=priceProductConverter.convert(getMainCategoryBo(), langCode);
		Assert.assertNotNull(expectedPriceProductHierarchy);
	}
	@Test
	public void convertFailTest(){
		String langCode="EN";
		PriceProductHierarchyBo expectedPriceProductHierarchy=priceProductConverter.convert(getMainCategoryBo(), langCode);
		Assert.assertNotSame(new Long(5l), expectedPriceProductHierarchy.getChildHierarchy().size());
		
	}
	
	private MainCategoryBo getMainCategoryBo(){
		 MainCategoryBo mainCategoryBo=new MainCategoryBo();
		 mainCategoryBo.setMainCategoryCode("All");
		 mainCategoryBo.setMainCategoryLangs(getMainCategoryLang());
		 mainCategoryBo.setProductCategories(getProductCategory());
		return mainCategoryBo;
	}
	private List<MainCategoryBo> getMainCategoryBoList() {
		 List<MainCategoryBo> mainCategoryBos=Lists.newArrayList();
		 MainCategoryBo mainCategoryBo=new MainCategoryBo();
		 mainCategoryBo.setMainCategoryCode("All");
		 mainCategoryBo.setMainCategoryLangs(getMainCategoryLang());
		 mainCategoryBo.setProductCategories(getProductCategory());
		 mainCategoryBos.add(mainCategoryBo);
		return mainCategoryBos;
	}
	private List<ProductCategoryBo> getProductCategory() {
		List<ProductCategoryBo> productCategoryBos=Lists.newArrayList();
		ProductCategoryBo productCategoryBo=new ProductCategoryBo();
		productCategoryBo.setMainCategoryCode("All");
		productCategoryBo.setProductCategoryCode("Baby");
		productCategoryBo.setProductCategoryLangs(getMainCategoryLang());
		productCategoryBo.setProductGroups(getProductGroup());
		productCategoryBos.add(productCategoryBo);
		return productCategoryBos;
	}
	private List<ProductGroupBo> getProductGroup() {
		List<ProductGroupBo> productGroupBos=Lists.newArrayList();
		ProductGroupBo productGroupBo=new ProductGroupBo();
		productGroupBo.setProductCategoryCode("all");
		productGroupBo.setProductGroupCode("baby");
		productGroupBo.setProductGroupLangs(getMainCategoryLang());
		productGroupBo.setProductSegments(getProductSegment());
		productGroupBos.add(productGroupBo);
		return productGroupBos;
	}
	private List<ProductSegmentBo> getProductSegment() {
		List<ProductSegmentBo> productSegmentBos=Lists.newArrayList();
		ProductSegmentBo productSegmentBo=new ProductSegmentBo();
		productSegmentBo.setProductGroupCode("All");
		productSegmentBo.setProductList(getProductList());
		productSegmentBo.setProductSegmentCode("All");
		productSegmentBo.setProductSegmentLangs(getMainCategoryLang());
		productSegmentBos.add(productSegmentBo);
		return productSegmentBos;
	}
	private List<PriceProductHierarchyBo> getProductList() {
		List<PriceProductHierarchyBo> priceProductHierarchyBos=Lists.newArrayList();
		PriceProductHierarchyBo priceProductHierarchyBo=new PriceProductHierarchyBo();
		//priceProductHierarchyBo.setChildHierarchy(getProductList());
		priceProductHierarchyBo.setExcluded(false);
		priceProductHierarchyBo.setExpanded(true);
		priceProductHierarchyBo.setId(1l);
		priceProductHierarchyBo.setIncluded(true);
		priceProductHierarchyBo.setLinkedContextHierarchyId(1l);
		priceProductHierarchyBo.setName("AllProducts");
		priceProductHierarchyBo.setParentValue("All");
		priceProductHierarchyBo.setTypeId(1l);
		priceProductHierarchyBo.setValue("All");
		priceProductHierarchyBos.add(priceProductHierarchyBo);
		return priceProductHierarchyBos;
	}
	private List<LangHirerachyBo> getMainCategoryLang() {
		List<LangHirerachyBo> langHirerachyBos=Lists.newArrayList();
		LangHirerachyBo langHirerachyBo=new LangHirerachyBo();
		langHirerachyBo.setName("Eng");
		langHirerachyBo.setRefIsoLanguage(getRefIsoLanguage());
		langHirerachyBos.add(langHirerachyBo);
		return langHirerachyBos;
	}
	private RefIsoLangBO getRefIsoLanguage() {
		RefIsoLangBO refIsoLangBO=new RefIsoLangBO();
		refIsoLangBO.setIsoLangCode("En");
		return refIsoLangBO;
	}
	
}
