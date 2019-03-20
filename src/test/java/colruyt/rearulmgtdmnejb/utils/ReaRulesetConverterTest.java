package colruyt.rearulmgtdmnejb.utils;

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
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;

import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReaRulesetConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReaRulesetConverterTest {
	@TestedObject
	private ReaRulesetConverter reaRulesetConverter;
	@InjectIntoByType
	private ReaRuleConverter reaRuleConverter=Mockito.mock(ReaRuleConverter.class);
	@InjectIntoByType
	private ReferenceDataService referenceDataService=Mockito.mock(ReferenceDataService.class);
	@Test
	public void createConverterTest(){
		ReactionRulesetBo reactionRulesetBo=getReactionRuleset();
		ReactionRuleSet reaRuleset = new ReactionRuleSet();
		String logonId="sa";
		reaRuleset.setColruytGroupChainId(reactionRulesetBo.getColruytGroupChainId());
		reaRuleset.setPriceCompetitorChainId(reactionRulesetBo.getPriceCompetitorChainId());
		reaRuleset.setRuleTypeId(reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId());
		reaRuleset.setRulesetComment(reactionRulesetBo.getComments());
		reaRuleset.setRulesetName(reactionRulesetBo.getName());
		reaRuleset.setLstUpdateBy(logonId);
		ReactionRuleSet expectedReaRuleset=reaRulesetConverter.convertFromBo(reaRuleset, reactionRulesetBo, logonId);
		assertEquals(new Long(1L), Long.valueOf(expectedReaRuleset.getColruytGroupChainId()));
	}
	@Test
	public void convertReactionRulesetTest(){
		ReactionRulesetBo reactionRulesetBo=new ReactionRulesetBo();
		ReactionRuleSet reactionRuleSet=getReactRuleset();
		reactionRulesetBo.setColruytGroupChainId(reactionRuleSet.getColruytGroupChainId());
		reactionRulesetBo.setComments(reactionRuleSet.getRulesetComment());
		reactionRulesetBo.setName(reactionRuleSet.getRulesetName());
		reactionRulesetBo.setPriceCompetitorChainId(reactionRuleSet.getPriceCompetitorChainId());
		reactionRulesetBo.setRefRuleTypeBo(getRefRuleType());
		reactionRulesetBo.setRuleLines(getReactionRuleBo());
		reactionRulesetBo.setRulesetId(reactionRuleSet.getReaRulesetId());
		when(reaRuleConverter.convertToBo(Mockito.anyListOf(ReactionRule.class))).thenReturn(getReactionRuleBo());
		ReactionRulesetBo expectedreactionRulesetBo=reaRulesetConverter.convertToBo(getReactRuleset());
		assertEquals(reactionRulesetBo.getRulesetId(), expectedreactionRulesetBo.getRulesetId());
	}
	/*@Test
	public void convertRefRuletypeLangsTest(){
		List<RefLangBo> refLangList = Lists.newArrayList();
		RefLangBo refLangBo=new RefLangBo();
		RefRuletypeLang refRuletypeLang=getRefRuletypeLang();
		refLangBo.setIsoLangCode(refRuletypeLang.getId().getIsoLangCode());
		refLangBo.setValue(refRuletypeLang.getDescription());
		refLangList.add(refLangBo);
		List<RefLangBo> expectedRefLangBo=reaRulesetConverter.convertRefRuletypeLangs(getRefRuletypeLanglist());
		assertEquals(refLangList.size(),expectedRefLangBo.size());	
	}*/
	/*@Test
	public void convertRulTypeTest(){
		RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
		when(referenceDataService.getAllRuleTypes(Mockito.anyListOf(RefRuleTypeBo.class))).thenReturn(getReactionRuleBo());
	}*/
	/*@Test
	public void convertRuleTypeTest(){
		List<RefRuleTypeBo> refRuleTypeBoList = Lists.newArrayList();
		RefRuletype refRuletype=getRefRuletype();
		RefRuleTypeBo refRuleTypeBo=new RefRuleTypeBo();
		refRuleTypeBo.setCodeLang(getRefLang());
		//refRuleTypeBo.setDescription();
		refRuleTypeBo.setRuleTypeId(refRuletype.getRuletypeId());
		when(reaRulesetConverter.convertRefRuletypeLangs(getRefRuletypeLanglist())).thenReturn(getRefLang());
		refRuleTypeBoList.add(refRuleTypeBo);
		List<RefRuleTypeBo> expectedRefRuleTypeBo=reaRulesetConverter.convertRuleType(getRefRuletypelist());
		assertEquals(refRuleTypeBoList.size(),expectedRefRuleTypeBo.size());	
		
	}*/
	@Test
	public void convertRuleSetBoTest(){
		List<ReactionRulesetBo> rulesetBoList = Lists.newArrayList();
		ReactionRulesetBo ruleBo = new ReactionRulesetBo();
		ReactionRuleSet reactionRuleSet=getReactRuleset();
		ruleBo.setRulesetId(reactionRuleSet.getReaRulesetId());
		ruleBo.setColruytGroupChainId(reactionRuleSet.getColruytGroupChainId());
		ruleBo.setPriceCompetitorChainId(reactionRuleSet.getPriceCompetitorChainId());
		rulesetBoList.add(ruleBo);
		List<ReactionRulesetBo> expectedReactionRulesetBo=reaRulesetConverter.convertToBo(getReactionRuleSetlist());
		assertEquals(rulesetBoList.size(),expectedReactionRulesetBo.size());
		
		
	}
	private List<ReactionRuleSet> getReactionRuleSetlist() {
		List<ReactionRuleSet> ruleSetList=Lists.newArrayList();
		ReactionRuleSet reactionRuleSet=new ReactionRuleSet();
		reactionRuleSet.setColruytGroupChainId(1);
		reactionRuleSet.setPriceCompetitorChainId(2);
		reactionRuleSet.setLstUpdateBy("Sa");
		reactionRuleSet.setReactionRules(getReactRuleList());
		reactionRuleSet.setReaRulesetId(12l);
		reactionRuleSet.setRulesetComment("Good");
		reactionRuleSet.setRulesetName("Filr");
		reactionRuleSet.setRuleTypeId(1);
		ruleSetList.add(reactionRuleSet);
		return ruleSetList;
	}
	/*private List<RefRuletype> getRefRuletypelist() {
		List<RefRuletype> refRuletypelist=Lists.newArrayList();
		RefRuletype refRuletype=new RefRuletype();
		refRuletype.setRefRuletypeLang(getRefRuletypeLanglist());
		refRuletype.setRuletypeId(1);
		refRuletypelist.add(refRuletype);
		return refRuletypelist;
	}
	private RefRuletypeLang getRefRuletypeLang() {
		RefRuletypeLang refRuletypeLang=new RefRuletypeLang();
		refRuletypeLang.setDescription("Eng");
		refRuletypeLang.setId(getRefRuleTypePK());
		refRuletypeLang.setRefRuletype(getRefRuletype());
		refRuletypeLang.setRuletypeName("Filt");
		return refRuletypeLang;
	}
	private RefRuletype getRefRuletype() {
		RefRuletype refRuletype=new RefRuletype();
		refRuletype.setRefRuletypeLang(getRefRuletypeLanglist());
		refRuletype.setRuletypeId(1);
		return refRuletype;
	}
	private RefRuleTypePK getRefRuleTypePK() {
		RefRuleTypePK refRuleTypePK=new RefRuleTypePK();
		refRuleTypePK.setIsoLangCode("En");
		refRuleTypePK.setRuletypeId(1);
		return refRuleTypePK;
	}
	private List<RefRuletypeLang> getRefRuletypeLanglist() {
		List<RefRuletypeLang> refRuletypeLangs=Lists.newArrayList();
		RefRuletypeLang refRuletypeLang=new RefRuletypeLang();
		refRuletypeLang.setDescription("Eng");
		refRuletypeLang.setId(getRefRuleTypePK());
		refRuletypeLang.setRuletypeName("Filt");
		refRuletypeLangs.add(refRuletypeLang);
		return refRuletypeLangs;
	}*/
	private ReactionRuleSet getReactRuleset() {
		ReactionRuleSet reactionRuleSet=new ReactionRuleSet();
		reactionRuleSet.setColruytGroupChainId(1);
		reactionRuleSet.setPriceCompetitorChainId(2);
		reactionRuleSet.setLstUpdateBy("Sa");
		reactionRuleSet.setReactionRules(getReactRuleList());
		reactionRuleSet.setReaRulesetId(12l);
		reactionRuleSet.setRulesetComment("Good");
		reactionRuleSet.setRulesetName("Filr");
		reactionRuleSet.setRuleTypeId(1);
		return reactionRuleSet;
	}
	private List<ReactionRule> getReactRuleList() {
		List<ReactionRule> reactionRules=Lists.newArrayList();
		ReactionRule reactionRule=new ReactionRule();
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
	/*public List<RefActionType> getRefActList(){
		List<RefActionType> refActlist=Lists.newArrayList();
		RefActionType refActionType = new RefActionType();
		refActionType.setActionTypeId(1l);
		refActionType.setActionType("all");
		refActionType.setDescription("xxx");
		refActionType.setSeq(123l);
		refActlist.add(refActionType);
		return refActlist;
		
	}*/
	private List<PriceProductHierarchySet> getreaPpdHchysets() {
		List<PriceProductHierarchySet>  priceProductHierarchySetlist=Lists.newArrayList();
		PriceProductHierarchySet priceProductHierarchySet=new PriceProductHierarchySet();
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
		List<PriceProductHierarchyElement> productHierarchyElements=Lists.newArrayList();
		PriceProductHierarchyElement priceProductHierarchyElement=new PriceProductHierarchyElement();
		priceProductHierarchyElement.setCreatedBy("sa");
		priceProductHierarchyElement.setProductHierarchyElementId(12l);
		priceProductHierarchyElement.setProductHierarchyTypeId(1l);
		priceProductHierarchyElement.setPpdHchyValue("xx");
		priceProductHierarchyElement.setProdHrchySetElement(getreaPpdHchysetElmnts());
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
		priceProductHierarchySetElmntPK.setProductHierarchyElementId(1);
		priceProductHierarchySetElmntPK.setProdicyHierarchySetId(1);
		return priceProductHierarchySetElmntPK;
	}

	public ReactionRulesetBo getReactionRuleset(){
		ReactionRulesetBo reactionRuleset=new ReactionRulesetBo();
		
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setComments("good");
		reactionRuleset.setName("aa");
		reactionRuleset.setPriceCompetitorChainId(1);
		reactionRuleset.setRefRuleTypeBo(getRefRuleType());
		reactionRuleset.setRuleLines(getReactionRuleBo());
		reactionRuleset.setRulesetId(1l);
		return reactionRuleset;
		
	}
	public List<GeneralRuleBo> getReactionRuleBo(){
		List<GeneralRuleBo> reactionRuleBolist=Lists.newArrayList();
		GeneralRuleBo reactionRuleBo=new GeneralRuleBo();
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
		reactionRuleBo.setLangCode("EN");
		reactionRuleBolist.add(reactionRuleBo);
		return reactionRuleBolist;
		
		
	}
	public RefRuleTypeBo getRefRuleType(){
		RefRuleTypeBo refRuleType=new RefRuleTypeBo();
		refRuleType.setDescription("asa");
		refRuleType.setRuleTypeId(1);
		refRuleType.setCodeLang(getRefLang());
		return refRuleType;
	}
	public List<RefLangBo> getRefLang(){
		List<RefLangBo> refLanglist=Lists.newArrayList();
		RefLangBo refLang=new RefLangBo();
		refLang.setIsoLangCode("EN");
		refLang.setValue("English");
		refLanglist.add(refLang);
		return refLanglist;
	}
	public List<RefActionTypeBo> getRefActionType(){
		List<RefActionTypeBo> refActionTypelist=Lists.newArrayList();
		RefActionTypeBo refActionType=new RefActionTypeBo();
		refActionType.setActionTypeId(1);
		refActionType.setActionTypeValue("asa");
		refActionType.setSequence(12l);
		refActionTypelist.add(refActionType);
		return refActionTypelist;
		
	}
	public List<ProductHierarchyElementBo> getProductHierarchyElement(){
		List<ProductHierarchyElementBo> productHierarchyElementlist=Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement=new ProductHierarchyElementBo();
		productHierarchyElement.setId(1l);
		productHierarchyElement.setPriceProductHierarchyTypeId(2l);
		productHierarchyElement.setPriceProductHierarchyValue("allProducts");
		productHierarchyElementlist.add(productHierarchyElement);
		return productHierarchyElementlist;
	}
	public List<RefSourceTypeBo> getRefSourceType(){
		List<RefSourceTypeBo> refSourceTypelist=Lists.newArrayList();
		RefSourceTypeBo refSourceType=new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		refSourceTypelist.add(refSourceType);
		return refSourceTypelist;
	}
	public ReactionRulesetBo createReactionRuleset(){
		ReactionRulesetBo reactionRuleset=new ReactionRulesetBo();
		reactionRuleset.setColruytGroupChainId(1l);
		reactionRuleset.setPriceCompetitorChainId(2l);
		reactionRuleset.setName("As");
		reactionRuleset.setRulesetId(1l);
		reactionRuleset.setRefRuleTypeBo(getRefRuleType());
		reactionRuleset.setComments("good");
		//reactionRuleset.setRuleLines(getReactionRuleBo());
		return reactionRuleset;
	}
}
