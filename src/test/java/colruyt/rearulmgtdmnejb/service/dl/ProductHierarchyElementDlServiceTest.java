package colruyt.rearulmgtdmnejb.service.dl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmntPK;
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
	/*@Test
	@DataSet
	public void findByHierarchyValueListTest(){
		List<PriceProductHierarchyElement> expectedPriceProductHierarchyElement=productHierarchyElementDlService.findByHierarchyValueList(getProductHierarchyElmntValues());
	}*/
	
	@Test
	@DataSet
	public void createProductHierarachyTest(){
		PriceProductHierarchyElement expectedPriceProductHierarchyElement=productHierarchyElementDlService.create(getProductHchyElement());
		Assert.assertNotNull(expectedPriceProductHierarchyElement);
	}
	private List<String> getProductHierarchyElmntValues() {
		List<String> productHierarchyElmnt=Lists.newArrayList();
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
	private List<PriceProductHierarchySetElmnt> getReaPpdHchysetElmnt() {
		List<PriceProductHierarchySetElmnt> reaPpdHchysetElmntlist = Lists.newArrayList();
		PriceProductHierarchySetElmnt reaPpdHchysetElmnt = new PriceProductHierarchySetElmnt();
		reaPpdHchysetElmnt.setLstUpdateBy("Sa");
		PriceProductHierarchySetElmntPK hierarchySetElmntPK = new PriceProductHierarchySetElmntPK();
		hierarchySetElmntPK.setProductHierarchyElementId(1L);
		hierarchySetElmntPK.setProdicyHierarchySetId(1L);
		reaPpdHchysetElmnt.setId(hierarchySetElmntPK);
		reaPpdHchysetElmntlist.add(reaPpdHchysetElmnt);
		return reaPpdHchysetElmntlist;

	}


}
