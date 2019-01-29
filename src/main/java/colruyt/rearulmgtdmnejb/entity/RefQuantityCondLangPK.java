package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REF_QTY_COND_LANG database table.
 * 
 */
@Embeddable
public class RefQuantityCondLangPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ISO_LANG_CODE")
	private String isoLangCode;

	@Column(name="QTY_COND_ID")
	private long qtyCondId;

	public RefQuantityCondLangPK() {
	}
	public String getIsoLangCode() {
		return this.isoLangCode;
	}
	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}
	public long getQtyCondId() {
		return this.qtyCondId;
	}
	public void setQtyCondId(long qtyCondId) {
		this.qtyCondId = qtyCondId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefQuantityCondLangPK)) {
			return false;
		}
		RefQuantityCondLangPK castOther = (RefQuantityCondLangPK)other;
		return 
			this.isoLangCode.equals(castOther.isoLangCode)
			&& (this.qtyCondId == castOther.qtyCondId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.isoLangCode.hashCode();
		hash = hash * prime + ((int) (this.qtyCondId ^ (this.qtyCondId >>> 32)));
		
		return hash;
	}
}