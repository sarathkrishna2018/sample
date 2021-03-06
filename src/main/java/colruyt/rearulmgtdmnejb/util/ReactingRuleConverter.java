package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;

public class ReactingRuleConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static ReactingRuleAction convertFromBo(ReactingRuleBo reactingRuleBo) {
		ReactingRuleAction reactingAct = new ReactingRuleAction();
		reactingAct.setReactionRuleId(reactingRuleBo.getRuleId());
		reactingAct.setReactingAmount(reactingRuleBo.getReactingAmount());
		reactingAct.setReactingPercentage(reactingRuleBo.getReactingPercentage());
		reactingAct.setThresholdAmount(reactingRuleBo.getThresholdAmount());
		reactingAct.setThresholdPercentage(reactingRuleBo.getThresholdPercentage());
		reactingAct.setCatchAll(reactingRuleBo.isCatchAll());
		return reactingAct;
	}

	public static ReactingRuleBo convertToBo(ReactingRuleAction reactingRuleAction, ReactingRuleBo reactingBo) {
		reactingBo.setReactingAmount(reactingRuleAction.getReactingAmount());
		reactingBo.setReactingPercentage(reactingRuleAction.getReactingPercentage());
		reactingBo.setThresholdAmount(reactingRuleAction.getThresholdAmount());
		reactingBo.setThresholdPercentage(reactingRuleAction.getThresholdPercentage());
		reactingBo.setCatchAll(reactingRuleAction.getCatchAll());
		return reactingBo;
	}
}
