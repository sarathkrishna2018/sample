package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import colruyt.rearulmgtdmnejb.enums.RuleType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReactingRuleBo extends GeneralRuleBo implements Serializable {
	
	public ReactingRuleBo(){
		this.setType(RuleType.REACTING.getRuleTypeName());
	}

	private static final long serialVersionUID = 1L;

	private Double reactingAmount;
	private Double reactingPercentage;
	private Double thresholdAmount;
	private Double thresholdPercentage;

	public Double getReactingAmount() {
		return reactingAmount;
	}

	public void setReactingAmount(Double reactingAmount) {
		this.reactingAmount = reactingAmount;
	}

	public Double getReactingPercentage() {
		return reactingPercentage;
	}

	public void setReactingPercentage(Double reactingPercentage) {
		this.reactingPercentage = reactingPercentage;
	}

	public Double getThresholdAmount() {
		return thresholdAmount;
	}

	public void setThresholdAmount(Double thresholdAmount) {
		this.thresholdAmount = thresholdAmount;
	}

	public Double getThresholdPercentage() {
		return thresholdPercentage;
	}

	public void setThresholdPercentage(Double thresholdPercentage) {
		this.thresholdPercentage = thresholdPercentage;
	}

}
