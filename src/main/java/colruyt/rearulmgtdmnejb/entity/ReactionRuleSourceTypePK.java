package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The primary key class for the REA_RULE_SRC database table.
 * 
 */
@Embeddable
public class ReactionRuleSourceTypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REA_RULE_ID")
	private long reaRuleId;

	@Column(name="SOURCE_ID")
	private long sourceId;

	public ReactionRuleSourceTypePK() {
	}
	public long getReaRuleId() {
		return this.reaRuleId;
	}
	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}
	public long getSourceId() {
		return this.sourceId;
	}
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReactionRuleSourceTypePK)) {
			return false;
		}
		ReactionRuleSourceTypePK castOther = (ReactionRuleSourceTypePK)other;
		return 
			(this.reaRuleId == castOther.reaRuleId)
			&& (this.sourceId == castOther.sourceId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.reaRuleId ^ (this.reaRuleId >>> 32)));
		hash = hash * prime + ((int) (this.sourceId ^ (this.sourceId >>> 32)));
		
		return hash;
	}
}