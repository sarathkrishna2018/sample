package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the REA_RNF_ACT database table.
 * 
 */
@Entity
@Table(name="REA_RNF_ACT")
public class RecordingNotFoundRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REA_RULE_ID")
	private long reaRuleId;

	@Column(name="NO_OF_RNF")
	private long noOfRnf;

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public long getNoOfRnf() {
		return this.noOfRnf;
	}

	public void setNoOfRnf(long noOfRnf) {
		this.noOfRnf = noOfRnf;
	}

}