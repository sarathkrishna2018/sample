/*package colruyt.rearulmgtdmnejb.service.dl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.ReaPrdAct;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactionPeriodRuleDlServiceTest {
	private ReactionPeriodActionDlService reactionPeriodActionDlService;
	@Before
	public void init(){
		reactionPeriodActionDlService=new ReactionPeriodActionDlService();
		JpaUnitils.injectEntityManagerInto(reactionPeriodActionDlService);
	}
	@Test
	@DataSet
	public void createTest(){
		ReaPrdAct reaPrdAct=getReaPrdAct();
		reactionPeriodActionDlService.createOrUpdate(reaPrdAct);
		Assert.assertNotNull(reaPrdAct);
		
	}
	public ReaPrdAct getReaPrdAct(){
		ReaPrdAct reaPrdAct=new ReaPrdAct();
		reaPrdAct.setReaRuleId(1l);
		reaPrdAct.setEndDtDays(10l);
		reaPrdAct.setMinDays(8l);
		return reaPrdAct;
	}

}
*/