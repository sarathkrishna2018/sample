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

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactionRuleSourceTypeDlServiceTest {
	private ReactionRuleSourceTypeDlService reactionRuleSourceTypeDlService;

	@Before
	public void init() {
		reactionRuleSourceTypeDlService = new ReactionRuleSourceTypeDlService();
		JpaUnitils.injectEntityManagerInto(reactionRuleSourceTypeDlService);
	}

	@Test
	@DataSet
	public void physicalDeleteActionForRulesTest() {
		reactionRuleSourceTypeDlService.physicalDeleteSourceType(getDeleteRuleInfoBo());
		// Assert.assertEquals(getDeleteRuleInfoBo().getRuleId(), new Long(1l));
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}

}
