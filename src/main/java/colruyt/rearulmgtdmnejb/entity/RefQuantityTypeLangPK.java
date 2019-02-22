package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the QTY_TYPE_LANG database table.
 *
 */
@Embeddable
public class RefQuantityTypeLangPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "QTY_TYPE_ID")
	private long quantityTypeId;

	@Column(name = "ISO_LANG_CODE")
	private String isoLangCode;

	public RefQuantityTypeLangPK() {

	}

	public long getQuantityTypeId() {
		return quantityTypeId;
	}

	public void setQuantityTypeId(long quantityTypeId) {
		this.quantityTypeId = quantityTypeId;
	}

	public String getIsoLangCode() {
		return isoLangCode;
	}

	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}

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
		RefQuantityTypeLangPK other = (RefQuantityTypeLangPK) obj;
		if (isoLangCode == null) {
			if (other.isoLangCode != null) {
				return false;
			}
		} else if (!isoLangCode.equals(other.isoLangCode)) {
			return false;
		}
		if (quantityTypeId != other.quantityTypeId) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isoLangCode == null) ? 0 : isoLangCode.hashCode());
		result = prime * result + (int) (quantityTypeId ^ (quantityTypeId >>> 32));
		return result;
	}

}
