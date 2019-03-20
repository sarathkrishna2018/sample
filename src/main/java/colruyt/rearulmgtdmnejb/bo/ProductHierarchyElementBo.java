package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductHierarchyElementBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private long priceProductHierarchyTypeId;
	private String priceProductHierarchyValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getPriceProductHierarchyTypeId() {
		return priceProductHierarchyTypeId;
	}

	public void setPriceProductHierarchyTypeId(long priceProductHierarchyTypeId) {
		this.priceProductHierarchyTypeId = priceProductHierarchyTypeId;
	}

	public String getPriceProductHierarchyValue() {
		return priceProductHierarchyValue;
	}

	public void setPriceProductHierarchyValue(String priceProductHierarchyValue) {
		this.priceProductHierarchyValue = priceProductHierarchyValue;
	}

}