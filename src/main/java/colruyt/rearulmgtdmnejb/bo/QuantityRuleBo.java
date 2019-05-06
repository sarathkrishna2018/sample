package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import colruyt.rearulmgtdmnejb.enums.RuleType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuantityRuleBo extends GeneralRuleBo implements Serializable {
	
	public QuantityRuleBo(){
		this.setType(RuleType.QUANTITY.getRuleTypeName());
	}

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