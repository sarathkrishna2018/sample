package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import colruyt.rearulmgtdmnejb.bo.ReactionPeriodRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;
import colruyt.rearulmgtdmnejb.util.ReactionPeriodRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)


public class ReactionPeriodRuleConverterTest {
	@Test
	public void createConverterTest(){
		ReactionPeriodRuleAction reactionPeriodRuleAction=new ReactionPeriodRuleAction();
		ReactionPeriodRuleBo reactionPeriodRule=getReactionPeriodRule();
		reactionPeriodRuleAction.setReaRuleId(1L);
		reactionPeriodRuleAction.setEndDtDays(reactionPeriodRule.getEndDateMinusDate());
		reactionPeriodRuleAction.setMinDays(reactionPeriodRule.getMinimumDays());
		ReactionPeriodRuleAction expectedReaPrdAct=ReactionPeriodRuleConverter.convertFromBo(reactionPeriodRule);
		assertEquals(new Long(10l),expectedReaPrdAct.getEndDtDays());
	}
	public ReactionPeriodRuleBo getReactionPeriodRule(){
		ReactionPeriodRuleBo reactionPeriodRuleBo=new ReactionPeriodRuleBo();
		reactionPeriodRuleBo.setRuleId(1L);
		reactionPeriodRuleBo.setEndDateMinusDate(10l);
		reactionPeriodRuleBo.setMinimumDays(7l);
		return reactionPeriodRuleBo;
	}

}
