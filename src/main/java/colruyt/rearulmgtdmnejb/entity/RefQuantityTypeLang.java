package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the REF_QTY_TYPE_LANG database table.
 * 
 */
@Entity
@Table(name="QTY_TYPE_LANG")
public class RefQuantityTypeLang implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefQuantityTypeLangPK id;

	private String description;

	@Column(name="QTY_TYPE_NAME")
	private String qtyTypeName;

	//bi-directional many-to-one association to RefQtyType
	@ManyToOne
	@JoinColumn(name="QTY_TYPE_ID")
	private RefQuantityType refQtyType;

	public RefQuantityTypeLang() {
	}

	public RefQuantityTypeLangPK getId() {
		return this.id;
	}

	public void setId(RefQuantityTypeLangPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQtyTypeName() {
		return this.qtyTypeName;
	}

	public void setQtyTypeName(String qtyTypeName) {
		this.qtyTypeName = qtyTypeName;
	}

	public RefQuantityType getRefQtyType() {
		return this.refQtyType;
	}

	public void setRefQtyType(RefQuantityType refQtyType) {
		this.refQtyType = refQtyType;
	}

}