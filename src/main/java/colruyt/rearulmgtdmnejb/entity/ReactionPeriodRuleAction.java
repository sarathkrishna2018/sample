package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the REA_PRD_ACT database table.
 * 
 */
@Entity
@Table(name="REA_PRD_ACT")
public class ReactionPeriodRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REA_RULE_ID")
	private long reaRuleId;

	@Column(name="END_DT_DAYS")
	private long endDtDays;

	@Column(name="MIN_DAYS")
	private long minDays;

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(Long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public Long getEndDtDays() {
		return this.endDtDays;
	}

	public void setEndDtDays(Long endDtDays) {
		this.endDtDays = endDtDays;
	}

	public Long getMinDays() {
		return this.minDays;
	}

	public void setMinDays(Long minDays) {
		this.minDays = minDays;
	}

}