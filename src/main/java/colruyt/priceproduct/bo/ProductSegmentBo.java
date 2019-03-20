package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSegmentBo implements ProductHierarchyBO,Serializable {
	
	private static final long serialVersionUID=1L;
	private String productSegmentCode;
	private List<LangHierarchyBo> productSegmentLangs;
	private String productGroupCode;
	private List<PriceProductHierarchyBo> productList;
 
	public String getProductGroupCode() {
		return productGroupCode;
	}

	public void setProductGroupCode(String productGroupCode) {
		this.productGroupCode = productGroupCode;
	}

	public String getProductSegmentCode() {
		return productSegmentCode;
	}

	public void setProductSegmentCode(String productSegmentCode) {
		this.productSegmentCode = productSegmentCode;
	}

	public List<LangHierarchyBo> getProductSegmentLangs() {
		return productSegmentLangs;
	}

	public void setProductSegmentLangs(List<LangHierarchyBo> productSegmentLangs) {
		this.productSegmentLangs = productSegmentLangs;
	}

	public List<PriceProductHierarchyBo> getProductList() {
		return productList;
	}

	public void setProductList(List<PriceProductHierarchyBo> productList) {
		this.productList = productList;
	}
	
	


}
