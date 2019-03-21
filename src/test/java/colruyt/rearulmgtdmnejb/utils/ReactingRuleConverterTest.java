package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;
import colruyt.rearulmgtdmnejb.util.ReactingRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactingRuleConverterTest {
	@Test
	public void createConverterTest(){
		ReactingRuleBo reactingRuleBo=getReactingRuleBo();
		ReactingRuleAction reactingRuleAction = new ReactingRuleAction();
		reactingRuleAction.setReactionRuleId(reactingRuleBo.getRuleId());
		reactingRuleAction.setReactingAmt(reactingRuleBo.getReactingAmount());
		reactingRuleAction.setReactingPercentage(reactingRuleBo.getReactingAmount());
		reactingRuleAction.setThresholdAmount(reactingRuleBo.getThresholdAmount());
		reactingRuleAction.setThresholdPercentage(reactingRuleBo.getThresholdPercentage());
		ReactingRuleAction expectedReaReactingAct=ReactingRuleConverter.convertFromBo(reactingRuleBo);
		assertEquals(new Double(12d),expectedReaReactingAct.getReactingAmt());	
	}
	public ReactingRuleBo getReactingRuleBo(){
		ReactingRuleBo reactingRule=new ReactingRuleBo();
		reactingRule.setRuleId(1L);
		reactingRule.setReactingAmount(12d);
		reactingRule.setReactingPercentage(5d);
		reactingRule.setThresholdAmount(13d);
		reactingRule.setThresholdPercentage(6d);
		return reactingRule;
		
	}
}
