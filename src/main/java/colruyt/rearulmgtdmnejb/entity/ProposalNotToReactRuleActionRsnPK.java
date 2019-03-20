package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProposalNotToReactRuleActionRsnPK implements Serializable {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (reactionRuleId ^ (reactionRuleId >>> 32));
		result = prime * result + reasonId;
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
		ProposalNotToReactRuleActionRsnPK other = (ProposalNotToReactRuleActionRsnPK) obj;
		if (reactionRuleId != other.reactionRuleId) {
			return false;
		}
		if (reasonId != other.reasonId) {
			return false;
		}
		return true;
	}

}
