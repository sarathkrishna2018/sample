package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;

public class FilteringRuleActionConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static FilteringRuleAction convertFromBo(FilteringRuleBo filteringRuleBo) {
		FilteringRuleAction filteringRuleAction = new FilteringRuleAction();
		filteringRuleAction.setReactionRuleId(filteringRuleBo.getRuleId());
		filteringRuleAction.setMaxTimesRecordingProduct(filteringRuleBo.getMaxTimesRecordingProduct());
		filteringRuleAction.setMaxTimesPriceArticle(filteringRuleBo.getMaxTimesPriceArticle());
		return filteringRuleAction;
	}

	public static FilteringRuleBo convertToBo(FilteringRuleAction filteringRuleAction) {
		FilteringRuleBo filteringRuleBo = new FilteringRuleBo();
		filteringRuleBo.setMaxTimesRecordingProduct(filteringRuleAction.getMaxTimesRecordingProduct());
		filteringRuleBo.setMaxTimesPriceArticle(filteringRuleAction.getMaxTimesPriceArticle());
		return filteringRuleBo;
	}

	public static FilteringRuleBo convertToBo(FilteringRuleAction filteringRule, FilteringRuleBo filteringRuleBo) {
		filteringRuleBo.setMaxTimesRecordingProduct(filteringRule.getMaxTimesRecordingProduct());
		filteringRuleBo.setMaxTimesPriceArticle(filteringRule.getMaxTimesPriceArticle());
		return filteringRuleBo;
	}

}
