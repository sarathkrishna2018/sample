package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REA_RULE_ACTTYPE database table.
 * 
 */
@Embeddable
public class ReactionRuleActionTypePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ACTION_TYPE_ID")
	private int actionTypeId;

	@Column(name = "REA_RULE_ID")
	private long reaRuleId;

	public int getActionTypeId() {
		return this.actionTypeId;
	}

	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + actionTypeId;
		result = prime * result + (int) (reaRuleId ^ (reaRuleId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ReactionRuleActionTypePK other = (ReactionRuleActionTypePK) obj;
		if (actionTypeId != other.actionTypeId) {
			return false;
		}
		if (reaRuleId != other.reaRuleId) {
			return false;
		}
		return true;
	}

}