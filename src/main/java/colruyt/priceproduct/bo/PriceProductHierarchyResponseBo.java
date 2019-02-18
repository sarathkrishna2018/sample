package colruyt.priceproduct.bo;

import java.util.List;

public class PriceProductHierarchyResponseBo {

	private List<MainCategoryBo> result;
	private String code;
	
	public List<MainCategoryBo> getResult() {
		return result;
	}
	public void setResult(List<MainCategoryBo> result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
