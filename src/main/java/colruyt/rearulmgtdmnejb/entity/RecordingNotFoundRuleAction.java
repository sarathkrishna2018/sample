package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REA_RNF_ACT")
public class RecordingNotFoundRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REA_RULE_ID")
	private long reaRuleId;

	@Column(name = "NO_OF_RNF")
	private long noOfRecordNotFound;

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public long getNoOfRecordNotFound() {
		return this.noOfRecordNotFound;
	}

	public void setNoOfRecordNotFound(long noOfRnf) {
		this.noOfRecordNotFound = noOfRnf;
	}

}