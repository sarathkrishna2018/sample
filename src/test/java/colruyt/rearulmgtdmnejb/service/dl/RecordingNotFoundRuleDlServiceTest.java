package colruyt.rearulmgtdmnejb.service.dl;

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
	@DataSet
	public void createTest() {
		RecordingNotFoundRuleAction expectedRecordingNotFoundRule = recordingNotFoundRuleActionDlService
				.createOrUpdate(getrecordingNotFoundRuleAction());
		Assert.assertNotNull(expectedRecordingNotFoundRule);

	}

	public RecordingNotFoundRuleAction getrecordingNotFoundRuleAction() {
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = new RecordingNotFoundRuleAction();
		recordingNotFoundRuleAction.setReaRuleId(1l);
		recordingNotFoundRuleAction.setNoOfRecordNotFound(5l);
		return recordingNotFoundRuleAction;

	}

}
