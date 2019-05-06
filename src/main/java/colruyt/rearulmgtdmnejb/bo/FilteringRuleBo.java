package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import colruyt.rearulmgtdmnejb.enums.RuleType;

@JsonIgnoreType(value = true)
public class FilteringRuleBo extends GeneralRuleBo implements Serializable {
	
	public FilteringRuleBo(){
		this.setType(RuleType.FILTERING.getRuleTypeName());
	}

	private static final long serialVersionUID = 1L;
	private Double maxCompQuantity;
	private Double xTimeQuantity;

	public Double getMaxCompQuantity() {
		return maxCompQuantity;
	}

	public void setMaxCompQuantity(Double maxCompQuantity) {
		this.maxCompQuantity = maxCompQuantity;
	}

	public Double getxTimeQuantity() {
		return xTimeQuantity;
	}

	public void setxTimeQuantity(Double xTimeQuantity) {
		this.xTimeQuantity = xTimeQuantity;
	}

}