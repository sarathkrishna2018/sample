package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;

@Stateless
@LocalBean
public class FilteringRuleActionConverter  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FilteringRuleAction convertFromBo(FilteringRuleBo filteringRuleBo) {
		FilteringRuleAction filteringRuleAction = new FilteringRuleAction();
		filteringRuleAction.setReactionRuleId(filteringRuleBo.getRuleId());
		filteringRuleAction.setMaximumCompetitorQuantity(filteringRuleBo.getMaxCompQuantity());
		filteringRuleAction.setXTimeQty(filteringRuleBo.getxTimeQuantity());
		return filteringRuleAction;
	}
	public FilteringRuleBo convertToBo(FilteringRuleAction filteringRuleAction){
		FilteringRuleBo filteringRuleBo=new FilteringRuleBo();
		filteringRuleBo.setMaxCompQuantity(filteringRuleAction.getMaximumCompetitorQuantity());
		filteringRuleBo.setxTimeQuantity(filteringRuleAction.getXTimeQty());
		return filteringRuleBo;
	}
		
	public FilteringRuleBo convertToBo(FilteringRuleAction filteringRule, FilteringRuleBo filteringRuleBo) {
		filteringRuleBo.setMaxCompQuantity(filteringRule.getMaximumCompetitorQuantity());
		filteringRuleBo.setxTimeQuantity(filteringRule.getXTimeQty());
		return filteringRuleBo;
	}

}
