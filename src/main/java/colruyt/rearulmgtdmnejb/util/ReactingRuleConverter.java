package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;

@Stateless
@LocalBean
public class ReactingRuleConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReactingRuleAction convert(ReactingRuleBo reactingRuleBo) {
		ReactingRuleAction reactingAct = new ReactingRuleAction();
		reactingAct.setReaRuleId(reactingRuleBo.getRuleId());
		reactingAct.setReactingAmt(reactingRuleBo.getReactingAmount());
		reactingAct.setReactingPercentage(reactingRuleBo.getReactingPercentage());
		reactingAct.setThresholdAmount(reactingRuleBo.getThresholdAmount());
		reactingAct.setThresholdPercentage(reactingRuleBo.getThresholdPercentage());
		reactingAct.setCatchAll(reactingRuleBo.isCatchAll());
		return reactingAct;
	}

	public ReactingRuleBo addingReactionRuleAction(ReactingRuleAction reactingRuleAction, ReactingRuleBo reactingBo) {
		reactingBo.setReactingAmount(reactingRuleAction.getReactingAmt());
		reactingBo.setReactingPercentage(reactingRuleAction.getReactingPercentage());
		reactingBo.setThresholdAmount(reactingRuleAction.getThresholdAmount());
		reactingBo.setThresholdPercentage(reactingRuleAction.getThresholdPercentage());
		reactingBo.setCatchAll(reactingRuleAction.getCatchAll());
		return reactingBo;
	}
}
