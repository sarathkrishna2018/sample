package colruyt.rearulmgtdmnejb.service.dl;

import java.text.ParseException;
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

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
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
	public void createRuleTest() {
		ReactionRule expectedReactionRule = reactionRuleDlService.createOrUpdate(getReactionRule());
		Assert.assertNotNull(expectedReactionRule);
	}

	@Test
	@DataSet
	public void findByPkTest() {
		ReactionRule expectedReactionRule = reactionRuleDlService.findByPk(1l);
		Assert.assertNotNull(expectedReactionRule);
	}

	@Test
	@DataSet
	public void findByRuleSetId() {
		List<ReactionRule> expectedReactionRules = reactionRuleDlService.findByRuleSetId(1l);
		Assert.assertNotNull(expectedReactionRules);
	}

	@Test
	@DataSet
	public void findParentRule() {
		ReactionRule expectedChildReactionRule = reactionRuleDlService.findParentRule(2l);
		Assert.assertNotNull(expectedChildReactionRule);
	}

	@Test
	@DataSet
	public void updateLogicallyDeletedDate() {
		reactionRuleDlService.updateLogicallyDeletedDate(getReactionRule());
	}

	@Test
	@DataSet
	public void getMaxRulePriorityByRuleSetIdTest() {
		Long expectedRulePriority = reactionRuleDlService.getMaxRulePriorityByRuleSetId(1l);
		Assert.assertNotNull(expectedRulePriority);
	}

	@Test
	@DataSet
	public void logicallyDeleteRulesTest() {
		String logonId = "ake";
		reactionRuleDlService.logicallyDeleteRules(getReactionRuleIds(), logonId);
	}

	@Test
	@DataSet
	public void findAllLogicallyDeletedRulesTest() throws ParseException {
		Date dateForRulesDelete = new Date();
		List<DeleteRuleInfoBo> expectedDeletedRuleBos = reactionRuleDlService
				.findAllLogicallyDeletedRules(dateForRulesDelete);
		Assert.assertNotNull(expectedDeletedRuleBos);
	}

	@Test
	@DataSet
	public void findAllExpiredRulesTest() throws ParseException {
		Date dateForRulesDelete = new Date();
		List<DeleteRuleInfoBo> expectedDeleteRuleInfo = reactionRuleDlService.findAllExpiredRules(dateForRulesDelete);
		Assert.assertNotNull(expectedDeleteRuleInfo);
	}

	@Test
	@DataSet
	public void physicalDeleteRuleTest() {
		reactionRuleDlService.physicalDeleteRule(getDeleteRuleInfoBo());
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}

	private List<Long> getReactionRuleIds() {
		List<Long> reactionRuleIds = Lists.newArrayList();
		reactionRuleIds.add(1l);
		return reactionRuleIds;
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
