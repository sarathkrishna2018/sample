package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the REF_REASON database table.
 * 
 */
@Entity
@Table(name="REF_REA_REASON")
public class RefReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REASON_ID")
	private long reasonId;

	//bi-directional many-to-one association to RefReasonLang
	@OneToMany(mappedBy="refReason")
	private List<RefReasonLang> refReasonLangs;

	public RefReason() {
	}

	public long getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(long reasonId) {
		this.reasonId = reasonId;
	}

	public List<RefReasonLang> getRefReasonLangs() {
		return this.refReasonLangs;
	}

	public void setRefReasonLangs(List<RefReasonLang> refReasonLangs) {
		this.refReasonLangs = refReasonLangs;
	}

	public RefReasonLang addRefReasonLang(RefReasonLang refReasonLang) {
		getRefReasonLangs().add(refReasonLang);
		refReasonLang.setRefReason(this);

		return refReasonLang;
	}

	public RefReasonLang removeRefReasonLang(RefReasonLang refReasonLang) {
		getRefReasonLangs().remove(refReasonLang);
		refReasonLang.setRefReason(null);

		return refReasonLang;
	}

}