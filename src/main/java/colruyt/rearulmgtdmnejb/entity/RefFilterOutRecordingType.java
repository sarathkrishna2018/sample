package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the REF_FLTOUT_TYPE database table.
 * 
 */
@Entity
@Table(name="REF_FLTOUT_TYPE")
public class RefFilterOutRecordingType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FLTOUT_TYPE_ID")
	private long fltoutTypeId;

	//bi-directional many-to-one association to RefFltoutTypeLang
	@OneToMany(mappedBy="refFltoutType")
	private List<RefFilterOutRecordingTypeLang> refFltoutTypeLangs;

	public RefFilterOutRecordingType() {
	}

	public long getFltoutTypeId() {
		return this.fltoutTypeId;
	}

	public void setFltoutTypeId(long fltoutTypeId) {
		this.fltoutTypeId = fltoutTypeId;
	}

	public List<RefFilterOutRecordingTypeLang> getRefFltoutTypeLangs() {
		return this.refFltoutTypeLangs;
	}

	public void setRefFltoutTypeLangs(List<RefFilterOutRecordingTypeLang> refFltoutTypeLangs) {
		this.refFltoutTypeLangs = refFltoutTypeLangs;
	}

	public RefFilterOutRecordingTypeLang addRefFltoutTypeLang(RefFilterOutRecordingTypeLang refFltoutTypeLang) {
		getRefFltoutTypeLangs().add(refFltoutTypeLang);
		refFltoutTypeLang.setRefFltoutType(this);

		return refFltoutTypeLang;
	}

	public RefFilterOutRecordingTypeLang removeRefFltoutTypeLang(RefFilterOutRecordingTypeLang refFltoutTypeLang) {
		getRefFltoutTypeLangs().remove(refFltoutTypeLang);
		refFltoutTypeLang.setRefFltoutType(null);

		return refFltoutTypeLang;
	}

}