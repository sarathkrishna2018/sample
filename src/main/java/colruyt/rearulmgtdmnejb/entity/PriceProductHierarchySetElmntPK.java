package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;




/**
 * The primary key class for the REA_PPD_HCHYSET_ELMNT database table.
 * 
 */
@Embeddable
public class PriceProductHierarchySetElmntPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="PPD_HCHYSET_ID")
	private long prodHrchySetId;

	@Column(name="PPD_HCHY_ELMNT_ID")
	private long prodHrchyElemntId;

	public long getProdHrchySetId() {
		return this.prodHrchySetId;
	}
	public void setProdHrchySetId(long ppdHchysetId) {
		this.prodHrchySetId = ppdHchysetId;
	}
	public long getProdHrchyElemntId() {
		return this.prodHrchyElemntId;
	}
	public void setProdHrchyElemntId(long ppdHchyElmntId) {
		this.prodHrchyElemntId = ppdHchyElmntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PriceProductHierarchySetElmntPK)) {
			return false;
		}
		PriceProductHierarchySetElmntPK castOther = (PriceProductHierarchySetElmntPK)other;
		return 
			(this.prodHrchySetId == castOther.prodHrchySetId)
			&& (this.prodHrchyElemntId == castOther.prodHrchyElemntId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.prodHrchySetId ^ (this.prodHrchySetId >>> 32)));
		hash = hash * prime + ((int) (this.prodHrchyElemntId ^ (this.prodHrchyElemntId >>> 32)));
		
		return hash;
	}
}