package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the REF_QTY_TYPE database table.
 * 
 */
@Entity
@Table(name="REF_QTY_TYPE")
public class RefQuantityType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QTY_TYPE_ID")
	private long qtyTypeId;

	//bi-directional many-to-one association to RefQtyTypeLang
	@OneToMany(mappedBy="refQtyType")
	private List<RefQuantityTypeLang> refQtyTypeLangs;

	public RefQuantityType() {
	}

	public long getQtyTypeId() {
		return this.qtyTypeId;
	}

	public void setQtyTypeId(long qtyTypeId) {
		this.qtyTypeId = qtyTypeId;
	}

	public List<RefQuantityTypeLang> getRefQtyTypeLangs() {
		return this.refQtyTypeLangs;
	}

	public void setRefQtyTypeLangs(List<RefQuantityTypeLang> refQtyTypeLangs) {
		this.refQtyTypeLangs = refQtyTypeLangs;
	}

	public RefQuantityTypeLang addRefQtyTypeLang(RefQuantityTypeLang refQtyTypeLang) {
		getRefQtyTypeLangs().add(refQtyTypeLang);
		refQtyTypeLang.setRefQtyType(this);

		return refQtyTypeLang;
	}

	public RefQuantityTypeLang removeRefQtyTypeLang(RefQuantityTypeLang refQtyTypeLang) {
		getRefQtyTypeLangs().remove(refQtyTypeLang);
		refQtyTypeLang.setRefQtyType(null);

		return refQtyTypeLang;
	}

}