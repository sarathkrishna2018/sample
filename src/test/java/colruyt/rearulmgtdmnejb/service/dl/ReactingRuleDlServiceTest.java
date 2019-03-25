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
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;



@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
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
	@ExpectedDataSet("result/ReactingRuleCreateTestResult.xml")
	public void createTest(){
		ReactingRuleAction expectedReactingRule=reactingRuleActionDlService.createOrUpdate(getReactingRule());
		Assert.assertEquals(expectedReactingRule.getReactionRuleId(), 1L);
	}
	@Test
	@DataSet("dataset/ReactingRuleDlServiceTest.xml")
	public void findByRuleIdTest(){
		ReactingRuleAction expectedReactingRule=reactingRuleActionDlService.findByRuleId(1l);
		Assert.assertEquals(expectedReactingRule.getReactionRuleId(), 1L);
		Assert.assertEquals(expectedReactingRule.getCatchAll(), true);
		Assert.assertEquals(expectedReactingRule.getReactingAmt(), 7,0);
		Assert.assertEquals(expectedReactingRule.getReactingPercentage(), 3,0);
		Assert.assertEquals(expectedReactingRule.getThresholdAmount(), 5,0);
		Assert.assertEquals(expectedReactingRule.getThresholdPercentage(), 2,0);
	}
	@Test
	@DataSet("dataset/ReactingRuleDlServiceTest.xml")
	@ExpectedDataSet("result/ReactingRulePhysicalDeleteElementsTestResult.xml")
	public void physicalDeleteElementsTest(){
		reactingRuleActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
	}
	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
	public ReactingRuleAction getReactingRule(){
		ReactingRuleAction reactingRuleAction=new ReactingRuleAction();
		reactingRuleAction.setCatchAll(true);
		reactingRuleAction.setReactingAmt(7d);
		reactingRuleAction.setReactionRuleId(1);
		reactingRuleAction.setReactingPercentage(3d);
		reactingRuleAction.setThresholdAmount(5d);
		reactingRuleAction.setThresholdPercentage(2d);
		return reactingRuleAction;	
		
	}
}
