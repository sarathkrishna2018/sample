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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactingRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ReactingRuleConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ReactingRuleServiceTest {
	@TestedObject
	private ReactingRuleService reactingRuleBlService;
	@InjectIntoByType
	private ReactingRuleActionDlService reactingRuleActionDlService = Mockito.mock(ReactingRuleActionDlService.class);;
	@InjectIntoByType
	private ReactingRuleConverter reactingRuleConverter = Mockito.mock(ReactingRuleConverter.class);
	@InjectMocks
	private GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class);

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);

	@Test
	public void createRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactingRuleConverter.convert(Mockito.any(ReactingRuleBo.class))).thenReturn(getReareactingAct());
		when(reactingRuleActionDlService.createOrUpdate(Mockito.any(ReactingRuleAction.class)))
				.thenReturn(getReareactingAct());
		GeneralRuleBo expectedReactingRule = reactingRuleBlService.createRuleSpecificAttributes(getReactingRuleBo());
		Assert.assertEquals(new Long(1l), expectedReactingRule.getRuleId());
	}

	@Test
	public void modifyRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactingRuleConverter.convert(Mockito.any(ReactingRuleBo.class))).thenReturn(getReareactingAct());
		when(reactingRuleActionDlService.createOrUpdate(Mockito.any(ReactingRuleAction.class)))
				.thenReturn(getReareactingAct());
		GeneralRuleBo expectedReactingRule = reactingRuleBlService.modifyRuleSpecificAttributes(getReactingRuleBo());
		Assert.assertEquals(new Long(1l), expectedReactingRule.getRuleId());
	}

	@Test
	public void getRuleSpecificValuesTest() throws ReaRuleManagementException {
		when(reactingRuleActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReareactingAct());
		when(reactingRuleConverter.addingReactionRuleAction(Mockito.any(ReactingRuleAction.class),
				Mockito.any(ReactingRuleBo.class))).thenReturn(getReactingRuleBo());
		GeneralRuleBo expectedReactingRule = reactingRuleBlService.getRuleSpecificValues(getReactingRuleBo());
		Assert.assertEquals(new Long(1l), expectedReactingRule.getRuleId());
	}

	@Test
	public void physicalDeleteElementsTest() {
		Mockito.doNothing().when(reactingRuleActionDlService).physicalDeleteElements(getXpsRuleBo());
		reactingRuleBlService.physicalDeleteElements(getXpsRuleBo());
		Mockito.verify(reactingRuleActionDlService).physicalDeleteElements(getXpsRuleBo());
	}

	private DeleteRuleInfoBo getXpsRuleBo() {
		DeleteRuleInfoBo xpsRuleBo = new DeleteRuleInfoBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
	}

	public ReactingRuleBo getReactingRuleBo() {
		ReactingRuleBo reactingRuleBo = new ReactingRuleBo();
		reactingRuleBo.setActionSelectAll(false);
		reactingRuleBo.setActionTypeList(getActionTypeList());
		reactingRuleBo.setAssortmentName("xyz");
		reactingRuleBo.setCatchAll(false);
		reactingRuleBo.setCheapBrand(true);
		reactingRuleBo.setComments("Good");
		reactingRuleBo.setDirectBenefit(true);
		reactingRuleBo.setImportanceCodeFrom(10l);
		reactingRuleBo.setImportanceCodeTo(15l);
		reactingRuleBo.setLangCode("EN");
		reactingRuleBo.setLogonId("xyz");
		reactingRuleBo.setReactingAmount(15d);
		reactingRuleBo.setNationalBrand(false);
		reactingRuleBo.setOwnBrand(false);
		reactingRuleBo.setPermanentDuration(true);
		reactingRuleBo.setTemporaryDuration(false);
		reactingRuleBo.setPostponedBenefit(false);
		reactingRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		reactingRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		reactingRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactingRuleBo.setRuleId(1L);
		reactingRuleBo.setRuleName("Rule");
		reactingRuleBo.setRulesetId(1L);
		reactingRuleBo.setSourceSelectAll(false);
		reactingRuleBo.setSourceTypeList(getSourceTypeList());
		reactingRuleBo.setValidFrom(new Date());
		return reactingRuleBo;
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
		refRuleTypeBo.setRuleTypeId(1L);
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

	public ReactingRuleAction getReareactingAct() {
		ReactingRuleAction reaReactingAct = new ReactingRuleAction();
		reaReactingAct.setReactionRuleId(1l);
		reaReactingAct.setCatchAll(true);
		reaReactingAct.setReactingAmt(12d);
		reaReactingAct.setReactingPercentage(8d);
		reaReactingAct.setThresholdAmount(10d);
		reaReactingAct.setThresholdPercentage(6d);
		return reaReactingAct;

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

	public ReactionRulesetBo createReactionRuleset() {
		ReactionRulesetBo reactionRuleset = new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setComments("good");
		return reactionRuleset;
	}

}
