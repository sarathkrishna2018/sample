package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the REF_QTY_COND database table.
 * 
 */
@Entity
@Table(name="REF_QTY_COND")
public class RefQuantityCond implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QTY_COND_ID")
	private long qtyCondId;

	//bi-directional many-to-one association to RefQtyCondLang
	@OneToMany(mappedBy="refQtyCond")
	private List<RefQuantityCondLang> refQtyCondLangs;

	public RefQuantityCond() {
	}

	public long getQtyCondId() {
		return this.qtyCondId;
	}

	public void setQtyCondId(long qtyCondId) {
		this.qtyCondId = qtyCondId;
	}

	public List<RefQuantityCondLang> getRefQtyCondLangs() {
		return this.refQtyCondLangs;
	}

	public void setRefQtyCondLangs(List<RefQuantityCondLang> refQtyCondLangs) {
		this.refQtyCondLangs = refQtyCondLangs;
	}

	public RefQuantityCondLang addRefQtyCondLang(RefQuantityCondLang refQtyCondLang) {
		getRefQtyCondLangs().add(refQtyCondLang);
		refQtyCondLang.setRefQtyCond(this);

		return refQtyCondLang;
	}

	public RefQuantityCondLang removeRefQtyCondLang(RefQuantityCondLang refQtyCondLang) {
		getRefQtyCondLangs().remove(refQtyCondLang);
		refQtyCondLang.setRefQtyCond(null);

		return refQtyCondLang;
	}

}