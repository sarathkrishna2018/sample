package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * The persistent class for the REF_FLTOUT_TYPE_LANG database table.
 * 
 */
@Entity
@Table(name="FLTOUT_TYPE_LANG")
public class RefFilterOutRecordingTypeLang implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefFilterOutRecordingTypeLangPK id;

	private String description;

	@Column(name="FLTOUT_TYPE_NAME")
	private String fltoutTypeName;

	//bi-directional many-to-one association to RefFltoutType
	@ManyToOne
	@JoinColumn(name="FLTOUT_TYPE_ID")
	private RefFilterOutRecordingType refFltoutType;

	public RefFilterOutRecordingTypeLang() {
	}

	public RefFilterOutRecordingTypeLangPK getId() {
		return this.id;
	}

	public void setId(RefFilterOutRecordingTypeLangPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFltoutTypeName() {
		return this.fltoutTypeName;
	}

	public void setFltoutTypeName(String fltoutTypeName) {
		this.fltoutTypeName = fltoutTypeName;
	}

	public RefFilterOutRecordingType getRefFltoutType() {
		return this.refFltoutType;
	}

	public void setRefFltoutType(RefFilterOutRecordingType refFltoutType) {
		this.refFltoutType = refFltoutType;
	}

}