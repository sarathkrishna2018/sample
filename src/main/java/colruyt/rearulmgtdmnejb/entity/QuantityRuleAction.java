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
	private long reaRuleId;

	@Column(name="QTY_COND_ID")
	private long qtyCondId;

	@Column(name="QTY_TYPE_ID")
	private long QtyTypeId;

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(Long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public Long getQtyCondId() {
		return qtyCondId;
	}

	public void setQtyCondId(Long qtyCondId) {
		this.qtyCondId = qtyCondId;
	}

	public Long getQtyTypeId() {
		return QtyTypeId;
	}

	public void setQtyTypeId(Long qtyTypeId) {
		QtyTypeId = qtyTypeId;
	}

}