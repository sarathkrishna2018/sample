package colruyt.rearulmgtdmnejb.bo;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @version 1.0
 * @created 28-nov-2018 9:01:37
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuantityRuleBo extends GeneralRuleBo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private RefQuantityConditionTypeBo conditionType;
	private RefQuantityPriceTypeBo quantityPriceType;

	public RefQuantityConditionTypeBo getConditionType() {
		return conditionType;
	}

	public void setConditionType(RefQuantityConditionTypeBo conditionType) {
		this.conditionType = conditionType;
	}

	public RefQuantityPriceTypeBo getQuantityPriceType() {
		return quantityPriceType;
	}

	public void setQuantityPriceType(RefQuantityPriceTypeBo quantityPriceType) {
		this.quantityPriceType = quantityPriceType;
	}

}