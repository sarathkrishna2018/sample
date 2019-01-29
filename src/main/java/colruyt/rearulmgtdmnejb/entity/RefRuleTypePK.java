package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REF_QTY_COND_LANG database table.
 * 
 */
@Embeddable
public class RefRuleTypePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "RULETYPE_ID")
	private long ruletypeId;

	@Column(name = "ISO_LANG_CODE")
	private String isoLangCode;

	public RefRuleTypePK() {
	}

	public long getRuletypeId() {
		return ruletypeId;
	}

	public void setRuletypeId(long ruletypeId) {
		this.ruletypeId = ruletypeId;
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
		result = prime * result + (int) (ruletypeId ^ (ruletypeId >>> 32));
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
		RefRuleTypePK other = (RefRuleTypePK) obj;
		if (isoLangCode == null) {
			if (other.isoLangCode != null)
				return false;
		} else if (!isoLangCode.equals(other.isoLangCode))
			return false;
		if (ruletypeId != other.ruletypeId)
			return false;
		return true;
	}
}