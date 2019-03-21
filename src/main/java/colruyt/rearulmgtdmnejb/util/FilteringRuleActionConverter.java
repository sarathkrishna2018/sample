package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;


public class FilteringRuleActionConverter  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static FilteringRuleAction convertFromBo(FilteringRuleBo filteringRuleBo) {
		FilteringRuleAction filteringRuleAction = new FilteringRuleAction();
		filteringRuleAction.setReactionRuleId(filteringRuleBo.getRuleId());
		filteringRuleAction.setMaximumCompetitorQuantity(filteringRuleBo.getMaxCompQuantity());
		filteringRuleAction.setXTimeQty(filteringRuleBo.getxTimeQuantity());
		return filteringRuleAction;
	}
	public static FilteringRuleBo convertToBo(FilteringRuleAction filteringRuleAction){
		FilteringRuleBo filteringRuleBo=new FilteringRuleBo();
		filteringRuleBo.setMaxCompQuantity(filteringRuleAction.getMaximumCompetitorQuantity());
		filteringRuleBo.setxTimeQuantity(filteringRuleAction.getXTimeQty());
		return filteringRuleBo;
	}
		
	public static FilteringRuleBo convertToBo(FilteringRuleAction filteringRule, FilteringRuleBo filteringRuleBo) {
		filteringRuleBo.setMaxCompQuantity(filteringRule.getMaximumCompetitorQuantity());
		filteringRuleBo.setxTimeQuantity(filteringRule.getXTimeQty());
		return filteringRuleBo;
	}

}
