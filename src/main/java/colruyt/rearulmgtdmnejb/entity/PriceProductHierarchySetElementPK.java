package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PriceProductHierarchySetElementPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PPD_HCHYSET_ID")
	private long productHierarchySetId;

	@Column(name = "PPD_HCHY_ELMNT_ID")
	private long productHierarchyElementId;

	public long getProductHierarchySetId() {
		return this.productHierarchySetId;
	}

	public void setProdicyHierarchySetId(long productHierarchySetId) {
		this.productHierarchySetId = productHierarchySetId;
	}

	public long getProductHierarchyElementId() {
		return this.productHierarchyElementId;
	}

	public void setProductHierarchyElementId(long productHierarchyElementId) {
		this.productHierarchyElementId = productHierarchyElementId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PriceProductHierarchySetElementPK)) {
			return false;
		}
		PriceProductHierarchySetElementPK castOther = (PriceProductHierarchySetElementPK) other;
		return (this.productHierarchySetId == castOther.productHierarchySetId)
				&& (this.productHierarchyElementId == castOther.productHierarchyElementId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productHierarchySetId ^ (this.productHierarchySetId >>> 32)));
		hash = hash * prime + ((int) (this.productHierarchyElementId ^ (this.productHierarchyElementId >>> 32)));

		return hash;
	}
}