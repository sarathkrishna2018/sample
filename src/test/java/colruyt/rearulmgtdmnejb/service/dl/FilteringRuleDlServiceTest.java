package colruyt.rearulmgtdmnejb.service.dl;

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
	@DataSet("dataset/FilteringRuleDlServiceTest.xml")
	@ExpectedDataSet("result/FilteringRulePhysicalDeleteElementsTestResult.xml")
	public void physicalDeleteElementsTest() {
		filteringRuleActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
	}



}
