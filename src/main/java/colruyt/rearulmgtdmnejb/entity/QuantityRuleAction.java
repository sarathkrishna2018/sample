package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * The persistent class for the REA_QTY_RULE database table.
 * 
 */
@Entity
@Table(name="REA_QTY_RULE")
public class QuantityRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REA_RULE_ID")
	private long reactionRuleId;

	@Column(name="QTY_COND_ID")
	private long quantityConditionId;

	@Column(name="QTY_TYPE_ID")
	private long quantityTypeId;

	public long getReactionRuleId() {
		return this.reactionRuleId;
	}

	public void setReactionRuleId(Long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}

	public Long getQuantityConditionId() {
		return quantityConditionId;
	}

	public void setQuantityConditionId(Long quantityConditionId) {
		this.quantityConditionId = quantityConditionId;
	}

	public Long getQuantityTypeId() {
		return quantityTypeId;
	}

	public void setQuantityTypeId(Long quantityTypeId) {
		this.quantityTypeId = quantityTypeId;
	}

}