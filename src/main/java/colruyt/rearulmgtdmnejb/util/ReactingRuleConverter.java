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
		reactingAct.setReactingAmt(reactingRuleBo.getReactingAmount());
		reactingAct.setReactingPerc(reactingRuleBo.getReactingPercentage());
		reactingAct.setTholdAmt(reactingRuleBo.getThresholdAmount());
		reactingAct.setTholdPerc(reactingRuleBo.getThresholdPercentage());
		reactingAct.setCatchAllYn(reactingRuleBo.isCatchAll());
		return reactingAct;
	}

	public ReactingRuleBo convertToBo(ReactingRuleAction reactingRuleAction, ReactingRuleBo reactingBo) {
		reactingBo.setReactingAmount(reactingRuleAction.getReactingAmt());
		reactingBo.setReactingPercentage(reactingRuleAction.getReactingPerc());
		reactingBo.setThresholdAmount(reactingRuleAction.getTholdAmt());
		reactingBo.setThresholdPercentage(reactingRuleAction.getTholdPerc());
		reactingBo.setCatchAll(reactingRuleAction.getCatchAllYn());
		return reactingBo;
	}
}
