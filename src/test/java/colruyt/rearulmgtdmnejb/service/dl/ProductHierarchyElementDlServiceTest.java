package colruyt.rearulmgtdmnejb.service.dl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import junit.framework.Assert;

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
	}

	@Test
	@DataSet("dataset/ProductHierarchyElementDlServiceTest.xml")
	public void createProductHierarachyTest() {
		PriceProductHierarchyElement expectedPriceProductHierarchyElement = productHierarchyElementDlService
				.create(getProductHchyElement());
		Assert.assertEquals(expectedPriceProductHierarchyElement.getProductHierarchyElementId(), new Long(1l));
	}

	@Test
	@DataSet("dataset/ProductHierarchyElementDlServiceTest.xml")
	public void findAllElementsTest() {
		List<PriceProductHierarchyElement> priceProductHierarchyElements = productHierarchyElementDlService
				.findAllElements();
		assertThat(priceProductHierarchyElements.size()).isEqualTo(3);
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
		reaPpdHchyElmnt.setPpdHchyValue("ASA");
		reaPpdHchyElmnt.setCreatedBy("SA");
		reaPpdHchyElmnt.setProdHrchySetElement(getReaPpdHchysetElmnt());
		return reaPpdHchyElmnt;
	}

	private List<PriceProductHierarchySetElement> getReaPpdHchysetElmnt() {
		List<PriceProductHierarchySetElement> reaPpdHchysetElmntlist = Lists.newArrayList();
		PriceProductHierarchySetElement reaPpdHchysetElmnt = new PriceProductHierarchySetElement();
		reaPpdHchysetElmnt.setLstUpdateBy("Sa");
		PriceProductHierarchySetElementPK hierarchySetElmntPK = new PriceProductHierarchySetElementPK();
		hierarchySetElmntPK.setProductHierarchyElementId(1L);
		hierarchySetElmntPK.setProdicyHierarchySetId(1L);
		reaPpdHchysetElmnt.setId(hierarchySetElmntPK);
		reaPpdHchysetElmntlist.add(reaPpdHchysetElmnt);
		return reaPpdHchysetElmntlist;

	}

}
