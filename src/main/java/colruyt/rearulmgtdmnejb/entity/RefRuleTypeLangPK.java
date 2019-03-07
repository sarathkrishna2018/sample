package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RefRuleTypeLangPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "RULETYPE_ID")
	private int ruleTypeId;

	@Column(name = "ISO_LANG_CODE")
	private String isoLangCode;

	public RefRuleTypeLangPK() {

	}

	public int getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(int ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public String getIsoLangCode() {
		return isoLangCode;
	}

	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isoLangCode == null) ? 0 : isoLangCode.hashCode());
		result = prime * result + ruleTypeId;
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
		RefRuleTypeLangPK other = (RefRuleTypeLangPK) obj;
		if (isoLangCode == null) {
			if (other.isoLangCode != null) {
				return false;
			}
		} else if (!isoLangCode.equals(other.isoLangCode)) {
			return false;
		}
		if (ruleTypeId != other.ruleTypeId) {
			return false;
		}
		return true;
	}

}
