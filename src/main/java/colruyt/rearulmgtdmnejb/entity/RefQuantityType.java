package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="QTY_TYPE_LANG")
public class RefQuantityType implements Serializable {
private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RefQuantityTypeLangPK id;

	@Column(name="QTY_TYPE_NAME")
	private String quantityTypeName;

	@Column(name="DESCRIPTION")
	private String description;

	public RefQuantityType() {
	}

	public RefQuantityTypeLangPK getId() {
		return id;
	}

	public void setId(RefQuantityTypeLangPK id) {
		this.id = id;
	}

	public String getQuantityTypeName() {
		return quantityTypeName;
	}

	public void setQuantityTypeName(String quantityTypeName) {
		this.quantityTypeName = quantityTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
