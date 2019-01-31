/*package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.ReaReactingAct;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactingRuleDlServiceTest {
	private ReactingRuleActionDlService reactingRuleActionDlService;
	@Before
	public void init(){
		reactingRuleActionDlService=new ReactingRuleActionDlService();
		JpaUnitils.injectEntityManagerInto(reactingRuleActionDlService);
	}
	@Test
	@DataSet
	public void createTest(){
		ReaReactingAct reaReactingAct=getReareactingAct();
		reactingRuleActionDlService.createOrUpdate(reaReactingAct);
		Assert.notNull(reaReactingAct);
	}
	public ReaReactingAct getReareactingAct(){
		ReaReactingAct reaReactingAct=new ReaReactingAct();
		reaReactingAct.setReaRuleId(1l);
		reaReactingAct.setCatchAllYn(true);
		reaReactingAct.setReactingAmt(12d);
		reaReactingAct.setReactingPerc(8d);
		reaReactingAct.setTholdAmt(10d);
		reaReactingAct.setTholdPerc(6d);
		return reaReactingAct;
		
	}
}
*/