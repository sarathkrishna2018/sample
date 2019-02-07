package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;
import colruyt.rearulmgtdmnejb.util.FilteringRuleActionConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class FilteringRuleActionConverterTest {
	@TestedObject
	private FilteringRuleActionConverter filteringRuleActionConverter;
	
	@Test
	public void createConverterTest(){
		FilteringRuleBo filteringRuleBo=getFilteringRuleBo();
		FilteringRuleAction filteringRuleAction=new FilteringRuleAction();
		filteringRuleAction.setReaRuleId(1L);
		filteringRuleAction.setMaxCompQty(filteringRuleBo.getMaxCompQuantity());
		filteringRuleAction.setXTimeQty(filteringRuleBo.getxTimeQuantity());
		FilteringRuleAction expectedReaFltRule=filteringRuleActionConverter.convert(filteringRuleBo);	
		assertEquals(expectedReaFltRule.getMaxCompQty(), filteringRuleAction.getMaxCompQty());
		assertEquals(expectedReaFltRule.getXTimeQty(), filteringRuleAction.getXTimeQty());

		
	}
	
	@Test
	public void convertFilteringRuleActionTest(){
		FilteringRuleAction reaFltRule=getReaFltRule();
		FilteringRuleBo filteringRuleBo=new FilteringRuleBo();
		filteringRuleBo.setMaxCompQuantity(reaFltRule.getMaxCompQty());
		filteringRuleBo.setxTimeQuantity(reaFltRule.getXTimeQty());
		FilteringRuleBo finalBo = filteringRuleActionConverter.convertFilteringRuleAction(reaFltRule);
		assertEquals(finalBo.getMaxCompQuantity(),reaFltRule.getMaxCompQty());
		assertEquals(finalBo.getxTimeQuantity(),reaFltRule.getXTimeQty());
	}
	
	@Test
	public void convertToBoTest(){
		FilteringRuleAction reaFltRule=new FilteringRuleAction();
		FilteringRuleBo filteringBoParam=new FilteringRuleBo();
		FilteringRuleBo filteringRuleBo=getFilteringRuleBo();
		filteringRuleBo.setMaxCompQuantity(reaFltRule.getMaxCompQty());
		filteringRuleBo.setxTimeQuantity(reaFltRule.getXTimeQty());
		FilteringRuleBo finalBo =filteringRuleActionConverter.addFilteringRuleAction(reaFltRule, filteringBoParam);
		assertEquals(finalBo.getMaxCompQuantity(),filteringRuleBo.getMaxCompQuantity());
		assertEquals(finalBo.getxTimeQuantity(),filteringRuleBo.getxTimeQuantity());
	}
	
	public FilteringRuleBo getFilteringRuleBo(){
		FilteringRuleBo filteringRuleBo=new FilteringRuleBo();
		filteringRuleBo.setRuleId(1L);
		filteringRuleBo.setMaxCompQuantity(12d);
		filteringRuleBo.setxTimeQuantity(12d);
		return filteringRuleBo;
	}
	
	public FilteringRuleAction getReaFltRule() {
		FilteringRuleAction reaFltRule = new FilteringRuleAction();
		reaFltRule.setMaxCompQty(12d);
		reaFltRule.setXTimeQty(1d);
		return reaFltRule;
	}
	
}
