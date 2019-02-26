package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class QuantityRuleDlServiceTest {

	private QuantityRuleActionDlService quantityRuleActionDlService;
	@Before
	public void init(){
		quantityRuleActionDlService=new QuantityRuleActionDlService();
		JpaUnitils.injectEntityManagerInto(quantityRuleActionDlService);
	}
	@Test
	@DataSet
	public void createTest(){
		QuantityRuleAction expectedQuantityRule=quantityRuleActionDlService.createOrUpdate(getReaQtyRule());
		Assert.assertNotNull(expectedQuantityRule);
	}
	public QuantityRuleAction getReaQtyRule(){
		QuantityRuleAction reaQtyRule=new QuantityRuleAction();
		reaQtyRule.setQuantityConditionId(1l);
		reaQtyRule.setQuantityTypeId(1l);
		reaQtyRule.setReactionRuleId(1l);
		return reaQtyRule;
		
	}
}
