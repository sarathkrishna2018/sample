package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RefQuantityConditionTypeLangPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "QTY_COND_ID")
	private int quantityConditionId;

	@Column(name = "ISO_LANG_CODE")
	private String isoLangCode;

	public RefQuantityConditionTypeLangPK() {
	}

	public int getQuantityConditionId() {
		return quantityConditionId;
	}

	public void setQuantityConditionId(int qtyCondId) {
		this.quantityConditionId = qtyCondId;
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
		result = prime * result + quantityConditionId;
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
		RefQuantityConditionTypeLangPK other = (RefQuantityConditionTypeLangPK) obj;
		if (isoLangCode == null) {
			if (other.isoLangCode != null) {
				return false;
			}
		} else if (!isoLangCode.equals(other.isoLangCode)) {
			return false;
		}
		if (quantityConditionId != other.quantityConditionId) {
			return false;
		}
		return true;
	}

}
