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
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.QuantityRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.QuantityRuleActionConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class QuantityRuleServiceTest {
	@TestedObject
	private QuantityRuleService quantityRuleBlService;
	@InjectIntoByType
	private QuantityRuleActionConverter quantityRuleActionConverter = Mockito.mock(QuantityRuleActionConverter.class);;
	@InjectIntoByType
	private QuantityRuleActionDlService quantityRuleActionDlService = Mockito.mock(QuantityRuleActionDlService.class);
	@InjectMocks
	private GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class);

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);

	@Test
	public void createRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(quantityRuleActionConverter.convert(Mockito.any(QuantityRuleBo.class))).thenReturn(getReaQtyRule());
		when(quantityRuleActionDlService.createOrUpdate(Mockito.any(QuantityRuleAction.class)))
				.thenReturn(getReaQtyRule());
		GeneralRuleBo expectedQuantityRule = quantityRuleBlService.createRuleSpecificAttributes(getQuantityRuleBo());
		Assert.assertEquals(new Long(1l), expectedQuantityRule.getRuleId());
	}

	@Test
	public void modifyRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(quantityRuleActionConverter.convert(Mockito.any(QuantityRuleBo.class))).thenReturn(getReaQtyRule());
		when(quantityRuleActionDlService.createOrUpdate(Mockito.any(QuantityRuleAction.class)))
				.thenReturn(getReaQtyRule());
		GeneralRuleBo expectedQuantityRule = quantityRuleBlService.modifyRuleSpecificAttributes(getQuantityRuleBo());
		Assert.assertEquals(new Long(1l), expectedQuantityRule.getRuleId());
	}

	@Test
	public void getReactionRulesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		int ruleId = 1;
		ReactionRulesetBo reactionRulesetBo = getReactionRulesetBo();
		when(generalRuleService.getRuleTypeId(Mockito.anyString())).thenReturn(ruleId);
		when(referenceDataService.findPkByType(Mockito.anyString())).thenReturn(ruleId);
		reactionRulesetBo.setRuleLines(getReactionRuleBoList());
		when(generalRuleService.getRulesByRuleSetId(Mockito.anyLong())).thenReturn(getRuleList());
		when(generalRuleService.getGeneralRuleAttributes(Mockito.any(ReactionRule.class),
				Mockito.any(GeneralRuleBo.class))).thenReturn(getQuantityRuleBo());
		when(quantityRuleActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaQtyRule());
		when(quantityRuleActionConverter.addQuantityRuleAction(Mockito.any(QuantityRuleAction.class),
				Mockito.any(QuantityRuleBo.class))).thenReturn(getQuantityRuleBo());
		List<ReactionRulesetBo> expectedQuantityRule = quantityRuleBlService.getReactionRules(getReaRuleList());
		Assert.assertEquals(1l, expectedQuantityRule.size());
	}

	@Test
	public void getRuleSpecificValuesTest() throws ReaRuleManagementException {
		when(quantityRuleActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaQtyRule());
		when(quantityRuleActionConverter.addQuantityRuleAction(Mockito.any(QuantityRuleAction.class),
				Mockito.any(QuantityRuleBo.class))).thenReturn(getQuantityRuleBo());
		GeneralRuleBo expectedQuantityRule = quantityRuleBlService.getRuleSpecificValues(getQuantityRuleBo());
		Assert.assertEquals(new Long(1l), expectedQuantityRule.getRuleId());
	}

	@Test
	public void physicalDeleteElementsTest() {
		Mockito.doNothing().when(quantityRuleActionDlService).physicalDeleteElements(getXpsRuleBo());
		quantityRuleBlService.physicalDeleteElements(getXpsRuleBo());
		Mockito.verify(quantityRuleActionDlService).physicalDeleteElements(getXpsRuleBo());
	}

	private DeleteRuleInfoBo getXpsRuleBo() {
		DeleteRuleInfoBo xpsRuleBo = new DeleteRuleInfoBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
	}

	private QuantityRuleBo getQuantityRuleBo() {
		QuantityRuleBo quantityRuleBo = new QuantityRuleBo();
		quantityRuleBo.setActionSelectAll(false);
		quantityRuleBo.setActionTypeList(getActionTypeList());
		quantityRuleBo.setAssortmentName("Products");
		quantityRuleBo.setCheapBrand(true);
		quantityRuleBo.setDirectBenefit(true);
		quantityRuleBo.setImportanceCodeFrom(1L);
		quantityRuleBo.setImportanceCodeTo(5L);
		quantityRuleBo.setLangCode("EN");
		quantityRuleBo.setLogonId("xyz");
		quantityRuleBo.setNationalBrand(false);
		quantityRuleBo.setOwnBrand(false);
		quantityRuleBo.setPermanentDuration(true);
		quantityRuleBo.setTemporaryDuration(false);
		quantityRuleBo.setPostponedBenefit(false);
		quantityRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		quantityRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		quantityRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		quantityRuleBo.setRuleId(1L);
		quantityRuleBo.setRuleName("Rule");
		quantityRuleBo.setRulesetId(1L);
		quantityRuleBo.setSourceSelectAll(false);
		quantityRuleBo.setSourceTypeList(getSourceTypeList());
		quantityRuleBo.setValidFrom(new Date());
		RefQuantityPriceTypeBo quantityPriceType = new RefQuantityPriceTypeBo();
		quantityPriceType.setQuantityTypeId(1);
		quantityRuleBo.setQuantityPriceType(quantityPriceType);
		RefQuantityConditionTypeBo conditionType = new RefQuantityConditionTypeBo();
		conditionType.setCodeTypeId(1);
		quantityRuleBo.setConditionType(conditionType);
		return quantityRuleBo;
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
		refRuleTypeBo.setRuleTypeId(2);
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

	public ReactionRulesetBo createReactionRuleset() {
		ReactionRulesetBo reactionRuleset = new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setComments("good");
		return reactionRuleset;
	}

	public QuantityRuleAction getReaQtyRule() {
		QuantityRuleAction reaQtyRule = new QuantityRuleAction();
		reaQtyRule.setQuantityConditionId(1);

		return reaQtyRule;
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
		return reaRule;
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
