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
import colruyt.rearulmgtdmnejb.bo.ProposeNotToReactRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleActionRsn;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleActionRsnPK;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ProposalNotToReactActionDlService;
import colruyt.rearulmgtdmnejb.util.ProposeNotToReactRuleConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ProposeNotToReactRuleServiceTest {
	@TestedObject
	private ProposeNotToReactRuleService proposeNotToReactRuleService;
	@InjectIntoByType
	private ProposalNotToReactActionDlService proposalNotToReactActionDlService =  Mockito.mock(ProposalNotToReactActionDlService.class);
	@InjectIntoByType
	private ProposeNotToReactRuleConverter proposeNotToReactRuleConverter = Mockito.mock(ProposeNotToReactRuleConverter.class);
	@InjectMocks
	private GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class);

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);
	
	@Test
	public void createRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException{
		when(proposeNotToReactRuleConverter.convert(Mockito.any(ProposeNotToReactRuleBo.class))).thenReturn(getReaNreactAct());
		when(proposalNotToReactActionDlService.createOrUpdate(Mockito.any(ProposalNotToReactRuleAction.class))).thenReturn(getReaNreactAct());
		GeneralRuleBo expectedProposeNotToReactRule = proposeNotToReactRuleService.createRuleSpecificAttributes(getProposeNotToReactRuleBo());
		Assert.assertEquals(new Long(1l), expectedProposeNotToReactRule.getRuleId());
	}
	@Test
	public void modifyRuleSpecificAttributesTest() throws ReaRuleValidationException, ReaRuleManagementException {
		Long ruleId=1l;
		when(proposeNotToReactRuleConverter.convert(Mockito.any(ProposeNotToReactRuleBo.class))).thenReturn(getReaNreactAct());
		when(proposalNotToReactActionDlService.createOrUpdate(Mockito.any(ProposalNotToReactRuleAction.class))).thenReturn(getReaNreactAct());
		GeneralRuleBo expectedProposeNotToReactRule = proposeNotToReactRuleService.modifyRuleSpecificAttributes(getProposeNotToReactRuleBo());
		Assert.assertEquals(new Long(1l), expectedProposeNotToReactRule.getRuleId());
	}
	@Test
	public void getReactionRulesTest() throws ReaRuleValidationException, ReaRuleManagementException{
		Long ruleId = 1l;
		ReactionRulesetBo reactionRulesetBo = getReactionRulesetBo();
		when(generalRuleService.getRuleTypeId(Mockito.anyString())).thenReturn(ruleId);
		when(referenceDataService.findPkByType(Mockito.anyString())).thenReturn(ruleId);
		reactionRulesetBo.setRuleLines(getReactionRuleBoList());
		when(generalRuleService.getRulesByRuleSetId(Mockito.anyLong())).thenReturn(getRuleList());
		when(generalRuleService.getGeneralRuleAttributes(Mockito.any(ReactionRule.class),
				Mockito.any(GeneralRuleBo.class))).thenReturn(getProposeNotToReactRuleBo());
		when(proposalNotToReactActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaNreactAct());
		when(proposeNotToReactRuleConverter.convertToBo(Mockito.any(ProposalNotToReactRuleAction.class), Mockito.any(ProposeNotToReactRuleBo.class))).thenReturn(getProposeNotToReactRuleBo());
		List<ReactionRulesetBo> expectedProposeNotToReactRule = proposeNotToReactRuleService.getReactionRules(getReaRuleList());
		Assert.assertEquals(1, expectedProposeNotToReactRule.size());
	}
	@Test
	public void getRuleSpecificValuesTest() throws ReaRuleManagementException{
		when(proposalNotToReactActionDlService.findByRuleId(Mockito.anyLong())).thenReturn(getReaNreactAct());
		when(proposeNotToReactRuleConverter.convertToBo(Mockito.any(ProposalNotToReactRuleAction.class), Mockito.any(ProposeNotToReactRuleBo.class))).thenReturn(getProposeNotToReactRuleBo());
		GeneralRuleBo expectedProposeNotToReactRule = proposeNotToReactRuleService.getRuleSpecificValues(getProposeNotToReactRuleBo());
		Assert.assertEquals(new Long(1l), expectedProposeNotToReactRule.getRuleId());
	}
	/*@Test
	public void physicalDeleteElementsTest(){
		long id = 1;
		when(proposalNotToReactActionDlService.physicalDeleteElementsRsn(Mockito.any(DeleteRuleInfoBo.class))).thenReturn(id);
		when(proposalNotToReactActionDlService.physicalDeleteElements(Mockito.any(DeleteRuleInfoBo.class))).thenReturn(id);
		long expectedProposeNotToReactRule = proposeNotToReactRuleService.physicalDeleteElements(getXpsRuleBo());
		Assert.assertNotNull(expectedProposeNotToReactRule);
		
	}*/
	
	private DeleteRuleInfoBo getXpsRuleBo() {
		DeleteRuleInfoBo xpsRuleBo = new DeleteRuleInfoBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
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
	
	private List<RefActionTypeBo> getActionTypeList() {
		List<RefActionTypeBo> refActionTypeBos = new ArrayList<>();
		RefActionTypeBo refActionTypeBo = new RefActionTypeBo();
		refActionTypeBo.setActionTypeId(1L);
		refActionTypeBos.add(refActionTypeBo);
		return refActionTypeBos;
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

	public ProposeNotToReactRuleBo getProposeNotToReactRuleBo() {
		ProposeNotToReactRuleBo proposeNotToReactRuleBo = new ProposeNotToReactRuleBo();
		proposeNotToReactRuleBo.setActionSelectAll(false);
		proposeNotToReactRuleBo.setActionTypeList(getActionTypeList());
		proposeNotToReactRuleBo.setAssortmentName("xyz");
		proposeNotToReactRuleBo.setCatchAll(false);
		proposeNotToReactRuleBo.setCheapBrand(true);
		proposeNotToReactRuleBo.setComments("Good");
		proposeNotToReactRuleBo.setDirectBenefit(true);
		proposeNotToReactRuleBo.setImportanceCodeFrom(10l);
		proposeNotToReactRuleBo.setImportanceCodeTo(15l);
		proposeNotToReactRuleBo.setLangCode("EN");
		proposeNotToReactRuleBo.setLogonId("xyz");
		proposeNotToReactRuleBo.setNationalBrand(false);
		proposeNotToReactRuleBo.setOwnBrand(false);
		proposeNotToReactRuleBo.setPermanentDuration(true);
		proposeNotToReactRuleBo.setTemporaryDuration(false);
		proposeNotToReactRuleBo.setPostponedBenefit(false);
		proposeNotToReactRuleBo.setPriceProductHierarchySet(getPriceProductHierarchyList());
		proposeNotToReactRuleBo.setReactionRulesetBo(getReactionRulesetBo());
		proposeNotToReactRuleBo.setRefRuleTypeBo(getRefRuleTypeBo());
		proposeNotToReactRuleBo.setRuleId(1L);
		proposeNotToReactRuleBo.setRuleName("Rule");
		proposeNotToReactRuleBo.setRulesetId(1L);
		proposeNotToReactRuleBo.setSourceSelectAll(false);
		proposeNotToReactRuleBo.setSourceTypeList(getSourceTypeList());
		proposeNotToReactRuleBo.setValidFrom(new Date());
		proposeNotToReactRuleBo.setFilterOutType(getRefFilterOutRecordingTypeBo());
		proposeNotToReactRuleBo.setNotToReactCodes(getRefNotToReactCodeBo());
		return proposeNotToReactRuleBo;
	}
	public ProposalNotToReactRuleAction getReaNreactAct() {
		ProposalNotToReactRuleAction reaNreactAct = new ProposalNotToReactRuleAction();
		reaNreactAct.setReactionRuleId(1);
		reaNreactAct.setFltoutTypeId(2);
		return reaNreactAct;
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
		refRuleTypeBo.setRuleTypeId(5L);
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

	public List<ProposalNotToReactRuleActionRsn> getReaNreactSetRsn() {
		List<ProposalNotToReactRuleActionRsn> reaNreactSetRsnlist = Lists.newArrayList();
		ProposalNotToReactRuleActionRsn reaNreactSetRsn = new ProposalNotToReactRuleActionRsn();
		reaNreactSetRsn.setId(getreaNreactSetRsnPK());
		reaNreactSetRsn.setLstUpdateBy("xxx");
		reaNreactSetRsnlist.add(reaNreactSetRsn);
		return reaNreactSetRsnlist;
	}

	public ProposalNotToReactRuleActionRsnPK getreaNreactSetRsnPK() {
		ProposalNotToReactRuleActionRsnPK reaNreactSetRsnPK = new ProposalNotToReactRuleActionRsnPK();
		reaNreactSetRsnPK.setReactionRuleId(1);
		reaNreactSetRsnPK.setReasonId(2);
		return reaNreactSetRsnPK;
	}
	public RefFilterOutRecordingTypeBo getRefFilterOutRecordingTypeBo() {
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
		refFilterOutRecordingTypeBo.setFilterOutTypeId(1l);
		refFilterOutRecordingTypeBo.setDescription("xcx");
		refFilterOutRecordingTypeBo.setCodeLang(getRefLangBo());
		return refFilterOutRecordingTypeBo;

	}

	public List<RefLangBo> getRefLangBo() {
		List<RefLangBo> refLangBolist = Lists.newArrayList();
		RefLangBo refLangBo = new RefLangBo();
		refLangBo.setIsoLangCode("EN");
		refLangBo.setValue("English");
		refLangBolist.add(refLangBo);
		return refLangBolist;

	}

	public List<RefNotToReactCodeBo> getRefNotToReactCodeBo() {
		List<RefNotToReactCodeBo> refNotToReactCodeBolist = Lists.newArrayList();
		RefNotToReactCodeBo refNotToReactCodeBo = new RefNotToReactCodeBo();
		refNotToReactCodeBo.setCodeLang(getRefLangBo());
		refNotToReactCodeBo.setNotToReactCodeTypeId(1l);
		refNotToReactCodeBo.setDescription("scx");
		refNotToReactCodeBolist.add(refNotToReactCodeBo);
		return refNotToReactCodeBolist;
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
	private List<ReactionRule> getRuleList(){
		List<ReactionRule> ruleList = Lists.newArrayList();
		ReactionRule rule = getReaRule();
		 ruleList.add(rule); 
		 return ruleList;
	}
	
	private List<ReactionRulesetBo> getReaRuleList(){
		List<ReactionRulesetBo> reaList = Lists.newArrayList();
		ReactionRulesetBo ruleSetBo = getReactionRulesetBo();
		reaList.add(ruleSetBo);
		return reaList;
	}
	
	
}
