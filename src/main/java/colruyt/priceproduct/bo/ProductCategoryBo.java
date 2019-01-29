package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategoryBo implements ProductHierarchyBO,Serializable {
	
	private static final long serialVersionUID=1L;
	private String mainCategoryCode;
	private String productCategoryCode;
	private List<LangHirerachyBo> productCategoryLangs;
	private List<ProductGroupBo> productGroups;

	public List<ProductGroupBo> getProductGroups() {
		return productGroups;
	}

	public void setProductGroups(List<ProductGroupBo> productGroups) {
		this.productGroups = productGroups;
	}

	public String getMainCategoryCode() {
		return mainCategoryCode;
	}

	public void setMainCategoryCode(String mainCategoryCode) {
		this.mainCategoryCode = mainCategoryCode;
	}

	public String getProductCategoryCode() {
		return productCategoryCode;
	}

	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}

	public List<LangHirerachyBo> getProductCategoryLangs() {
		return productCategoryLangs;
	}

	public void setProductCategoryLangs(List<LangHirerachyBo> productCategoryLangs) {
		this.productCategoryLangs = productCategoryLangs;
	}
}
