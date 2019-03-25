package colruyt.rearulmgtdmnejb.service.dl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactionRuleSetDlServiceTest {
	private ReactionRuleSetDlService reactionRuleSetDlService;

	@Before
	public void init() {
		reactionRuleSetDlService = new ReactionRuleSetDlService();
		JpaUnitils.injectEntityManagerInto(reactionRuleSetDlService);

	}

	@Test
	@DataSet("dataset/ReactionRulsetDlServiceCreateTest.xml")
	@ExpectedDataSet("result/ReactionRuleSetCreateTestResult.xml")
	public void createTest() {
		ReactionRuleSet expectedReactionRuleSet = reactionRuleSetDlService.createOrUpdate(getReactionRuleSet());
		Assert.assertEquals(expectedReactionRuleSet.getReaRulesetId(), new Long(501L));

	}
	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findByPkTest() {
		ReactionRuleSet expectedReactionRuleSet = reactionRuleSetDlService.findByPk(501l);
		Assert.assertEquals(expectedReactionRuleSet.getColruytGroupChainId(), 1);
		Assert.assertEquals(expectedReactionRuleSet.getPriceCompetitorChainId(), 63);
		Assert.assertEquals(expectedReactionRuleSet.getRuleTypeId(), 2);
		Assert.assertEquals(expectedReactionRuleSet.getRulesetName(), "Name");

	}
	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findByAttributesTest() {
		List<ReactionRuleSet> expectedReactionRuleSetList = reactionRuleSetDlService.findByAttributes(1l, 1l, 1l);
		assertThat(expectedReactionRuleSetList.size()).isEqualTo(1);
		Assert.assertEquals(expectedReactionRuleSetList.get(0).getRulesetName(), "Name");
		Assert.assertEquals(expectedReactionRuleSetList.get(0).getRuleTypeId(), 1L);
		Assert.assertEquals(expectedReactionRuleSetList.get(0).getLstUpdateBy(), "ktr");
		
	}
	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void findByCgChainAndPCChainTest() {
		List<ReactionRuleSet> expectedReactionRuleSetList = reactionRuleSetDlService.findByCgChainAndPCChain(1l, 1l);
		assertThat(expectedReactionRuleSetList.size()).isEqualTo(1);
		Assert.assertEquals(expectedReactionRuleSetList.get(0).getRulesetName(), "Name");
		Assert.assertEquals(expectedReactionRuleSetList.get(0).getRuleTypeId(), 1L);
		Assert.assertEquals(expectedReactionRuleSetList.get(0).getLstUpdateBy(), "ktr");
	}
	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	public void logicallyDeleteRuleSetTest() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date actualDate = formatter.parse(formatter.format(new Date()));
		reactionRuleSetDlService.logicallyDeleteRuleSet(getReactionRuleSet());
		ReactionRuleSet expectedReactionRuleSet = reactionRuleSetDlService.findByPk(501l);
		Assert.assertEquals(expectedReactionRuleSet.getLogicallyDeletedDate(), actualDate);
		Assert.assertEquals(expectedReactionRuleSet.getColruytGroupChainId(), 1);
		Assert.assertEquals(expectedReactionRuleSet.getPriceCompetitorChainId(), 63);
		Assert.assertEquals(expectedReactionRuleSet.getRulesetName(), "Name");

	}
	@Test
	@DataSet("dataset/ReactionRuleSetDlServiceTest.xml")
	@ExpectedDataSet("result/PhysicalDeleteAllRuleSetTestResult.xml")
	public void physicalDeleteAllRuleSetTest() {
		reactionRuleSetDlService.physicalDeleteAllRuleSet(getReactionRuleId());

	}
	private ReactionRuleSet getReactionRuleSet() {
		ReactionRuleSet reactionRuleSet = new ReactionRuleSet();
		reactionRuleSet.setColruytGroupChainId(1l);
		reactionRuleSet.setPriceCompetitorChainId(63l);
		reactionRuleSet.setReaRulesetId(501l);
		reactionRuleSet.setRuleTypeId(2);
		reactionRuleSet.setRulesetName("Name");
		reactionRuleSet.setRulesetComment("Comment");
		reactionRuleSet.setLstUpdateBy("ktr");
		reactionRuleSet.setLogicallyDeletedDate(null);
		reactionRuleSet.setReactionRules(getReactionRuleList());
		return reactionRuleSet;
	}
	private List<ReactionRule> getReactionRuleList(){
		List<ReactionRule> reactionRuleList=Lists.newArrayList();
		ReactionRule reactionRule = getReactionRule();
		reactionRuleList.add(reactionRule);
		return reactionRuleList;
	}
	
	private ReactionRule getReactionRule() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reactionRule = new ReactionRule();
		reactionRule.setReaRuleId(1L);
		reactionRule.setReaRulesetId(501l);
		reactionRule.setRuleName("Sample");
		reactionRule.setImportancecodeFrom(10);
		reactionRule.setImportancecodeTo(20);
		reactionRule.setDirect(true);
		reactionRule.setPostponed(false);
		reactionRule.setPermenant(true);
		reactionRule.setTemporary(true);
		reactionRule.setValidFrom(validFromdate);
		reactionRule.setValidUpto(validTodate);
		reactionRule.setRecalculate(false);
		reactionRule.setRuleComment("Good");
		reactionRule.setCreatedBy("ktr");
		reactionRule.setLstUpdateBy("ktr");
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
	private Set<Long> getReactionRuleId(){
		Set<Long> reactionRuleIdSet = new HashSet<>();
		reactionRuleIdSet.add(5l);
		return reactionRuleIdSet;
	}

}
