package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * The persistent class for the REA_FLT_RULE database table.
 * 
 */
@Entity
@Table(name="REA_FLT_RULE")
public class FilteringRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="REA_RULE_ID")
	private long reaRuleId;

	@Column(name="MAX_COMP_QTY")
	private Double maxCompQty;

	@Column(name="X_TIME_QTY")
	private Double xTimeQty;

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public Double getMaxCompQty() {
		return this.maxCompQty;
	}

	public void setMaxCompQty(Double maxCompQty) {
		this.maxCompQty = maxCompQty;
	}

	public Double getXTimeQty() {
		return this.xTimeQty;
	}

	public void setXTimeQty(Double xTimeQty) {
		this.xTimeQty = xTimeQty;
	}

}