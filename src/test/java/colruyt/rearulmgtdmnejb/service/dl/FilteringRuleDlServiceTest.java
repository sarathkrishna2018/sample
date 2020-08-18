/*package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class FilteringRuleDlServiceTest {

	private FilteringRuleActionDlService filteringRuleActionDlService;

	@Before
	public void init() {
		filteringRuleActionDlService = new FilteringRuleActionDlService();
		JpaUnitils.injectEntityManagerInto(filteringRuleActionDlService);
	}

	@Test
	@ExpectedDataSet("result/FilteringRuleCreateTestResult.xml")
	public void createTest() {
		FilteringRuleAction expectedFilteringRule = filteringRuleActionDlService.createOrUpdate(getReaFltRule());
		Assert.assertEquals(1L, expectedFilteringRule.getReactionRuleId());
	}

	@Test
	@DataSet("dataset/FilteringRuleDlServiceTest.xml")
	public void findByRuleIdTest() {
		FilteringRuleAction expectedFilteringRule = filteringRuleActionDlService.findByRuleId(2l);
		Assert.assertEquals(2L, expectedFilteringRule.getReactionRuleId());
		Assert.assertEquals(new Double(1.0), expectedFilteringRule.getMaxTimesRecordingProduct());
		Assert.assertEquals(new Double(2.5), expectedFilteringRule.getMaxTimesPriceArticle());
	}

	@Test
	@DataSet("dataset/FilteringRuleDlServiceTest.xml")
	@ExpectedDataSet("result/FilteringRulePhysicalDeleteElementsTestResult.xml")
	public void physicalDeleteElementsTest() {
		filteringRuleActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 3l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(3l);
		return deleteRuleInfoBo;
	}

	private FilteringRuleAction getReaFltRule() {
		FilteringRuleAction reaFlt = new FilteringRuleAction();
		reaFlt.setMaxTimesRecordingProduct(2.0);
		reaFlt.setReactionRuleId(1);
		reaFlt.setMaxTimesPriceArticle(3.5);
		return reaFlt;
	}

}
*/