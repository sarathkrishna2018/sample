package colruyt.rearulmgtdmnejb.service.bl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.FilteringRuleActionDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleDlService;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class FilteringRuleServiceTest {
	@TestedObject
	private FilteringRuleService filteringRuleBlService;

	@InjectIntoByType
	private FilteringRuleActionDlService filteringRuleActionDlService = Mockito
			.mock(FilteringRuleActionDlService.class);
	@InjectIntoByType
	private ReactionRuleDlService reactionRuleDlService = Mockito.mock(ReactionRuleDlService.class);
	@InjectMocks
	private GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class);

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);
	
	@Test
	public void createRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(filteringRuleActionDlService.createOrUpdate(Mockito.any(FilteringRuleAction.class)))
				.thenReturn(getReaFltRule());
		GeneralRuleBo expectedFilteringRule = filteringRuleBlService.createRuleSpecificAttributes(getFilteringRuleBo());
		Assert.assertEquals(new Long(1l), expectedFilteringRule.getRuleId());
	}

	@Test
	public void modifyRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(filteringRuleActionDlService.createOrUpdate(Mockito.any(FilteringRuleAction.class)))
				.thenReturn(getReaFltRule());
		GeneralRuleBo expectedFilteringRule = filteringRuleBlService.modifyRuleSpecificAttributes(getFilteringRuleBo());
		Assert.assertEquals(new Long(1l), expectedFilteringRule.getRuleId());
	}
	@Test
	public void getRuleSpecificValuesTest() throws ReaRuleManagementException {
		when(filteringRuleActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaFltRule());
		GeneralRuleBo expectedFilteringRule = filteringRuleBlService.getRuleSpecificValues(getFilteringRuleBo());
		Assert.assertEquals(new Long(1l), expectedFilteringRule.getRuleId());
	}

	@Test
	public void physicalDeleteElementsTest() {
		Mockito.doNothing().when(filteringRuleActionDlService).physicalDeleteElements(getDeleteRuleInfoBo());
		filteringRuleBlService.physicalDeleteElements(getDeleteRuleInfoBo());
		Mockito.verify(filteringRuleActionDlService).physicalDeleteElements(getDeleteRuleInfoBo());
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}

	private FilteringRuleBo getFilteringRuleBo() {
		FilteringRuleBo filteringRuleBo = new FilteringRuleBo();
		filteringRuleBo.setActionSelectAll(false);
		filteringRuleBo.setActionTypeList(getActionTypeList());
		filteringRuleBo.setAssortmentName("Products");
		filteringRuleBo.setCheapBrand(true);
		filteringRuleBo.setDirectBenefit(true);
		filteringRuleBo.setImportanceCodeFrom(1L);
		filteringRuleBo.setImportanceCodeTo(5L);
		filteringRuleBo.setLangCode("EN");
		filteringRuleBo.setLogonId("xyz");
		filteringRuleBo.setMaxCompQuantity(5D);
		filteringRuleBo.setNationalBrand(false);
		filteringRuleBo.setOwnBrand(false);
		filteringRuleBo.setPermanentDuration(true);
		filteringRuleBo.setTemporaryDuration(false);
		filteringRuleBo.setPostponedBenefit(false);
		filteringRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		filteringRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		filteringRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		filteringRuleBo.setRuleId(1L);
		filteringRuleBo.setRuleName("Rule");
		filteringRuleBo.setRulesetId(1L);
		filteringRuleBo.setSourceSelectAll(false);
		filteringRuleBo.setSourceTypeList(getSourceTypeList());
		filteringRuleBo.setValidFrom(new Date());
		return filteringRuleBo;
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
		refRuleTypeBo.setRuleTypeId(3);
		return refRuleTypeBo;
	}

	private ReactionRulesetBo getReactionRulesetBo() {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1L);
		reactionRulesetBo.setName("Ruleset");
		reactionRulesetBo.setPriceCompetitorChainId(1L);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRulesetBo.setRulesetId(1L);
		reactionRulesetBo.setRuleLines(getReactionRuleBoList());
		return reactionRulesetBo;
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
		refActionTypeBo.setActionTypeId(1);
		refActionTypeBos.add(refActionTypeBo);
		return refActionTypeBos;
	}

	private List<GeneralRuleBo> getReactionRuleBoList() {
		List<GeneralRuleBo> generalRuleBos = Lists.newArrayList();
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
		// reactionRuleBo.setReactionRulesetBo(getReactionRulesetBo());
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

	private GeneralRuleBo getGeneralRuleBo() {
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
		// reactionRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		reactionRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRuleBo.setRuleId(1L);
		reactionRuleBo.setRuleName("Rule");
		reactionRuleBo.setRulesetId(1L);
		reactionRuleBo.setSourceSelectAll(false);
		reactionRuleBo.setSourceTypeList(getSourceTypeList());
		reactionRuleBo.setValidFrom(new Date());
		return reactionRuleBo;
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

	public FilteringRuleAction getReaFltRule() {
		FilteringRuleAction reaFltRule = new FilteringRuleAction();
		reaFltRule.setReactionRuleId(1l);
		reaFltRule.setMaximumCompetitorQuantity(12d);
		reaFltRule.setXTimeQty(1d);
		return reaFltRule;
	}

	public ReactionRule getReaRule() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRuleId(1L);
		reaRule.setReaRulesetId(1l);
		reaRule.setRuleName("Rule");
		reaRule.setImportancecodeFrom(10);
		reaRule.setImportancecodeTo(5);
		reaRule.setDirect(true);
		reaRule.setPostponed(true);
		reaRule.setPermenant(true);
		reaRule.setTemporary(false);
		reaRule.setValidFrom(validFromdate);
		reaRule.setValidUpto(validTodate);
		reaRule.setRecalculate(false);
		reaRule.setRuleComment("good");
		reaRule.setCreatedBy("sa");
		reaRule.setLstUpdateBy("sa");
		reaRule.setRefActionTypes(getActionTypes());
		reaRule.setRefSourceTypes(getSourceTypes());
		return reaRule;
	}

	private List<ActionType> getActionTypes() {
		List<ActionType> actionTypeList = Lists.newArrayList();
		actionTypeList.add(ActionType.ALL);
		return actionTypeList;

	}

	private List<SourceType> getSourceTypes() {
		List<SourceType> sourceTypeList = Lists.newArrayList();
		sourceTypeList.add(SourceType.ALL);
		return sourceTypeList;

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
