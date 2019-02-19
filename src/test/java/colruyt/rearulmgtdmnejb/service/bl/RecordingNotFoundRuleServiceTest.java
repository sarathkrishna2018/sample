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
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.RecordingNotFoundRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.RecordingNotFoundRuleConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class RecordingNotFoundRuleServiceTest {
	@TestedObject
	private RecordingNotFoundRuleService recordingNotFoundRuleBlService;
	@InjectIntoByType
	private RecordingNotFoundRuleActionDlService recordingNotFoundRuleActionDlService = Mockito
			.mock(RecordingNotFoundRuleActionDlService.class);;
	@InjectIntoByType
	private RecordingNotFoundRuleConverter recordingNotFoundRuleConverter = Mockito
			.mock(RecordingNotFoundRuleConverter.class);
	@InjectMocks
	private GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class);

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);
	
	@Test
	public void createRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(recordingNotFoundRuleConverter.convert(Mockito.any(RecordingNotFoundRuleBo.class))).thenReturn(getRecordingNotFoundRule());
		when(recordingNotFoundRuleActionDlService.createOrUpdate(Mockito.any(RecordingNotFoundRuleAction.class)))
				.thenReturn(getRecordingNotFoundRule());
		GeneralRuleBo expectedRecordNotFoundRule = recordingNotFoundRuleBlService.createRuleSpecificAttributes(getRecordingNotFoundRuleBo());
		Assert.assertEquals(new Long(1l), expectedRecordNotFoundRule.getRuleId());
	}
	@Test
	public void modifyRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException{
		when(recordingNotFoundRuleConverter.convert(Mockito.any(RecordingNotFoundRuleBo.class))).thenReturn(getRecordingNotFoundRule());
		when(recordingNotFoundRuleActionDlService.createOrUpdate(Mockito.any(RecordingNotFoundRuleAction.class)))
				.thenReturn(getRecordingNotFoundRule());
		GeneralRuleBo expectedRecordNotFoundRule = recordingNotFoundRuleBlService.modifyRuleSpecificAttributes(getRecordingNotFoundRuleBo());
		Assert.assertEquals(new Long(1l), expectedRecordNotFoundRule.getRuleId());
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
				Mockito.any(GeneralRuleBo.class))).thenReturn(getRecordingNotFoundRuleBo());
		when(recordingNotFoundRuleActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getRecordingNotFoundRule());
		when(recordingNotFoundRuleConverter.addRecordingNotFoundRuleAction(Mockito.any(RecordingNotFoundRuleAction.class),
				Mockito.any(RecordingNotFoundRuleBo.class))).thenReturn(getRecordingNotFoundRuleBo());
		List<ReactionRulesetBo> expectedRecordNotFoundRule = recordingNotFoundRuleBlService.getReactionRules(getReaRuleList());
		Assert.assertEquals(1l, expectedRecordNotFoundRule.size());
	}
	@Test
	public void getRuleSpecificValuesTest() throws ReaRuleManagementException {
		when(recordingNotFoundRuleActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getRecordingNotFoundRule());
		when(recordingNotFoundRuleConverter.addRecordingNotFoundRuleAction(Mockito.any(RecordingNotFoundRuleAction.class),
				Mockito.any(RecordingNotFoundRuleBo.class))).thenReturn(getRecordingNotFoundRuleBo());
		GeneralRuleBo expectedRecordNotFoundRule = recordingNotFoundRuleBlService.getRuleSpecificValues(getRecordingNotFoundRuleBo());
		Assert.assertEquals(new Long(1l), expectedRecordNotFoundRule.getRuleId());
	}
	/*@Test
	public void physicalDeleteElementsTest() {
		long id = 1;
		when(recordingNotFoundRuleActionDlService.physicalDeleteElements(Mockito.any(DeleteRuleInfoBo.class))).thenReturn(id);
		long expectedRecordNotFoundRule = recordingNotFoundRuleBlService.physicalDeleteElements(getXpsRuleBo());
		Assert.assertNotNull(expectedRecordNotFoundRule);
	}*/

	private DeleteRuleInfoBo getXpsRuleBo() {
		DeleteRuleInfoBo xpsRuleBo = new DeleteRuleInfoBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
	}

	private RecordingNotFoundRuleBo getRecordingNotFoundRuleBo() {
		RecordingNotFoundRuleBo recordingNotFoundRuleBo = new RecordingNotFoundRuleBo();
		recordingNotFoundRuleBo.setActionSelectAll(false);
		recordingNotFoundRuleBo.setActionTypeList(getActionTypeList());
		recordingNotFoundRuleBo.setAssortmentName("Products");
		recordingNotFoundRuleBo.setCheapBrand(true);
		recordingNotFoundRuleBo.setDirectBenefit(true);
		recordingNotFoundRuleBo.setImportanceCodeFrom(1L);
		recordingNotFoundRuleBo.setImportanceCodeTo(5L);
		recordingNotFoundRuleBo.setLangCode("EN");
		recordingNotFoundRuleBo.setLogonId("xyz");
		recordingNotFoundRuleBo.setNationalBrand(false);
		recordingNotFoundRuleBo.setOwnBrand(false);
		recordingNotFoundRuleBo.setPermanentDuration(true);
		recordingNotFoundRuleBo.setTemporaryDuration(false);
		recordingNotFoundRuleBo.setPostponedBenefit(false);
		recordingNotFoundRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		recordingNotFoundRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		recordingNotFoundRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		recordingNotFoundRuleBo.setRuleId(1L);
		recordingNotFoundRuleBo.setRuleName("Rule");
		recordingNotFoundRuleBo.setRulesetId(1L);
		recordingNotFoundRuleBo.setSourceSelectAll(false);
		recordingNotFoundRuleBo.setSourceTypeList(getSourceTypeList());
		recordingNotFoundRuleBo.setValidFrom(new Date());
		recordingNotFoundRuleBo.setNoOfNotFoundRecordings(5L);
		return recordingNotFoundRuleBo;
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
		refRuleTypeBo.setRuleTypeId(4L);
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
		refActionTypeBo.setActionTypeId(1L);
		refActionTypeBos.add(refActionTypeBo);
		return refActionTypeBos;
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

	public ReactionRulesetBo createReactionRuleset() {
		ReactionRulesetBo reactionRuleset = new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setComments("good");
		return reactionRuleset;
	}

	public RecordingNotFoundRuleAction getRecordingNotFoundRule() {
		RecordingNotFoundRuleAction reaRnfAct = new RecordingNotFoundRuleAction();
		reaRnfAct.setNoOfRecordNotFound(5L);
		return reaRnfAct;
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
