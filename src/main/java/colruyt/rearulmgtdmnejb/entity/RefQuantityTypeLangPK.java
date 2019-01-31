package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REF_QTY_TYPE_LANG database table.
 * 
 */
@Embeddable
public class RefQuantityTypeLangPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="QTY_TYPE_ID")
	private long qtyTypeId;

	@Column(name="ISO_LANG_CODE")
	private String isoLangCode;

	public RefQuantityTypeLangPK() {
	}
	public long getQtyTypeId() {
		return this.qtyTypeId;
	}
	public void setQtyTypeId(long qtyTypeId) {
		this.qtyTypeId = qtyTypeId;
	}
	public String getIsoLangCode() {
		return this.isoLangCode;
	}
	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefQuantityTypeLangPK)) {
			return false;
		}
		RefQuantityTypeLangPK castOther = (RefQuantityTypeLangPK)other;
		return 
			(this.qtyTypeId == castOther.qtyTypeId)
			&& this.isoLangCode.equals(castOther.isoLangCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.qtyTypeId ^ (this.qtyTypeId >>> 32)));
		hash = hash * prime + this.isoLangCode.hashCode();
		
		return hash;
	}
}