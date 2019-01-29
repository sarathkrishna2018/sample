package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

public class XPSRuleSetBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long ruleSetId;
	private Long ruleType;
	public Long getRuleSetId() {
		return ruleSetId;
	}
	public void setRuleSetId(Long ruleSetId) {
		this.ruleSetId = ruleSetId;
	}
	public Long getRuleType() {
		return ruleType;
	}
	public void setRuleType(Long ruleType) {
		this.ruleType = ruleType;
	}
	public XPSRuleSetBo(Long ruleSetId, Long ruleType) {
		super();
		this.ruleSetId = ruleSetId;
		this.ruleType = ruleType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ruleSetId == null) ? 0 : ruleSetId.hashCode());
		result = prime * result + ((ruleType == null) ? 0 : ruleType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XPSRuleSetBo other = (XPSRuleSetBo) obj;
		if (ruleSetId == null) {
			if (other.ruleSetId != null)
				return false;
		} else if (!ruleSetId.equals(other.ruleSetId))
			return false;
		if (ruleType == null) {
			if (other.ruleType != null)
				return false;
		} else if (!ruleType.equals(other.ruleType))
			return false;
		return true;
	}
	
	
}
