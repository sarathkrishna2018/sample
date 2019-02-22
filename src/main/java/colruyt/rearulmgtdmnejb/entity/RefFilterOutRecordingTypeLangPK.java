package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The primary key class for the REF_FLTOUT_TYPE_LANG database table.
 * 
 */
@Embeddable
public class RefFilterOutRecordingTypeLangPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FLTOUT_TYPE_ID")
	private long fltoutTypeId;

	@Column(name="ISO_LANG_CODE")
	private String isoLangCode;

	public RefFilterOutRecordingTypeLangPK() {
	}
	public long getFltoutTypeId() {
		return this.fltoutTypeId;
	}
	public void setFltoutTypeId(long fltoutTypeId) {
		this.fltoutTypeId = fltoutTypeId;
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
		if (!(other instanceof RefFilterOutRecordingTypeLangPK)) {
			return false;
		}
		RefFilterOutRecordingTypeLangPK castOther = (RefFilterOutRecordingTypeLangPK)other;
		return 
			(this.fltoutTypeId == castOther.fltoutTypeId)
			&& this.isoLangCode.equals(castOther.isoLangCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.fltoutTypeId ^ (this.fltoutTypeId >>> 32)));
		hash = hash * prime + this.isoLangCode.hashCode();
		
		return hash;
	}
}
