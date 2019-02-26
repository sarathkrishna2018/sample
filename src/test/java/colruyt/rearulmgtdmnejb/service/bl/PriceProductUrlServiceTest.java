package colruyt.rearulmgtdmnejb.service.bl;

import java.util.List;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class PriceProductUrlServiceTest {
	@TestedObject
	private PriceProductUrlService priceProductUrlService;
	@Test
	public void getPriceProductUrlTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/hierarchy?list_category_ids=Baby,girl&exclusion=false";
		String expectedpriceProductUrl=priceProductUrlService.getPriceProductUrlForHierarchyList(getHierarchyValues());
		Assert.assertEquals(expectedpriceProductUrl, url);
		
	}
	
	
	@Test
	public void getPriceProductUrlFailTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/hierarchy?list_category_ids=Baby,girl&exclusion=true";
		String expectedpriceProductUrl=priceProductUrlService.getPriceProductUrlForHierarchyList(getHierarchyValues());
		Assert.assertNotSame(expectedpriceProductUrl, url);
		
	}
	
	@Test
	public void getPriceProductURLTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/priceproducts?products_ids=allProducts";
		String expectedPriceProductURL=priceProductUrlService.getPriceProductURL(getProductList());
		Assert.assertEquals(expectedPriceProductURL, url);
	}
	@Test
	public void getPriceProductURLFailTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/priceproducts/products_ids=allProducts";
		String expectedPriceProductURL=priceProductUrlService.getPriceProductURL(getProductList());
		Assert.assertNotSame(expectedPriceProductURL, url);
	}
	@Test
	public void getCommaSeperatedValuesForURLTest(){
		String commaValue="Baby,girl";
		String expectedCommaValue=priceProductUrlService.getCommaSeperatedValuesForURL(getHierarchyValues());
		Assert.assertEquals(expectedCommaValue, commaValue);
		
	}
	@Test
	public void getCommaSeperatedValuesForURLFailTest(){
		String commaValue="Baby;girl";
		String expectedCommaValue=priceProductUrlService.getCommaSeperatedValuesForURL(getHierarchyValues());
		Assert.assertNotSame(expectedCommaValue, commaValue);
		
	}
	@Test
	public void getPriceProductURLForPriceProductIdTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/priceproducts/XX";
		String priceProductId="XX";
		String expectedPriceProductUrlPriceProduct=priceProductUrlService.getPriceProductURLForPriceProductId(priceProductId);
		Assert.assertEquals(expectedPriceProductUrlPriceProduct, url);
	}
	@Test
	public void getPriceProductURLForPriceProductIdFailTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/priceproducts?priceProductId";
		String priceProductId="XX";
		String expectedPriceProductUrlPriceProduct=priceProductUrlService.getPriceProductURLForPriceProductId(priceProductId);
		Assert.assertNotSame(expectedPriceProductUrlPriceProduct, url);
	}
	@Test
	public void getAllHierarchyUrlTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/hierarchy/";
		String expectedAllHierarchyUrl=priceProductUrlService.getAllHierarchyUrl();
		Assert.assertEquals(expectedAllHierarchyUrl, url);
	}
	@Test
	public void getAllHierarchyUrlFailTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/hierarchy/allProducts";
		String expectedAllHierarchyUrl=priceProductUrlService.getAllHierarchyUrl();
		Assert.assertNotSame(expectedAllHierarchyUrl, url);
	}
	@Test
	public void getAllProductsTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/hierarchy/products-hierarchy";
		String expectedAllProducts=priceProductUrlService.getAllProducts();
		Assert.assertEquals(expectedAllProducts, url);
	}
	@Test
	public void getAllProductsFailTest(){
		String url="http://test-priceproductmw.colruyt.int/priceproductmw/hierarchy?products-hierarchy";
		String expectedAllProducts=priceProductUrlService.getAllProducts();
		Assert.assertNotSame(expectedAllProducts, url);
	}
	private List<String> getProductList() {
		List<String> productList=Lists.newArrayList();
		productList.add("allProducts");
		return productList;
	}
	private List<String> getHierarchyValues(){
		List<String> hierarchyValues=Lists.newArrayList();
		hierarchyValues.add("Baby");
		hierarchyValues.add("girl");
		return hierarchyValues;
		
	}

}
