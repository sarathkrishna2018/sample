package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "QTY_COND_LANG")
public class RefQuantityConditionType implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private RefQuantityConditionTypeLangPK id;
	@Column(name = "QTY_COND_NAME")
	private String quantityConditionName;

	@Column(name = "DESCRIPTION")
	private String description;

	public RefQuantityConditionType() {

	}

	public RefQuantityConditionTypeLangPK getId() {
		return this.id;
	}

	public void setId(RefQuantityConditionTypeLangPK id) {
		this.id = id;
	}

	public String getQuantityConditionName() {
		return quantityConditionName;
	}

	public void setQuantityConditionName(String quantityConditionName) {
		this.quantityConditionName = quantityConditionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
