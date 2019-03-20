package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductGroupBo implements ProductHierarchyBO,Serializable {
	
	private static final long serialVersionUID=1L;
	private String productCategoryCode;
	private String productGroupCode;
	private List<LangHierarchyBo> productGroupLangs;
	private List<ProductSegmentBo> productSegments;

	public List<ProductSegmentBo> getProductSegments() {
		return productSegments;
	}

	public void setProductSegments(List<ProductSegmentBo> productSegments) {
		this.productSegments = productSegments;
	}

	public String getProductCategoryCode() {
		return productCategoryCode;
	}

	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}

	public String getProductGroupCode() {
		return productGroupCode;
	}

	public void setProductGroupCode(String productGroupCode) {
		this.productGroupCode = productGroupCode;
	}

	public List<LangHierarchyBo> getProductGroupLangs() {
		return productGroupLangs;
	}

	public void setProductGroupLangs(List<LangHierarchyBo> productGroupLangs) {
		this.productGroupLangs = productGroupLangs;
	}
}
