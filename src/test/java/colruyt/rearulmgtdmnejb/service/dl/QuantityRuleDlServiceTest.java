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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
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
	@Test
	@DataSet
	public void findByRuleIdTest(){
		QuantityRuleAction expectedQuantityRule=quantityRuleActionDlService.findByRuleId(1);
		Assert.assertNotNull(expectedQuantityRule);	
	}
	@Test
	@DataSet
	public void physicalDeleteElementsTest(){
		quantityRuleActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
	}
	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
	public QuantityRuleAction getReaQtyRule(){
		QuantityRuleAction reaQtyRule=new QuantityRuleAction();
		reaQtyRule.setQuantityConditionId(1);
		reaQtyRule.setQuantityTypeId(1);
		reaQtyRule.setReactionRuleId(1l);
		return reaQtyRule;
		
	}
}
