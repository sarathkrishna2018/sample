package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the REF_REASON_LANG database table.
 * 
 */
@Entity
@Table(name="REA_REASON_LANG")
public class RefReasonLang implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefReasonLangPK id;

	private String description;

	@Column(name="REASON_NAME")
	private String reasonName;

	//bi-directional many-to-one association to RefReason
	@ManyToOne
	@JoinColumn(name="REASON_ID")
	private RefReason refReason;

	public RefReasonLang() {
	}

	public RefReasonLangPK getId() {
		return this.id;
	}

	public void setId(RefReasonLangPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public RefReason getRefReason() {
		return this.refReason;
	}

	public void setRefReason(RefReason refReason) {
		this.refReason = refReason;
	}

}