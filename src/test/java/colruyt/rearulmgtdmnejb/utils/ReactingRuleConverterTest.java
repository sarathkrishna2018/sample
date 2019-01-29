package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;
import colruyt.rearulmgtdmnejb.util.ReactingRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactingRuleConverterTest {
	@TestedObject
	private ReactingRuleConverter reactingRuleConverter;
	@Test
	public void createConverterTest(){
		ReactingRuleBo reactingRuleBo=getReactingRuleBo();
		ReactingRuleAction reactingAct = new ReactingRuleAction();
		reactingAct.setReactingAmt(reactingRuleBo.getReactingAmount());
		reactingAct.setReactingPerc(reactingRuleBo.getReactingAmount());
		reactingAct.setTholdAmt(reactingRuleBo.getThresholdAmount());
		reactingAct.setTholdPerc(reactingRuleBo.getThresholdPercentage());
		ReactingRuleAction expectedReaReactingAct=reactingRuleConverter.convert(reactingRuleBo);
		assertEquals(new Double(12d),expectedReaReactingAct.getReactingAmt());	
	}
	public ReactingRuleBo getReactingRuleBo(){
		ReactingRuleBo reactingRule=new ReactingRuleBo();
		reactingRule.setReactingAmount(12d);
		reactingRule.setReactingPercentage(5d);
		reactingRule.setThresholdAmount(13d);
		reactingRule.setThresholdPercentage(6d);
		return reactingRule;
		
	}
}
