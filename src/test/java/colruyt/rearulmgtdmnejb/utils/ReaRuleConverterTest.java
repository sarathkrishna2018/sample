package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElementPK;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReaRuleConverterTest {
	
	@Test
	public void convertRuleBoTest() {
		ReactionRule expectedReaRule = ReaRuleConverter.convertFromBo(getReactRule(), getReactionRule());
		Assert.assertEquals(getReactRule().getReaRuleId(), expectedReaRule.getReaRuleId());
	}

	@Test
	public void convertRuleActionTest() {
		List<ActionType> expectedReaRuleSetActtype = ReaRuleConverter.convertFromActionTypeBo(getRefActionType());
		assertEquals(1l, expectedReaRuleSetActtype.size());

	}

	@Test
	public void convertRuleSourceTest() {
		List<SourceType> expectedReaRuleSetSrc = ReaRuleConverter.convertFromSourceTypeBo(getRefSourceType());
		assertEquals(1, expectedReaRuleSetSrc.size());
	}

	@Test
	public void convertAllActionTest() {
		int idForAll = 1;
		List<ActionType> expectedActionTypes = ReaRuleConverter.convertFromActionTypeAll(idForAll);
		Assert.assertNotNull(expectedActionTypes);
	}

	@Test
	public void convertAllSourceTest() {
		int idForAll = 1;
		List<SourceType> expectedSourceTypes = ReaRuleConverter.convertFromSourceTypeAll(idForAll);
		Assert.assertNotNull(expectedSourceTypes);
	}

	@Test
	public void createconvertRuleLineTest() {
		List<GeneralRuleBo> expectedGeneralRuleBoList = ReaRuleConverter.convertToBo(getReactRuleList());
		assertEquals(getruleLines().size(), expectedGeneralRuleBoList.size());
	}

	@Test
	public void convertGeneralRuleBoTest() {
		GeneralRuleBo expectedGeneralRuleBo = ReaRuleConverter.convertToBo(getReactRule(), getReactionRule());
		assertEquals(new Long(1l), expectedGeneralRuleBo.getRuleId());

	}

	private List<ReactionRule> getReactRuleList() {
		List<ReactionRule> reactionRules = Lists.newArrayList();
		ReactionRule reactionRule = new ReactionRule();
		reactionRule.setCreatedBy("Sa");
		reactionRule.setDirect(true);
		reactionRule.setImportancecodeFrom(5);
		reactionRule.setImportancecodeTo(10);
		reactionRule.setLstUpdateBy("sa");
		reactionRule.setPermenant(true);
		reactionRule.setPostponed(false);
		reactionRule.setReaRuleId(1);
		reactionRule.setReaRulesetId(10);
		reactionRule.setPriceProductHierarchySet(getreaPpdHchysets());
		reactionRule.setRuleComment("good");
		reactionRule.setRecalculate(true);
		reactionRule.setRuleName("filt");
		reactionRule.setRulePriority(1);
		reactionRule.setTemporary(true);
		reactionRule.setValidFrom(new Date());
		reactionRule.setValidUpto(new Date());
		reactionRules.add(reactionRule);
		return reactionRules;
	}

	private List<GeneralRuleBo> getruleLines() {
		List<GeneralRuleBo> generalRuleBos = Lists.newArrayList();
		GeneralRuleBo reactionRuleBo = new GeneralRuleBo();
		Date validFromdate = new Date();
		Date validTodate = new Date();
		reactionRuleBo.setRuleName("Filtering");
		reactionRuleBo.setAssortmentName("wsa");
		reactionRuleBo.setComments("Good");
		reactionRuleBo.setLogonId("sa");
		reactionRuleBo.setRuleId(1l);
		reactionRuleBo.setRulesetId(2l);
		reactionRuleBo.setImportanceCodeFrom(10l);
		reactionRuleBo.setImportanceCodeTo(15l);
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
		reactionRuleBo.setValidFrom(validFromdate);
		reactionRuleBo.setValidTo(validTodate);
		reactionRuleBo.setRefRuleTypeBo(getRefRuleType());
		reactionRuleBo.setReactionRulesetBo(createReactionRuleset());
		reactionRuleBo.setActionTypeList(getRefActionType());
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactionRuleBo.setSourceTypeList(getRefSourceType());
		generalRuleBos.add(reactionRuleBo);
		return generalRuleBos;
	}

	private ReactionRule getReactRule() {
		ReactionRule reactionRule = new ReactionRule();

		reactionRule.setCreatedBy("Sa");
		reactionRule.setDirect(true);
		reactionRule.setImportancecodeFrom(5);
		reactionRule.setImportancecodeTo(10);
		reactionRule.setLstUpdateBy("sa");
		reactionRule.setPermenant(true);
		reactionRule.setPostponed(false);
		reactionRule.setReaRuleId(1);
		reactionRule.setReaRulesetId(10);
		reactionRule.setPriceProductHierarchySet(getreaPpdHchysets());
		reactionRule.setRuleComment("good");
		reactionRule.setRecalculate(true);
		reactionRule.setRuleName("filt");
		reactionRule.setRulePriority(1);
		reactionRule.setTemporary(true);
		reactionRule.setValidFrom(new Date());
		reactionRule.setValidUpto(new Date());
		return reactionRule;
	}

	private List<PriceProductHierarchySet> getreaPpdHchysets() {
		List<PriceProductHierarchySet> priceProductHierarchySetlist = Lists.newArrayList();
		PriceProductHierarchySet priceProductHierarchySet = new PriceProductHierarchySet();
		priceProductHierarchySet.setAssortmentName("asas");
		priceProductHierarchySet.setCheapBrand(true);
		priceProductHierarchySet.setCreatedBy("sa");
		priceProductHierarchySet.setLstUpdateBy("sa");
		priceProductHierarchySet.setNationalBrand(true);
		priceProductHierarchySet.setOwnBrand(false);
		priceProductHierarchySet.setProdHrchySetId(1);
		priceProductHierarchySet.setPriceProductHierarchyElements(getpriceProductHierarchyElements());
		priceProductHierarchySet.setReactionRuleId(1);
		priceProductHierarchySetlist.add(priceProductHierarchySet);
		return priceProductHierarchySetlist;
	}

	private List<PriceProductHierarchyElement> getpriceProductHierarchyElements() {
		List<PriceProductHierarchyElement> productHierarchyElements = Lists.newArrayList();
		PriceProductHierarchyElement priceProductHierarchyElement = new PriceProductHierarchyElement();
		priceProductHierarchyElement.setCreatedBy("sa");
		priceProductHierarchyElement.setProductHierarchyElementId(12l);
		priceProductHierarchyElement.setProductHierarchyTypeId(1l);
		priceProductHierarchyElement.setPpdHchyValue("xx");
		priceProductHierarchyElement.setProdHrchySetElement(getreaPpdHchysetElmnts());
		productHierarchyElements.add(priceProductHierarchyElement);
		return productHierarchyElements;
	}

	private List<PriceProductHierarchySetElement> getreaPpdHchysetElmnts() {
		List<PriceProductHierarchySetElement> priceProductHierarchySetElmnts = Lists.newArrayList();
		PriceProductHierarchySetElement priceProductHierarchySetElmnt = new PriceProductHierarchySetElement();
		priceProductHierarchySetElmnt.setId(getppdid());
		priceProductHierarchySetElmnt.setLstUpdateBy("sa");
		priceProductHierarchySetElmnts.add(priceProductHierarchySetElmnt);
		return priceProductHierarchySetElmnts;
	}

	private PriceProductHierarchySetElementPK getppdid() {
		PriceProductHierarchySetElementPK priceProductHierarchySetElmntPK = new PriceProductHierarchySetElementPK();
		priceProductHierarchySetElmntPK.setProductHierarchyElementId(1);
		priceProductHierarchySetElmntPK.setProdicyHierarchySetId(1);
		return priceProductHierarchySetElmntPK;
	}

	private GeneralRuleBo getReactionRule() {
		GeneralRuleBo reactionRuleBo = new GeneralRuleBo();
		Date validFromdate = new Date();
		Date validTodate = new Date();
		reactionRuleBo.setRuleName("Filtering");
		reactionRuleBo.setAssortmentName("wsa");
		reactionRuleBo.setComments("Good");
		reactionRuleBo.setLogonId("sa");
		reactionRuleBo.setRuleId(1l);
		reactionRuleBo.setRulesetId(2l);
		reactionRuleBo.setImportanceCodeFrom(10l);
		reactionRuleBo.setImportanceCodeTo(15l);
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
		reactionRuleBo.setValidFrom(validFromdate);
		reactionRuleBo.setValidTo(validTodate);
		reactionRuleBo.setRefRuleTypeBo(getRefRuleType());
		reactionRuleBo.setReactionRulesetBo(createReactionRuleset());
		reactionRuleBo.setActionTypeList(getRefActionType());
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactionRuleBo.setSourceTypeList(getRefSourceType());
		return reactionRuleBo;
	}

	private RefRuleTypeBo getRefRuleType() {
		RefRuleTypeBo refRuleType = new RefRuleTypeBo();
		refRuleType.setDescription("asa");
		refRuleType.setRuleTypeId(1);
		refRuleType.setCodeLang(getRefLang());
		return refRuleType;
	}

	private List<RefLangBo> getRefLang() {
		List<RefLangBo> refLanglist = Lists.newArrayList();
		RefLangBo refLang = new RefLangBo();
		refLang.setIsoLangCode("EN");
		refLang.setValue("English");
		refLanglist.add(refLang);
		return refLanglist;
	}

	private ReactionRulesetBo createReactionRuleset() {
		ReactionRulesetBo reactionRuleset = new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setRefRuleTypeBo(getRefRuleType());
		reactionRuleset.setComments("good");
		return reactionRuleset;
	}

	private List<RefActionTypeBo> getRefActionType() {
		List<RefActionTypeBo> refActionTypelist = Lists.newArrayList();
		RefActionTypeBo refActionType = new RefActionTypeBo();
		refActionType.setActionTypeId(1);
		refActionType.setActionTypeValue("asa");
		refActionType.setSequence(12l);
		refActionTypelist.add(refActionType);
		return refActionTypelist;

	}

	private List<ProductHierarchyElementBo> getProductHierarchyElement() {
		List<ProductHierarchyElementBo> productHierarchyElementlist = Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement = new ProductHierarchyElementBo();
		productHierarchyElement.setId(1l);
		productHierarchyElement.setPriceProductHierarchyTypeId(2l);
		productHierarchyElement.setPriceProductHierarchyValue("allProducts");
		productHierarchyElementlist.add(productHierarchyElement);
		return productHierarchyElementlist;
	}

	private List<RefSourceTypeBo> getRefSourceType() {
		List<RefSourceTypeBo> refSourceTypelist = Lists.newArrayList();
		RefSourceTypeBo refSourceType = new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		refSourceTypelist.add(refSourceType);
		return refSourceTypelist;
	}

	private RefActionTypeBo createRefActionType() {
		RefActionTypeBo refActionType = new RefActionTypeBo();
		refActionType.setActionTypeId(1);
		refActionType.setActionTypeValue("asa");
		refActionType.setSequence(12l);
		return refActionType;
	}

	private RefSourceTypeBo createRefSourceTypeBo() {
		RefSourceTypeBo refSourceType = new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		return refSourceType;

	}

}
