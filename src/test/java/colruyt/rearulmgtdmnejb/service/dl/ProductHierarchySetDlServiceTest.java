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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElementPK;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ProductHierarchySetDlServiceTest {
	private ProductHierarchySetDlService productHierarchySetDlService;

	@Before
	public void init() {
		productHierarchySetDlService = new ProductHierarchySetDlService();
		JpaUnitils.injectEntityManagerInto(productHierarchySetDlService);
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	@ExpectedDataSet("result/ProductHierarchySetCreateTestResult.xml")
	public void createProductHierarachySetTest() {
		PriceProductHierarchySet expectedPriceProductHierarchySet = productHierarchySetDlService
				.create(getPriceProductHierarchySet());
		Assert.assertEquals(expectedPriceProductHierarchySet.getProductHierarchySetId(), new Long(504l));
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	@ExpectedDataSet("result/ProductHierarchySetCreateTestResult.xml")
	public void createProductHierarachySetElementTest() {
		PriceProductHierarchySetElement expectedPriceProductHierarchySetElement = productHierarchySetDlService
				.create(getPriceProductHierarchySetElmnt());
		Assert.assertEquals(expectedPriceProductHierarchySetElement.getId().getProductHierarchyElementId(), 1);
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	public void findByPkTest() {
		PriceProductHierarchySet expectedPriceProductHierarchySetElement = productHierarchySetDlService.findByPk(504l);
		Assert.assertEquals(expectedPriceProductHierarchySetElement.getProductHierarchySetId(), new Long(504l));
		Assert.assertEquals(expectedPriceProductHierarchySetElement.getAssortmentName(), "ALL");
		Assert.assertEquals(expectedPriceProductHierarchySetElement.getCheapBrand(), true );
		Assert.assertEquals(expectedPriceProductHierarchySetElement.getReaRuleId(), new Long(1l));
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	@ExpectedDataSet("result/RemoveProductHierarchyElementTestResult.xml")
	public void removeProductHierarchyElementTest() {
		productHierarchySetDlService.removeProductHierarchyElement(111l);
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	public void findSetElementByElementIdsTest() {
		List<PriceProductHierarchySetElement> expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.findSetElementByElementIds(getpriceProductHierarchySetElmntValues());
		assertThat(expectedpriceProductHierarchySetElmntList.size()).isEqualTo(1);
		Assert.assertEquals(expectedpriceProductHierarchySetElmntList.get(0).getId().getProductHierarchyElementId(), 1l);
		Assert.assertEquals(expectedpriceProductHierarchySetElmntList.get(0).getId().getProductHierarchySetId(), 504l);
		Assert.assertEquals(expectedpriceProductHierarchySetElmntList.get(0).getLstUpdateBy(), "ktr");
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	public void findSetElementBySetIdsTest() {
		List<Long> expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.findSetElementBySetIds(getpriceProductHierarchySetIds());
		assertThat(expectedpriceProductHierarchySetElmntList.size()).isEqualTo(1);
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	@ExpectedDataSet("result/DeleteSetElementsTestResult.xml")
	public void deleteSetElementsTest() {
		productHierarchySetDlService.deleteSetElements(getpriceProductHierarchySetIds());
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	public void findProductSetByIdsTest() {
		List<PriceProductHierarchySet> expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.findProductSetByIds(getpriceProductHierarchySetIds());
		assertThat(expectedpriceProductHierarchySetElmntList.size()).isEqualTo(1);
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	public void getPriceProductHierarchySetElementIdTest() {
		Long expectedResult=productHierarchySetDlService.getPriceProductHierarchySetElementId(getDeleteRuleInfoBo());
		Assert.assertEquals(expectedResult, new Long(504l));
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	@ExpectedDataSet("result/DeletePriceProductHierarchySetElemnetTestResult.xml")
	public void deletePriceProductHierarchySetElemnetTest() {
		productHierarchySetDlService.deletePriceProductHierarchySetElemnet(111l);
	}

	@Test
	@DataSet("dataset/ProductHierarchySetDlServiceTest.xml")
	@ExpectedDataSet("result/DeletePriceProductHierarchySetTestResult.xml")
	public void deletePriceProductHierarchySetTest() {
		productHierarchySetDlService.deletePriceProductHierarchySet(getDeleteRuleInfoBo());
	}

	private List<Long> getpriceProductHierarchySetIds() {
		List<Long> priceProductHierarchySetIds = Lists.newArrayList();
		priceProductHierarchySetIds.add(504l);
		return priceProductHierarchySetIds;
	}

	private PriceProductHierarchySet getPriceProductHierarchySet() {
		PriceProductHierarchySet reaPriceProductHierarchySet = new PriceProductHierarchySet();
		reaPriceProductHierarchySet.setAssortmentName("ALL");
		reaPriceProductHierarchySet.setCheapBrand(true);
		reaPriceProductHierarchySet.setCreatedBy("ktr");
		reaPriceProductHierarchySet.setLstUpdateBy("ktr");
		reaPriceProductHierarchySet.setNationalBrand(true);
		reaPriceProductHierarchySet.setOwnBrand(true);
		reaPriceProductHierarchySet.setProductHierarchySetId(504l);
		reaPriceProductHierarchySet.setReactionRuleId(1l);
		reaPriceProductHierarchySet.setPriceProductHierarchyElements(getPriceProductHierarchyElementList());
		return reaPriceProductHierarchySet;
	}

	private List<PriceProductHierarchyElement> getPriceProductHierarchyElementList() {
		List<PriceProductHierarchyElement> priceProductHierarchyElementList = Lists.newArrayList();
		priceProductHierarchyElementList.add(getProductHchyElement());
		return priceProductHierarchyElementList;
	}

	private PriceProductHierarchyElement getProductHchyElement() {
		PriceProductHierarchyElement reaPpdHchyElmnt = new PriceProductHierarchyElement();
		reaPpdHchyElmnt.setProductHierarchyElementId(1l);
		reaPpdHchyElmnt.setProductHierarchyTypeId(1l);
		reaPpdHchyElmnt.setPpdHchyValue("ASA");
		reaPpdHchyElmnt.setCreatedBy("ktr");
		reaPpdHchyElmnt.setProdHrchySetElement(getReaPpdHchysetElmnt());
		return reaPpdHchyElmnt;
	}

	private List<PriceProductHierarchySetElement> getReaPpdHchysetElmnt() {
		List<PriceProductHierarchySetElement> reaPpdHchysetElmntlist = Lists.newArrayList();
		PriceProductHierarchySetElement reaPpdHchysetElmnt = getPriceProductHierarchySetElmnt();
		reaPpdHchysetElmntlist.add(reaPpdHchysetElmnt);
		return reaPpdHchysetElmntlist;

	}

	private PriceProductHierarchySetElement getPriceProductHierarchySetElmnt() {
		PriceProductHierarchySetElement reaPpdHchysetElmnt = new PriceProductHierarchySetElement();
		reaPpdHchysetElmnt.setLstUpdateBy("ktr");
		PriceProductHierarchySetElementPK hierarchySetElmntPK = new PriceProductHierarchySetElementPK();
		hierarchySetElmntPK.setProductHierarchyElementId(1L);
		hierarchySetElmntPK.setProdicyHierarchySetId(504L);
		reaPpdHchysetElmnt.setId(hierarchySetElmntPK);
		return reaPpdHchysetElmnt;
	}

	private List<Long> getpriceProductHierarchySetElmntValues() {
		List<Long> priceProductHierarchySetElmntList = Lists.newArrayList();
		priceProductHierarchySetElmntList.add(1l);
		return priceProductHierarchySetElmntList;
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
}
