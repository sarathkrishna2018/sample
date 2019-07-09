package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import colruyt.rearulmgtdmnejb.enums.RuleType;

@JsonIgnoreType(value = true)
public class FilteringRuleBo extends GeneralRuleBo implements Serializable {

	public FilteringRuleBo() {
		this.setType(RuleType.FILTERING.getRuleTypeName());
	}

	private static final long serialVersionUID = 1L;
	private Double maxTimesRecordingProduct;
	private Double maxTimesPriceArticle;

	public Double getMaxTimesRecordingProduct() {
		return maxTimesRecordingProduct;
	}

	public void setMaxTimesRecordingProduct(Double maxTimesRecordingProduct) {
		this.maxTimesRecordingProduct = maxTimesRecordingProduct;
	}

	public Double getMaxTimesPriceArticle() {
		return maxTimesPriceArticle;
	}

	public void setMaxTimesPriceArticle(Double maxTimesPriceArticle) {
		this.maxTimesPriceArticle = maxTimesPriceArticle;
	}

}