package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;

public class QuantityRuleActionConverter implements Serializable {

	private static final long serialVersionUID = 1L;
	

	public static QuantityRuleAction convertFromBo(QuantityRuleBo quantityRuleBo) {
		QuantityRuleAction quantityRuleAction = new QuantityRuleAction();
		quantityRuleAction.setReactionRuleId(quantityRuleBo.getRuleId());
		quantityRuleAction.setQuantityConditionId(quantityRuleBo.getConditionType().getCodeTypeId());
		quantityRuleAction.setQuantityTypeId(quantityRuleBo.getQuantityPriceType().getQuantityTypeId());
		return quantityRuleAction;
	}

	public static QuantityRuleBo convertToBo(QuantityRuleAction quantityRuleAction, QuantityRuleBo quantityBo, List<RefQuantityPriceTypeBo> priceTypeLst, List<RefQuantityConditionTypeBo> conditionTypeLst) {
		quantityBo.setConditionType(getRefQuantityConditionType(quantityRuleAction.getQuantityConditionId(), conditionTypeLst));
		quantityBo.setQuantityPriceType(getRefQuantityPriceType(quantityRuleAction.getQuantityTypeId(), priceTypeLst));
		return quantityBo;
	}

	private static RefQuantityPriceTypeBo getRefQuantityPriceType(int qtyTypeId,  List<RefQuantityPriceTypeBo> priceTypeLst) {
		RefQuantityPriceTypeBo priceType = new RefQuantityPriceTypeBo();
		for (int i = 0; i < priceTypeLst.size(); i++) {
			if (qtyTypeId == priceTypeLst.get(i).getQuantityTypeId()) {
				priceType.setQuantityTypeId(qtyTypeId);
				priceType.setDescription(priceTypeLst.get(i).getDescription());
				priceType.setCodeLang(priceTypeLst.get(i).getCodeLang());
			}
		}
		return priceType;
	}

	private static RefQuantityConditionTypeBo getRefQuantityConditionType(int qtyCondId,List<RefQuantityConditionTypeBo> conditionTypeLst ) {
		RefQuantityConditionTypeBo refQuantityConditionTypeBo = new RefQuantityConditionTypeBo();
		for (int i = 0; i < conditionTypeLst.size(); i++) {
			if (qtyCondId == conditionTypeLst.get(i).getCodeTypeId()) {
				refQuantityConditionTypeBo.setCodeTypeId(qtyCondId);
				refQuantityConditionTypeBo.setDescription(conditionTypeLst.get(i).getDescription());
				refQuantityConditionTypeBo.setCodeLang(conditionTypeLst.get(i).getCodeLang());
			}
		}
		return refQuantityConditionTypeBo;
	}

}
