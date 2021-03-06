package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.openjpa.persistence.ExternalValues;
import org.apache.openjpa.persistence.Type;

@Entity
@Table(name = "REA_REACTING_ACT")
public class ReactingRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REA_RULE_ID")
	private long reactionRuleId;

	@Column(name = "CATCH_ALL_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean catchAll;

	@Column(name = "REACTING_AMT")
	private Double reactingAmount;

	@Column(name = "REACTING_PERC")
	private Double reactingPercentage;

	@Column(name = "THOLD_AMT")
	private Double thresholdAmount;

	@Column(name = "THOLD_PERC")
	private Double thresholdPercentage;

	public long getReactionRuleId() {
		return this.reactionRuleId;
	}

	public void setReactionRuleId(long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}

	public boolean getCatchAll() {
		return this.catchAll;
	}

	public void setCatchAll(boolean catchAllYn) {
		this.catchAll = catchAllYn;
	}

	public Double getReactingAmount() {
		return reactingAmount;
	}

	public void setReactingAmount(Double reactingAmt) {
		this.reactingAmount = reactingAmt;
	}

	public Double getReactingPercentage() {
		return reactingPercentage;
	}

	public void setReactingPercentage(Double reactingPerc) {
		this.reactingPercentage = reactingPerc;
	}

	public Double getThresholdAmount() {
		return thresholdAmount;
	}

	public void setThresholdAmount(Double tholdAmt) {
		this.thresholdAmount = tholdAmt;
	}

	public Double getThresholdPercentage() {
		return thresholdPercentage;
	}

	public void setThresholdPercentage(Double tholdPerc) {
		this.thresholdPercentage = tholdPerc;
	}

}