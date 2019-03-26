package colruyt.rearulmgtdmnejb.service.bl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
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
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleActionTypeDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSourceTypeDlService;
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
	private ReactionRuleSetService reactionRuleSetBlService = Mockito.mock(ReactionRuleSetService.class);
	@InjectIntoByType
	private PriceProductHierarchyService priceProductHierarchyBlService = Mockito
			.mock(PriceProductHierarchyService.class);
	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);

	@Test
	public void createGeneralRule_IsActionAndSourceSelectAllTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(getReactionRuleBo());
		assertEquals(true, expectedReactionRule.isActionSelectAll());
	}

	@Test
	public void createGeneralRule_RuleSourceandActionTest()
			throws ReaRuleManagementException, ReaRuleValidationException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(false);
		generalRuleBo.setActionSelectAll(false);
		generalRuleBo.setSourceSelectAll(false);
		generalRuleBo.setActionTypeList(getRefActionTypeBo());
		generalRuleBo.setSourceTypeList(getRefSourceTypeBo());
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(generalRuleBo);
		assertEquals(false, expectedReactionRule.isActionSelectAll());

	}

	@Test
	public void createReactingRule() throws ReaRuleManagementException, ReaRuleValidationException {
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedReactionRule = generalRuleService.createRule(getReactingRuleBO());
		assertEquals(true, expectedReactionRule.isActionSelectAll());
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidatePermanentandTempTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setPermanentDuration(false);
		generalRuleBo.setTemporaryDuration(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

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
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateHierarchySetTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setPriceProductHierarchySet(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateAssortmentNameTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setAssortmentName(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);
	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateImportanceCodeTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setImportanceCodeFrom(null);
		generalRuleBo.setImportanceCodeTo(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createValidateRuleNameTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setRuleName(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test
	public void createRuleTypeDateValidationTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setValidFrom(null);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeImportanceCodeValidationTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setImportanceCodeFrom(20l);
		generalRuleBo.setImportanceCodeTo(15l);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeImportanceCodeValidationElseTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setImportanceCodeFrom(-1l);
		generalRuleBo.setImportanceCodeTo(150l);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllRuleNameTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setRuleName("All");
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllPermanentandTempTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setPermanentDuration(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllActionAllTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setActionSelectAll(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllPostandDirectTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setPostponedBenefit(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllAssortmentNameTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setAssortmentName("products");
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test(expected = ReaRuleValidationException.class)
	public void createRuleTypeCatchAllCheapandNationalTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(true);
		generalRuleBo.setCheapBrand(false);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		generalRuleService.createRule(generalRuleBo);

	}

	@Test
	public void logicallyDeleteReactionRuleTest() throws ReaRuleManagementException {
		ReactionRule reactionRule = getReaRule();
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.findParentRule(Mockito.anyLong())).thenReturn(reactionRule);
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(reactionRule);
		Mockito.doNothing().when(reactionRuleDlService).updateLogicallyDeletedDate(Mockito.any(ReactionRule.class));
		generalRuleService.logicallyDeleteReactionRule(1L, "EN", "xyz");
		verify(reactionRuleDlService).findByPk(1L);
		verify(reactionRuleDlService).findParentRule(1L);
		verify(reactionRuleDlService).createOrUpdate(Mockito.any(ReactionRule.class));
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
		verify(reactionRuleDlService).findByPk(1L);
		verify(reactionRuleDlService).findParentRule(1L);
		verify(reactionRuleDlService).createOrUpdate(Mockito.any(ReactionRule.class));
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

	@Test
	public void modifyGeneralRule_isActionAndSourceSelectAllTest()
			throws ReaRuleValidationException, ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(generalRuleService.modifyRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedRuleBo = generalRuleService.modifyRule(getReactionRuleBo());
		assertEquals(true, expectedRuleBo.isActionSelectAll());

	}

	@Test
	public void modifyGeneralRule_SourceandActionTest() throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		generalRuleBo.setCatchAll(false);
		generalRuleBo.setActionSelectAll(false);
		generalRuleBo.setSourceSelectAll(false);
		generalRuleBo.setActionTypeList(getRefActionTypeBo());
		generalRuleBo.setSourceTypeList(getRefSourceTypeBo());
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(getReaRule());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		when(generalRuleService.modifyRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		GeneralRuleBo expectedRuleBo = generalRuleService.modifyRule(generalRuleBo);
		assertEquals(false, expectedRuleBo.isActionSelectAll());
	}

	@Test
	public void modifyChildRuleTest() throws ReaRuleManagementException, ReaRuleValidationException, ParseException {
		ReactionRule reaRule = getReaRule();
		GeneralRuleBo generalRuleBo = getReactionRuleBo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date validFrom = dateFormat.parse("2017/02/02");
		Date validFromBo = dateFormat.parse("2020/02/02");
		reaRule.setValidFrom(validFrom);
		generalRuleBo.setValidFrom(validFromBo);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(reaRule);
		when(reactionRuleSetBlService.createReactionRuleSet(Mockito.any(ReactionRulesetBo.class), Mockito.anyBoolean(),
				Mockito.anyString())).thenReturn(createReactionRuleset());
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(reaRule);
		when(reactionRuleDlService.createOrUpdate(Mockito.any(ReactionRule.class))).thenReturn(reaRule);
		when(generalRuleService.createRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		when(priceProductHierarchyBlService.createProductHierarchySet(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		when(generalRuleService.modifyRuleSpecificAttributes(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(generalRuleBo);
		GeneralRuleBo expectedRuleBo = generalRuleService.modifyRule(generalRuleBo);
		assertEquals(new Long(1l), expectedRuleBo.getChildRuleId());
	}

	@Test
	public void viewReactionRuleTest() throws ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(referenceDataService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(referenceDataService.removeActionTypeAll(Mockito.anyListOf(RefActionTypeBo.class), Mockito.anyBoolean()))
				.thenReturn(getRefActionTypeBo());
		when(referenceDataService.getAllSourceTypes()).thenReturn(getRefSourceTypeBo());
		when(referenceDataService.removeSourceTypeAll(Mockito.anyListOf(RefSourceTypeBo.class)))
				.thenReturn(getRefSourceTypeBo());
		when(generalRuleService.getRuleSpecificValues(Mockito.any(GeneralRuleBo.class)))
				.thenReturn(getReactionRuleBo());
		GeneralRuleBo expectedRuleBo = generalRuleService.viewReactionRule(getReactionRuleBo(), 1l, "En");
		assertEquals(new Long(1l), expectedRuleBo.getRuleId());
	}

	@Test
	public void getReactionRuleTest() throws ReaRuleManagementException {
		long ruleId = 1;
		String langCode = "EN";
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		ReactionRule expectedReactionRule = generalRuleService.getReactionRule(ruleId, langCode);
		assertEquals(1, expectedReactionRule.getReaRulesetId());
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
	public void modifyPriorityOfRulesTest() throws ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.mock(ReactionRule.class))).thenReturn(getReaRule());
		generalRuleService.modifyPriorityOfRules(getruleLines());
		verify(reactionRuleDlService, times(2)).findByPk(1L);
		verify(reactionRuleDlService, times(2)).createOrUpdate(Mockito.any(ReactionRule.class));

	}

	@Test
	public void modifyPriorityOfRulesHavingChildTest() throws ReaRuleManagementException {
		ReactionRule reactionRule = getReaRule();
		reactionRule.setChildRuleId(1L);
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(getReaRule());
		when(reactionRuleDlService.createOrUpdate(Mockito.mock(ReactionRule.class))).thenReturn(getReaRule());
		generalRuleService.modifyPriorityOfRules(getruleLines());
		verify(reactionRuleDlService, times(2)).findByPk(1L);

	}

	@Test(expected = ReaRuleManagementException.class)
	public void modifyPriorityOfRulesFailTest() throws ReaRuleManagementException {
		when(reactionRuleDlService.findByPk(Mockito.anyLong())).thenReturn(null);
		when(reactionRuleDlService.createOrUpdate(Mockito.mock(ReactionRule.class))).thenReturn(getReaRule());
		generalRuleService.modifyPriorityOfRules(getruleLines());
		verify(reactionRuleDlService, times(2)).findByPk(1L);
		verify(reactionRuleDlService).createOrUpdate(getReaRule());
	}

	@Test
	public void deleteReactionRuleTest() {
		String logonId = "xxx";
		Mockito.doNothing().when(reactionRuleDlService).updateLogicallyDeletedDate(Mockito.any(ReactionRule.class));
		generalRuleService.deleteReactionRule(getReaRule(), logonId);
		verify(reactionRuleDlService).updateLogicallyDeletedDate(Mockito.any(ReactionRule.class));
	}

	@Test
	public void getGeneralRuleAttributesTest() {
		when(referenceDataService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(referenceDataService.removeActionTypeAll(Mockito.anyListOf(RefActionTypeBo.class), Mockito.anyBoolean()))
				.thenReturn(getRefActionTypeBo());
		when(referenceDataService.getAllSourceTypes()).thenReturn(getRefSourceTypeBo());
		when(referenceDataService.removeSourceTypeAll(Mockito.anyListOf(RefSourceTypeBo.class)))
				.thenReturn(getRefSourceTypeBo());
		GeneralRuleBo expectedGeneralRuleAttributes = generalRuleService.getGeneralRuleAttributes(getReaRule(),
				getReactionRuleBo());
		Assert.assertNotNull(expectedGeneralRuleAttributes);
	}

	@Test
	public void getGeneralRuleAttributesFailTest() {
		when(referenceDataService.getAllActionTypes()).thenReturn(getRefActionTypeBo());
		when(referenceDataService.removeActionTypeAll(Mockito.anyListOf(RefActionTypeBo.class), Mockito.anyBoolean()))
				.thenReturn(getRefActionTypeBo());
		when(referenceDataService.getAllSourceTypes()).thenReturn(getRefSourceTypeBo());
		when(referenceDataService.removeSourceTypeAll(Mockito.anyListOf(RefSourceTypeBo.class)))
				.thenReturn(getRefSourceTypeBo());
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
		verify(reactionRuleDlService).logicallyDeleteRules(reactionRuleIds, logonId);
	}

	@Test
	public void findAllLogicallyDeletedRulesTest() {
		Date dateForRulesDelete = new Date();
		when(reactionRuleDlService.findAllLogicallyDeletedRules(dateForRulesDelete)).thenReturn(getXPSRuleBoList());
		List<DeleteRuleInfoBo> expectedRuleBo = generalRuleService.findAllLogicallyDeletedRules(dateForRulesDelete);
		verify(reactionRuleDlService).findAllLogicallyDeletedRules(dateForRulesDelete);
		Assert.assertEquals(1l, expectedRuleBo.size());
	}

	@Test
	public void findAllExpiredRulesTest() {
		Date dateDeleteRuleBefore = new Date();
		when(reactionRuleDlService.findAllExpiredRules(dateDeleteRuleBefore)).thenReturn(getXPSRuleBoList());
		List<DeleteRuleInfoBo> expectedRuleBo = generalRuleService.findAllExpiredRules(dateDeleteRuleBefore);
		verify(reactionRuleDlService).findAllExpiredRules(dateDeleteRuleBefore);
		Assert.assertEquals(1l, expectedRuleBo.size());
	}

	@Test
	public void physicalDeleteRulesTest() {
		Mockito.doNothing().when(reactionRuleActionTypeDlService).physicalDeleteActionForRules(getDeleteRuleInfoBo());
		Mockito.doNothing().when(priceProductHierarchyBlService).physicalDeleteElements(getDeleteRuleInfoBo());
		generalRuleService.physicalDeleteRules(getDeleteRuleInfoBo());
		verify(reactionRuleActionTypeDlService).physicalDeleteActionForRules(getDeleteRuleInfoBo());
		verify(priceProductHierarchyBlService).physicalDeleteElements(getDeleteRuleInfoBo());
	}

	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}

	private List<DeleteRuleInfoBo> getXPSRuleBoList() {
		List<DeleteRuleInfoBo> xpsRuleBos = Lists.newArrayList();
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		xpsRuleBos.add(deleteRuleInfoBo);
		return xpsRuleBos;
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
		reactionRuleBo.setChildRuleId(1l);
		generalRuleBos.add(reactionRuleBo);
		return generalRuleBos;
	}

	private List<ReactionRule> getReaRuleList() {
		List<ReactionRule> reaRulelist = Lists.newArrayList();
		ReactionRule reaRule = new ReactionRule();
		reaRule.setReaRulesetId(1l);
		reaRule.setChildRuleId(1l);
		reaRule.setReaRuleId(1l);
		reaRule.setRulePriority(2);
		reaRule.setRuleName("Reacting");
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
		reaRule.setLastUpdateBy("sa");
		reaRulelist.add(reaRule);
		return reaRulelist;

	}

	private GeneralRuleBo getReactionRuleBo() {
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

	private GeneralRuleBo getReactingRuleBO() {
		ReactingRuleBo reactingRuleBo = new ReactingRuleBo();
		Date validFromdate = new Date();
		reactingRuleBo.setRuleName("Catch ALL");
		reactingRuleBo.setAssortmentName("ALL");
		reactingRuleBo.setComments("Good");
		reactingRuleBo.setLogonId("sa");
		reactingRuleBo.setRuleId(1l);
		reactingRuleBo.setRulesetId(2l);
		reactingRuleBo.setImportanceCodeFrom(1l);
		reactingRuleBo.setImportanceCodeTo(99l);
		reactingRuleBo.setProductHierarchySetId(1l);
		reactingRuleBo.setCatchAll(true);
		reactingRuleBo.setCheapBrand(true);
		reactingRuleBo.setNationalBrand(true);
		reactingRuleBo.setOwnBrand(true);
		reactingRuleBo.setRecalculate(true);
		reactingRuleBo.setTemporaryDuration(true);
		reactingRuleBo.setPermanentDuration(true);
		reactingRuleBo.setActionSelectAll(true);
		reactingRuleBo.setSourceSelectAll(true);
		reactingRuleBo.setDirectBenefit(true);
		reactingRuleBo.setPostponedBenefit(true);
		reactingRuleBo.setValidFrom(validFromdate);
		reactingRuleBo.setValidTo(null);
		reactingRuleBo.setRefRuleTypeBo(getRefRuleType());
		reactingRuleBo.setReactionRulesetBo(createReactionRuleset());
		reactingRuleBo.setActionTypeList(getRefActionTypeBo());
		reactingRuleBo.setPriceProductHierarchySet(getProductHierarchyElement());
		reactingRuleBo.setSourceTypeList(getRefSourceTypeBo());
		reactingRuleBo.setLangCode("EN");
		reactingRuleBo.setType("Reacting");
		reactingRuleBo.setChildRuleId(1l);
		reactingRuleBo.setRulePriority(1l);
		reactingRuleBo.setReactingAmount(2d);
		return reactingRuleBo;
	}

	private RefRuleTypeBo getRefRuleType() {
		RefRuleTypeBo refRuleType = new RefRuleTypeBo();
		refRuleType.setDescription("Reacting");
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

	private List<ProductHierarchyElementBo> getProductHierarchyElement() {
		List<ProductHierarchyElementBo> productHierarchyElementlist = Lists.newArrayList();
		ProductHierarchyElementBo productHierarchyElement = new ProductHierarchyElementBo();
		productHierarchyElement.setId(1l);
		productHierarchyElement.setPriceProductHierarchyTypeId(2l);
		productHierarchyElement.setPriceProductHierarchyValue("allProducts");
		productHierarchyElementlist.add(productHierarchyElement);
		return productHierarchyElementlist;
	}

	private ReactionRule getReaRule() {
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
		reaRule.setLastUpdateBy("sa");
		reaRule.setRefActionTypes(getActionTypeList());
		reaRule.setRefSourceTypes(getSourceTypeList());
		reaRule.setChildRuleId(1l);
		reaRule.setRulePriority(1);
		reaRule.setPriceProductHierarchySet(getPriceProductHierarchySetList());
		return reaRule;

	}

	private List<SourceType> getSourceTypeList() {
		List<SourceType> sourceTypes = Arrays.asList(SourceType.values());
		return sourceTypes;
	}

	private List<ActionType> getActionTypeList() {
		List<ActionType> actionTypes = Arrays.asList(ActionType.values());
		return actionTypes;
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
	private List<PriceProductHierarchyElement> getPriceProductHierarchyElementList(){
		List<PriceProductHierarchyElement> priceProductHierarchyElementList = Lists.newArrayList();
		PriceProductHierarchyElement priceProductHierarchyElement = new PriceProductHierarchyElement();
		priceProductHierarchyElement.setCreatedBy("ktr");
		priceProductHierarchyElement.setProductHierarchyElementId(1l);
		priceProductHierarchyElement.setProductHierarchyTypeId(2l);
		priceProductHierarchyElement.setProductHierarchySetElement(getPriceProductHierarchySetElmntList());
		priceProductHierarchyElement.setProductHierarchyValue("ALL");
		priceProductHierarchyElementList.add(priceProductHierarchyElement);
		return priceProductHierarchyElementList;
	}
	private List<PriceProductHierarchySetElement> getPriceProductHierarchySetElmntList(){
		List<PriceProductHierarchySetElement> priceProductHierarchySetElmntList = Lists.newArrayList();
		PriceProductHierarchySetElement priceProductHierarchySetElmnt = new PriceProductHierarchySetElement();
		PriceProductHierarchySetElementPK priceProductHierarchySetElmntPK = new PriceProductHierarchySetElementPK();
		priceProductHierarchySetElmntPK.setProdicyHierarchySetId(1);
		priceProductHierarchySetElmntPK.setProductHierarchyElementId(1);
		priceProductHierarchySetElmnt.setId(priceProductHierarchySetElmntPK);
		priceProductHierarchySetElmnt.setLastUpdateBy("ktr");
		priceProductHierarchySetElmntList.add(priceProductHierarchySetElmnt);
		return priceProductHierarchySetElmntList; 
	}
	private List<PriceProductHierarchySet> getPriceProductHierarchySetList(){
		List<PriceProductHierarchySet> priceProductHierarchySetList = Lists.newArrayList();
		PriceProductHierarchySet priceProductHierarchySet = new PriceProductHierarchySet();
		priceProductHierarchySet.setAssortmentName("All");
		priceProductHierarchySet.setCheapBrand(true);
		priceProductHierarchySet.setNationalBrand(true);
		priceProductHierarchySet.setOwnBrand(false);
		priceProductHierarchySet.setProductHierarchySetId(1l);
		priceProductHierarchySet.setPriceProductHierarchyElements(getPriceProductHierarchyElementList());
		return priceProductHierarchySetList;
	}
}