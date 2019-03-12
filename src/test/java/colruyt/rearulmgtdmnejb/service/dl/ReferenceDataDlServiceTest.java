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
	@DataSet("dataset/ReferenceDataDlServiceTest.xml")
	public void findAllRefFilterOutRecordingTypesTest() {
		List<RefFilterOutRecordingType> expectedFilterOutRecords = referenceDataDlService
				.findAllRefFilterOutRecordingTypes();
		assertThat(expectedFilterOutRecords.size()).isEqualTo(2);
	}

	@Test
	@DataSet("dataset/ReferenceDataDlServiceTest.xml")
	public void findAllRefQuantityConditionTest() {
		List<RefQuantityConditionType> expectedQuantityConditions = referenceDataDlService
				.findAllRefQuantityCondition();
		assertThat(expectedQuantityConditions.size()).isEqualTo(1);
	}

	@Test
	@DataSet("dataset/ReferenceDataDlServiceTest.xml")
	public void findAllRefQuantityTypeTest() {
		List<RefQuantityType> expectedQuantityTypes = referenceDataDlService.findAllRefQuantityType();
		assertThat(expectedQuantityTypes.size()).isEqualTo(1);
	}

	@Test
	@DataSet("dataset/ReferenceDataDlServiceTest.xml")
	public void findAllRefReasonTypeTest() {
		List<RefReasonType> expectedReasonTypes = referenceDataDlService.findAllRefReasonType();
		assertThat(expectedReasonTypes.size()).isEqualTo(1);
	}

	@Test
	@DataSet("dataset/ReferenceDataDlServiceTest.xml")
	public void findAllRuleTypeTest() {
		List<RefRuleType> expectedRuleTypes = referenceDataDlService.findAllRuleType();
		assertThat(expectedRuleTypes.size()).isEqualTo(1);
	}
}
