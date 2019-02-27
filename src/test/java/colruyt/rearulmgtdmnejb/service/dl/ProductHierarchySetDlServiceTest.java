package colruyt.rearulmgtdmnejb.service.dl;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmntPK;
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
	@DataSet
	public void createProductHierarachySetTest() {
		PriceProductHierarchySet expectedPriceProductHierarchySet = productHierarchySetDlService
				.create(getPriceProductHierarchySet());
		Assert.assertNotNull(expectedPriceProductHierarchySet);
	}

	@Test
	@DataSet
	public void createProductHierarachySetElementTest() {
		PriceProductHierarchySetElmnt expectedPriceProductHierarchySetElement = productHierarchySetDlService
				.create(getPriceProductHierarchySetElmnt());
		Assert.assertNotNull(expectedPriceProductHierarchySetElement);
	}

	@Test
	@DataSet
	public void findByPkTest() {
		PriceProductHierarchySet expectedPriceProductHierarchySetElement = productHierarchySetDlService.findByPk(504l);
		Assert.assertNotNull(expectedPriceProductHierarchySetElement);
	}

	@Test
	@DataSet
	public void removeProductHierarchyElementTest() {
		productHierarchySetDlService.removeProductHierarchyElement(504l);
	}

	@Test
	@DataSet
	public void findSetElementByElementIdsTest() {
		List<PriceProductHierarchySetElmnt> expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.findSetElementByElementIds(getpriceProductHierarchySetElmntList());
		Assert.assertNotNull(expectedpriceProductHierarchySetElmntList);
	}

	@Test
	@DataSet
	public void findSetElementBySetIdsTest() {
		List<Long> expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.findSetElementBySetIds(getpriceProductHierarchySetElmntList());
		Assert.assertNotNull(expectedpriceProductHierarchySetElmntList);
	}

	@Test
	@DataSet
	public void deleteSetElementsTest() {
		productHierarchySetDlService.deleteSetElements(getpriceProductHierarchySetElmntList());
	}

	@Test
	@DataSet
	public void findProductSetByIdsTest() {
		List<PriceProductHierarchySet> expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.findProductSetByIds(getpriceProductHierarchySetElmntList());
		Assert.assertNotNull(expectedpriceProductHierarchySetElmntList);
	}

	@Test
	@DataSet
	public void getPriceProductHierarchySetElementIdTest() {
		long expectedpriceProductHierarchySetElmntList = productHierarchySetDlService
				.getPriceProductHierarchySetElementId(getDeleteRuleInfoBo());
		Assert.assertNotNull(expectedpriceProductHierarchySetElmntList);
	}

	@Test
	@DataSet
	public void deletePriceProductHierarchySetElemnetTest() {
		productHierarchySetDlService.deletePriceProductHierarchySetElemnet(5l);
	}

	@Test
	@DataSet
	public void deletePriceProductHierarchySetTest() {
		productHierarchySetDlService.deletePriceProductHierarchySet(getDeleteRuleInfoBo());
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
		reaPriceProductHierarchySet.setReactionRuleId(2l);
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
		reaPpdHchyElmnt.setCreatedBy("SA");
		reaPpdHchyElmnt.setProdHrchySetElement(getReaPpdHchysetElmnt());
		return reaPpdHchyElmnt;
	}

	private List<PriceProductHierarchySetElmnt> getReaPpdHchysetElmnt() {
		List<PriceProductHierarchySetElmnt> reaPpdHchysetElmntlist = Lists.newArrayList();
		PriceProductHierarchySetElmnt reaPpdHchysetElmnt = getPriceProductHierarchySetElmnt();
		reaPpdHchysetElmntlist.add(reaPpdHchysetElmnt);
		return reaPpdHchysetElmntlist;

	}

	private PriceProductHierarchySetElmnt getPriceProductHierarchySetElmnt() {
		PriceProductHierarchySetElmnt reaPpdHchysetElmnt = new PriceProductHierarchySetElmnt();
		reaPpdHchysetElmnt.setLstUpdateBy("Sa");
		PriceProductHierarchySetElmntPK hierarchySetElmntPK = new PriceProductHierarchySetElmntPK();
		hierarchySetElmntPK.setProductHierarchyElementId(1L);
		hierarchySetElmntPK.setProdicyHierarchySetId(1L);
		reaPpdHchysetElmnt.setId(hierarchySetElmntPK);
		return reaPpdHchysetElmnt;
	}

	List<Long> getpriceProductHierarchySetElmntList() {
		List<Long> priceProductHierarchySetElmntList = Lists.newArrayList();
		priceProductHierarchySetElmntList.add(5l);
		priceProductHierarchySetElmntList.add(2l);
		return priceProductHierarchySetElmntList;
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
}
