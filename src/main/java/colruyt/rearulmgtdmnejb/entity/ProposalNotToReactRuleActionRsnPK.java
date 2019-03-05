package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProposalNotToReactRuleActionRsnPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "REA_RULE_ID")
	private long reactionRuleId;

	@Column(name = "REASON_ID")
	private int reasonId;

	public long getReactionRuleId() {
		return reactionRuleId;
	}

	public void setReactionRuleId(long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
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
		return (this.reactionRuleId == castOther.reactionRuleId) && (this.reasonId == castOther.reasonId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.reactionRuleId ^ (this.reactionRuleId >>> 32)));
		hash = hash * prime + (this.reasonId ^ (this.reasonId >>> 32));

		return hash;
	}
}
