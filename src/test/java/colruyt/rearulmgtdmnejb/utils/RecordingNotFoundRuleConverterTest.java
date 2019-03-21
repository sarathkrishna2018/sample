package colruyt.rearulmgtdmnejb.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;
import colruyt.rearulmgtdmnejb.util.RecordingNotFoundRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class RecordingNotFoundRuleConverterTest {

	@Test
	public void createConverterTest() {
		RecordingNotFoundRuleBo recordingNotFoundRule = getRecordingNotFoundRule();
		RecordingNotFoundRuleAction recordNotFoundRuleAction = new RecordingNotFoundRuleAction();
		recordNotFoundRuleAction.setReaRuleId(1L);
		recordNotFoundRuleAction.setNoOfRecordNotFound(recordingNotFoundRule.getNoOfNotFoundRecordings());
		RecordingNotFoundRuleAction expectedReaRnfAct = RecordingNotFoundRuleConverter
				.convertFromBo(recordingNotFoundRule);
		Assert.assertEquals(new Long(12l), Long.valueOf(expectedReaRnfAct.getNoOfRecordNotFound()));

	}

	public RecordingNotFoundRuleBo getRecordingNotFoundRule() {
		RecordingNotFoundRuleBo recordingNotFoundRule = new RecordingNotFoundRuleBo();
		recordingNotFoundRule.setRuleId(1L);
		recordingNotFoundRule.setNoOfNotFoundRecordings(12l);
		return recordingNotFoundRule;
	}

}
