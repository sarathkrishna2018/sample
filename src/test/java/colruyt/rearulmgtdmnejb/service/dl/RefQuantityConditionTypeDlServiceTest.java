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

import colruyt.rearulmgtdmnejb.entity.RefQtyCond;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefQuantityConditionTypeDlServiceTest {

	private RefQuantityConditionTypeDlService refQuantityConditionTypeDlService;

	@Before
	public void init() {
		refQuantityConditionTypeDlService = new RefQuantityConditionTypeDlService();
		JpaUnitils.injectEntityManagerInto(refQuantityConditionTypeDlService);
	}

	@Test
	@DataSet
	public void findAllQuantityConditionTypesTest() {

		List<RefQtyCond> refQtyCondList = refQuantityConditionTypeDlService.findAllQuantityConditionTypes();
		Assert.assertEquals(refQtyCondList.size(), getAllRefQtyCond().size());
	}

	public List<RefQtyCond> getAllRefQtyCond() {
		List<RefQtyCond> refQtyCondList = Lists.newArrayList();
		RefQtyCond refQtyCond = new RefQtyCond();
		refQtyCond.setQtyCondId(1L);
		refQtyCondList.add(refQtyCond);
		refQtyCond = new RefQtyCond();
		refQtyCond.setQtyCondId(2L);
		refQtyCondList.add(refQtyCond);
		return refQtyCondList;

	}
}
*/