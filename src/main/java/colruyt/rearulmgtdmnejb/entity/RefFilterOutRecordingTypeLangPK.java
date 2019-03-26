package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RefFilterOutRecordingTypeLangPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "FLTOUT_TYPE_ID")
	private int filterOutTypeId;

	@Column(name = "ISO_LANG_CODE")
	private String isoLangCode;

	public RefFilterOutRecordingTypeLangPK() {
	}

	public int getFilterOutTypeId() {
		return this.filterOutTypeId;
	}

	public void setFilterOutTypeId(int fltoutTypeId) {
		this.filterOutTypeId = fltoutTypeId;
	}

	public String getIsoLangCode() {
		return this.isoLangCode;
	}

	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + filterOutTypeId;
		result = prime * result + ((isoLangCode == null) ? 0 : isoLangCode.hashCode());
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
		RefFilterOutRecordingTypeLangPK other = (RefFilterOutRecordingTypeLangPK) obj;
		if (filterOutTypeId != other.filterOutTypeId) {
			return false;
		}
		if (isoLangCode == null) {
			if (other.isoLangCode != null) {
				return false;
			}
		} else if (!isoLangCode.equals(other.isoLangCode)) {
			return false;
		}
		return true;
	}

}
