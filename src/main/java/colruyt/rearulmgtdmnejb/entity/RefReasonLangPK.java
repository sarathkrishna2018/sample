package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REF_REASON_LANG database table.
 * 
 */
@Embeddable
public class RefReasonLangPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REASON_ID")
	private long reasonId;

	@Column(name="ISO_LANG_CODE")
	private String isoLangCode;

	public RefReasonLangPK() {
	}
	public long getReasonId() {
		return this.reasonId;
	}
	public void setReasonId(long reasonId) {
		this.reasonId = reasonId;
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
		if (!(other instanceof RefReasonLangPK)) {
			return false;
		}
		RefReasonLangPK castOther = (RefReasonLangPK)other;
		return 
			(this.reasonId == castOther.reasonId)
			&& this.isoLangCode.equals(castOther.isoLangCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.reasonId ^ (this.reasonId >>> 32)));
		hash = hash * prime + this.isoLangCode.hashCode();
		
		return hash;
	}
}