package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
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
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReaRuleConverterTest {
	@TestedObject
	private ReaRuleConverter reaRuleConverter;
	@InjectIntoByType
	private ProductHrchyElmntConverter productHrchyElmntConverter = Mockito.mock(ProductHrchyElmntConverter.class);

	@Test
	public void convertRuleBoTest() {
		ReactionRule expectedReaRule = reaRuleConverter.convertRuleBo(getReactRule(), getReactionRule());
		Assert.assertEquals(getReactRule().getReaRuleId(), expectedReaRule.getReaRuleId());
	}

	@Test
	public void convertRuleActionTest() {
		List<Long> expectedReaRuleSetActtype = reaRuleConverter.convertRuleAction(getReactionRule());
		assertEquals(1l, expectedReaRuleSetActtype.size());

	}

	@Test
	public void convertRuleSourceTest() {
		List<Long> expectedReaRuleSetSrc = reaRuleConverter.convertRuleSource(getReactionRule());
		assertEquals(1, expectedReaRuleSetSrc.size());
	}

	@Test
	public void convertRuleTypeForAllTest() {
		long idForAll = 1;
		List<Long> expectedRuleTypeForAll = reaRuleConverter.convertRuleTypeForAll(idForAll);
		assertEquals(1l, expectedRuleTypeForAll.size());

	}

	@Test
	public void createconvertRuleLineTest() {
		List<GeneralRuleBo> expectedGeneralRuleBoList = reaRuleConverter.convertRuleLine(getReactRuleList());
		assertEquals(getruleLines().size(), expectedGeneralRuleBoList.size());
	}

	@Test
	public void convertGeneralRuleBoTest() {
		when(productHrchyElmntConverter.convertAssortment(Mockito.any(GeneralRuleBo.class),
				Mockito.any(ReactionRule.class))).thenReturn(getReactionRule());
		GeneralRuleBo expectedGeneralRuleBo = reaRuleConverter.convertGeneralRuleBo(getReactRule(), getReactionRule());
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
		priceProductHierarchySet.setReaRuleId(1);
		priceProductHierarchySetlist.add(priceProductHierarchySet);
		return priceProductHierarchySetlist;
	}

	private List<PriceProductHierarchyElement> getpriceProductHierarchyElements() {
		List<PriceProductHierarchyElement> productHierarchyElements = Lists.newArrayList();
		PriceProductHierarchyElement priceProductHierarchyElement = new PriceProductHierarchyElement();
		priceProductHierarchyElement.setCreatedBy("sa");
		priceProductHierarchyElement.setPpdHchyElmntId(12l);
		priceProductHierarchyElement.setPpdHchyTypeId(1l);
		priceProductHierarchyElement.setPpdHchyValue("xx");
		priceProductHierarchyElement.setProdHrchySetElement(getreaPpdHchysetElmnts());
		productHierarchyElements.add(priceProductHierarchyElement);
		return productHierarchyElements;
	}

	private List<PriceProductHierarchySetElmnt> getreaPpdHchysetElmnts() {
		List<PriceProductHierarchySetElmnt> priceProductHierarchySetElmnts = Lists.newArrayList();
		PriceProductHierarchySetElmnt priceProductHierarchySetElmnt = new PriceProductHierarchySetElmnt();
		priceProductHierarchySetElmnt.setId(getppdid());
		priceProductHierarchySetElmnt.setLstUpdateBy("sa");
		priceProductHierarchySetElmnts.add(priceProductHierarchySetElmnt);
		return priceProductHierarchySetElmnts;
	}

	private PriceProductHierarchySetElmntPK getppdid() {
		PriceProductHierarchySetElmntPK priceProductHierarchySetElmntPK = new PriceProductHierarchySetElmntPK();
		priceProductHierarchySetElmntPK.setProdHrchyElemntId(1);
		priceProductHierarchySetElmntPK.setProdHrchySetId(1);
		return priceProductHierarchySetElmntPK;
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

}
