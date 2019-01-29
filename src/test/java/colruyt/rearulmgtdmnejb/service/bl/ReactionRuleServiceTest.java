/*package colruyt.rearulmgtdmnejb.service.bl;

import static org.junit.Assert.assertEquals;
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

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionTypePK;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceTypePK;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleActionTypeDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSourceTypeDlService;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReactionRuleServiceTest {
	@TestedObject
	GeneralRuleService reactionRuleBlService = Mockito.mock(GeneralRuleService.class, Mockito.CALLS_REAL_METHODS);
	@InjectIntoByType
	private ReactionRuleActionTypeDlService reactionRuleActionTypeDlService = Mockito
			.mock(ReactionRuleActionTypeDlService.class);
	@InjectIntoByType
	private ReactionRuleSourceTypeDlService reactionRuleSourceTypeDlService = Mockito
			.mock(ReactionRuleSourceTypeDlService.class);
	@InjectIntoByType
	private ReactionRuleDlService reactionRuleDlService = Mockito.mock(ReactionRuleDlService.class);
	@InjectIntoByType
	private ReaRuleConverter reaRuleConverter = Mockito.mock(ReaRuleConverter.class);
	@InjectIntoByType
	private ReactionRuleSetService reactionRuleSetBlService = Mockito.mock(ReactionRuleSetService.class);
	@InjectIntoByType
	private PriceProductHierarchyService priceProductHierarchyBlService = Mockito
			.mock(PriceProductHierarchyService.class);

	@Test
	public void createReactionRuleLineActionSelectAllTest()
			throws ReaRuleManagementException, ReaRuleValidationException {
		boolean isDuplicateAllowed = true;
		String logonId = "sa";
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		reactionRuleBo.setActionSelectAll(true);
		when(reactionRuleSetBlService.createReactionRuleSet(reactionRuleBo.getReactionRulesetBo(), isDuplicateAllowed,
				logonId)).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(null, reactionRuleBo)).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleActionForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetActtype());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.createGeneralRuleAttributes(reactionRuleBo,
				isDuplicateAllowed);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void createReactionRuleLineSourceSelectAllTest()
			throws ReaRuleManagementException, ReaRuleValidationException {
		boolean isDuplicateAllowed = true;
		String logonId = "sa";
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		reactionRuleBo.setSourceSelectAll(true);
		when(reactionRuleSetBlService.createReactionRuleSet(reactionRuleBo.getReactionRulesetBo(), isDuplicateAllowed,
				logonId)).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(null, reactionRuleBo)).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSourceForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleSourceTypeDlService).createOrUpdate(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.createGeneralRuleAttributes(reactionRuleBo,
				isDuplicateAllowed);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void createReactionRuleLineElseTest() throws ReaRuleManagementException, ReaRuleValidationException {
		boolean isDuplicateAllowed = true;
		String logonId = "sa";
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		when(reactionRuleSetBlService.createReactionRuleSet(reactionRuleBo.getReactionRulesetBo(), isDuplicateAllowed,
				logonId)).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(null, reactionRuleBo)).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleSourceTypeDlService).createOrUpdate(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.createGeneralRuleAttributes(reactionRuleBo,
				isDuplicateAllowed);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void createReactionRuleLineFailTest() throws ReaRuleManagementException, ReaRuleValidationException {
		boolean isDuplicateAllowed = true;
		String logonId = "sa";
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		when(reactionRuleSetBlService.createReactionRuleSet(reactionRuleBo.getReactionRulesetBo(), isDuplicateAllowed,
				logonId)).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(null, reactionRuleBo)).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleActionForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		when(reaRuleConverter.convertRuleSourceForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleSourceTypeDlService).createOrUpdate(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.createGeneralRuleAttributes(reactionRuleBo,
				isDuplicateAllowed);
		Assert.assertNotSame(new Long(999l), expectedReactionRule.getRuleId());
	}

	public GeneralRuleBo getReactionRuleBo() {
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
		reactionRuleBo.setActionTypeList(getRefActionType());
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactionRuleBo.setSourceTypeList(getRefSourceType());
		reactionRuleBo.setLangCode("EN");
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

	public ReactionRule getReaRule() {
		Date validFromdate = new Date();
		Date validTodate = new Date();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRuleId(1L);
		reaRule.setReaRulesetId(1l);
		reaRule.setRuleName("Filtering");
		reaRule.setIcFrom(10);
		reaRule.setIcTo(5);
		reaRule.setDirectYn(true);
		reaRule.setPostponedYn(true);
		reaRule.setPermenantYn(true);
		reaRule.setTemporaryYn(false);
		reaRule.setValidFrom(validFromdate);
		reaRule.setValidUpto(validTodate);
		reaRule.setRecalculateYn(false);
		reaRule.setRuleComment("good");
		reaRule.setCreatedBy("sa");
		reaRule.setLstUpdateBy("sa");
		return reaRule;

	}

	public List<ReactionRuleActionType> getReaRuleSetActtype() {
		List<ReactionRuleActionType> reaRuleSetActtypelist = Lists.newArrayList();
		ReactionRuleActionType reaRuleSetActtype = new ReactionRuleActionType();
		reaRuleSetActtype.setId(getReaRuleSetActtypePK());
		reaRuleSetActtype.setLstUpdateBy("sa");
		reaRuleSetActtypelist.add(reaRuleSetActtype);
		return reaRuleSetActtypelist;
	}

	public ReactionRuleActionTypePK getReaRuleSetActtypePK() {
		ReactionRuleActionTypePK reaRuleSetActtypePK = new ReactionRuleActionTypePK();
		reaRuleSetActtypePK.setActionTypeId(1);
		reaRuleSetActtypePK.setReaRuleId(1);
		return reaRuleSetActtypePK;

	}

	public List<ReactionRuleSourceType> getReaRuleSetSrc() {
		List<ReactionRuleSourceType> reaRuleSetSrclist = Lists.newArrayList();
		ReactionRuleSourceType reaRuleSetSrc = new ReactionRuleSourceType();
		reaRuleSetSrc.setLstUpdateBy("sa");
		reaRuleSetSrc.setId(getReaRuleSetSrcPK());
		reaRuleSetSrclist.add(reaRuleSetSrc);
		return reaRuleSetSrclist;
	}

	public ReactionRuleSourceTypePK getReaRuleSetSrcPK() {
		ReactionRuleSourceTypePK reaRuleSetSrcPK = new ReactionRuleSourceTypePK();
		reaRuleSetSrcPK.setReaRuleId(1);
		reaRuleSetSrcPK.setSourceId(1);
		return reaRuleSetSrcPK;
	}

	public List<RefActionTypeBo> getRefActionTypeBo() {
		List<RefActionTypeBo> refActionTypeBolist = Lists.newArrayList();
		RefActionTypeBo refActionTypeBo = new RefActionTypeBo();
		refActionTypeBo.setActionTypeId(1);
		refActionTypeBo.setActionTypeValue("sas");
		refActionTypeBo.setSequence(123);
		refActionTypeBolist.add(refActionTypeBo);
		return refActionTypeBolist;

	}

	public List<RefSourceTypeBo> getRefSourceTypeBo() {
		List<RefSourceTypeBo> refSourceTypeBolist = Lists.newArrayList();
		RefSourceTypeBo refSourceTypeBo = new RefSourceTypeBo();
		refSourceTypeBo.setSourceName("xyx");
		refSourceTypeBo.setSourceTypeId(1);
		refSourceTypeBolist.add(refSourceTypeBo);
		return refSourceTypeBolist;
	}

	@Test
	public void modifyReactionRuleLineActionSelectAllTest()
			throws ReaRuleManagementException, ReaRuleValidationException {
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		reactionRuleBo.setActionSelectAll(true);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		// when(referenceDataBlService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(reaRuleConverter.convertRuleActionForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetActtype());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.modifyGeneralRuleAttributes(reactionRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void modifyReactionRuleLineSourceSelectAllTest()
			throws ReaRuleManagementException, ReaRuleValidationException {
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		reactionRuleBo.setSourceSelectAll(true);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		// when(referenceDataBlService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(reaRuleConverter.convertRuleActionForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetActtype());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.modifyGeneralRuleAttributes(reactionRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void modifyReactionRuleLineSourceListActionListPresentTest()
			throws ReaRuleManagementException, ReaRuleValidationException {
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleSourceTypeDlService).createOrUpdate(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.modifyGeneralRuleAttributes(reactionRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void modifyReactionRuleLineFailTest() throws ReaRuleManagementException, ReaRuleValidationException {
		GeneralRuleBo reactionRuleBo = getReactionRuleBo();
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleActionForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		when(reaRuleConverter.convertRuleSourceForAll(Mockito.anyLong(), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleActionTypeDlService).createOrUpdate(getReaRuleSetActtype());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getReaRuleSetSrc());
		Mockito.doNothing().when(reactionRuleSourceTypeDlService).createOrUpdate(getReaRuleSetSrc());
		when(priceProductHierarchyBlService.createProductHierarchySet(reactionRuleBo)).thenReturn(reactionRuleBo);
		GeneralRuleBo expectedReactionRule = reactionRuleBlService.modifyGeneralRuleAttributes(reactionRuleBo);
		Assert.assertNotSame(new Long(999l), expectedReactionRule.getRuleId());
	}
}
*/