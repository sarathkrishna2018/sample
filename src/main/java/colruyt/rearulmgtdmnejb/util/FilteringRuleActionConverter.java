package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import javax.ejb.EJB;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;

public class FilteringRuleActionConverter  implements Serializable {
	
	@EJB
	private ReferenceDataConverter referenceDataConverter;
	
	@EJB
	private ProductHrchyElmntConverter productHrchyElmntConverter;

	
	private static final long serialVersionUID = 1L;

	public FilteringRuleAction convert(FilteringRuleBo filteringRuleBo) {
		FilteringRuleAction reaFltRule = new FilteringRuleAction();
		reaFltRule.setMaxCompQty(filteringRuleBo.getMaxCompQuantity());
		reaFltRule.setXTimeQty(filteringRuleBo.getxTimeQuantity());
		return reaFltRule;
	}
	public FilteringRuleBo convertFilteringRuleAction(FilteringRuleAction filteringRuleAction){
		FilteringRuleBo filteringRuleBo=new FilteringRuleBo();
		filteringRuleBo.setMaxCompQuantity(filteringRuleAction.getMaxCompQty());
		filteringRuleBo.setxTimeQuantity(filteringRuleAction.getXTimeQty());
		return filteringRuleBo;
	}
		
	public FilteringRuleBo convertToBo(FilteringRuleAction filteringRule, FilteringRuleBo filteringRuleBo) {
		filteringRuleBo.setMaxCompQuantity(filteringRule.getMaxCompQty());
		filteringRuleBo.setxTimeQuantity(filteringRule.getXTimeQty());
		return filteringRuleBo;
	}

}
