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
	private long ppdHchysetId;

	@Column(name="PPD_HCHY_ELMNT_ID")
	private long ppdHchyElmntId;

	public long getPpdHchysetId() {
		return this.ppdHchysetId;
	}
	public void setPpdHchysetId(long ppdHchysetId) {
		this.ppdHchysetId = ppdHchysetId;
	}
	public long getPpdHchyElmntId() {
		return this.ppdHchyElmntId;
	}
	public void setPpdHchyElmntId(long ppdHchyElmntId) {
		this.ppdHchyElmntId = ppdHchyElmntId;
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
			(this.ppdHchysetId == castOther.ppdHchysetId)
			&& (this.ppdHchyElmntId == castOther.ppdHchyElmntId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ppdHchysetId ^ (this.ppdHchysetId >>> 32)));
		hash = hash * prime + ((int) (this.ppdHchyElmntId ^ (this.ppdHchyElmntId >>> 32)));
		
		return hash;
	}
}