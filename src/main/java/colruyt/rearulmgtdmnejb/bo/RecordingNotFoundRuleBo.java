package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.enums.RuleType;

public class RecordingNotFoundRuleBo extends GeneralRuleBo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public RecordingNotFoundRuleBo(){
		this.setType(RuleType.RECORD_NOT_FOUND.getRuleTypeName());
	}
	
	private Long noOfNotFoundRecordings;

	public Long getNoOfNotFoundRecordings() {
		return noOfNotFoundRecordings;
	}

	public void setNoOfNotFoundRecordings(Long noOfNotFoundRecordings) {
		this.noOfNotFoundRecordings = noOfNotFoundRecordings;
	}

	

}