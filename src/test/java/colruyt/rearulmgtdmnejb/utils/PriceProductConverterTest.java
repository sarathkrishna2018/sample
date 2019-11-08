package colruyt.rearulmgtdmnejb.utils;

import java.util.ArrayList;
import java.util.List;

import colruyt.priceproduct.bo.PriceProductResponseBo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import com.google.common.collect.Lists;

import colruyt.priceproduct.bo.LangHierarchyBo;
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
	@Test
	public void convertProductsTest() {
		String langCode = "EN";
		List<PriceProductHierarchyBo> expectedProducts = PriceProductConverter.convertToBo(getMainCategoryBoList(),
				langCode);
		Assert.assertEquals(getMainCategoryBoList().size(), expectedProducts.size());
	}

	@Test
	public void convertProductsFailTest() {
		String langCode = "EN";
		List<PriceProductHierarchyBo> expectedProducts = PriceProductConverter.convertToBo(getMainCategoryBoList(),
				langCode);
		Assert.assertNotSame(new Long(5l), expectedProducts.size());
	}

	@Test
	public void convertTest() {
		String langCode = "EN";
		PriceProductHierarchyBo expectedPriceProductHierarchy = PriceProductConverter.convertToBo(getMainCategoryBo(),
				langCode);
		Assert.assertNotNull(expectedPriceProductHierarchy);
	}

	@Test
	public void convertFailTest() {
		String langCode = "EN";
		PriceProductHierarchyBo expectedPriceProductHierarchy = PriceProductConverter.convertToBo(getMainCategoryBo(),
				langCode);
		Assert.assertNotSame(new Long(5l), expectedPriceProductHierarchy.getChildHierarchy().size());

	}

	@Test
	public void convertToPriceProductHierarchyBosTest(){
		List<PriceProductResponseBo> priceProductResponseBos = new ArrayList<>();
		PriceProductResponseBo priceProductResponseBo = new PriceProductResponseBo();
		priceProductResponseBo.setId(1L);
		List<String> names = new ArrayList<>();
		names.add("bread 500g");
		names.add("brood 500g");
		priceProductResponseBo.setName(names);
		priceProductResponseBos.add(priceProductResponseBo);
		List<PriceProductHierarchyBo> priceProductHierarchyBos = PriceProductConverter
				.convertToPriceProductHierarchyBos(priceProductResponseBos);
		Assert.assertNotNull(priceProductHierarchyBos);
		Assert.assertEquals(1,priceProductHierarchyBos.size());
	}

	private MainCategoryBo getMainCategoryBo() {
		MainCategoryBo mainCategoryBo = new MainCategoryBo();
		mainCategoryBo.setMainCategoryCode("All");
		mainCategoryBo.setMainCategoryLangs(getMainCategoryLang());
		mainCategoryBo.setProductCategories(getProductCategory());
		return mainCategoryBo;
	}

	private List<MainCategoryBo> getMainCategoryBoList() {
		List<MainCategoryBo> mainCategoryBos = Lists.newArrayList();
		MainCategoryBo mainCategoryBo = new MainCategoryBo();
		mainCategoryBo.setMainCategoryCode("All");
		mainCategoryBo.setMainCategoryLangs(getMainCategoryLang());
		mainCategoryBo.setProductCategories(getProductCategory());
		mainCategoryBos.add(mainCategoryBo);
		return mainCategoryBos;
	}

	private List<ProductCategoryBo> getProductCategory() {
		List<ProductCategoryBo> productCategoryBos = Lists.newArrayList();
		ProductCategoryBo productCategoryBo = new ProductCategoryBo();
		productCategoryBo.setMainCategoryCode("All");
		productCategoryBo.setProductCategoryCode("Baby");
		productCategoryBo.setProductCategoryLangs(getMainCategoryLang());
		productCategoryBo.setProductGroups(getProductGroup());
		productCategoryBos.add(productCategoryBo);
		return productCategoryBos;
	}

	private List<ProductGroupBo> getProductGroup() {
		List<ProductGroupBo> productGroupBos = Lists.newArrayList();
		ProductGroupBo productGroupBo = new ProductGroupBo();
		productGroupBo.setProductCategoryCode("all");
		productGroupBo.setProductGroupCode("baby");
		productGroupBo.setProductGroupLangs(getMainCategoryLang());
		productGroupBo.setProductSegments(getProductSegment());
		productGroupBos.add(productGroupBo);
		return productGroupBos;
	}

	private List<ProductSegmentBo> getProductSegment() {
		List<ProductSegmentBo> productSegmentBos = Lists.newArrayList();
		ProductSegmentBo productSegmentBo = new ProductSegmentBo();
		productSegmentBo.setProductGroupCode("All");
		productSegmentBo.setProductList(getProductList());
		productSegmentBo.setProductSegmentCode("All");
		productSegmentBo.setProductSegmentLangs(getMainCategoryLang());
		productSegmentBos.add(productSegmentBo);
		return productSegmentBos;
	}

	private List<PriceProductHierarchyBo> getProductList() {
		List<PriceProductHierarchyBo> priceProductHierarchyBos = Lists.newArrayList();
		PriceProductHierarchyBo priceProductHierarchyBo = new PriceProductHierarchyBo();
		// priceProductHierarchyBo.setChildHierarchy(getProductList());
		priceProductHierarchyBo.setExcluded(false);
		priceProductHierarchyBo.setExpanded(true);
		priceProductHierarchyBo.setId(1l);
		priceProductHierarchyBo.setIncluded(true);
		priceProductHierarchyBo.setLinkedContextHierarchyId(1l);
		priceProductHierarchyBo.setName("AllProducts");
		priceProductHierarchyBo.setParentValue("All");
		priceProductHierarchyBo.setTypeId(1);
		priceProductHierarchyBo.setValue("All");
		priceProductHierarchyBos.add(priceProductHierarchyBo);
		return priceProductHierarchyBos;
	}

	private List<LangHierarchyBo> getMainCategoryLang() {
		List<LangHierarchyBo> langHierarchyBos = Lists.newArrayList();
		LangHierarchyBo langHierarchyBo = new LangHierarchyBo();
		langHierarchyBo.setName("Eng");
		langHierarchyBo.setRefIsoLanguage(getRefIsoLanguage());
		langHierarchyBos.add(langHierarchyBo);
		return langHierarchyBos;
	}

	private RefIsoLangBO getRefIsoLanguage() {
		RefIsoLangBO refIsoLangBO = new RefIsoLangBO();
		refIsoLangBO.setIsoLangCode("En");
		return refIsoLangBO;
	}

}
