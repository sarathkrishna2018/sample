package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REA_PPD_HCHYSET_ELMNT")
public class PriceProductHierarchySetElement implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PriceProductHierarchySetElementPK id;

	@Column(name = "LST_UPDATE_BY")
	private String lastUpdateBy;

	public PriceProductHierarchySetElementPK getId() {
		return this.id;
	}

	public void setId(PriceProductHierarchySetElementPK id) {
		this.id = id;
	}

	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(String lstUpdateBy) {
		this.lastUpdateBy = lstUpdateBy;
	}

}