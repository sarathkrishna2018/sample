package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

public class RefBrandLayerBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String brandLayerId;
	private List<LangPriceBO> brandLayerLangs;

	public String getBrandLayerId() {
		return brandLayerId;
	}

	public void setBrandLayerId(String brandLayerId) {
		this.brandLayerId = brandLayerId;
	}

	public List<LangPriceBO> getBrandLayerLangs() {
		return brandLayerLangs;
	}

	public void setBrandLayerLangs(List<LangPriceBO> brandLayerLangs) {
		this.brandLayerLangs = brandLayerLangs;
	}
}
