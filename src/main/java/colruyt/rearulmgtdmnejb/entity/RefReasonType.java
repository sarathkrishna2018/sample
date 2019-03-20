package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REA_REASON_LANG")
public class RefReasonType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefReasonTypeLangPK id;

	@Column(name = "REASON_NAME")
	private String reasonName;

	@Column(name = "DESCRIPTION")
	private String description;

	public RefReasonType() {
	}

	public RefReasonTypeLangPK getId() {
		return id;
	}

	public void setId(RefReasonTypeLangPK id) {
		this.id = id;
	}

	public String getReasonName() {
		return reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
