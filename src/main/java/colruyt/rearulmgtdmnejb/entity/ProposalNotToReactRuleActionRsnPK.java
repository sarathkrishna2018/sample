package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProposalNotToReactRuleActionRsnPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "REA_RULE_ID")
	private long reaRuleId;

	@Column(name = "REASON_ID")
	private long reasonId;

	public long getReaRuleId() {
		return reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public long getReasonId() {
		return reasonId;
	}

	public void setReasonId(long reasonId) {
		this.reasonId = reasonId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProposalNotToReactRuleActionRsnPK)) {
			return false;
		}
		ProposalNotToReactRuleActionRsnPK castOther = (ProposalNotToReactRuleActionRsnPK) other;
		return (this.reaRuleId == castOther.reaRuleId) && (this.reasonId == castOther.reasonId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.reaRuleId ^ (this.reaRuleId >>> 32)));
		hash = hash * prime + ((int) (this.reasonId ^ (this.reasonId >>> 32)));

		return hash;
	}
}
