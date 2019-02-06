package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.ReactionPeriodRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;

public class ReactionPeriodRuleConverter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ReactionPeriodRuleAction convert(ReactionPeriodRuleBo reactionPeriodRuleBo){
		ReactionPeriodRuleAction reaPrdAct=new ReactionPeriodRuleAction();
		reaPrdAct.setEndDtDays(reactionPeriodRuleBo.getEndDateMinusDate());
		reaPrdAct.setMinDays(reactionPeriodRuleBo.getMinimumDays());
		return reaPrdAct;
		
	}
	public ReactionPeriodRuleBo convertToBo(ReactionPeriodRuleAction reactionPeriodRuleAction,
			ReactionPeriodRuleBo reacPrdRuleBo) {
		reacPrdRuleBo.setEndDateMinusDate(reactionPeriodRuleAction.getEndDtDays());
		reacPrdRuleBo.setMinimumDays(reactionPeriodRuleAction.getMinDays());
		return reacPrdRuleBo;
	}
}
