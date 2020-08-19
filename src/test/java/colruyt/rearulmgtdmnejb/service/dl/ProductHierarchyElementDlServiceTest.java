package colruyt.rearulmgtdmnejb.service.dl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElementPK;


@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ProductHierarchyElementDlServiceTest {
	private ProductHierarchyElementDlService productHierarchyElementDlService;

	@Before
	public void init() {
		productHierarchyElementDlService = new ProductHierarchyElementDlService();
		JpaUnitils.injectEntityManagerInto(productHierarchyElementDlService);
	}

	@Test
	@DataSet("dataset/ProductHierarchyElementDlServiceTest.xml")
	public void findByHierarchyValueListTest() {
		List<PriceProductHierarchyElement> expectedPriceProductHierarchyElement = productHierarchyElementDlService
				.findByHierarchyValueList(getProductHierarchyElmntValues());
		assertThat(expectedPriceProductHierarchyElement.size()).isEqualTo(2);
		Assert.assertEquals( new Long(1l),expectedPriceProductHierarchyElement.get(0).getProductHierarchyElementId());
		Assert.assertEquals( new Long(1l) ,expectedPriceProductHierarchyElement.get(0).getProductHierarchyTypeId());
		Assert.assertEquals("ake",expectedPriceProductHierarchyElement.get(0).getCreatedBy());
		Assert.assertEquals("All",expectedPriceProductHierarchyElement.get(0).getProductHierarchyValue());
	}

	@Test
	@DataSet("dataset/ProductHierarchyElementDlServiceCreateTest.xml")
	@ExpectedDataSet("result/ProductHierarchyElementCreateTestResult.xml")
	public void createProductHierarachyTest() {
		PriceProductHierarchyElement expectedPriceProductHierarchyElement = productHierarchyElementDlService
				.create(getProductHchyElement());
		Assert.assertEquals( new Long(1l),expectedPriceProductHierarchyElement.getProductHierarchyElementId());
		Assert.assertEquals( new Long(1l),expectedPriceProductHierarchyElement.getProductHierarchyTypeId());
		Assert.assertEquals("SA",expectedPriceProductHierarchyElement.getCreatedBy());
		Assert.assertEquals("All",expectedPriceProductHierarchyElement.getProductHierarchyValue());
	}

	@Test
	@DataSet("dataset/ProductHierarchyElementDlServiceTest.xml")
	public void findAllElementsTest() {
		List<PriceProductHierarchyElement> priceProductHierarchyElements = productHierarchyElementDlService
				.findAllElements();
		assertThat(priceProductHierarchyElements.size()).isEqualTo(3);
		Assert.assertEquals("All",priceProductHierarchyElements.get(1).getProductHierarchyValue() );
		Assert.assertEquals("ake",priceProductHierarchyElements.get(1).getCreatedBy());
		Assert.assertEquals( new Long(2l),priceProductHierarchyElements.get(1).getProductHierarchyElementId());
		Assert.assertEquals( new Long(2l),priceProductHierarchyElements.get(1).getProductHierarchyTypeId());
	}

	@Test
	@DataSet("dataset/ProductHierarchyElementDlServiceTest.xml")
	@ExpectedDataSet("result/DeleteElementsTestResult.xml")
	public void deleteElementsTest() {
		productHierarchyElementDlService.deleteElements(getElementsIds());
	}

	private List<Long> getElementsIds() {
		List<Long> elementIds = Lists.newArrayList();
		elementIds.add(1l);
		elementIds.add(2l);
		return elementIds;
	}

	private List<String> getProductHierarchyElmntValues() {
		List<String> productHierarchyElmnt = Lists.newArrayList();
		productHierarchyElmnt.add("All");
		return productHierarchyElmnt;
	}

	private PriceProductHierarchyElement getProductHchyElement() {
		PriceProductHierarchyElement reaPpdHchyElmnt = new PriceProductHierarchyElement();
		reaPpdHchyElmnt.setProductHierarchyElementId(1l);
		reaPpdHchyElmnt.setProductHierarchyTypeId(1l);
		reaPpdHchyElmnt.setProductHierarchyValue("All");
		reaPpdHchyElmnt.setCreatedBy("SA");
		reaPpdHchyElmnt.setProductHierarchySetElement(getReaPpdHchysetElmnt());
		return reaPpdHchyElmnt;
	}

	private List<PriceProductHierarchySetElement> getReaPpdHchysetElmnt() {
		List<PriceProductHierarchySetElement> reaPpdHchysetElmntlist = Lists.newArrayList();
		PriceProductHierarchySetElement reaPpdHchysetElmnt = new PriceProductHierarchySetElement();
		reaPpdHchysetElmnt.setLastUpdateBy("SA");
		PriceProductHierarchySetElementPK hierarchySetElmntPK = new PriceProductHierarchySetElementPK();
		hierarchySetElmntPK.setProductHierarchyElementId(1L);
		hierarchySetElmntPK.setProdicyHierarchySetId(1L);
		reaPpdHchysetElmnt.setId(hierarchySetElmntPK);
		reaPpdHchysetElmntlist.add(reaPpdHchysetElmnt);
		return reaPpdHchysetElmntlist;

	}

}
