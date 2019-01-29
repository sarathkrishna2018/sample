package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the REA_PPD_HCHY_ELMNT database table.
 * 
 */
@Entity
@Table(name="REA_PPD_HCHY_ELMNT")
public class PriceProductHierarchyElement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REA_PPD_HCHY_ELMNT_SEQ_GEN", sequenceName="SEQ_REA_PPD_HCHY_ELMNT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REA_PPD_HCHY_ELMNT_SEQ_GEN")
	@Column(name="PPD_HCHY_ELMNT_ID")
	private long ppdHchyElmntId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="PPD_HCHY_TYPE_ID")
	private long ppdHchyTypeId;

	@Column(name="PPD_HCHY_VALUE")
	private String ppdHchyValue;

	//bi-directional many-to-one association to ReaPpdHchysetElmnt
	@OneToMany
	@JoinColumn(name = "PPD_HCHY_ELMNT_ID",referencedColumnName = "PPD_HCHY_ELMNT_ID")
	private List<PriceProductHierarchySetElmnt> reaPpdHchysetElmnts;
	
	public Long getPpdHchyElmntId() {
		return this.ppdHchyElmntId;
	}

	public void setPpdHchyElmntId(Long ppdHchyElmntId) {
		this.ppdHchyElmntId = ppdHchyElmntId;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getPpdHchyTypeId() {
		return this.ppdHchyTypeId;
	}

	public void setPpdHchyTypeId(Long ppdHchyTypeId) {
		this.ppdHchyTypeId = ppdHchyTypeId;
	}

	public String getPpdHchyValue() {
		return this.ppdHchyValue;
	}

	public void setPpdHchyValue(String ppdHchyValue) {
		this.ppdHchyValue = ppdHchyValue;
	}

	public List<PriceProductHierarchySetElmnt> getReaPpdHchysetElmnts() {
		return this.reaPpdHchysetElmnts;
	}

	public void setReaPpdHchysetElmnts(List<PriceProductHierarchySetElmnt> reaPpdHchysetElmnts) {
		this.reaPpdHchysetElmnts = reaPpdHchysetElmnts;
	}

}