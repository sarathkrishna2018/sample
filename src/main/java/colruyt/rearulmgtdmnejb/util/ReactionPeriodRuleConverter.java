package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.bo.ReactionPeriodRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;


public class ReactionPeriodRuleConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static ReactionPeriodRuleAction convertFromBo(ReactionPeriodRuleBo reactionPeriodRuleBo) {
		ReactionPeriodRuleAction reactionPeriodRuleAction = new ReactionPeriodRuleAction();
		reactionPeriodRuleAction.setReaRuleId(reactionPeriodRuleBo.getRuleId());
		reactionPeriodRuleAction.setEndDtDays(reactionPeriodRuleBo.getEndDateMinusDate());
		reactionPeriodRuleAction.setMinDays(reactionPeriodRuleBo.getMinimumDays());
		return reactionPeriodRuleAction;

	}

	public static ReactionPeriodRuleBo convertToBo(ReactionPeriodRuleAction reactionPeriodRuleAction,
			ReactionPeriodRuleBo reacPrdRuleBo) {
		reacPrdRuleBo.setEndDateMinusDate(reactionPeriodRuleAction.getEndDtDays());
		reacPrdRuleBo.setMinimumDays(reactionPeriodRuleAction.getMinDays());
		return reacPrdRuleBo;
	}
}
