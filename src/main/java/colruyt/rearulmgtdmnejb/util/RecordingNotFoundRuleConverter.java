package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;

public class RecordingNotFoundRuleConverter implements Serializable{

	private static final long serialVersionUID = 1L;
	public RecordingNotFoundRuleAction convert(RecordingNotFoundRuleBo recordingNotFoundRuleBo){
		RecordingNotFoundRuleAction recordNotFoundRuleAction=new RecordingNotFoundRuleAction();
		recordNotFoundRuleAction.setReaRuleId(recordingNotFoundRuleBo.getRuleId());
		recordNotFoundRuleAction.setNoOfRnf(recordingNotFoundRuleBo.getNoOfNotFoundRecordings());
		return recordNotFoundRuleAction;
		
	}
	public RecordingNotFoundRuleBo addRecordingNotFoundRuleAction(RecordingNotFoundRuleAction recordNotFoundRule,
			RecordingNotFoundRuleBo recordingNotFoundBo) {
		recordingNotFoundBo.setNoOfNotFoundRecordings(recordNotFoundRule.getNoOfRnf());
		return recordingNotFoundBo;
	}

}
