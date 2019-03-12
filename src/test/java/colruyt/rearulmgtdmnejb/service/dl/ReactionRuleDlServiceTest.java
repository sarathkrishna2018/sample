package colruyt.rearulmgtdmnejb.service.dl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
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
public class ReactionRuleDlServiceTest extends UnitilsJUnit4 {
	private ReactionRuleDlService reactionRuleDlService;

	@Before
	public void init() {
		reactionRuleDlService = new ReactionRuleDlService();
		JpaUnitils.injectEntityManagerInto(reactionRuleDlService);
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void createRuleTest() {
		ReactionRule expectedReactionRule = reactionRuleDlService.createOrUpdate(getReactionRule());
		assertThat(expectedReactionRule.getReaRulesetId() == 2L);

	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void findByPkTest() {
		ReactionRule expectedReactionRule = reactionRuleDlService.findByPk(1l);
		Assert.assertEquals(expectedReactionRule.getReaRuleId(), 1L);
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void findByRuleSetId() {
		List<ReactionRule> expectedReactionRules = reactionRuleDlService.findByRuleSetId(2l);
		assertThat(expectedReactionRules.size()).isEqualTo(3);
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void findParentRule() {
		ReactionRule expectedChildReactionRule = reactionRuleDlService.findParentRule(3l);
		Assert.assertEquals(expectedChildReactionRule.getChildRuleId(), new Long(3L));
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void updateLogicallyDeletedDate() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date actualDate = formatter.parse(formatter.format(new Date()));
		reactionRuleDlService.updateLogicallyDeletedDate(getReactionRule());
		ReactionRule expectedReactionRule = reactionRuleDlService.findByPk(1l);
		Assert.assertEquals(expectedReactionRule.getLogicallyDeletedDate(), actualDate);
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	@ExpectedDataSet("result/GetMaxRulePriorityByRuleSetIdTestResult.xml")
	public void getMaxRulePriorityByRuleSetIdTest() {
		reactionRuleDlService.getMaxRulePriorityByRuleSetId(2l);
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	@ExpectedDataSet("result/LogicallyDeleteRulesTestResult.xml")
	public void logicallyDeleteRulesTest() {
		String logonId = "ake";
		reactionRuleDlService.logicallyDeleteRules(getReactionRuleIds(), logonId);
	}

	/*@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findAllLogicallyDeletedRulesTest() throws ParseException {
		Date newDate = new Date();
		List<DeleteRuleInfoBo> expectedDeletedRuleBos = reactionRuleDlService.findAllLogicallyDeletedRules(newDate);
		assertThat(expectedDeletedRuleBos.size()).isEqualTo(1);
	}

	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findAllExpiredRulesTest() throws ParseException {
		Date dateForRulesDelete = new Date();
		List<DeleteRuleInfoBo> expectedDeleteRuleInfo = reactionRuleDlService.findAllExpiredRules(dateForRulesDelete);
		assertThat(expectedDeleteRuleInfo.size()).isEqualTo(1);
	}
*/
	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	@ExpectedDataSet("result/PhysicalDeleteRuleTestResult.xml")
	public void physicalDeleteRuleTest() {
		reactionRuleDlService.physicalDeleteRule(getDeleteRuleInfoBo());
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(4l, 1l);
		deleteRuleInfoBo.setRuleId(4l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}

	private List<Long> getReactionRuleIds() {
		List<Long> reactionRuleIds = Lists.newArrayList();
		reactionRuleIds.add(3l);
		reactionRuleIds.add(4l);
		return reactionRuleIds;
	}

	private ReactionRule getReactionRule() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reactionRule = new ReactionRule();
		reactionRule.setReaRuleId(1L);
		reactionRule.setReaRulesetId(1L);
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
		reactionRule.setChildRuleId(3l);
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
