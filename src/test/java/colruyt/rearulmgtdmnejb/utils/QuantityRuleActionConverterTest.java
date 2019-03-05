package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;
import colruyt.rearulmgtdmnejb.util.QuantityRuleActionConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class QuantityRuleActionConverterTest {
	@TestedObject
	private QuantityRuleActionConverter quantityRuleActionConverter;

	@InjectIntoByType
	private ReferenceDataService referenceDataService = Mockito.mock(ReferenceDataService.class);

	@Test
	public void createConverterTest() {
		QuantityRuleBo quantityRule = getQuantityRule();
		QuantityRuleAction quantityRuleAction = new QuantityRuleAction();
		quantityRuleAction.setReactionRuleId(1L);
		quantityRuleAction.setQuantityConditionId(quantityRule.getConditionType().getCodeTypeId());
		quantityRuleAction.setQuantityTypeId(quantityRule.getQuantityPriceType().getQuantityTypeId());
		QuantityRuleAction expectedReaQtyRule = quantityRuleActionConverter.convert(quantityRule);
		assertEquals(2, expectedReaQtyRule.getQuantityTypeId());

	}

	@Test
	public void convertToBoTest() {
		QuantityRuleAction qtyRule = getReaQtyRule();
		QuantityRuleBo quantityRule = getQuantityRule();
		when(referenceDataService.getAllQuantityPriceTypes()).thenReturn(getPriceTypeLst());
		when(referenceDataService.getAllQuantityConditionTypes()).thenReturn(getQtyCondLst());
		quantityRule.setQuantityPriceType(getRefQuantityPriceType());
		quantityRule.setConditionType(getRefQuantityConditionType());
		QuantityRuleBo qtyRuleBo = quantityRuleActionConverter.addQuantityRuleAction(qtyRule, quantityRule);
		assertEquals(qtyRuleBo.getQuantityPriceType(), quantityRule.getQuantityPriceType());
		assertEquals(qtyRuleBo.getConditionType(), quantityRule.getConditionType());

	}

	private List<RefQuantityConditionTypeBo> getQtyCondLst() {
		List<RefQuantityConditionTypeBo> qtyCondLst = Lists.newArrayList();
		RefQuantityConditionTypeBo refQuantityConditionType = getRefQuantityConditionType();
		qtyCondLst.add(refQuantityConditionType);
		return qtyCondLst;
	}

	private List<RefQuantityPriceTypeBo> getPriceTypeLst() {
		List<RefQuantityPriceTypeBo> qtyPriceTypeLst = Lists.newArrayList();
		RefQuantityPriceTypeBo typeBo = getRefQuantityPriceType();
		qtyPriceTypeLst.add(typeBo);
		return qtyPriceTypeLst;
	}

	public QuantityRuleBo getQuantityRule() {
		QuantityRuleBo quantityRule = new QuantityRuleBo();
		quantityRule.setRuleId(1L);
		quantityRule.setConditionType(getRefQuantityConditionType());
		quantityRule.setQuantityPriceType(getRefQuantityPriceType());
		return quantityRule;

	}

	public RefQuantityConditionTypeBo getRefQuantityConditionType() {
		RefQuantityConditionTypeBo refQuantityConditionType = new RefQuantityConditionTypeBo();
		refQuantityConditionType.setCodeTypeId(1);
		refQuantityConditionType.setDescription("English");
		refQuantityConditionType.setCodeLang(getRefLangBo());
		return refQuantityConditionType;
	}

	public RefQuantityPriceTypeBo getRefQuantityPriceType() {
		RefQuantityPriceTypeBo refQuantityPriceType = new RefQuantityPriceTypeBo();
		refQuantityPriceType.setCodeLang(getRefLangBo());
		refQuantityPriceType.setDescription("English");
		refQuantityPriceType.setQuantityTypeId(2);
		return refQuantityPriceType;
	}

	public List<RefLangBo> getRefLangBo() {
		List<RefLangBo> refLanglist = Lists.newArrayList();
		RefLangBo refLang = new RefLangBo();
		refLang.setIsoLangCode("EN");
		refLang.setValue("English");
		refLanglist.add(refLang);
		return refLanglist;

	}

	public QuantityRuleAction getReaQtyRule() {
		QuantityRuleAction reaQtyRule = new QuantityRuleAction();
		reaQtyRule.setQuantityConditionId(1);
		reaQtyRule.setQuantityTypeId(1);
		return reaQtyRule;
	}

}
