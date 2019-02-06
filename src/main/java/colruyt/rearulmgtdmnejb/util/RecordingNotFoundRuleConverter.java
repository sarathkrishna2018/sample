package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;

@Stateless
@LocalBean
public class RecordingNotFoundRuleConverter implements Serializable{

	private static final long serialVersionUID = 1L;
	public RecordingNotFoundRuleAction convert(RecordingNotFoundRuleBo recordingNotFoundRuleBo){
		RecordingNotFoundRuleAction reaRnfAct=new RecordingNotFoundRuleAction();
		reaRnfAct.setNoOfRnf(recordingNotFoundRuleBo.getNoOfNotFoundRecordings());
		return reaRnfAct;
		
	}
	public RecordingNotFoundRuleBo convertToBo(RecordingNotFoundRuleAction recordNotFoundRule,
			RecordingNotFoundRuleBo recordingNotFoundBo) {
		recordingNotFoundBo.setNoOfNotFoundRecordings(recordNotFoundRule.getNoOfRnf());
		return recordingNotFoundBo;
	}

}
