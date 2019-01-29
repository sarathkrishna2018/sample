package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import org.apache.openjpa.persistence.ExternalValues;
import org.apache.openjpa.persistence.Type;
import javax.persistence.JoinTable;


/**
 * The persistent class for the REA_PPD_HCHYSET database table.
 * 
 */
@Entity
@Table(name="REA_PPD_HCHYSET")
public class PriceProductHierarchySet implements Serializable {
	public List<PriceProductHierarchyElement> getPriceProductHierarchyElements() {
		return priceProductHierarchyElements;
	}

	public void setPriceProductHierarchyElements(List<PriceProductHierarchyElement> priceProductHierarchyElements) {
		this.priceProductHierarchyElements = priceProductHierarchyElements;
	}

	public void setPpdHchysetId(long ppdHchysetId) {
		this.ppdHchysetId = ppdHchysetId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REA_PPD_HCHYSET_SEQ_GEN", sequenceName="SEQ_REA_PPD_HCHYSET")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REA_PPD_HCHYSET_SEQ_GEN")
	@Column(name="PPD_HCHYSET_ID")
	private long ppdHchysetId;
	
	@Column(name="REA_RULE_ID")
	private long reaRuleId;

	@Column(name="ASSORTMENT_NAME")
	private String assortmentName;

	@Column(name="CHEAP_BRAND_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean cheapBrandYn;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="LST_UPDATE_BY")
	private String lstUpdateBy;

	@Column(name="NAT_BRAND_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean natBrandYn;

	@Column(name="OWN_BRAND_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean ownBrandYn;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = false)
	@JoinTable(name = "REA_PPD_HCHYSET_ELMNT", joinColumns = @JoinColumn(name = "PPD_HCHYSET_ID"), inverseJoinColumns = @JoinColumn(name = "PPD_HCHY_ELMNT_ID"))
	private List<PriceProductHierarchyElement> priceProductHierarchyElements;
	
	public Long getPpdHchysetId() {
		return this.ppdHchysetId;
	}

	public void setPpdHchysetId(Long ppdHchysetId) {
		this.ppdHchysetId = ppdHchysetId;
	}

	public String getAssortmentName() {
		return this.assortmentName;
	}

	public void setAssortmentName(String assortmentName) {
		this.assortmentName = assortmentName;
	}

	public boolean getCheapBrandYn() {
		return this.cheapBrandYn;
	}

	public void setCheapBrandYn(boolean cheapBrandYn) {
		this.cheapBrandYn = cheapBrandYn;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLstUpdateBy() {
		return this.lstUpdateBy;
	}

	public void setLstUpdateBy(String lstUpdateBy) {
		this.lstUpdateBy = lstUpdateBy;
	}

	public boolean getNatBrandYn() {
		return this.natBrandYn;
	}

	public void setNatBrandYn(boolean natBrandYn) {
		this.natBrandYn = natBrandYn;
	}

	public boolean getOwnBrandYn() {
		return this.ownBrandYn;
	}

	public void setOwnBrandYn(boolean ownBrandYn) {
		this.ownBrandYn = ownBrandYn;
	}
	
	public Long getReaRuleId() {
		return reaRuleId;
	}

	public void setReaRuleId(Long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	
	
}