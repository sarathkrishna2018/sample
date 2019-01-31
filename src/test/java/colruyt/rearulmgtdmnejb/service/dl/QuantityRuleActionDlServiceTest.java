/*package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.ReaQtyRule;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class QuantityRuleActionDlServiceTest {

	private QuantityRuleActionDlService quantityRuleActionDlService;
	@Before
	public void init(){
		quantityRuleActionDlService=new QuantityRuleActionDlService();
		JpaUnitils.injectEntityManagerInto(quantityRuleActionDlService);
	}
	public void createTest(){
		ReaQtyRule reaQtyRule=getReaQtyRule();
		quantityRuleActionDlService.createOrUpdate(reaQtyRule);
		Assert.assertNotNull(reaQtyRule);
	}
	public ReaQtyRule getReaQtyRule(){
		ReaQtyRule reaQtyRule=new ReaQtyRule();
		reaQtyRule.setReaRuleId(1l);
		reaQtyRule.setQtyTypeId(1l);
		reaQtyRule.setQtyCondId(2l);
		return reaQtyRule;
		
	}
}
*/