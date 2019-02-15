package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

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
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmntPK;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.util.ProductHrchyElmntConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)


public class ProductHrchyElmntConverterTest {
	@TestedObject
	private ProductHrchyElmntConverter productHrchyElmntConverter;
	@Test
	public void createConverterTest(){
		List<PriceProductHierarchyElement> reaPpdHchyElmnts=Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement=createProductHierarchyElementBo();
		PriceProductHierarchyElement reaPpdHchyElmnt=new PriceProductHierarchyElement();
		String logonId="sa";
		reaPpdHchyElmnt.setPpdHchyValue(productHierarchyElement.getPriceProductHierarchyValue());
		reaPpdHchyElmnt.setPpdHchyTypeId(productHierarchyElement.getPriceProductHierarchyTypeId());
		reaPpdHchyElmnt.setCreatedBy(logonId);
		reaPpdHchyElmnts.add(reaPpdHchyElmnt);
		List<PriceProductHierarchyElement> expectedReaPpdHchyElmnt=productHrchyElmntConverter.convertProductHierarchyElement(getProductHierarchyElement(), logonId);
		assertEquals(reaPpdHchyElmnts.size(),expectedReaPpdHchyElmnt.size());
	}
	@Test
	public void convertAssortmentTest(){
		GeneralRuleBo expectedGeneralRuleBo=productHrchyElmntConverter.convertAssortment(getRuleBo(), getRuleAction());
		Assert.assertEquals(getRuleBo().getRuleId(), expectedGeneralRuleBo.getRuleId());
	}
	private ReactionRule getRuleAction() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRuleId(1L);
		reaRule.setReaRulesetId(1l);
		reaRule.setRuleName("Filtering");
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
		reaRule.setPriceProductHierarchySet(getReaPpdHchysets());
		return reaRule;
	}
	private List<PriceProductHierarchySet> getReaPpdHchysets() {
		 List<PriceProductHierarchySet> priceProductHierarchySets=Lists.newArrayList();
		 PriceProductHierarchySet priceProductHierarchySet=new PriceProductHierarchySet();
		 priceProductHierarchySet.setAssortmentName("all");
		 priceProductHierarchySet.setCheapBrand(false);
		 priceProductHierarchySet.setCreatedBy("ake");
		 priceProductHierarchySet.setLstUpdateBy("ake");
		 priceProductHierarchySet.setProdHrchySetId(1l);
		 priceProductHierarchySet.setPriceProductHierarchyElements(getPriceProductHierarchyElements());
		 priceProductHierarchySet.setReaRuleId(1);
		 priceProductHierarchySets.add(priceProductHierarchySet);
		return priceProductHierarchySets;
	}
	private List<PriceProductHierarchyElement> getPriceProductHierarchyElements() {
		List<PriceProductHierarchyElement> priceProductHierarchyElements=Lists.newArrayList();
		PriceProductHierarchyElement priceProductHierarchyElement=new PriceProductHierarchyElement();
		priceProductHierarchyElement.setCreatedBy("ak");
		priceProductHierarchyElement.setPpdHchyElmntId(1l);
		priceProductHierarchyElement.setPpdHchyTypeId(1l);
		priceProductHierarchyElement.setPpdHchyValue("All");
		priceProductHierarchyElement.setProdHrchySetElement(getReaPpdHchysetElmnts());
		priceProductHierarchyElements.add(priceProductHierarchyElement);
		return priceProductHierarchyElements;
	}
	private List<PriceProductHierarchySetElmnt> getReaPpdHchysetElmnts() {
		List<PriceProductHierarchySetElmnt> priceProductHierarchySetElmnts=Lists.newArrayList();
		PriceProductHierarchySetElmnt priceProductHierarchySetElmnt=new PriceProductHierarchySetElmnt();
		priceProductHierarchySetElmnt.setId(getPriceProductHierarchySetElmntPK());
		priceProductHierarchySetElmnt.setLstUpdateBy("ake");
		priceProductHierarchySetElmnts.add(priceProductHierarchySetElmnt);
		return priceProductHierarchySetElmnts;
	}
	private PriceProductHierarchySetElmntPK getPriceProductHierarchySetElmntPK() {
		PriceProductHierarchySetElmntPK priceProductHierarchySetElmntPK=new PriceProductHierarchySetElmntPK();
		priceProductHierarchySetElmntPK.setProdHrchyElemntId(1);
		priceProductHierarchySetElmntPK.setProdHrchySetId(1);
		return priceProductHierarchySetElmntPK;
	}
	
	private GeneralRuleBo getRuleBo() {
		GeneralRuleBo reactionRuleBo = new GeneralRuleBo();
		Date validFromdate = new Date();
		reactionRuleBo.setRuleName("Catch ALL");
		reactionRuleBo.setAssortmentName("ALL");
		reactionRuleBo.setComments("Good");
		reactionRuleBo.setLogonId("sa");
		reactionRuleBo.setRuleId(1l);
		reactionRuleBo.setRulesetId(2l);
		reactionRuleBo.setImportanceCodeFrom(1l);
		reactionRuleBo.setImportanceCodeTo(99l);
		reactionRuleBo.setProductHierarchySetId(1l);
		reactionRuleBo.setCatchAll(true);
		reactionRuleBo.setCheapBrand(true);
		reactionRuleBo.setNationalBrand(true);
		reactionRuleBo.setOwnBrand(true);
		reactionRuleBo.setRecalculate(true);
		reactionRuleBo.setTemporaryDuration(true);
		reactionRuleBo.setPermanentDuration(true);
		reactionRuleBo.setActionSelectAll(true);
		reactionRuleBo.setSourceSelectAll(true);
		reactionRuleBo.setDirectBenefit(true);
		reactionRuleBo.setPostponedBenefit(true);
		reactionRuleBo.setValidFrom(validFromdate);
		reactionRuleBo.setValidTo(null);
		reactionRuleBo.setRefRuleTypeBo(getRefRuleType());
		reactionRuleBo.setReactionRulesetBo(createReactionRuleset());
		reactionRuleBo.setActionTypeList(getRefActionTypeBo());
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactionRuleBo.setSourceTypeList(getRefSourceTypeBo());
		reactionRuleBo.setLangCode("EN");
		reactionRuleBo.setType("Reacting");
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		return reactionRuleBo;
	}
	private List<RefActionTypeBo> getRefActionTypeBo() {
		List<RefActionTypeBo> refActionTypeBolist = Lists.newArrayList();
		RefActionTypeBo refActionTypeBo = new RefActionTypeBo();
		refActionTypeBo.setActionTypeId(1);
		refActionTypeBo.setActionTypeValue("sas");
		refActionTypeBo.setSequence(123);
		refActionTypeBolist.add(refActionTypeBo);
		return refActionTypeBolist;

	}

	private List<RefSourceTypeBo> getRefSourceTypeBo() {
		List<RefSourceTypeBo> refSourceTypeBolist = Lists.newArrayList();
		RefSourceTypeBo refSourceTypeBo = new RefSourceTypeBo();
		refSourceTypeBo.setSourceName("xyx");
		refSourceTypeBo.setSourceTypeId(1);
		refSourceTypeBolist.add(refSourceTypeBo);
		return refSourceTypeBolist;
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
	private ProductHierarchyElementBo createProductHierarchyElementBo(){
		ProductHierarchyElementBo productHierarchyElementBo=new ProductHierarchyElementBo();
		productHierarchyElementBo.setId(1l);
		productHierarchyElementBo.setPriceProductHierarchyTypeId(1l);
		productHierarchyElementBo.setPriceProductHierarchyValue("All products");
		return productHierarchyElementBo;
	}
	private List<ProductHierarchyElementBo> getProductHierarchyElement(){
		List<ProductHierarchyElementBo> productHierarchyElementlist=Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElementBo=new ProductHierarchyElementBo();
		productHierarchyElementBo.setId(1l);
		productHierarchyElementBo.setPriceProductHierarchyTypeId(1l);
		productHierarchyElementBo.setPriceProductHierarchyValue("All products");
		productHierarchyElementlist.add(productHierarchyElementBo);
		return productHierarchyElementlist;
		
	}

}
