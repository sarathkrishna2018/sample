/*package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;

import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
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
		FilteringRuleAction reaFltRule=getReaFltRule();
		filteringRuleActionDlService.createOrUpdate(reaFltRule);
		Assert.assertNotNull(reaFltRule);

	}
	public FilteringRuleAction getReaFltRule(){
		FilteringRuleAction reaFlt=new FilteringRuleAction();
		reaFlt.setReaRuleId(1l);
		reaFlt.setMaxCompQty(12d);
		reaFlt.setXTimeQty(1d);
		return reaFlt;
	}
	
}
*/