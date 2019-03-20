package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REA_QTY_RULE")
public class QuantityRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REA_RULE_ID")
	private long reactionRuleId;

	@Column(name = "QTY_COND_ID")
	private int quantityConditionId;

	@Column(name = "QTY_TYPE_ID")
	private int quantityTypeId;

	public long getReactionRuleId() {
		return this.reactionRuleId;
	}

	public void setReactionRuleId(Long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}

	public int getQuantityConditionId() {
		return quantityConditionId;
	}

	public void setQuantityConditionId(int quantityConditionId) {
		this.quantityConditionId = quantityConditionId;
	}

	public int getQuantityTypeId() {
		return quantityTypeId;
	}

	public void setQuantityTypeId(int quantityTypeId) {
		this.quantityTypeId = quantityTypeId;
	}

}