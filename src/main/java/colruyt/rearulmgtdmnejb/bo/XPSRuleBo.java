package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

public class XPSRuleBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long ruleId;
	private Long ruleType;
	
	public XPSRuleBo(Long ruleId, Long ruleType) {
		super();
		this.ruleId = ruleId;
		this.ruleType = ruleType;
	}
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public Long getRuleType() {
		return ruleType;
	}
	public void setRuleType(Long ruleType) {
		this.ruleType = ruleType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		result = prime * result + ((ruleType == null) ? 0 : ruleType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		XPSRuleBo other = (XPSRuleBo) obj;
		if (ruleId == null) {
			if (other.ruleId != null)
			{
				return false;
			}
		} else if (!ruleId.equals(other.ruleId))
			return false;
		if (ruleType == null) {
			if (other.ruleType != null)
			{
				return false;
			}
		} else if (!ruleType.equals(other.ruleType))
		{
			return false;
		}
		return true;
	}

	
}
