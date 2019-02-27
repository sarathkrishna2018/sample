package colruyt.rearulmgtdmnejb.service.dl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.RefFilterOutRecordingType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityConditionType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityType;
import colruyt.rearulmgtdmnejb.entity.RefReasonType;
import colruyt.rearulmgtdmnejb.entity.RefRuleType;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReferenceDataDlServiceTest {
	private ReferenceDataDlService referenceDataDlService;

	@Before
	public void init() {
		referenceDataDlService = new ReferenceDataDlService();
		JpaUnitils.injectEntityManagerInto(referenceDataDlService);
	}

	@Test
	@DataSet
	public void findAllRefFilterOutRecordingTypesTest(){
		List<RefFilterOutRecordingType> expectedFilterOutRecords=referenceDataDlService.findAllRefFilterOutRecordingTypes();
		Assert.assertNotNull(expectedFilterOutRecords);
	}
	@Test
	@DataSet
	public void findAllRefQuantityConditionTest(){
		List<RefQuantityConditionType> expectedQuantityConditions=referenceDataDlService.findAllRefQuantityCondition();
		Assert.assertNotNull(expectedQuantityConditions);
	}
	@Test
	@DataSet
	public void findAllRefQuantityTypeTest(){
		List<RefQuantityType> expectedQuantityTypes=referenceDataDlService.findAllRefQuantityType();
		Assert.assertNotNull(expectedQuantityTypes);
	}
	@Test
	@DataSet
	public void findAllRefReasonTypeTest(){
		List<RefReasonType> expectedReasonTypes=referenceDataDlService.findAllRefReasonType();
		Assert.assertNotNull(expectedReasonTypes);
	}
	@Test
	@DataSet
	public void findAllRuleTypeTest(){
		List<RefRuleType> expectedRuleTypes=referenceDataDlService.findAllRuleType();
		Assert.assertNotNull(expectedRuleTypes);
	}
}
