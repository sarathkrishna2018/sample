package colruyt.rearulmgtdmnejb.service.bl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.jose4j.json.internal.json_simple.parser.ParseException;
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
import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmnt;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySetElmntPK;
import colruyt.rearulmgtdmnejb.exception.PriceProductExternalServiceException;
import colruyt.rearulmgtdmnejb.exception.PriceProductServiceDownException;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchyElementDlService;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchySetDlService;
import colruyt.rearulmgtdmnejb.util.ProductHrchyElmntConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class PriceProductHierarchyServiceTest {
	@TestedObject
	private PriceProductHierarchyService priceProductHierarchyBlService;
	@InjectIntoByType
	private ProductHrchyElmntConverter productHrchyElmntConverter = Mockito.mock(ProductHrchyElmntConverter.class);
	@InjectIntoByType
	private ProductHierarchyElementDlService productHierarchyElementDlService = Mockito
			.mock(ProductHierarchyElementDlService.class);
	@InjectIntoByType
	private ProductHierarchySetDlService productHierarchySetDlService = Mockito
			.mock(ProductHierarchySetDlService.class);

	@Test
	public void createProductHierarchySetTest() {
		String logonId = "xyz";
		long productHierarchySetId = 1l;
		when(priceProductHierarchyBlService.getProductHrchyElmnt(getProductHierarchyElement(), logonId))
				.thenReturn(getReaPpdHchyElmnt());
		when(productHierarchySetDlService.create(Mockito.any(PriceProductHierarchySet.class)))
				.thenReturn(getReaPpdHchyset());
		when(productHierarchyElementDlService.create(Mockito.any(PriceProductHierarchyElement.class)))
				.thenReturn(createReaPpdHchyElmnt());
		when(productHierarchySetDlService.findByPk(productHierarchySetId)).thenReturn(getReaPpdHchyset());
		GeneralRuleBo expectedReactionRuleBo = priceProductHierarchyBlService
				.createProductHierarchySet(getReactionRuleBo());
		assertEquals(new Long(2l), expectedReactionRuleBo.getRulesetId());
	}

	@Test
	public void createProductHierarchySetFailTest() {
		String logonId = "sa";
		long productHierarchySetId = 1l;
		when(priceProductHierarchyBlService.getProductHrchyElmnt(getProductHierarchyElement(), logonId))
				.thenReturn(getReaPpdHchyElmnt());
		when(productHierarchySetDlService.create(Mockito.any(PriceProductHierarchySet.class)))
				.thenReturn(getReaPpdHchyset());
		when(productHierarchyElementDlService.create(Mockito.any(PriceProductHierarchyElement.class)))
				.thenReturn(createReaPpdHchyElmnt());
		when(productHierarchySetDlService.findByPk(productHierarchySetId)).thenReturn(getReaPpdHchyset());
		GeneralRuleBo expectedReactionRuleBo = priceProductHierarchyBlService
				.createProductHierarchySet(getReactionRuleBo());
		Assert.assertNotSame(new Long(999l), expectedReactionRuleBo.getRulesetId());

	}

	@Test
	public void manageExternalChangesTest() throws UnsupportedEncodingException, PriceProductExternalServiceException,
			PriceProductServiceDownException, IOException, ParseException {
		Set<String> externalValuesSet = getValuesSet();
		when(productHierarchyElementDlService.findAllElements()).thenReturn(getReaPpdHchyElmnt());
		when(productHierarchySetDlService.findSetElementByElementIds(Mockito.anyListOf(Long.class)))
				.thenReturn(getReaPpdHchysetElmnt());
		when(productHierarchySetDlService.findSetElementBySetIds(Mockito.anyListOf(Long.class)))
				.thenReturn(getPrdHrySetIds());
		Mockito.doNothing().when(productHierarchySetDlService).deleteSetElements(Mockito.anyListOf(Long.class));
		Mockito.doNothing().when(productHierarchyElementDlService).deleteElements(Mockito.anyListOf(Long.class));
		List<Long> productHierarchySetIds = priceProductHierarchyBlService.getproductHierarchySetIdList(externalValuesSet);
		Assert.assertNotNull(productHierarchySetIds);

	}
	
	@Test
	public void findByElementValueListTest() {
		Set<String> hierarchyValuesToDelete = getValuesSet();
		List<PriceProductHierarchyElement> productHierarchyElementList = getReaPpdHchyElmnt();
		List<Long> priceProductHierarchyElements = priceProductHierarchyBlService
				.findByElementValueList(hierarchyValuesToDelete, productHierarchyElementList);
		Assert.assertNotNull(priceProductHierarchyElements);
	}
	
	@Test
	public void findProductHierarchySetsTest() {
		List<Long> productHierarchySetIds = getPrdHrySetIds();
		when(productHierarchySetDlService.findProductSetByIds(getPrdHrySetIds()))
				.thenReturn(getPriceProductHierarchySetList());
		List<PriceProductHierarchySet> priceProductHierarchySets = priceProductHierarchyBlService
				.findProductHierarchySets(productHierarchySetIds);
		Assert.assertNotNull(priceProductHierarchySets);
	}
	@Test
	public void physicalDeleteElementsTest(){
		Long id=1l;
		when(productHierarchySetDlService.getPriceProductHierarchySetElementId(Mockito.any(DeleteRuleInfoBo.class))).thenReturn(id);
		when(productHierarchySetDlService.deletePriceProductHierarchySetElemnet(Mockito.anyLong())).thenReturn(id);
		when(productHierarchySetDlService.deletePriceProductHierarchySet(Mockito.any(DeleteRuleInfoBo.class))).thenReturn(id);
		priceProductHierarchyBlService.physicalDeleteElements(getXpsRuleBo());
	}
	private DeleteRuleInfoBo getXpsRuleBo() {
		DeleteRuleInfoBo xpsRuleBo = new DeleteRuleInfoBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
	}
	private List<PriceProductHierarchySet> getPriceProductHierarchySetList() {
		List<PriceProductHierarchySet> priceProductHierarchySets = new ArrayList<>();
		PriceProductHierarchySet priceProductHierarchySet = getReaPpdHchyset();
		priceProductHierarchySets.add(priceProductHierarchySet);
		return priceProductHierarchySets;
	}

	private Set<String> getValuesSet() {
		Set<String> externalValuesSet = new HashSet<>();
		externalValuesSet.add("A");
		return externalValuesSet;
	}

	private List<Long> getPrdHrySetIds() {
		List<Long> prdHrySetIdList = new ArrayList<>();
		prdHrySetIdList.add(1L);
		prdHrySetIdList.add(2L);
		prdHrySetIdList.add(3L);
		return prdHrySetIdList;
	}

	public GeneralRuleBo getReactionRuleBo() {
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
		reactionRuleBo.setLogonId("xyz");
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

	public List<RefSourceTypeBo> getRefSourceType() {
		List<RefSourceTypeBo> refSourceTypelist = Lists.newArrayList();
		RefSourceTypeBo refSourceType = new RefSourceTypeBo();
		refSourceType.setSourceName("online");
		refSourceType.setSourceTypeId(1);
		refSourceTypelist.add(refSourceType);
		return refSourceTypelist;
	}

	public PriceProductHierarchySet getReaPpdHchyset() {
		PriceProductHierarchySet reaPpdHchyset = new PriceProductHierarchySet();
		reaPpdHchyset.setAssortmentName("Asa");
		reaPpdHchyset.setCheapBrand(true);
		reaPpdHchyset.setCreatedBy("Sa");
		reaPpdHchyset.setLstUpdateBy("Sa");
		reaPpdHchyset.setNationalBrand(true);
		reaPpdHchyset.setOwnBrand(true);
		reaPpdHchyset.setProdHrchySetId(1l);
		reaPpdHchyset.setReactionRuleId(1l);
		return reaPpdHchyset;

	}

	public List<ProductHierarchyElementBo> getProductHierarchyElement() {
		List<ProductHierarchyElementBo> productHierarchyElementlist = Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElementBo = new ProductHierarchyElementBo();
		productHierarchyElementBo.setId(1l);
		productHierarchyElementBo.setPriceProductHierarchyTypeId(1l);
		productHierarchyElementBo.setPriceProductHierarchyValue("All products");
		productHierarchyElementlist.add(productHierarchyElementBo);
		return productHierarchyElementlist;

	}

	public List<PriceProductHierarchyElement> getReaPpdHchyElmnt() {
		List<PriceProductHierarchyElement> reaPpdHchyElmntlist = Lists.newArrayList();
		PriceProductHierarchyElement reaPpdHchyElmnt = new PriceProductHierarchyElement();
		reaPpdHchyElmnt.setProductHierarchyElementId(1l);
		reaPpdHchyElmnt.setProductHierarchyTypeId(1l);
		reaPpdHchyElmnt.setPpdHchyValue("ASA");
		reaPpdHchyElmnt.setCreatedBy("SA");
		reaPpdHchyElmnt.setProdHrchySetElement(getReaPpdHchysetElmnt());
		reaPpdHchyElmntlist.add(reaPpdHchyElmnt);
		return reaPpdHchyElmntlist;

	}

	public List<PriceProductHierarchySetElmnt> getReaPpdHchysetElmnt() {
		List<PriceProductHierarchySetElmnt> reaPpdHchysetElmntlist = Lists.newArrayList();
		PriceProductHierarchySetElmnt reaPpdHchysetElmnt = new PriceProductHierarchySetElmnt();
		reaPpdHchysetElmnt.setLstUpdateBy("Sa");
		PriceProductHierarchySetElmntPK hierarchySetElmntPK = new PriceProductHierarchySetElmntPK();
		hierarchySetElmntPK.setProductHierarchyElementId(1L);
		hierarchySetElmntPK.setProdicyHierarchySetId(1L);
		reaPpdHchysetElmnt.setId(hierarchySetElmntPK);
		reaPpdHchysetElmntlist.add(reaPpdHchysetElmnt);
		return reaPpdHchysetElmntlist;

	}

	public PriceProductHierarchyElement createReaPpdHchyElmnt() {
		PriceProductHierarchyElement reaPpdHchyElmnt = new PriceProductHierarchyElement();
		reaPpdHchyElmnt.setProductHierarchyElementId(1l);
		reaPpdHchyElmnt.setProductHierarchyTypeId(1l);
		reaPpdHchyElmnt.setPpdHchyValue("ASA");
		reaPpdHchyElmnt.setCreatedBy("SA");
		reaPpdHchyElmnt.setProdHrchySetElement(getReaPpdHchysetElmnt());
		return reaPpdHchyElmnt;

	}

}
