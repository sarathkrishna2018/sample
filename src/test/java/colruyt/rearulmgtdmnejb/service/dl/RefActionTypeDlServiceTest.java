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

import colruyt.rearulmgtdmnejb.entity.RefReaActiontype;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefActionTypeDlServiceTest {

	private RefActionTypeDlService refActionTypeDlService;

	@Before
	public void init() {
		refActionTypeDlService = new RefActionTypeDlService();
		JpaUnitils.injectEntityManagerInto(refActionTypeDlService);
	}

	@Test
	@DataSet
	public void findAllActionTypesTest() {

		List<RefReaActiontype> actionTypesList = refActionTypeDlService.findAllActionTypes();
		Assert.assertEquals(actionTypesList.size(), getAllActionTypes().size());
	}

	public List<RefReaActiontype> getAllActionTypes() {
		List<RefReaActiontype> actionTypesList = Lists.newArrayList();
		RefReaActiontype refReaActiontype = new RefReaActiontype();
		refReaActiontype.setActionTypeId(1L);
		refReaActiontype.setActionType("Price Promo");
		refReaActiontype.setDescription("Price Promo");
		refReaActiontype.setSeq(1L);
		actionTypesList.add(refReaActiontype);
		refReaActiontype = new RefReaActiontype();
		refReaActiontype.setActionTypeId(2L);
		refReaActiontype.setActionType("Discount %");
		refReaActiontype.setDescription("Discount %");
		refReaActiontype.setSeq(2L);
		actionTypesList.add(refReaActiontype);
		return actionTypesList;

	}
}
*/