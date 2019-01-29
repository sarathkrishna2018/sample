package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the REA_PPD_HCHYSET_ELMNT database table.
 * 
 */
@Entity
@Table(name="REA_PPD_HCHYSET_ELMNT")
public class PriceProductHierarchySetElmnt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PriceProductHierarchySetElmntPK id;

	@Column(name="LST_UPDATE_BY")
	private String lstUpdateBy;

	public PriceProductHierarchySetElmntPK getId() {
		return this.id;
	}

	public void setId(PriceProductHierarchySetElmntPK id) {
		this.id = id;
	}

	public String getLstUpdateBy() {
		return this.lstUpdateBy;
	}

	public void setLstUpdateBy(String lstUpdateBy) {
		this.lstUpdateBy = lstUpdateBy;
	}

}