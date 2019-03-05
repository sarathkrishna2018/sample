package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;

@Stateless
@LocalBean
public class QuantityRuleActionConverter implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@EJB
	private ReferenceDataService referenceDataService;

	public QuantityRuleAction convert(QuantityRuleBo quantityRuleBo) {
		QuantityRuleAction quantityRuleAction = new QuantityRuleAction();
		quantityRuleAction.setReactionRuleId(quantityRuleBo.getRuleId());
		quantityRuleAction.setQuantityConditionId(quantityRuleBo.getConditionType().getCodeTypeId());
		quantityRuleAction.setQuantityTypeId(quantityRuleBo.getQuantityPriceType().getQuantityTypeId());
		return quantityRuleAction;
	}

	public QuantityRuleBo addQuantityRuleAction(QuantityRuleAction quantityRuleAction, QuantityRuleBo quantityBo) {
		quantityBo.setConditionType(convertConditionType(quantityRuleAction.getQuantityConditionId()));
		quantityBo.setQuantityPriceType(convertQtyPriceType(quantityRuleAction.getQuantityTypeId()));
		return quantityBo;
	}

	private RefQuantityPriceTypeBo convertQtyPriceType(int qtyTypeId) {
		RefQuantityPriceTypeBo priceType = new RefQuantityPriceTypeBo();
		List<RefQuantityPriceTypeBo> priceTypeLst = referenceDataService.getAllQuantityPriceTypes();
		for(int i=0; i<priceTypeLst.size();i++ ){
			if(qtyTypeId  == priceTypeLst.get(i).getQuantityTypeId()){
				priceType.setQuantityTypeId(qtyTypeId);
				priceType.setDescription(priceTypeLst.get(i).getDescription());
				priceType.setCodeLang(priceTypeLst.get(i).getCodeLang());
			}
		}
		return priceType;
	}

	private RefQuantityConditionTypeBo convertConditionType(int qtyCondId) {
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
