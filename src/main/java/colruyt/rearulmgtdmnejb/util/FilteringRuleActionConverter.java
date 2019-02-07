package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;


public class FilteringRuleActionConverter  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FilteringRuleAction convert(FilteringRuleBo filteringRuleBo) {
		FilteringRuleAction filteringRuleAction = new FilteringRuleAction();
		filteringRuleAction.setReaRuleId(filteringRuleBo.getRuleId());
		filteringRuleAction.setMaxCompQty(filteringRuleBo.getMaxCompQuantity());
		filteringRuleAction.setXTimeQty(filteringRuleBo.getxTimeQuantity());
		return filteringRuleAction;
	}
	public FilteringRuleBo convertFilteringRuleAction(FilteringRuleAction filteringRuleAction){
		FilteringRuleBo filteringRuleBo=new FilteringRuleBo();
		filteringRuleBo.setMaxCompQuantity(filteringRuleAction.getMaxCompQty());
		filteringRuleBo.setxTimeQuantity(filteringRuleAction.getXTimeQty());
		return filteringRuleBo;
	}
		
	public FilteringRuleBo addFilteringRuleAction(FilteringRuleAction filteringRule, FilteringRuleBo filteringRuleBo) {
		filteringRuleBo.setMaxCompQuantity(filteringRule.getMaxCompQty());
		filteringRuleBo.setxTimeQuantity(filteringRule.getXTimeQty());
		return filteringRuleBo;
	}

}
