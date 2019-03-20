package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FLTOUT_TYPE_LANG")
public class RefFilterOutRecordingType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefFilterOutRecordingTypeLangPK id;

	@Column(name = "FLTOUT_TYPE_NAME")
	private String filterOutTypeName;

	@Column(name = "DESCRIPTION")
	private String description;

	public RefFilterOutRecordingType() {
	}

	public RefFilterOutRecordingTypeLangPK getId() {
		return this.id;
	}

	public void setId(RefFilterOutRecordingTypeLangPK id) {
		this.id = id;
	}

	public String getFilterOutTypeName() {
		return filterOutTypeName;
	}

	public void setFilterOutTypeName(String filterOutTypeName) {
		this.filterOutTypeName = filterOutTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
