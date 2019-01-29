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

import colruyt.rearulmgtdmnejb.entity.RefFltoutType;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefFltoutTypeDlServiceTest {

	private RefFltoutTypeDlService refFltoutTypeDlService;

	@Before
	public void init() {
		refFltoutTypeDlService = new RefFltoutTypeDlService();
		JpaUnitils.injectEntityManagerInto(refFltoutTypeDlService);
	}

	@Test
	@DataSet
	public void getFilteringOutTypesTest() {

		List<RefFltoutType> refFltoutTypeList = refFltoutTypeDlService.getFilteringOutTypes();
		Assert.assertEquals(refFltoutTypeList.size(), getAllFilteringOutTypes().size());

	}

	public List<RefFltoutType> getAllFilteringOutTypes() {
		List<RefFltoutType> refFltoutTypeList = Lists.newArrayList();
		RefFltoutType refFltoutType = new RefFltoutType();
		refFltoutType.setFltoutTypeId(1L);
		refFltoutTypeList.add(refFltoutType);
		refFltoutType = new RefFltoutType();
		refFltoutType.setFltoutTypeId(2L);
		refFltoutTypeList.add(refFltoutType);
		return refFltoutTypeList;

	}
}
*/