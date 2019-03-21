package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;


public class RecordingNotFoundRuleConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static RecordingNotFoundRuleAction convertFromBo(RecordingNotFoundRuleBo recordingNotFoundRuleBo) {
		RecordingNotFoundRuleAction recordNotFoundRuleAction = new RecordingNotFoundRuleAction();
		recordNotFoundRuleAction.setReaRuleId(recordingNotFoundRuleBo.getRuleId());
		recordNotFoundRuleAction.setNoOfRecordNotFound(recordingNotFoundRuleBo.getNoOfNotFoundRecordings());
		return recordNotFoundRuleAction;

	}

	public static RecordingNotFoundRuleBo convertToBo(RecordingNotFoundRuleAction recordNotFoundRule,
			RecordingNotFoundRuleBo recordingNotFoundBo) {
		recordingNotFoundBo.setNoOfNotFoundRecordings(recordNotFoundRule.getNoOfRecordNotFound());
		return recordingNotFoundBo;
	}

}
