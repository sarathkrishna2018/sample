package colruyt.rearulmgtdmnejb.service.dl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactionRuleDlServiceTest {
	private ReactionRuleDlService reactionRuleDlService;

	@Before
	public void init() {
		reactionRuleDlService = new ReactionRuleDlService();
		JpaUnitils.injectEntityManagerInto(reactionRuleDlService);
	}

	@Test
	@DataSet
	public void createRuleTest(){
		ReactionRule expectedReactionRule=reactionRuleDlService.createOrUpdate(getReactionRule());
		Assert.assertNotNull(expectedReactionRule);
	}

	private ReactionRule getReactionRule() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reactionRule = new ReactionRule();
		reactionRule.setReaRuleId(1L);
		reactionRule.setReaRulesetId(1l);
		reactionRule.setRuleName("Filtering");
		reactionRule.setImportancecodeFrom(10);
		reactionRule.setImportancecodeTo(5);
		reactionRule.setDirect(true);
		reactionRule.setPostponed(true);
		reactionRule.setPermenant(true);
		reactionRule.setTemporary(false);
		reactionRule.setValidFrom(validFromdate);
		reactionRule.setValidUpto(validTodate);
		reactionRule.setRecalculate(false);
		reactionRule.setRuleComment("good");
		reactionRule.setCreatedBy("sa");
		reactionRule.setLstUpdateBy("sa");
		reactionRule.setRefActionTypes(getActionTypeList());
		reactionRule.setRefSourceTypes(getSourceTypeList());
		reactionRule.setChildRuleId(2l);
		reactionRule.setRulePriority(1);
		return reactionRule;
	}
	private List<SourceType> getSourceTypeList() {
		List<SourceType> sourceTypes = Arrays.asList(SourceType.values());
		return sourceTypes;
	}

	private List<ActionType> getActionTypeList() {
		List<ActionType> actionTypes = Arrays.asList(ActionType.values());
		return actionTypes;
	}

}
