/*package colruyt.rearulmgtdmnejb.service.dl;

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

import colruyt.rearulmgtdmnejb.entity.ReaRnfAct;

@JpaEntityManagerFactory(persistenceUnit ="rearulmgtdmnmwtest",configFile="/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class RecordingNotFoundRuleDlServiceTest {
	private RecordingNotFoundRuleActionDlService recordingNotFoundRuleActionDlService;
	
	
	
	@Before
	public void init(){
		recordingNotFoundRuleActionDlService=new RecordingNotFoundRuleActionDlService();
		JpaUnitils.injectEntityManagerInto(recordingNotFoundRuleActionDlService);	
		
	}
	@Test
	@DataSet
	public void createTest(){
		ReaRnfAct reaRnfAct=getReaRnfAct();
		recordingNotFoundRuleActionDlService.createOrUpdate(reaRnfAct);
		Assert.assertNotNull(reaRnfAct);
		
	}
	public ReaRnfAct getReaRnfAct(){
		ReaRnfAct reaRnfAct=new ReaRnfAct();
		reaRnfAct.setReaRuleId(1l);
		reaRnfAct.setNoOfRnf(5l);
		return reaRnfAct;
		
	}
	
}
*/