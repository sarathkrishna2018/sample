package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

public class RecordingNotFoundRuleBo extends GeneralRuleBo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long noOfNotFoundRecordings;

	public Long getNoOfNotFoundRecordings() {
		return noOfNotFoundRecordings;
	}

	public void setNoOfNotFoundRecordings(Long noOfNotFoundRecordings) {
		this.noOfNotFoundRecordings = noOfNotFoundRecordings;
	}

	

}