package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.openjpa.persistence.ExternalValues;
import org.apache.openjpa.persistence.Type;

/**
 * The persistent class for the REA_REACTING_ACT database table.
 * 
 */
@Entity
@Table(name="REA_REACTING_ACT")
public class ReactingRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REA_RULE_ID")
	private long reaRuleId;

	@Column(name="CATCH_ALL_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean catchAllYn;

	@Column(name="REACTING_AMT")
	private Double reactingAmt;

	@Column(name="REACTING_PERC")
	private Double reactingPerc;

	@Column(name="THOLD_AMT")
	private Double tholdAmt;

	@Column(name="THOLD_PERC")
	private Double tholdPerc;

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public boolean getCatchAllYn() {
		return this.catchAllYn;
	}

	public void setCatchAllYn(boolean catchAllYn) {
		this.catchAllYn = catchAllYn;
	}

	public Double getReactingAmt() {
		return reactingAmt;
	}

	public void setReactingAmt(Double reactingAmt) {
		this.reactingAmt = reactingAmt;
	}

	public Double getReactingPerc() {
		return reactingPerc;
	}

	public void setReactingPerc(Double reactingPerc) {
		this.reactingPerc = reactingPerc;
	}

	public Double getTholdAmt() {
		return tholdAmt;
	}

	public void setTholdAmt(Double tholdAmt) {
		this.tholdAmt = tholdAmt;
	}

	public Double getTholdPerc() {
		return tholdPerc;
	}

	public void setTholdPerc(Double tholdPerc) {
		this.tholdPerc = tholdPerc;
	}

}