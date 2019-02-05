package colruyt.rearulmgtdmnejb.service.bl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionTypePK;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceTypePK;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleActionTypeDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSourceTypeDlService;
import colruyt.rearulmgtdmnejb.util.ProductHrchyElmntConverter;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;
import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class GeneralRuleServiceTest {
	@TestedObject
	GeneralRuleService generalRuleService = Mockito.mock(GeneralRuleService.class, Mockito.CALLS_REAL_METHODS);
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
	@InjectIntoByType
	private ReferenceDataConverter referenceDataConverter = Mockito.mock(ReferenceDataConverter.class);
	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);
	@InjectMocks
	private ProductHrchyElmntConverter productHrchyElmntConverter = Mockito.mock(ProductHrchyElmntConverter.class);

	@Test
	public void createRuleTypeAllTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		// action
		when(reaRuleConverter.convertRuleTypeForAll(Mockito.anyLong())).thenReturn(getAllTypeList());
		// source
		when(reaRuleConverter.convertRuleTypeForAll(Mockito.anyLong())).thenReturn(getAllTypeList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(getReactionRuleBo());
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void createRuleSourceandActionTest() throws ReaRuleManagementException, ReaRuleValidationException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setActionSelectAll(true);
		generalRuleBo.setSourceSelectAll(true);
		generalRuleBo.setActionTypeList(getRefActionTypeBo());
		generalRuleBo.setSourceTypeList(getRefSourceTypeBo());
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidatePermanentandTempTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setPermanentDuration(false);
		generalRuleBo.setTemporaryDuration(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateCheapandNationalBrandTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCheapBrand(false);
		generalRuleBo.setNationalBrand(false);
		generalRuleBo.setOwnBrand(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateHierarchySetTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setPriceProductHierarchySet(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateAssortmentNameTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setAssortmentName(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateImportanceCodeTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setImportanceCodeFrom(null);
		generalRuleBo.setImportanceCodeTo(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateRuleNameTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setRuleName(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void createRuleTypeDateValidationTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setValidFrom(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeImportanceCodeValidationTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setImportanceCodeFrom(20l);
		generalRuleBo.setImportanceCodeTo(15l);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeImportanceCodeValidationElseTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setImportanceCodeFrom(-1l);
		generalRuleBo.setImportanceCodeTo(150l);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllRuleNameTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setRuleName("All");
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllPermanentandTempTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setPermanentDuration(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllActionAllTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setActionSelectAll(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllPostandDirectTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setPostponedBenefit(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllAssortmentNameTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setAssortmentName("products");
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllCheapandNationalTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setCheapBrand(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.isNull(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(new Long(1l), expectedReactionRule.getRuleId());
	}

	@Test
	public void logicallyDeleteReactionRuleTest() throws ReaRuleManagementException {
		ReactionRule reactionRule = getReaRule();
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.findParentRule(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(reactionRule);
		Mockito.doNothing().when(reactionRuleDlService).updateLogicallyDeletedDate(Mockito.any(ReactionRule.class));
		generalRuleService.logicallyDeleteReactionRule(1L, "EN", "xyz");
	}

	@Test
	public void logicallyDeleteReactionRuleHavingChildTest() throws ReaRuleManagementException {
		ReactionRule reactionRule = getReaRule();
		reactionRule.setChildRuleId(1L);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.findParentRule(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(reactionRule);
		Mockito.doNothing().when(reactionRuleDlService).updateLogicallyDeletedDate(Mockito.any(ReactionRule.class));
		generalRuleService.logicallyDeleteReactionRule(1L, "EN", "xyz");
	}

	@Test(expected = ReaRuleManagementException.class)
	public void logicallyDeleteReactionRuleFailTest() throws ReaRuleManagementException {
		ReactionRule reactionRule = getReaRule();
		reactionRule.setChildRuleId(1L);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(null);
		when(reactionRuleDlService.findParentRule(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(reactionRule);
		Mockito.doNothing().when(reactionRuleDlService).updateLogicallyDeletedDate(Mockito.any(ReactionRule.class));
		generalRuleService.logicallyDeleteReactionRule(1L, "EN", "xyz");
	}

	/*@Test
	public void modifyRuleTypeAllTest() throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reaRuleConverter.convertRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleTypeForAll(Mockito.anyLong())).thenReturn(getAllTypeList());

		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.modifyRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedRuleBo = generalRuleService.modifyRule(getReactionRuleBo());
		assertEquals(new Long(1l), expectedRuleBo.getRuleId());

	}*/

	@Test(expected = ReaRuleValidationException.class)
	public void modifyRuleSourceandActionTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setActionSelectAll(false);
		generalRuleBo.setSourceSelectAll(false);
		generalRuleBo.setActionTypeList(getRefActionTypeBo());
		generalRuleBo.setSourceTypeList(getRefSourceTypeBo());
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reaRuleConverter.convertRuleAction(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reaRuleConverter.convertRuleSource(Mockito.any(GeneralRuleBo.class))).thenReturn(getSourceandActionList());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		when(generalRuleService.modifyRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		GeneralRuleBo expectedRuleBo = generalRuleService.modifyRule(generalRuleBo);
		assertEquals(new Long(1l), expectedRuleBo.getRuleId());
	}

	@Test
	public void getReactionRuleTest() throws ReaRuleManagementException {
		long ruleId = 1;
		String langCode = "EN";
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		ReactionRule expectedReactionRule = generalRuleService.getReactionRule(ruleId, langCode);
		Assert.assertNotNull(expectedReactionRule);
	}

	@Test
	public void getReactionRuleFailTest() throws ReaRuleManagementException {
		long ruleId = 1;
		String langCode = "EN";
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		ReactionRule expectedReactionRule = generalRuleService.getReactionRule(ruleId, langCode);
		Assert.assertNotSame(new Long(999l), expectedReactionRule.getReaRuleId());
	}

	@Test
	public void getRulesByRuleSetIdTest() {
		long ruleSetId = 1;
		when(reactionRuleDlService.findByRuleSetId(Mockito.anyLong())).thenReturn(getReaRuleList());
		List<ReactionRule> expectedReactionRuleList = generalRuleService.getRulesByRuleSetId(ruleSetId);
		assertEquals(getReaRuleList().size(), expectedReactionRuleList.size());
	}

	@Test
	public void getRulesByRuleSetIdFailTest() {
		long ruleSetId = 1;
		when(reactionRuleDlService.findByRuleSetId(Mockito.anyLong())).thenReturn(getReaRuleList());
		List<ReactionRule> expectedReactionRuleList = generalRuleService.getRulesByRuleSetId(ruleSetId);
		Assert.assertNotSame(new Long(999l), expectedReactionRuleList.size());
	}

	@Test
	public void getRuleTypeIdTest() {
		String ruleType = "Filtering";
		long ruleTypeId = 3;
		when(referenceDataService.findPkByType(Mockito.anyString())).thenReturn(ruleTypeId);
		long expectedRuleTypeId = generalRuleService.getRuleTypeId(ruleType);
		Assert.assertNotNull(expectedRuleTypeId);
	}

	@Test
	public void modifyPriorityOfRulesTest() throws ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.mock(ReactionRule.class))).thenReturn(getReaRule());
		generalRuleService.modifyPriorityOfRules(getruleLines());
	}

	@Test
	public void modifyPriorityOfRulesHavingChildTest() throws ReaRuleManagementException {
		ReactionRule reactionRule = getReaRule();
		reactionRule.setChildRuleId(1L);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.mock(ReactionRule.class))).thenReturn(getReaRule());
		generalRuleService.modifyPriorityOfRules(getruleLines());
	}

	@Test(expected = ReaRuleManagementException.class)
	public void modifyPriorityOfRulesFailTest() throws ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(null);
		when(reactionRuleDlService.createOrUpdate(Mockito.mock(ReactionRule.class))).thenReturn(getReaRule());
		generalRuleService.modifyPriorityOfRules(getruleLines());
	}

	@Test
	public void deleteReactionRuleTest() {
		String logonId = "xxx";
		generalRuleService.deleteReactionRule(getReaRule(), logonId);
	}

	@Test
	public void getGeneralRuleAttributesTest() {
		ReactionRule reactionRule = getReaRule();
		reactionRule.setRefActionTypes(getAllTypeList());
		reactionRule.setRefSourceTypes(getAllTypeList());
		when(referenceDataService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(referenceDataService.removeActionTypeAll(Mockito.anyListOf(RefActionTypeBo.class), Mockito.anyBoolean()))
				.thenReturn(getRefActionTypeBo());
		when(referenceDataService.getAllSourceTypes()).thenReturn(getRefSourceTypeBo());
		when(referenceDataService.removeSourceTypeAll(Mockito.anyListOf(RefSourceTypeBo.class)))
				.thenReturn(getRefSourceTypeBo());
		when(reaRuleConverter.convertGeneralRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedGeneralRuleAttributes = generalRuleService.getGeneralRuleAttributes(getReaRule(),
				getReactionRuleBo());
		assertEquals(getAllTypeList().size(), expectedGeneralRuleAttributes.getActionTypeList().size());
	}

	@Test
	public void getGeneralRuleAttributesNoActionandSourceTest() {
		ReactionRule reactionRule = getReaRule();
		reactionRule.setRefActionTypes(null);
		reactionRule.setRefSourceTypes(null);
		when(referenceDataConverter.convertRefReaActiontype(Mockito.anyListOf(Long.class)))
				.thenReturn(getRefActionTypeBo());
		when(referenceDataConverter.convertRefReaSource(Mockito.anyListOf(Long.class)))
				.thenReturn(getRefSourceTypeBo());
		when(reaRuleConverter.convertGeneralRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedGeneralRuleAttributes = generalRuleService.getGeneralRuleAttributes(getReaRule(),
				getReactionRuleBo());
		assertEquals(new Long(1l), expectedGeneralRuleAttributes.getRuleId());
	}

	@Test
	public void getGeneralRuleAttributesFailTest() {
		when(referenceDataService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(referenceDataService.removeActionTypeAll(Mockito.anyListOf(RefActionTypeBo.class), Mockito.anyBoolean()))
				.thenReturn(getRefActionTypeBo());
		when(referenceDataService.getAllSourceTypes()).thenReturn(getRefSourceTypeBo());
		when(referenceDataService.removeSourceTypeAll(Mockito.anyListOf(RefSourceTypeBo.class)))
				.thenReturn(getRefSourceTypeBo());
		when(reaRuleConverter.convertGeneralRuleBo(Mockito.any(ReactionRule.class), Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedGeneralRuleAttributes = generalRuleService.getGeneralRuleAttributes(getReaRule(),
				getReactionRuleBo());
		Assert.assertNotSame(new Long(99l), expectedGeneralRuleAttributes.getRuleId());
	}

	@Test
	public void logicallyDeleteReactionRulesTest() {
		List<Long> reactionRuleIds = getReactionRuleId();
		String logonId = "sa";
		Mockito.doNothing().when(reactionRuleDlService).logicallyDeleteRules(reactionRuleIds, logonId);
		generalRuleService.logicallyDeleteReactionRules(reactionRuleIds, logonId);
	}

	@Test
	public void findAllLogicallyDeletedRulesTest() {
		Date dateForRulesDelete = new Date();
		Date dateDeleteRuleBefore = new Date();
		when(reactionRuleDlService.findAllLogicallyDeletedRules(dateForRulesDelete)).thenReturn(getXPSRuleBoList());
		List<XPSRuleBo> expectedRuleBo = generalRuleService.findAllLogicallyDeletedRules(dateDeleteRuleBefore);
		Assert.assertEquals(1l, expectedRuleBo.size());
	}

	@Test
	public void findAllExpiredRulesTest() {
		Date dateDeleteRuleBefore = new Date();
		when(reactionRuleDlService.findAllExpiredRules(dateDeleteRuleBefore)).thenReturn(getXPSRuleBoList());
		List<XPSRuleBo> expectedRuleBo = generalRuleService.findAllExpiredRules(dateDeleteRuleBefore);
		Assert.assertEquals(1l, expectedRuleBo.size());
	}

	@Test
	public void physicalDeleteRulesTest() {
		Long deleteId = 1l;
		when(reactionRuleActionTypeDlService.physicalDeleteActionForRules(Mockito.any(XPSRuleBo.class)))
				.thenReturn(deleteId);
		when(reactionRuleSourceTypeDlService.physicalDeleteSourceType(Mockito.any(XPSRuleBo.class)))
				.thenReturn(deleteId);
		Mockito.doNothing().when(priceProductHierarchyBlService).physicalDeleteElements(getXpsRuleBo());
		long expectedResult = generalRuleService.physicalDeleteRules(getXpsRuleBo());
		Assert.assertNotNull(expectedResult);

	}

	private XPSRuleBo getXpsRuleBo() {
		XPSRuleBo xpsRuleBo = new XPSRuleBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		return xpsRuleBo;
	}

	private List<XPSRuleBo> getXPSRuleBoList() {
		List<XPSRuleBo> xpsRuleBos = Lists.newArrayList();
		XPSRuleBo xpsRuleBo = new XPSRuleBo(1l, 1l);
		xpsRuleBo.setRuleId(1l);
		xpsRuleBo.setRuleType(1l);
		xpsRuleBos.add(xpsRuleBo);
		return xpsRuleBos;
	}

	private List<Long> getAllTypeList() {
		List<Long> allTypeList = Lists.newArrayList();
		allTypeList.add(14l);
		return allTypeList;
	}

	private List<Long> getSourceandActionList() {
		List<Long> sourceandAction = Lists.newArrayList();
		sourceandAction.add(1l);
		return sourceandAction;
	}

	private List<Long> getReactionRuleId() {
		List<Long> reactionRuleId = Lists.newArrayList();
		return reactionRuleId;
	}

	private List<GeneralRuleBo> getruleLines() {
		List<GeneralRuleBo> generalRuleBos = Lists.newArrayList();
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
		reactionRuleBo.setRulePriority(1L);
		generalRuleBos.add(reactionRuleBo);
		return generalRuleBos;
	}

	public ReactionRuleSet getReaRuleset() {
		ReactionRuleSet reaRuleset = new ReactionRuleSet();
		reaRuleset.setCgChnId(1l);
		reaRuleset.setCompChainId(1l);
		reaRuleset.setLstUpdateBy("sa");
		reaRuleset.setReaRules(getReaRuleList());
		reaRuleset.setReaRulesetId(1l);
		reaRuleset.setRulesetComment("good");
		reaRuleset.setRulesetName("asa");
		reaRuleset.setRuleTypeId(1);
		return reaRuleset;

	}

	public List<ReactionRule> getReaRuleList() {
		List<ReactionRule> reaRulelist = Lists.newArrayList();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRulesetId(1l);
		reaRule.setChildRuleId(1l);
		reaRule.setReaRuleId(1l);
		reaRule.setRulePriority(2);
		reaRule.setRuleName("Reacting");
		reaRule.setIcFrom(10);
		reaRule.setIcTo(5);
		reaRule.setDirectYn(true);
		reaRule.setPostponedYn(true);
		reaRule.setPermenantYn(true);
		reaRule.setTemporaryYn(false);
		reaRule.setValidFrom(new Date());
		reaRule.setValidUpto(new Date());
		reaRule.setRecalculateYn(false);
		reaRule.setRuleComment("good");
		reaRule.setCreatedBy("sa");
		reaRule.setLstUpdateBy("sa");
		reaRulelist.add(reaRule);
		return reaRulelist;

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
		reactionRuleBo.setActionTypeList(getRefActionTypeBo());
		reactionRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactionRuleBo.setSourceTypeList(getRefSourceTypeBo());
		reactionRuleBo.setLangCode("EN");
		reactionRuleBo.setType("Reacting");
		reactionRuleBo.setChildRuleId(1l);
		reactionRuleBo.setRulePriority(1l);
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

	
	public List<ProductHierarchyElementBo> getProductHierarchyElement() {
		List<ProductHierarchyElementBo> productHierarchyElementlist = Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement = new ProductHierarchyElementBo();
		productHierarchyElement.setId(1l);
		productHierarchyElement.setPriceProductHierarchyTypeId(2l);
		productHierarchyElement.setPriceProductHierarchyValue("allProducts");
		productHierarchyElementlist.add(productHierarchyElement);
		return productHierarchyElementlist;
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
		reaRule.setRefActionTypes(getAllTypeList());
		reaRule.setRefSourceTypes(getAllTypeList());
		reaRule.setChildRuleId(1l);
		return reaRule;

	}

	public List<ReactionRuleSourceType> getReaRuleSetSrc() {
		List<ReactionRuleSourceType> reaRuleSetSrclist = Lists.newArrayList();
		ReactionRuleSourceType reaRuleSetSrc = new ReactionRuleSourceType();

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

	public PriceProductHierarchySet getReaPpdHchyset() {
		PriceProductHierarchySet reaPpdHchyset = new PriceProductHierarchySet();
		reaPpdHchyset.setAssortmentName("Asa");
		reaPpdHchyset.setCheapBrandYn(true);
		reaPpdHchyset.setCreatedBy("Sa");
		reaPpdHchyset.setLstUpdateBy("Sa");
		reaPpdHchyset.setNatBrandYn(true);
		reaPpdHchyset.setOwnBrandYn(true);
		reaPpdHchyset.setPpdHchysetId(1l);
		reaPpdHchyset.setReaRuleId(1l);
		return reaPpdHchyset;

	}

}