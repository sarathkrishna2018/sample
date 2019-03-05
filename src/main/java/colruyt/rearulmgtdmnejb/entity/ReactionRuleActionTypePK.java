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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReactionRuleActionTypePK)) {
			return false;
		}
		ReactionRuleActionTypePK castOther = (ReactionRuleActionTypePK) other;
		return (this.actionTypeId == castOther.actionTypeId) && (this.reaRuleId == castOther.reaRuleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + (this.actionTypeId ^ (this.actionTypeId >>> 32));
		hash = hash * prime + (int) ((this.reaRuleId ^ (this.reaRuleId >>> 32)));

		return hash;
	}
}