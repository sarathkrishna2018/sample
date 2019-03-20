package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainCategoryBo implements ProductHierarchyBO,Serializable {
	
	private static final long serialVersionUID=1L;
	private String mainCategoryCode;
	private List<LangHierarchyBo> mainCategoryLangs;
	private List<ProductCategoryBo> productCategories;

	public List<ProductCategoryBo> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategoryBo> productCategories) {
		this.productCategories = productCategories;
	}

	public String getMainCategoryCode() {
		return mainCategoryCode;
	}

	public void setMainCategoryCode(String mainCategoryCode) {
		this.mainCategoryCode = mainCategoryCode;
	}

	public List<LangHierarchyBo> getMainCategoryLangs() {
		return mainCategoryLangs;
	}

	public void setMainCategoryLangs(List<LangHierarchyBo> mainCategoryLangs) {
		this.mainCategoryLangs = mainCategoryLangs;
	}

}
