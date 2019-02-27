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
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactionPeriodRuleDlServiceTest {
	private ReactionPeriodActionDlService reactionPeriodActionDlService;

	@Before
	public void init() {
		reactionPeriodActionDlService = new ReactionPeriodActionDlService();
		JpaUnitils.injectEntityManagerInto(reactionPeriodActionDlService);
	}

	@Test
	@DataSet
	public void createTest() {
		ReactionPeriodRuleAction expectedReactionPeriodRule = reactionPeriodActionDlService
				.createOrUpdate(getreactionPeriodRuleAction());
		Assert.assertNotNull(expectedReactionPeriodRule);

	}
	@Test
	@DataSet
	public void findByRuleIdTest() {
		ReactionPeriodRuleAction expectedReactionPeriodRule = reactionPeriodActionDlService.findByRuleId(1l);
		Assert.assertNotNull(expectedReactionPeriodRule);
	}

	@Test
	@DataSet
	public void physicalDeleteElementsTest() {
		reactionPeriodActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
		
	}
	public ReactionPeriodRuleAction getreactionPeriodRuleAction() {
		ReactionPeriodRuleAction reactionPeriodRuleAction = new ReactionPeriodRuleAction();
		reactionPeriodRuleAction.setReaRuleId(1l);
		reactionPeriodRuleAction.setEndDtDays(10l);
		reactionPeriodRuleAction.setMinDays(8l);
		return reactionPeriodRuleAction;
	}
	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
}
