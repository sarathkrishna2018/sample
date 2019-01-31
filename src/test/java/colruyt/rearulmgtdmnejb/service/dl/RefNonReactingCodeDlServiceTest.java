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

import colruyt.rearulmgtdmnejb.entity.RefReason;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefNonReactingCodeDlServiceTest {

	private RefNonReactingCodeDlService refNonReactingCodeDlService;

	@Before
	public void init() {
		refNonReactingCodeDlService = new RefNonReactingCodeDlService();
		JpaUnitils.injectEntityManagerInto(refNonReactingCodeDlService);
	}

	@Test
	@DataSet
	public void findAllNonReactingCodeTypesTest() {

		List<RefReason> refReasonList = refNonReactingCodeDlService.findAllNonReactingCodeTypes();
		Assert.assertEquals(refReasonList.size(), getAllRefReason().size());

	}

	public List<RefReason> getAllRefReason() {
		List<RefReason> refReasonList = Lists.newArrayList();
		RefReason refReason = new RefReason();
		refReason.setReasonId(1L);
		refReasonList.add(refReason);
		refReason = new RefReason();
		refReason.setReasonId(2L);
		refReasonList.add(refReason);
		return refReasonList;

	}
}
*/