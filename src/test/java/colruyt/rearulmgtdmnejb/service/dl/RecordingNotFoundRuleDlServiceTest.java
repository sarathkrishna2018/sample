package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Assert;
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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class RecordingNotFoundRuleDlServiceTest {
	private RecordingNotFoundRuleActionDlService recordingNotFoundRuleActionDlService;

	@Before
	public void init() {
		recordingNotFoundRuleActionDlService = new RecordingNotFoundRuleActionDlService();
		JpaUnitils.injectEntityManagerInto(recordingNotFoundRuleActionDlService);

	}

	@Test
	@ExpectedDataSet("result/RecordNotFoundCreateTestResult.xml")
	public void createTest() {
		RecordingNotFoundRuleAction expectedRecordingNotFoundRule = recordingNotFoundRuleActionDlService
				.createOrUpdate(getrecordingNotFoundRuleAction());
		Assert.assertEquals(1L,expectedRecordingNotFoundRule.getReaRuleId());

	}
	@Test
	@DataSet("dataset/RecordingNotFoundRuleDlServiceTest.xml")
	public void findByRuleIdTest() {
		RecordingNotFoundRuleAction expectedRecordingNotFoundRule = recordingNotFoundRuleActionDlService.findByRuleId(1l);
		Assert.assertEquals(1L,expectedRecordingNotFoundRule.getReaRuleId());
		Assert.assertEquals(4L,expectedRecordingNotFoundRule.getNoOfRecordNotFound());
	}

	@Test
	@DataSet("dataset/RecordingNotFoundRuleDlServiceTest.xml")
	@ExpectedDataSet("result/RecordNotFoundPhysicalDeleteElementsTestResult.xml")
	public void physicalDeleteElementsTest() {
		recordingNotFoundRuleActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
		
	}

	public RecordingNotFoundRuleAction getrecordingNotFoundRuleAction() {
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = new RecordingNotFoundRuleAction();
		recordingNotFoundRuleAction.setReaRuleId(1l);
		recordingNotFoundRuleAction.setNoOfRecordNotFound(4l);
		return recordingNotFoundRuleAction;

	}
	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
}
