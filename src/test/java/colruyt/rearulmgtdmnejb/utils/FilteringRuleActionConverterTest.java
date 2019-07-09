package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;
import colruyt.rearulmgtdmnejb.util.FilteringRuleActionConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class FilteringRuleActionConverterTest {

	@Test
	public void createConverterTest() {
		FilteringRuleBo filteringRuleBo = getFilteringRuleBo();
		FilteringRuleAction filteringRuleAction = new FilteringRuleAction();
		filteringRuleAction.setReactionRuleId(1L);
		filteringRuleAction.setMaxTimesRecordingProduct(filteringRuleBo.getMaxTimesRecordingProduct());
		filteringRuleAction.setMaxTimesPriceArticle(filteringRuleBo.getMaxTimesPriceArticle());
		FilteringRuleAction expectedReaFltRule = FilteringRuleActionConverter.convertFromBo(filteringRuleBo);
		assertEquals(expectedReaFltRule.getMaxTimesRecordingProduct(),
				filteringRuleAction.getMaxTimesRecordingProduct());
		assertEquals(expectedReaFltRule.getMaxTimesPriceArticle(), filteringRuleAction.getMaxTimesPriceArticle());

	}

	@Test
	public void convertFilteringRuleActionTest() {
		FilteringRuleAction reaFltRule = getReaFltRule();
		FilteringRuleBo filteringRuleBo = new FilteringRuleBo();
		filteringRuleBo.setMaxTimesRecordingProduct(reaFltRule.getMaxTimesRecordingProduct());
		filteringRuleBo.setMaxTimesPriceArticle(reaFltRule.getMaxTimesPriceArticle());
		FilteringRuleBo finalBo = FilteringRuleActionConverter.convertToBo(reaFltRule);
		assertEquals(finalBo.getMaxTimesRecordingProduct(), reaFltRule.getMaxTimesRecordingProduct());
		assertEquals(finalBo.getMaxTimesPriceArticle(), reaFltRule.getMaxTimesPriceArticle());
	}

	@Test
	public void convertToBoTest() {
		FilteringRuleAction reaFltRule = new FilteringRuleAction();
		FilteringRuleBo filteringBoParam = new FilteringRuleBo();
		FilteringRuleBo filteringRuleBo = getFilteringRuleBo();
		filteringRuleBo.setMaxTimesRecordingProduct(reaFltRule.getMaxTimesRecordingProduct());
		filteringRuleBo.setMaxTimesPriceArticle(reaFltRule.getMaxTimesPriceArticle());
		FilteringRuleBo finalBo = FilteringRuleActionConverter.convertToBo(reaFltRule, filteringBoParam);
		assertEquals(finalBo.getMaxTimesRecordingProduct(), filteringRuleBo.getMaxTimesRecordingProduct());
		assertEquals(finalBo.getMaxTimesPriceArticle(), filteringRuleBo.getMaxTimesPriceArticle());
	}

	private FilteringRuleBo getFilteringRuleBo() {
		FilteringRuleBo filteringRuleBo = new FilteringRuleBo();
		filteringRuleBo.setRuleId(1L);
		filteringRuleBo.setMaxTimesRecordingProduct(12d);
		filteringRuleBo.setMaxTimesPriceArticle(12d);
		return filteringRuleBo;
	}

	private FilteringRuleAction getReaFltRule() {
		FilteringRuleAction reaFltRule = new FilteringRuleAction();
		reaFltRule.setMaxTimesRecordingProduct(12d);
		reaFltRule.setMaxTimesPriceArticle(1d);
		return reaFltRule;
	}

}
