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
	private long reactionRuleId;

	@Column(name="MAX_COMP_QTY")
	private Double maximumCompetitorQuantity;

	@Column(name="X_TIME_QTY")
	private Double xTimeQty;

	public long getReactionRuleId() {
		return this.reactionRuleId;
	}

	public void setReactionRuleId(long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}

	public Double getMaximumCompetitorQuantity() {
		return this.maximumCompetitorQuantity;
	}

	public void setMaximumCompetitorQuantity(Double maximumCompetitorQuantity) {
		this.maximumCompetitorQuantity = maximumCompetitorQuantity;
	}

	public Double getXTimeQty() {
		return this.xTimeQty;
	}

	public void setXTimeQty(Double xTimeQty) {
		this.xTimeQty = xTimeQty;
	}

}