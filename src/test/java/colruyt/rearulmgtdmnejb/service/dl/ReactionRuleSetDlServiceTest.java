package colruyt.rearulmgtdmnejb.service.dl;

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
	@DataSet
	public void createTest() {
		ReactionRuleSet expectedReactionRuleSet = reactionRuleSetDlService.createOrUpdate(getReactionRuleSet());
		Assert.assertNotNull(expectedReactionRuleSet);

	}
	@Test
	@DataSet
	public void findByPkTest() {
		ReactionRuleSet expectedReactionRuleSet = reactionRuleSetDlService.findByPk(501l);
		Assert.assertNotNull(expectedReactionRuleSet);

	}
	@Test
	@DataSet
	public void findByAttributesTest() {
		List<ReactionRuleSet> expectedReactionRuleSetList = reactionRuleSetDlService.findByAttributes(1l, 63l, 2l);
		Assert.assertNotNull(expectedReactionRuleSetList);

	}
	@Test
	@DataSet
	public void findByCgChainAndPCChainTest() {
		List<ReactionRuleSet> expectedReactionRuleSetList = reactionRuleSetDlService.findByCgChainAndPCChain(1l, 63l);
		Assert.assertNotNull(expectedReactionRuleSetList);
	}
	@Test
	@DataSet
	public void logicallyDeleteRuleSetTest() {
		reactionRuleSetDlService.logicallyDeleteRuleSet(getReactionRuleSet());

	}
	/*@Test
	@DataSet
	public void findAllLogicallyDeletedRuleSetTest() {
		Date date =new Date();
		List<DeleteRuleSetInfoBo> expectedLogicallyDeletedRuleSet=reactionRuleSetDlService.findAllLogicallyDeletedRuleSet(date);
		Assert.assertNotNull(expectedLogicallyDeletedRuleSet);
	}*/
	@Test
	@DataSet
	public void physicalDeleteAllRuleSetTest() {
		reactionRuleSetDlService.physicalDeleteAllRuleSet(getReactionRuleId());

	}
	private ReactionRuleSet getReactionRuleSet() {
		ReactionRuleSet reactionRuleSet = new ReactionRuleSet();
		reactionRuleSet.setColruytGroupChainId(1l);
		reactionRuleSet.setPriceCompetitorChainId(-73l);
		reactionRuleSet.setReaRulesetId(501l);
		reactionRuleSet.setRuleTypeId(4);
		reactionRuleSet.setRulesetName("Name");
		reactionRuleSet.setRulesetComment("Comment");
		reactionRuleSet.setLstUpdateBy("ktr");
		Date date =new Date();
		reactionRuleSet.setLogicallyDeletedDate(date);
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
	private Set<Long> getReactionRuleId(){
		Set<Long> reactionRuleIdSet = new HashSet<>();
		reactionRuleIdSet.add(5l);
		reactionRuleIdSet.add(2l);
		return reactionRuleIdSet;
	}

}
