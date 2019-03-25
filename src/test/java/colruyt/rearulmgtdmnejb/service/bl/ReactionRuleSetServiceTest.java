package colruyt.rearulmgtdmnejb.service.bl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleSetInfoBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSetDlService;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReactionRuleSetServiceTest {
	@TestedObject
	private ReactionRuleSetService reactionRuleSetBlService;
	@InjectIntoByType
	private ReactionRuleSetDlService reactionRuleSetDlService = Mockito.mock(ReactionRuleSetDlService.class);

	@Test(expected = ReaRuleManagementException.class)
	public void createReactionRuleSetTest() throws ReaRuleManagementException {
		long reactionRulesetId = 1l;
		long colruytGroupChainId = 1l;
		long priceCompetitorChainId = 1l;
		long ruleTypeId = 1;
		String logonId = "sa";
		when(reactionRuleSetDlService.findByAttributes(colruytGroupChainId, priceCompetitorChainId, ruleTypeId))
				.thenReturn(getReactionRuleset());
		when(reactionRuleSetDlService.findByPk(reactionRulesetId)).thenReturn(getReaRuleset());
		when(reactionRuleSetDlService.createOrUpdate(getReaRuleset())).thenReturn(getReaRuleset());
		ReactionRulesetBo expectedReactionRuleset = reactionRuleSetBlService
				.createReactionRuleSet(getReactionRulesetBo(), false, logonId);
		assertEquals(new Long(1l), expectedReactionRuleset.getRulesetId());
	}

	/*@Test
	public void findReactionRuleSetTest() {
		long cgChainId = 1;
		long compChainId = 2;
		when(reactionRuleSetDlService.findByCgChainAndPCChain(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(getReactionRuleset());
		List<ReactionRulesetBo> expectedReactionRulesetBo = reactionRuleSetBlService.find(cgChainId, compChainId);
		Assert.assertNotNull(expectedReactionRulesetBo);
	}*/

	/*@Test
	public void findReactionRuleSetFailTest() {
		List<ReactionRulesetBo> reactionRulesetBos = getReactionRuleSetList();
		long cgChainId = 1;
		long compChainId = 2;
		when(reactionRuleSetDlService.findByCgChainAndPCChain(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(getReactionRuleset());
		List<ReactionRulesetBo> expectedReactionRulesetBo = reactionRuleSetBlService.find(cgChainId, compChainId);
		Assert.assertNotSame(reactionRulesetBos.size(), expectedReactionRulesetBo.size());
	}*/

	@Test
	public void getReactionRulesetTest() throws ReaRuleManagementException {
		long rulesetId = 1;
		when(reactionRuleSetDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRuleset());
		ReactionRulesetBo expectedReactionRulesetBo = reactionRuleSetBlService.getReactionRuleset(rulesetId);
		assertEquals(new Long(1l), expectedReactionRulesetBo.getRulesetId());
	}

	@Test
	public void getReactionRulesetFailTest() throws ReaRuleManagementException {
		long rulesetId = 1;
		when(reactionRuleSetDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRuleset());
		ReactionRulesetBo expectedReactionRulesetBo = reactionRuleSetBlService.getReactionRuleset(rulesetId);
		Assert.assertNotSame(new Long(999l), expectedReactionRulesetBo.getRulesetId());
	}

	@Test
	public void modifyRuleSetDetailsTest() throws ReaRuleValidationException, ReaRuleManagementException {
		String logonId = "ake201h";
		when(reactionRuleSetDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRuleset());
		when(reactionRuleSetDlService.createOrUpdate(getReaRuleset())).thenReturn(getReaRuleset());
		reactionRuleSetBlService.modifyRuleSetDetails(getReactionRulesetId(), logonId);
		Mockito.verify(reactionRuleSetDlService).findByPk(Mockito.anyLong());

	}

	@Test
	public void logicallyDeleteReactionRuleSetTest() throws ReaRuleManagementException {
		long rulesetId = 1;
		when(reactionRuleSetDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRuleset());
		Mockito.doNothing().when(reactionRuleSetDlService).logicallyDeleteRuleSet(getReaRuleset());
		reactionRuleSetBlService.logicallyDeleteReactionRuleSet(rulesetId, "EN", "ktr18lq");
		Mockito.verify(reactionRuleSetDlService).findByPk(Mockito.anyLong());
		Mockito.verify(reactionRuleSetDlService).logicallyDeleteRuleSet(Mockito.any(ReactionRuleSet.class));
	}

	@Test(expected = ReaRuleManagementException.class)
	public void logicallyDeleteReactionRuleSetFailTest() throws ReaRuleManagementException {
		long rulesetId = 1;
		when(reactionRuleSetDlService.findByPk(Mockito.anyLong())).thenReturn(null);
		Mockito.doNothing().when(reactionRuleSetDlService).logicallyDeleteRuleSet(getReaRuleset());
		reactionRuleSetBlService.logicallyDeleteReactionRuleSet(rulesetId, "EN", "ktr18lq");

	}

	@Test
	public void findAllLogicallyDeletedRuleSetTest() {
		Date dateForRulesDelete = new Date();
		Date dateDeleteRuleSetBefore = new Date();
		when(reactionRuleSetDlService.findAllLogicallyDeletedRuleSet(dateForRulesDelete))
				.thenReturn(getXPSRuleSetBoList());
		List<DeleteRuleSetInfoBo> expectedXpsRulesetBo = reactionRuleSetBlService
				.findAllLogicallyDeletedRuleSet(dateDeleteRuleSetBefore);
		Assert.assertEquals(1l, expectedXpsRulesetBo.size());
	}

	private List<DeleteRuleSetInfoBo> getXPSRuleSetBoList() {
		List<DeleteRuleSetInfoBo> xpsRuleSetBos = Lists.newArrayList();
		DeleteRuleSetInfoBo xpsRuleSetBo = new DeleteRuleSetInfoBo(1l, 1l);
		xpsRuleSetBo.setRuleSetId(1l);
		xpsRuleSetBo.setRuleType(1l);
		xpsRuleSetBos.add(xpsRuleSetBo);
		return xpsRuleSetBos;
	}

	private List<ReactionRulesetBo> getReactionRuleSetList() {
		List<ReactionRulesetBo> reactionRulesetBos = Lists.newArrayList();
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1l);
		reactionRulesetBo.setComments("good");
		reactionRulesetBo.setName("aa");
		reactionRulesetBo.setPriceCompetitorChainId(1l);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRulesetBo.setRuleLines(getReactionRuleBo());
		reactionRulesetBo.setRulesetId(1l);
		reactionRulesetBos.add(reactionRulesetBo);
		return reactionRulesetBos;
	}

	public ReactionRulesetBo getReaRulesetBo() {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1l);
		reactionRulesetBo.setComments("good");
		reactionRulesetBo.setName("aa");
		reactionRulesetBo.setPriceCompetitorChainId(1l);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRulesetBo.setRuleLines(getReactionRuleBo());
		reactionRulesetBo.setRulesetId(1l);
		return reactionRulesetBo;

	}

	public ReactionRulesetBo getReactionRulesetBo() {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1l);
		reactionRulesetBo.setComments("good");
		reactionRulesetBo.setName("aa");
		reactionRulesetBo.setPriceCompetitorChainId(1l);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRulesetBo.setRuleLines(getReactionRuleBo());
		// reactionRulesetBo.setRulesetId(1l);
		return reactionRulesetBo;

	}

	public ReactionRulesetBo getReactionRulesetId() {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1l);
		reactionRulesetBo.setComments("good");
		reactionRulesetBo.setName("aa");
		reactionRulesetBo.setPriceCompetitorChainId(1l);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleTypeBo());
		reactionRulesetBo.setRuleLines(getReactionRuleBo());
		reactionRulesetBo.setRulesetId(1l);
		return reactionRulesetBo;

	}

	public RefRuleTypeBo getRefRuleTypeBo() {
		RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
		refRuleTypeBo.setRuleTypeId(1);
		refRuleTypeBo.setCodeLang(getRefLangBo());
		refRuleTypeBo.setDescription("English");
		return refRuleTypeBo;
	}

	public List<RefLangBo> getRefLangBo() {
		List<RefLangBo> refLangBolist = Lists.newArrayList();
		RefLangBo refLangBo = new RefLangBo();
		refLangBo.setIsoLangCode("En");
		refLangBo.setValue("English");
		refLangBolist.add(refLangBo);
		return refLangBolist;

	}

	public List<GeneralRuleBo> getReactionRuleBo() {
		List<GeneralRuleBo> reactionRuleBolist = Lists.newArrayList();
		GeneralRuleBo reactionRuleBo = new GeneralRuleBo();
		reactionRuleBo.setRuleName("Filtering");
		reactionRuleBo.setAssortmentName("wsa");
		reactionRuleBo.setComments("Good");
		reactionRuleBo.setLogonId("sa");
		reactionRuleBo.setRuleId(1l);
		reactionRuleBo.setRulesetId(2l);
		reactionRuleBo.setImportanceCodeFrom(10l);
		reactionRuleBo.setImportanceCodeTo(5l);
		reactionRuleBo.setProductHierarchySetId(1l);
		reactionRuleBo.setCatchAll(true);
		reactionRuleBo.setCheapBrand(true);
		reactionRuleBo.setDirectBenefit(true);
		reactionRuleBo.setNationalBrand(true);
		reactionRuleBo.setOwnBrand(true);
		reactionRuleBo.setPermanentDuration(true);
		reactionRuleBo.setPostponedBenefit(true);
		reactionRuleBo.setRecalculate(false);
		reactionRuleBo.setTemporaryDuration(false);
		reactionRuleBo.setValidFrom(new Date());
		reactionRuleBo.setValidTo(new Date());
		reactionRuleBo.setRefRuleTypeBo(getRefRuleType());
		reactionRuleBo.setReactionRulesetBo(createReactionRuleset());
		reactionRuleBo.setActionTypeList(getRefActionType());
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactionRuleBo.setSourceTypeList(getRefSourceType());
		reactionRuleBolist.add(reactionRuleBo);
		return reactionRuleBolist;

	}

	public RefRuleTypeBo getRefRuleType() {
		RefRuleTypeBo refRuleType = new RefRuleTypeBo();
		refRuleType.setDescription("asa");
		refRuleType.setRuleTypeId(1);
		refRuleType.setCodeLang(getRefLang());
		return refRuleType;
	}

	public List<RefLangBo> getRefLang() {
		List<RefLangBo> refLanglist = Lists.newArrayList();
		RefLangBo refLang = new RefLangBo();
		refLang.setIsoLangCode("EN");
		refLang.setValue("English");
		refLanglist.add(refLang);
		return refLanglist;
	}

	public List<RefActionTypeBo> getRefActionType() {
		List<RefActionTypeBo> refActionTypelist = Lists.newArrayList();
		RefActionTypeBo refActionType = new RefActionTypeBo();
		refActionType.setActionTypeId(1);
		refActionType.setActionTypeValue("asa");
		refActionType.setSequence(12l);
		refActionTypelist.add(refActionType);
		return refActionTypelist;

	}

	public List<ProductHierarchyElementBo> getProductHierarchyElement() {
		List<ProductHierarchyElementBo> productHierarchyElementlist = Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement = new ProductHierarchyElementBo();
		productHierarchyElement.setId(1l);
		productHierarchyElement.setPriceProductHierarchyTypeId(2l);
		productHierarchyElement.setPriceProductHierarchyValue("allProducts");
		productHierarchyElementlist.add(productHierarchyElement);
		return productHierarchyElementlist;
	}

	public List<RefSourceTypeBo> getRefSourceType() {
		List<RefSourceTypeBo> refSourceTypelist = Lists.newArrayList();
		RefSourceTypeBo refSourceType = new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		refSourceTypelist.add(refSourceType);
		return refSourceTypelist;
	}

	public ReactionRulesetBo createReactionRuleset() {
		ReactionRulesetBo reactionRuleset = new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setRefRuleTypeBo(getRefRuleType());
		reactionRuleset.setComments("good");
		return reactionRuleset;
	}

	public ReactionRuleSet getReaRuleset() {
		ReactionRuleSet reaRuleset = new ReactionRuleSet();
		reaRuleset.setColruytGroupChainId(1l);
		reaRuleset.setPriceCompetitorChainId(1l);
		reaRuleset.setLstUpdateBy("sa");
		reaRuleset.setReactionRules(getReaRule());
		reaRuleset.setReaRulesetId(1l);
		reaRuleset.setRulesetComment("good");
		reaRuleset.setRulesetName("asa");
		reaRuleset.setRuleTypeId(1);
		return reaRuleset;

	}

	public List<ReactionRuleSet> getReactionRuleset() {
		List<ReactionRuleSet> reaRulesetlist = Lists.newArrayList();
		ReactionRuleSet reaRuleset = new ReactionRuleSet();
		reaRuleset.setColruytGroupChainId(1l);
		reaRuleset.setPriceCompetitorChainId(1l);
		reaRuleset.setLstUpdateBy("sa");
		reaRuleset.setReactionRules(getReaRule());
		reaRuleset.setReaRulesetId(1l);
		reaRuleset.setRulesetComment("good");
		reaRuleset.setRulesetName("asa");
		reaRuleset.setRuleTypeId(1);
		reaRulesetlist.add(reaRuleset);
		return reaRulesetlist;

	}

	public List<ReactionRule> getReaRule() {
		List<ReactionRule> reaRulelist = Lists.newArrayList();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRulesetId(1l);
		reaRule.setChildRuleId(1l);
		reaRule.setReaRuleId(1l);
		reaRule.setRulePriority(2);
		reaRule.setRuleName("Filtering");
		reaRule.setImportancecodeFrom(10);
		reaRule.setImportancecodeTo(5);
		reaRule.setDirect(true);
		reaRule.setPostponed(true);
		reaRule.setPermenant(true);
		reaRule.setTemporary(false);
		reaRule.setValidFrom(new Date());
		reaRule.setValidUpto(new Date());
		reaRule.setRecalculate(false);
		reaRule.setRuleComment("good");
		reaRule.setCreatedBy("sa");
		reaRule.setLstUpdateBy("sa");
		reaRulelist.add(reaRule);
		return reaRulelist;

	}
}
