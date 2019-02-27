package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
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
	@DataSet
	public void createTest() {
		FilteringRuleAction expectedFilteringRule = filteringRuleActionDlService.createOrUpdate(getReaFltRule());
		Assert.assertNotNull(expectedFilteringRule);

	}

	@Test
	@DataSet
	public void findByRuleIdTest() {
		FilteringRuleAction expectedFilteringRule = filteringRuleActionDlService.findByRuleId(1l);
		Assert.assertNotNull(expectedFilteringRule);
	}

	@Test
	@DataSet
	public void physicalDeleteElementsTest() {
		filteringRuleActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
		
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}

	private FilteringRuleAction getReaFltRule() {
		FilteringRuleAction reaFlt = new FilteringRuleAction();
		reaFlt.setMaximumCompetitorQuantity(5d);
		reaFlt.setReactionRuleId(1);
		reaFlt.setXTimeQty(2d);
		return reaFlt;
	}

}
