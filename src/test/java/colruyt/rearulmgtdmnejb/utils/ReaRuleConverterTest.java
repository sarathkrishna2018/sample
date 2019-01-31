package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmntPK;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionTypePK;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceTypePK;
import colruyt.rearulmgtdmnejb.entity.RefActionType;
import colruyt.rearulmgtdmnejb.entity.RefSourceType;
import colruyt.rearulmgtdmnejb.util.ProductHrchyElmntConverter;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReaRuleConverterTest {
	@TestedObject
	private ReaRuleConverter reaRuleConverter;
	@InjectIntoByType
	private ReferenceDataConverter referenceDataConverter=Mockito.mock(ReferenceDataConverter.class);
	@InjectIntoByType
	private ProductHrchyElmntConverter productHrchyElmntConverter=Mockito.mock(ProductHrchyElmntConverter.class);

	@Test
	public void convertRuleBoTest() {
		ReactionRule reaRule = new ReactionRule();
		GeneralRuleBo reactionRule = getReactionRule();
		reaRule.setReaRulesetId(reactionRule.getRulesetId());
		reaRule.setRuleName(reactionRule.getRuleName());
		reaRule.setIcFrom(reactionRule.getImportanceCodeFrom());
		reaRule.setIcTo(reactionRule.getImportanceCodeTo());
		reaRule.setDirectYn(reactionRule.isDirectBenefit());
		reaRule.setPostponedYn(reactionRule.isPostponedBenefit());
		reaRule.setPermenantYn(reactionRule.isPermanentDuration());
		reaRule.setTemporaryYn(reactionRule.isTemporaryDuration());
		reaRule.setValidFrom(reactionRule.getValidFrom());
		reaRule.setValidUpto(reactionRule.getValidTo());
		reaRule.setRecalculateYn(reactionRule.isRecalculate());
		reaRule.setRuleComment(reactionRule.getComments());
		reaRule.setCreatedBy(reactionRule.getLogonId());
		reaRule.setLstUpdateBy(reactionRule.getLogonId());
		ReactionRule expectedReaRule = reaRuleConverter.convertRuleBo(reaRule, reactionRule);
		assertEquals(reaRule.getReaRulesetId(), expectedReaRule.getReaRulesetId());
	}

	@Test
	public void convertRuleActionTest() {
		List<ReactionRuleActionType> actionTypeLst = Lists.newArrayList();
		ReactionRuleActionType actionType = new ReactionRuleActionType();
		ReactionRuleActionTypePK reaRuleSetActtypePK = new ReactionRuleActionTypePK();
		RefActionTypeBo refActionTypeBo = createRefActionType();
		GeneralRuleBo reactionRule = getReactionRule();
		reaRuleSetActtypePK.setReaRuleId(reactionRule.getRuleId());
		reaRuleSetActtypePK.setActionTypeId(refActionTypeBo.getActionTypeId());
		actionType.setId(reaRuleSetActtypePK);
		actionType.setLstUpdateBy(reactionRule.getLogonId());
		actionTypeLst.add(actionType);
		List<ReactionRuleActionType> expectedReaRuleSetActtype = reaRuleConverter.convertRuleAction(reactionRule);
		assertEquals(actionTypeLst.size(), expectedReaRuleSetActtype.size());

	}

	@Test
	public void convertRuleSourceTest() {
		List<ReactionRuleSourceType> sourceList = Lists.newArrayList();
		GeneralRuleBo reactionRule = getReactionRule();
		ReactionRuleSourceType source = new ReactionRuleSourceType();
		RefSourceTypeBo refSourceTypeBo = createRefSourceTypeBo();
		ReactionRuleSourceTypePK reaRuleSetSrcPK = new ReactionRuleSourceTypePK();
		reaRuleSetSrcPK.setReaRuleId(reactionRule.getRuleId());
		reaRuleSetSrcPK.setSourceId(refSourceTypeBo.getSourceTypeId());
		source.setId(reaRuleSetSrcPK);
		source.setLstUpdateBy(reactionRule.getLogonId());
		sourceList.add(source);
		List<ReactionRuleSourceType> expectedReaRuleSetSrc = reaRuleConverter.convertRuleSource(reactionRule);
		assertEquals(sourceList.size(), expectedReaRuleSetSrc.size());
	}

	@Test
	public void convertRuleActionForAllTest() {
		GeneralRuleBo reactionRule = getReactionRule();
		long actionIdForAll = 12;
		List<ReactionRuleActionType> actionTypeLst = Lists.newArrayList();
		ReactionRuleActionType actionType = new ReactionRuleActionType();
		ReactionRuleActionTypePK reaRuleSetActtypePK = new ReactionRuleActionTypePK();
		reaRuleSetActtypePK.setReaRuleId(reactionRule.getRuleId());
		reaRuleSetActtypePK.setActionTypeId(actionIdForAll);
		actionType.setId(reaRuleSetActtypePK);
		actionType.setLstUpdateBy(reactionRule.getLogonId());
		actionTypeLst.add(actionType);
		List<ReactionRuleActionType> expectedReaRuleSetActtypeForAll = reaRuleConverter
				.convertRuleActionForAll(actionIdForAll, reactionRule);
		assertEquals(actionTypeLst.size(), expectedReaRuleSetActtypeForAll.size());

	}

	@Test
	public void convertRuleSourceForAllTest() {
		GeneralRuleBo reactionRule = getReactionRule();
		long sourceIdForAll = 12;
		List<ReactionRuleSourceType> sourceList = Lists.newArrayList();
		ReactionRuleSourceType source = new ReactionRuleSourceType();
		ReactionRuleSourceTypePK reaRuleSetSrcPK = new ReactionRuleSourceTypePK();
		reaRuleSetSrcPK.setReaRuleId(reactionRule.getRuleId());
		reaRuleSetSrcPK.setSourceId(sourceIdForAll);
		source.setId(reaRuleSetSrcPK);
		source.setLstUpdateBy(reactionRule.getLogonId());
		sourceList.add(source);
		List<ReactionRuleSourceType> expectedReaRuleSetSrcForAll = reaRuleConverter
				.convertRuleSourceForAll(sourceIdForAll, reactionRule);
		assertEquals(sourceList.size(), expectedReaRuleSetSrcForAll.size());
	}
	@Test
	public void createconvertRuleLineTest(){
		List<GeneralRuleBo>  ruleLines = Lists.newArrayList();
		ReactionRule rule = getReactRule();
		GeneralRuleBo ruleBo = new GeneralRuleBo();
		ruleBo.setRuleId(rule.getReaRuleId());
		ruleBo.setRulesetId(rule.getReaRulesetId());
		ruleBo.setRuleName(rule.getRuleName());
		ruleBo.setImportanceCodeFrom(rule.getIcFrom());
		ruleBo.setImportanceCodeTo(rule.getIcTo());
		ruleBo.setDirectBenefit(rule.getDirectYn());
		ruleBo.setPostponedBenefit(rule.getPostponedYn());
		ruleBo.setPermanentDuration(rule.getPermenantYn());
		ruleBo.setTemporaryDuration(rule.getTemporaryYn());
		ruleBo.setValidFrom(rule.getValidFrom());
		ruleBo.setValidTo(rule.getValidUpto());
		ruleBo.setRecalculate(rule.getRecalculateYn());
		ruleBo.setComments(rule.getRuleComment());
		ruleLines.add(ruleBo);
		List<GeneralRuleBo> expectedGeneralRuleBoList=reaRuleConverter.convertRuleLine(getReactRuleList());
		assertEquals(ruleLines.size(), expectedGeneralRuleBoList.size());
	}
	
	@Test
	public void convertGeneralRuleBoTest() {
		GeneralRuleBo ruleBo = new GeneralRuleBo();
		ReactionRule rule = getReactRule();
		ruleBo.setRuleId(rule.getReaRuleId());
		ruleBo.setRulesetId(rule.getReaRulesetId());
		ruleBo.setRuleName(rule.getRuleName());
		ruleBo.setImportanceCodeFrom(rule.getIcFrom());
		ruleBo.setImportanceCodeTo(rule.getIcTo());
		ruleBo.setDirectBenefit(rule.getDirectYn());
		ruleBo.setPostponedBenefit(rule.getPostponedYn());
		ruleBo.setPermanentDuration(rule.getPermenantYn());
		ruleBo.setTemporaryDuration(rule.getTemporaryYn());
		ruleBo.setValidFrom(rule.getValidFrom());
		ruleBo.setValidTo(rule.getValidUpto());
		ruleBo.setRecalculate(rule.getRecalculateYn());
		ruleBo.setComments(rule.getRuleComment());
		ruleBo.setActionSelectAll(true);
		ruleBo.setActionTypeList(getRefActionType());
		ruleBo.setSourceSelectAll(true);
		ruleBo.setSourceTypeList(getRefSourceType());
		ruleBo.setLangCode("En");
		ruleBo.setLogonId("xa");
		ruleBo.setProductHierarchySetId(1l);
		ruleBo.setReactionRulesetBo(getreactionRulesetBo());
		ruleBo.setRefRuleTypeBo(getRefRuleType());
		ruleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		/*when(referenceDataConverter.convertRefReaActiontype(getRefActList())).thenReturn(getRefActionType());
		when(referenceDataConverter.convertRefReaSource(getRefSourceList())).thenReturn(getRefSourceType());*/
		when(productHrchyElmntConverter.convertAssortment(Mockito.any(GeneralRuleBo.class),Mockito.any(ReactionRule.class))).thenReturn(getReactionRule());
		GeneralRuleBo expectedGeneralRuleBo=reaRuleConverter.convertGeneralRuleBo(rule, ruleBo);
		assertEquals(ruleBo.getRuleId(), expectedGeneralRuleBo.getRuleId());
		
	}
	private List<ReactionRule> getReactRuleList() {
		List<ReactionRule> reactionRules=Lists.newArrayList();
		ReactionRule reactionRule=new ReactionRule();
		reactionRule.setCreatedBy("Sa");
		reactionRule.setDirectYn(true);
		reactionRule.setIcFrom(5);
		reactionRule.setIcTo(10);
		reactionRule.setLstUpdateBy("sa");
		reactionRule.setPermenantYn(true);
		reactionRule.setPostponedYn(false);
		reactionRule.setReaRuleId(1);
		reactionRule.setReaRulesetId(10);
		reactionRule.setRefActionTypes(getRefActList());
		reactionRule.setRefSourceTypes(getRefSourceList());
		reactionRule.setReaPpdHchysets(getreaPpdHchysets());
		reactionRule.setRuleComment("good");
		reactionRule.setRecalculateYn(true);
		reactionRule.setRuleName("filt");
		reactionRule.setRulePriority(1);
		reactionRule.setTemporaryYn(true);
		reactionRule.setValidFrom(new Date());
		reactionRule.setValidUpto(new Date());
		reactionRules.add(reactionRule);
		return reactionRules;
	}


	private ReactionRulesetBo getreactionRulesetBo() {
		ReactionRulesetBo reactionRulesetBo=new ReactionRulesetBo();
		reactionRulesetBo.setColruytGroupChainId(1);
		reactionRulesetBo.setComments("good");
		reactionRulesetBo.setName("xxx");
		reactionRulesetBo.setPriceCompetitorChainId(1);
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleType());
		reactionRulesetBo.setRuleLines(getruleLines());
		reactionRulesetBo.setRulesetId(1l);
		return reactionRulesetBo;
	}

	private List<GeneralRuleBo> getruleLines() {
		List<GeneralRuleBo> generalRuleBos=Lists.newArrayList();
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
		reactionRule.setDirectYn(true);
		reactionRule.setIcFrom(5);
		reactionRule.setIcTo(10);
		reactionRule.setLstUpdateBy("sa");
		reactionRule.setPermenantYn(true);
		reactionRule.setPostponedYn(false);
		reactionRule.setReaRuleId(1);
		reactionRule.setReaRulesetId(10);
		reactionRule.setRefActionTypes(getRefActList());
		reactionRule.setRefSourceTypes(getRefSourceList());
		reactionRule.setReaPpdHchysets(getreaPpdHchysets());
		reactionRule.setRuleComment("good");
		reactionRule.setRecalculateYn(true);
		reactionRule.setRuleName("filt");
		reactionRule.setRulePriority(1);
		reactionRule.setTemporaryYn(true);
		reactionRule.setValidFrom(new Date());
		reactionRule.setValidUpto(new Date());
		return reactionRule;
	}

	private List<PriceProductHierarchySet> getreaPpdHchysets() {
		List<PriceProductHierarchySet>  priceProductHierarchySetlist=Lists.newArrayList();
		PriceProductHierarchySet priceProductHierarchySet=new PriceProductHierarchySet();
		priceProductHierarchySet.setAssortmentName("asas");
		priceProductHierarchySet.setCheapBrandYn(true);
		priceProductHierarchySet.setCreatedBy("sa");
		priceProductHierarchySet.setLstUpdateBy("sa");
		priceProductHierarchySet.setNatBrandYn(true);
		priceProductHierarchySet.setOwnBrandYn(false);
		priceProductHierarchySet.setPpdHchysetId(1);
		priceProductHierarchySet.setPriceProductHierarchyElements(getpriceProductHierarchyElements());
		priceProductHierarchySet.setReaRuleId(1);
		priceProductHierarchySetlist.add(priceProductHierarchySet);	
		return priceProductHierarchySetlist;
	}

	private List<PriceProductHierarchyElement> getpriceProductHierarchyElements() {
		List<PriceProductHierarchyElement> productHierarchyElements=Lists.newArrayList();
		PriceProductHierarchyElement priceProductHierarchyElement=new PriceProductHierarchyElement();
		priceProductHierarchyElement.setCreatedBy("sa");
		priceProductHierarchyElement.setPpdHchyElmntId(12l);
		priceProductHierarchyElement.setPpdHchyTypeId(1l);
		priceProductHierarchyElement.setPpdHchyValue("xx");
		priceProductHierarchyElement.setReaPpdHchysetElmnts(getreaPpdHchysetElmnts());
		productHierarchyElements.add(priceProductHierarchyElement);
		return productHierarchyElements;
	}

	private List<PriceProductHierarchySetElmnt> getreaPpdHchysetElmnts() {
		List<PriceProductHierarchySetElmnt> priceProductHierarchySetElmnts=Lists.newArrayList();
		PriceProductHierarchySetElmnt priceProductHierarchySetElmnt=new PriceProductHierarchySetElmnt();
		priceProductHierarchySetElmnt.setId(getppdid());
		priceProductHierarchySetElmnt.setLstUpdateBy("sa");
		priceProductHierarchySetElmnts.add(priceProductHierarchySetElmnt);
		return priceProductHierarchySetElmnts;
	}

	private PriceProductHierarchySetElmntPK getppdid() {
		PriceProductHierarchySetElmntPK priceProductHierarchySetElmntPK=new PriceProductHierarchySetElmntPK();
		priceProductHierarchySetElmntPK.setPpdHchyElmntId(1);
		priceProductHierarchySetElmntPK.setPpdHchysetId(1);
		return priceProductHierarchySetElmntPK;
	}

	private List<RefSourceType> getRefSourceList() {
		List<RefSourceType> refSourcelist=Lists.newArrayList();
		RefSourceType refSourceType=new RefSourceType();
		refSourceType.setDescription("xxx");
		refSourceType.setSourceId(1);
		refSourceType.setSourceName("All");
		refSourcelist.add(refSourceType);
		return refSourcelist;
	}

	public GeneralRuleBo getReactionRule() {
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

	public List<RefActionTypeBo> getRefActionType() {
		List<RefActionTypeBo> refActionTypelist = Lists.newArrayList();
		RefActionTypeBo refActionType = new RefActionTypeBo();
		refActionType.setActionTypeId(1l);
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

	public RefActionTypeBo createRefActionType() {
		RefActionTypeBo refActionType = new RefActionTypeBo();
		refActionType.setActionTypeId(1l);
		refActionType.setActionTypeValue("asa");
		refActionType.setSequence(12l);
		return refActionType;
	}

	public RefSourceTypeBo createRefSourceTypeBo() {
		RefSourceTypeBo refSourceType = new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		return refSourceType;

	}
	public List<RefActionType> getRefActList(){
		List<RefActionType> refActlist=Lists.newArrayList();
		RefActionType refActionType = new RefActionType();
		refActionType.setActionTypeId(1l);
		refActionType.setActionType("all");
		refActionType.setDescription("xxx");
		refActionType.setSeq(123l);
		refActlist.add(refActionType);
		return refActlist;
		
	}

}
