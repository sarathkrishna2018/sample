package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreType;



/**
 * @version 1.0
 * @created 28-nov-2018 8:16:32
 */
@JsonIgnoreType(value = true)
public class FilteringRuleBo extends GeneralRuleBo implements Serializable{

	
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