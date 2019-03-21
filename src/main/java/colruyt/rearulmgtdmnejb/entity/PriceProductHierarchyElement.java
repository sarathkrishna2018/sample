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

@Entity
@Table(name = "REA_PPD_HCHY_ELMNT")
public class PriceProductHierarchyElement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "REA_PPD_HCHY_ELMNT_SEQ_GEN", sequenceName = "SEQ_REA_PPD_HCHY_ELMNT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REA_PPD_HCHY_ELMNT_SEQ_GEN")
	@Column(name = "PPD_HCHY_ELMNT_ID")
	private long productHierarchyElementId;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "PPD_HCHY_TYPE_ID")
	private long productHierarchyTypeId;

	@Column(name = "PPD_HCHY_VALUE")
	private String prodHrchyValue;

	// bi-directional many-to-one association to ReaPpdHchysetElmnt
	@OneToMany
	@JoinColumn(name = "PPD_HCHY_ELMNT_ID", referencedColumnName = "PPD_HCHY_ELMNT_ID")
	private List<PriceProductHierarchySetElement> prodHrchySetElement;

	public Long getProductHierarchyElementId() {
		return this.productHierarchyElementId;
	}

	public void setProductHierarchyElementId(Long productHierarchyElementId) {
		this.productHierarchyElementId = productHierarchyElementId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getProductHierarchyTypeId() {
		return this.productHierarchyTypeId;
	}

	public void setProductHierarchyTypeId(Long productHierarchyTypeId) {
		this.productHierarchyTypeId = productHierarchyTypeId;
	}

	public String getProdHrchyValue() {
		return this.prodHrchyValue;
	}

	public void setPpdHchyValue(String ppdHchyValue) {
		this.prodHrchyValue = ppdHchyValue;
	}

	public List<PriceProductHierarchySetElement> getProdHrchySetElement() {
		return this.prodHrchySetElement;
	}

	public void setProdHrchySetElement(List<PriceProductHierarchySetElement> reaPpdHchysetElmnts) {
		this.prodHrchySetElement = reaPpdHchysetElmnts;
	}

}