package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REA_REASON_LANG database table.
 *
 */
@Embeddable
public class RefReasonTypeLangPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "REASON_ID")
	private int reasonId;

	@Column(name = "ISO_LANG_CODE")
	private String isoLangCode;

	public RefReasonTypeLangPK() {

	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
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
		RefReasonTypeLangPK other = (RefReasonTypeLangPK) obj;
		if (isoLangCode == null) {
			if (other.isoLangCode != null) {
				return false;
			}
		} else if (!isoLangCode.equals(other.isoLangCode)) {
			return false;
		}
		if (reasonId != other.reasonId) {
			return false;
		}
		return true;
	}

}
