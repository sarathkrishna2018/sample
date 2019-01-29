/*package colruyt.rearulmgtdmnejb.service.dl;

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

import colruyt.rearulmgtdmnejb.entity.RefQtyType;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefQuantityPriceTypeDlServiceTest {

	private RefQuantityPriceTypeDlService refQuantityPriceTypeDlService;

	@Before
	public void init() {
		refQuantityPriceTypeDlService = new RefQuantityPriceTypeDlService();
		JpaUnitils.injectEntityManagerInto(refQuantityPriceTypeDlService);
	}

	@Test
	@DataSet
	public void findAllQuantityConditionTypesTest() {

		List<RefQtyType> refQtyTypeList = refQuantityPriceTypeDlService.findAllQuantityConditionTypes();
		Assert.assertEquals(refQtyTypeList.size(), getAllRefQtyType().size());
	}

	public List<RefQtyType> getAllRefQtyType() {
		List<RefQtyType> refQtyTypeList = Lists.newArrayList();
		RefQtyType refQtyType = new RefQtyType();
		refQtyType.setQtyTypeId(1L);
		refQtyTypeList.add(refQtyType);
		refQtyType = new RefQtyType();
		refQtyType.setQtyTypeId(2L);
		refQtyTypeList.add(refQtyType);
		return refQtyTypeList;

	}
}
*/