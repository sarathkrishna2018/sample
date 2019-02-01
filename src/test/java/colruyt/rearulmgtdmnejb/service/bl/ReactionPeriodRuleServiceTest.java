package colruyt.rearulmgtdmnejb.service.bl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactionPeriodRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionPeriodActionDlService;
import colruyt.rearulmgtdmnejb.util.ReactionPeriodRuleConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactionPeriodRuleServiceTest {
	@TestedObject
	private ReactionPeriodRuleService reactionPeriodRuleBlService;
	@InjectIntoByType
	private ReactionPeriodActionDlService reactionPeriodActionDlService = Mockito
			.mock(ReactionPeriodActionDlService.class);
	@InjectIntoByType
	private ReactionPeriodRuleConverter reactionPeriodRuleConverter = Mockito.mock(ReactionPeriodRuleConverter.class);
	@InjectIntoByType
	private ReactionRuleSetService reactionRuleSetBlService = Mockito.mock(ReactionRuleSetService.class);
	@InjectMocks
	private GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class);

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);
	@Test
	public void createRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactionPeriodRuleConverter.convert(Mockito.any(ReactionPeriodRuleBo.class))).thenReturn(getReaPrdAct());
		when(reactionPeriodActionDlService.createOrUpdate(Mockito.any(ReactionPeriodRuleAction.class)))
				.thenReturn(getReaPrdAct());
		GeneralRuleBo expectedReactionPrdRule = reactionPeriodRuleBlService.createRuleSpecificAttributes(getReactionPeriodRuleBo());
		Assert.assertEquals(new Long(1l), expectedReactionPrdRule.getRuleId());
	}
	@Test
	public void modifyRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException{
		when(reactionPeriodRuleConverter.convert(Mockito.any(ReactionPeriodRuleBo.class))).thenReturn(getReaPrdAct());
		when(reactionPeriodActionDlService.createOrUpdate(Mockito.any(ReactionPeriodRuleAction.class)))
				.thenReturn(getReaPrdAct());
		GeneralRuleBo expectedReactionPrdRule = reactionPeriodRuleBlService.modifyRuleSpecificAttributes(getReactionPeriodRuleBo());
		Assert.assertEquals(new Long(1l), expectedReactionPrdRule.getRuleId());
	}
	@Test
	public void getReactionRulesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		Long ruleId = 1l;
		ReactionRulesetBo reactionRulesetBo = getReactionRulesetBo();
		when(generalRuleService.getRuleTypeId(Mockito.anyString())).thenReturn(ruleId);
		when(referenceDataService.findPkByType(Mockito.anyString())).thenReturn(ruleId);
		reactionRulesetBo.setRuleLines(getReactionRuleBoList());
		when(generalRuleService.getRulesByRuleSetId(Mockito.anyLong())).thenReturn(getRuleList());
		when(generalRuleService.getGeneralRuleAttributes(Mockito.any(ReactionRule.class),
				Mockito.any(GeneralRuleBo.class))).thenReturn(getReactionPeriodRuleBo());
		when(reactionPeriodActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaPrdAct());
		when(reactionPeriodRuleConverter.convertToBo(Mockito.any(ReactionPeriodRuleAction.class),
				Mockito.any(ReactionPeriodRuleBo.class))).thenReturn(getReactionPeriodRuleBo());
		List<ReactionRulesetBo> expectedReactionPrdRule = reactionPeriodRuleBlService.getReactionRules(getReaRuleList());
		Assert.assertEquals(1l, expectedReactionPrdRule.size());
	}
	@Test
	public void getRuleSpecificValuesTest() throws ReaRuleManagementException {
		when(reactionPeriodActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaPrdAct());
		when(reactionPeriodRuleConverter.convertToBo(Mockito.any(ReactionPeriodRuleAction.class),
				Mockito.any(ReactionPeriodRuleBo.class))).thenReturn(getReactionPeriodRuleBo());
		GeneralRuleBo expectedReactionPrdRule = reactionPeriodRuleBlService.getRuleSpecificValues(getReactionPeriodRuleBo());
		Assert.assertEquals(new Long(1l), expectedReactionPrdRule.getRuleId());
	}

	@Test
	public void physicalDeleteElementsTest() {
		long id = 1;
		when(reactionPeriodActionDlService.physicalDeleteElements(Mockito.any(XPSRuleBo.class))).thenReturn(id);
		long expectedReactionPrdRule = reactionPeriodRuleBlService.physicalDeleteElements(getXpsRuleBo());
		Assert.assertNotNull(expectedReactionPrdRule);
	}

	private XPSRuleBo getXpsRuleBo() {
		XPSRuleBo xpsRuleBo = new XPSRuleBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
	}
	public ReactionPeriodRuleBo getReactionPeriodRuleBo() {
		ReactionPeriodRuleBo reactionPeriodRuleBo = new ReactionPeriodRuleBo();
		reactionPeriodRuleBo.setActionSelectAll(false);
		reactionPeriodRuleBo.setActionTypeList(getActionTypeList());
		reactionPeriodRuleBo.setAssortmentName("xyz");
		reactionPeriodRuleBo.setCatchAll(false);
		reactionPeriodRuleBo.setCheapBrand(true);
		reactionPeriodRuleBo.setComments("Good");
		reactionPeriodRuleBo.setDirectBenefit(true);
		reactionPeriodRuleBo.setImportanceCodeFrom(10l);
		reactionPeriodRuleBo.setImportanceCodeTo(15l);
		reactionPeriodRuleBo.setLangCode("EN");
		reactionPeriodRuleBo.setLogonId("xyz");
		reactionPeriodRuleBo.setNationalBrand(false);
		reactionPeriodRuleBo.setOwnBrand(false);
		reactionPeriodRuleBo.setPermanentDuration(true);
		reactionPeriodRuleBo.setTemporaryDuration(false);
		reactionPeriodRuleBo.setPostponedBenefit(false);
		reactionPeriodRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		reactionPeriodRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		reactionPeriodRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionPeriodRuleBo.setRuleId(1L);
		reactionPeriodRuleBo.setRuleName("Rule");
		reactionPeriodRuleBo.setRulesetId(1L);
		reactionPeriodRuleBo.setSourceSelectAll(false);
		reactionPeriodRuleBo.setSourceTypeList(getSourceTypeList());
		reactionPeriodRuleBo.setValidFrom(new Date());
		reactionPeriodRuleBo.setEndDateMinusDate(10l);
		reactionPeriodRuleBo.setMinimumDays(7l);
		return reactionPeriodRuleBo;
	}

	private List<GeneralRuleBo> getReactionRuleBoList() {
		List<GeneralRuleBo> generalRuleBos=Lists.newArrayList();
		GeneralRuleBo reactionRuleBo = new GeneralRuleBo();
		reactionRuleBo.setActionSelectAll(true);
		reactionRuleBo.setActionTypeList(getActionTypeList());
		reactionRuleBo.setAssortmentName("Products");
		reactionRuleBo.setCheapBrand(true);
		reactionRuleBo.setDirectBenefit(true);
		reactionRuleBo.setImportanceCodeFrom(1L);
		reactionRuleBo.setImportanceCodeTo(5L);
		reactionRuleBo.setLangCode("EN");
		reactionRuleBo.setLogonId("xyz");
		reactionRuleBo.setNationalBrand(false);
		reactionRuleBo.setOwnBrand(false);
		reactionRuleBo.setPermanentDuration(true);
		reactionRuleBo.setTemporaryDuration(false);
		reactionRuleBo.setPostponedBenefit(false);
		reactionRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		reactionRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		reactionRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRuleBo.setRuleId(1L);
		reactionRuleBo.setRuleName("Rule");
		reactionRuleBo.setRulesetId(1L);
		reactionRuleBo.setSourceSelectAll(false);
		reactionRuleBo.setSourceTypeList(getSourceTypeList());
		reactionRuleBo.setValidFrom(new Date());
		generalRuleBos.add(reactionRuleBo);
		return generalRuleBos;
	}

	private List<ProductHierarchyElementBo> getPriceProductHierarchyList() {
		List<ProductHierarchyElementBo> hierarchyElementBos = new ArrayList<>();
		ProductHierarchyElementBo hierarchyElementBo = new ProductHierarchyElementBo();
		hierarchyElementBo.setId(1L);
		hierarchyElementBo.setPriceProductHierarchyTypeId(1L);
		hierarchyElementBo.setPriceProductHierarchyValue("1");
		hierarchyElementBos.add(hierarchyElementBo);
		return hierarchyElementBos;
	}

	private List<RefActionTypeBo> getActionTypeList() {
		List<RefActionTypeBo> refActionTypeBos = new ArrayList<>();
		RefActionTypeBo refActionTypeBo = new RefActionTypeBo();
		refActionTypeBo.setActionTypeId(1L);
		refActionTypeBos.add(refActionTypeBo);
		return refActionTypeBos;
	}

	private List<RefSourceTypeBo> getSourceTypeList() {
		List<RefSourceTypeBo> refSourceTypeBos = new ArrayList<>();
		RefSourceTypeBo refSourceTypeBo = new RefSourceTypeBo();
		refSourceTypeBo.setSourceName("Online");
		refSourceTypeBo.setSourceTypeId(1);
		refSourceTypeBos.add(refSourceTypeBo);
		return refSourceTypeBos;
	}

	private RefRuleTypeBo getRefRuleTypeBo() {
		RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
		refRuleTypeBo.setRuleTypeId(6L);
		return refRuleTypeBo;
	}

	private ReactionRulesetBo getReactionRulesetBo() {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1L);
		reactionRulesetBo.setName("Ruleset");
		reactionRulesetBo.setPriceCompetitorChainId(1L);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRulesetBo.setRulesetId(1L);
		return reactionRulesetBo;
	}

	public ReactionRule getReaRule() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRuleId(1L);
		reaRule.setReaRulesetId(1l);
		reaRule.setRuleName("Rule");
		reaRule.setIcFrom(10);
		reaRule.setIcTo(5);
		reaRule.setDirectYn(true);
		reaRule.setPostponedYn(true);
		reaRule.setPermenantYn(true);
		reaRule.setTemporaryYn(false);
		reaRule.setValidFrom(validFromdate);
		reaRule.setValidUpto(validTodate);
		reaRule.setRecalculateYn(false);
		reaRule.setRuleComment("good");
		reaRule.setCreatedBy("sa");
		reaRule.setLstUpdateBy("sa");
		return reaRule;
	}

	public ReactionRulesetBo createReactionRuleset() {
		ReactionRulesetBo reactionRuleset = new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setComments("good");
		return reactionRuleset;
	}

	public ReactionPeriodRuleAction getReaPrdAct() {
		ReactionPeriodRuleAction reaPrdAct = new ReactionPeriodRuleAction();
		reaPrdAct.setReaRuleId(1l);
		reaPrdAct.setEndDtDays(10l);
		reaPrdAct.setMinDays(8l);
		return reaPrdAct;
	}

	private List<ReactionRule> getRuleList() {
		List<ReactionRule> ruleList = Lists.newArrayList();
		ReactionRule rule = getReaRule();
		ruleList.add(rule);
		return ruleList;
	}

	private List<ReactionRulesetBo> getReaRuleList() {
		List<ReactionRulesetBo> reaList = Lists.newArrayList();
		ReactionRulesetBo ruleSetBo = getReactionRulesetBo();
		reaList.add(ruleSetBo);
		return reaList;
	}

}
