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
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "REA_RULE_ID")
	private long reaRuleId;

	@Column(name = "SOURCE_ID")
	private int sourceId;

	public ReactionRuleSourceTypePK() {
	}

	public long getReaRuleId() {
		return this.reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public int getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (reaRuleId ^ (reaRuleId >>> 32));
		result = prime * result + sourceId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;}
		if (obj == null){
			return false;}
		if (getClass() != obj.getClass()){
			return false;}
		ReactionRuleSourceTypePK other = (ReactionRuleSourceTypePK) obj;
		if (reaRuleId != other.reaRuleId){
			return false;}
		if (sourceId != other.sourceId){
			return false;}
		return true;
	}

}