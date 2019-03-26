package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

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
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.util.ReaRulesetConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReaRulesetConverterTest {

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
		reaRuleset.setLastUpdateBy(logonId);
		ReactionRuleSet expectedReaRuleset=ReaRulesetConverter.convertFromBo(reaRuleset, reactionRulesetBo, logonId);
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
		ReactionRulesetBo expectedreactionRulesetBo=ReaRulesetConverter.convertToBo(getReactRuleset());
		assertEquals(reactionRulesetBo.getRulesetId(), expectedreactionRulesetBo.getRulesetId());
	}
	@Test
	public void convertRuleSetBoTest(){
		List<ReactionRulesetBo> rulesetBoList = Lists.newArrayList();
		ReactionRulesetBo ruleBo = new ReactionRulesetBo();
		ReactionRuleSet reactionRuleSet=getReactRuleset();
		ruleBo.setRulesetId(reactionRuleSet.getReaRulesetId());
		ruleBo.setColruytGroupChainId(reactionRuleSet.getColruytGroupChainId());
		ruleBo.setPriceCompetitorChainId(reactionRuleSet.getPriceCompetitorChainId());
		rulesetBoList.add(ruleBo);
		List<ReactionRulesetBo> expectedReactionRulesetBo=ReaRulesetConverter.convertToBo(getReactionRuleSetlist(),getRefRuleTypeBoList());
		assertEquals(rulesetBoList.size(),expectedReactionRulesetBo.size());
		
		
	}
	private List<RefRuleTypeBo> getRefRuleTypeBoList(){
		List<RefRuleTypeBo> refRuleTypeBoList = Lists.newArrayList();
		RefRuleTypeBo refRuleTypeBo=new RefRuleTypeBo();
		refRuleTypeBo.setRuleTypeId(1);
		refRuleTypeBo.setDescription("all");
		refRuleTypeBo.setCodeLang(getRefLang());
		refRuleTypeBoList.add(refRuleTypeBo);
		return refRuleTypeBoList;
  }
	private List<ReactionRuleSet> getReactionRuleSetlist() {
		List<ReactionRuleSet> ruleSetList=Lists.newArrayList();
		ReactionRuleSet reactionRuleSet=new ReactionRuleSet();
		reactionRuleSet.setColruytGroupChainId(1);
		reactionRuleSet.setPriceCompetitorChainId(2);
		reactionRuleSet.setLastUpdateBy("Sa");
		reactionRuleSet.setReactionRules(getReactRuleList());
		reactionRuleSet.setReaRulesetId(12l);
		reactionRuleSet.setRulesetComment("Good");
		reactionRuleSet.setRulesetName("Filr");
		reactionRuleSet.setRuleTypeId(1);
		ruleSetList.add(reactionRuleSet);
		return ruleSetList;
	}
	private ReactionRuleSet getReactRuleset() {
		ReactionRuleSet reactionRuleSet=new ReactionRuleSet();
		reactionRuleSet.setColruytGroupChainId(1);
		reactionRuleSet.setPriceCompetitorChainId(2);
		reactionRuleSet.setLastUpdateBy("Sa");
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
		reactionRule.setLastUpdateBy("sa");
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
	private List<PriceProductHierarchySet> getreaPpdHchysets() {
		List<PriceProductHierarchySet>  priceProductHierarchySetlist=Lists.newArrayList();
		PriceProductHierarchySet priceProductHierarchySet=new PriceProductHierarchySet();
		priceProductHierarchySet.setAssortmentName("asas");
		priceProductHierarchySet.setCheapBrand(true);
		priceProductHierarchySet.setCreatedBy("sa");
		priceProductHierarchySet.setLastUpdateBy("sa");
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
		priceProductHierarchyElement.setProductHierarchyValue("xx");
		priceProductHierarchyElement.setProductHierarchySetElement(getreaPpdHchysetElmnts());
		productHierarchyElements.add(priceProductHierarchyElement);
		return productHierarchyElements;
	}
	private List<PriceProductHierarchySetElement> getreaPpdHchysetElmnts() {
		List<PriceProductHierarchySetElement> priceProductHierarchySetElmnts=Lists.newArrayList();
		PriceProductHierarchySetElement priceProductHierarchySetElmnt=new PriceProductHierarchySetElement();
		priceProductHierarchySetElmnt.setId(getppdid());
		priceProductHierarchySetElmnt.setLastUpdateBy("sa");
		priceProductHierarchySetElmnts.add(priceProductHierarchySetElmnt);
		return priceProductHierarchySetElmnts;
	}

	private PriceProductHierarchySetElementPK getppdid() {
		PriceProductHierarchySetElementPK priceProductHierarchySetElmntPK=new PriceProductHierarchySetElementPK();
		priceProductHierarchySetElmntPK.setProductHierarchyElementId(1);
		priceProductHierarchySetElmntPK.setProdicyHierarchySetId(1);
		return priceProductHierarchySetElmntPK;
	}

	private ReactionRulesetBo getReactionRuleset(){
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
	private List<GeneralRuleBo> getReactionRuleBo(){
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
	private RefRuleTypeBo getRefRuleType(){
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
	private List<RefActionTypeBo> getRefActionType(){
		List<RefActionTypeBo> refActionTypelist=Lists.newArrayList();
		RefActionTypeBo refActionType=new RefActionTypeBo();
		refActionType.setActionTypeId(1);
		refActionType.setActionTypeValue("asa");
		refActionType.setSequence(12l);
		refActionTypelist.add(refActionType);
		return refActionTypelist;
		
	}
	private List<ProductHierarchyElementBo> getProductHierarchyElement(){
		List<ProductHierarchyElementBo> productHierarchyElementlist=Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement=new ProductHierarchyElementBo();
		productHierarchyElement.setId(1l);
		productHierarchyElement.setPriceProductHierarchyTypeId(2l);
		productHierarchyElement.setPriceProductHierarchyValue("allProducts");
		productHierarchyElementlist.add(productHierarchyElement);
		return productHierarchyElementlist;
	}
	private List<RefSourceTypeBo> getRefSourceType(){
		List<RefSourceTypeBo> refSourceTypelist=Lists.newArrayList();
		RefSourceTypeBo refSourceType=new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		refSourceTypelist.add(refSourceType);
		return refSourceTypelist;
	}
	private ReactionRulesetBo createReactionRuleset(){
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
