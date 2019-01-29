package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * The persistent class for the REF_QTY_COND_LANG database table.
 * 
 */
@Entity
@Table(name="QTY_COND_LANG")
public class RefQuantityCondLang implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefQuantityCondLangPK id;

	private String description;

	@Column(name="QTY_COND_NAME")
	private String qtyCondName;

	//bi-directional many-to-one association to RefQtyCond
	@ManyToOne
	@JoinColumn(name="QTY_COND_ID")
	private RefQuantityCond refQtyCond;

	public RefQuantityCondLang() {
	}

	public RefQuantityCondLangPK getId() {
		return this.id;
	}

	public void setId(RefQuantityCondLangPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQtyCondName() {
		return this.qtyCondName;
	}

	public void setQtyCondName(String qtyCondName) {
		this.qtyCondName = qtyCondName;
	}

	public RefQuantityCond getRefQtyCond() {
		return this.refQtyCond;
	}

	public void setRefQtyCond(RefQuantityCond refQtyCond) {
		this.refQtyCond = refQtyCond;
	}

}