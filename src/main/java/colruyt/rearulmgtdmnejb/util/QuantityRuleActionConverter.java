package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;

public class QuantityRuleActionConverter implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@EJB
	private ReferenceDataService referenceDataService;

	public QuantityRuleAction convert(QuantityRuleBo quantityRuleBo) {
		QuantityRuleAction qtyRule = new QuantityRuleAction();
		qtyRule.setQtyCondId(quantityRuleBo.getConditionType().getCodeTypeId());
		qtyRule.setQtyTypeId(quantityRuleBo.getQuantityPriceType().getQuantityTypeId());
		return qtyRule;
	}

	public QuantityRuleBo convertToBo(QuantityRuleAction quantityRuleAction, QuantityRuleBo quantityBo) {
		quantityBo.setConditionType(convertConditionType(quantityRuleAction.getQtyCondId()));
		quantityBo.setQuantityPriceType(convertQtyPriceType(quantityRuleAction.getQtyTypeId()));
		return quantityBo;
	}

	private RefQuantityPriceTypeBo convertQtyPriceType(Long qtyTypeId) {
		RefQuantityPriceTypeBo priceType = new RefQuantityPriceTypeBo();
		List<RefQuantityPriceTypeBo> priceTypeLst = referenceDataService.getAllQuantityPriceTypes();
		for(int i=0; i<priceTypeLst.size();i++ ){
			if(qtyTypeId.equals(priceTypeLst.get(i).getQuantityTypeId())){
				priceType.setQuantityTypeId(qtyTypeId);
				priceType.setDescription(priceTypeLst.get(i).getDescription());
				priceType.setCodeLang(priceTypeLst.get(i).getCodeLang());
			}
		}
		return priceType;
	}

	private RefQuantityConditionTypeBo convertConditionType(Long qtyCondId) {
		RefQuantityConditionTypeBo refQuantityConditionTypeBo = new RefQuantityConditionTypeBo();
		List<RefQuantityConditionTypeBo> conditionTypeLst = referenceDataService.getAllQuantityConditionTypes();
		for(int i=0; i<conditionTypeLst.size();i++ ){
			if(qtyCondId==conditionTypeLst.get(i).getCodeTypeId()){
				refQuantityConditionTypeBo.setCodeTypeId(qtyCondId);
				refQuantityConditionTypeBo.setDescription(conditionTypeLst.get(i).getDescription());
				refQuantityConditionTypeBo.setCodeLang(conditionTypeLst.get(i).getCodeLang());
			}
		}
	return refQuantityConditionTypeBo;
	}

}
