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

	/*
	 * @Test
	 * 
	 * @DataSet("dataset/ReactionRuleDlServiceCreateTest.xml")
	 * 
	 * @ExpectedDataSet("result/ReactionRuleCreateTestResult.xml") public void
	 * createRuleTest() { ReactionRule expectedReactionRule =
	 * reactionRuleDlService.createOrUpdate(getReactionRule());
	 * Assert.assertEquals(1L,expectedReactionRule.getReaRulesetId());
	 * 
	 * }
	 */

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void findByPkTest() {
		ReactionRule expectedReactionRule = reactionRuleDlService.findByPk(1l);
		Assert.assertEquals(1L,expectedReactionRule.getReaRuleId());
		Assert.assertEquals(1L,expectedReactionRule.getReaRulesetId());
		Assert.assertEquals("Sample",expectedReactionRule.getRuleName());
		Assert.assertEquals("ake" ,expectedReactionRule.getCreatedBy());
	}

	/*
	 * @Test
	 * 
	 * @DataSet("dataset/ReactionRuleDlServiceTestFindRuleSet.xml") public void
	 * findByRuleSetId() { List<ReactionRule> expectedReactionRules =
	 * reactionRuleDlService.findByRuleSetId(2l);
	 * assertThat(expectedReactionRules.size()).isEqualTo(3);
	 * Assert.assertEquals(2L,expectedReactionRules.get(0).getReaRuleId());
	 * Assert.assertEquals(2L,expectedReactionRules.get(0).getReaRulesetId());
	 * Assert.assertEquals("Sample",expectedReactionRules.get(0).getRuleName() ); }
	 */

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void findParentRule() {
		ReactionRule expectedChildReactionRule = reactionRuleDlService.findParentRule(3l);
		Assert.assertEquals(new Long(3L),expectedChildReactionRule.getChildRuleId() );
		Assert.assertEquals(2L,expectedChildReactionRule.getReaRuleId() );
		Assert.assertEquals(2L ,expectedChildReactionRule.getReaRulesetId());
		Assert.assertEquals("Sample" ,expectedChildReactionRule.getRuleName());
	}

	@Test
	@DataSet("dataset/ReactionRuleDlServiceTest.xml")
	public void updateLogicallyDeletedDate() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date actualDate = formatter.parse(formatter.format(new Date()));
		reactionRuleDlService.updateLogicallyDeletedDate(getReactionRule());
		ReactionRule expectedReactionRule = reactionRuleDlService.findByPk(1l);
		Assert.assertEquals(actualDate,expectedReactionRule.getLogicallyDeletedDate());
		Assert.assertEquals( new Long(2L),expectedReactionRule.getChildRuleId());
		Assert.assertEquals(1L,expectedReactionRule.getReaRuleId());
		Assert.assertEquals(1L,expectedReactionRule.getReaRulesetId());
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

	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findAllLogicallyDeletedRulesTest() throws ParseException {
		Date newDate = new Date();
		List<DeleteRuleInfoBo> expectedDeletedRuleBos = reactionRuleDlService.findAllLogicallyDeletedRules(newDate);
		assertThat(expectedDeletedRuleBos.size()).isEqualTo(1);
		Assert.assertEquals(expectedDeletedRuleBos.get(0).getRuleId(), new Long(1L));
		Assert.assertEquals(expectedDeletedRuleBos.get(0).getRuleType(), new Long(2L));
	}

	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findAllExpiredRulesTest() throws ParseException {
		Date dateForRulesDelete = new Date();
		List<DeleteRuleInfoBo> expectedDeleteRuleInfo = reactionRuleDlService.findAllExpiredRules(dateForRulesDelete);
		assertThat(expectedDeleteRuleInfo.size()).isEqualTo(1);
		Assert.assertEquals(expectedDeleteRuleInfo.get(0).getRuleId(), new Long(2L));
		Assert.assertEquals(expectedDeleteRuleInfo.get(0).getRuleType(), new Long(1L));
	}

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
		ReactionRule reactionRule = new ReactionRule();
		reactionRule.setReaRuleId(1L);
		reactionRule.setReaRulesetId(1L);
		reactionRule.setRuleName("Filtering");
		reactionRule.setImportancecodeFrom(10);
		reactionRule.setImportancecodeTo(20);
		reactionRule.setDirect(true);
		reactionRule.setPostponed(true);
		reactionRule.setPermenant(true);
		reactionRule.setTemporary(false);
		reactionRule.setValidFrom(null);
		reactionRule.setValidUpto(null);
		reactionRule.setRecalculate(false);
		reactionRule.setRuleComment("good");
		reactionRule.setCreatedBy("sa");
		reactionRule.setLastUpdateBy("sa");
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
