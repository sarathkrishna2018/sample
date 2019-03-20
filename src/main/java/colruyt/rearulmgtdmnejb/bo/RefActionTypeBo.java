package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefActionTypeBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int actionTypeId;
	private String actionTypeValue;
	private long sequence;

	public int getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public String getActionTypeValue() {
		return actionTypeValue;
	}

	public void setActionTypeValue(String actionTypeValue) {
		this.actionTypeValue = actionTypeValue;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

}