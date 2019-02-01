/**
 * 
 */
package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.RefFilterOutRecordingType;
import colruyt.rearulmgtdmnejb.entity.RefFilterOutRecordingTypeLang;
import colruyt.rearulmgtdmnejb.entity.RefQuantityCond;
import colruyt.rearulmgtdmnejb.entity.RefQuantityCondLang;
import colruyt.rearulmgtdmnejb.entity.RefQuantityType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityTypeLang;
import colruyt.rearulmgtdmnejb.enums.ActionTypeEnum;
import colruyt.rearulmgtdmnejb.enums.SourceTypeEnum;
import colruyt.rearulmgtdmnejb.entity.RefReason;
import colruyt.rearulmgtdmnejb.entity.RefReasonLang;
import colruyt.rearulmgtdmnejb.entity.RefRuletype;
import colruyt.rearulmgtdmnejb.entity.RefRuletypeLang;

/**
 *
 */
@Stateless
@LocalBean
public class ReferenceDataConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<RefActionTypeBo> convertRefReaActiontype(List<Long> refActionTypes) {
		List<RefActionTypeBo> refActionTypeBoList = Lists.newArrayList();
		for (Long ruleAction : refActionTypes) {
			RefActionTypeBo actionTypeBo = new RefActionTypeBo();
			for (ActionTypeEnum refActionType : ActionTypeEnum.values()) {
				if (ruleAction == refActionType.getActionTypeId()) {
					actionTypeBo.setActionTypeId(ruleAction);
					actionTypeBo.setActionTypeValue(refActionType.getActionTypeValue());
					actionTypeBo.setSequence(refActionType.getSequence());
					refActionTypeBoList.add(actionTypeBo);
				}
			}
		}
		return refActionTypeBoList;
	}

	

	public List<RefNotToReactCodeBo> convertRefNonReactingCodeType(List<RefReason> refNonReactingCodeTypes) {
		List<RefNotToReactCodeBo> refNotToReactCodeBoList = new ArrayList<>();
		for (RefReason refReason : refNonReactingCodeTypes) {
			RefNotToReactCodeBo refNotToReactCodeBo = new RefNotToReactCodeBo();
			refNotToReactCodeBo.setNotToReactCodeTypeId(refReason.getReasonId());
			List<RefLangBo> refLangList = convertRefReasonLangs(refReason.getRefReasonLangs());
			refNotToReactCodeBo.setCodeLang(refLangList);
			refNotToReactCodeBoList.add(refNotToReactCodeBo);
		}
		return refNotToReactCodeBoList;
	}

	public List<RefLangBo> convertRefReasonLangs(List<RefReasonLang> refReasonLangs) {
		List<RefLangBo> refLangList = Lists.newArrayList();
		if (refReasonLangs != null) {
			for (RefReasonLang refReasonLang : refReasonLangs) {
				RefLangBo refLangBo = new RefLangBo();
				refLangBo.setIsoLangCode(refReasonLang.getId().getIsoLangCode());
				refLangBo.setValue(refReasonLang.getReasonName());
				refLangList.add(refLangBo);
			}
		}
		return refLangList;
	}

	public List<RefQuantityConditionTypeBo> convertRefQtyCond(List<RefQuantityCond> refQtyConds) {
		List<RefQuantityConditionTypeBo> refQuantityConditionTypeBoList = Lists.newArrayList();
		for (RefQuantityCond refQtyCond : refQtyConds) {
			RefQuantityConditionTypeBo refQuantityConditionTypeBo = new RefQuantityConditionTypeBo();
			refQuantityConditionTypeBo.setCodeTypeId(refQtyCond.getQtyCondId());
			List<RefLangBo> refLangList = convertRefQtyCondLangs(refQtyCond.getRefQtyCondLangs());
			refQuantityConditionTypeBo.setCodeLang(refLangList);
			refQuantityConditionTypeBoList.add(refQuantityConditionTypeBo);
		}
		return refQuantityConditionTypeBoList;
	}

	public List<RefLangBo> convertRefQtyCondLangs(List<RefQuantityCondLang> refQtyCondLangs) {
		List<RefLangBo> refLangList = Lists.newArrayList();
		if (refQtyCondLangs != null) {
			for (RefQuantityCondLang refQtyCondLang : refQtyCondLangs) {
				RefLangBo refLangBo = new RefLangBo();
				refLangBo.setIsoLangCode(refQtyCondLang.getId().getIsoLangCode());
				refLangBo.setValue(refQtyCondLang.getQtyCondName());
				refLangList.add(refLangBo);
			}
		}
		return refLangList;
	}

	public List<RefSourceTypeBo> convertRefReaSource(List<Long> refSourceTypes) {
		List<RefSourceTypeBo> refSourceTypeBoList = Lists.newArrayList();
		for (Long sourceType : refSourceTypes) {
			RefSourceTypeBo sourceTypeBo = new RefSourceTypeBo();
			for (SourceTypeEnum refSourceType : SourceTypeEnum.values()) {
				if (sourceType == refSourceType.getSourceTypeId()) {
					sourceTypeBo.setSourceName(refSourceType.getSourceTypeName());
					sourceTypeBo.setSourceTypeId(refSourceType.getSourceTypeId());
					refSourceTypeBoList.add(sourceTypeBo);
				}
			}
		}
		return refSourceTypeBoList;
	}


	public List<RefQuantityPriceTypeBo> convertRefQtyType(List<RefQuantityType> refQtyTypes) {
		List<RefQuantityPriceTypeBo> rrefQuantityPriceTypeBoList = Lists.newArrayList();
		for (RefQuantityType refQtyType : refQtyTypes) {
			RefQuantityPriceTypeBo refQuantityPriceTypeBo = new RefQuantityPriceTypeBo();
			refQuantityPriceTypeBo.setQuantityTypeId(refQtyType.getQtyTypeId());
			List<RefLangBo> refLangList = convertRefQtyTypeLangs(refQtyType.getRefQtyTypeLangs());
			refQuantityPriceTypeBo.setCodeLang(refLangList);
			rrefQuantityPriceTypeBoList.add(refQuantityPriceTypeBo);
		}
		return rrefQuantityPriceTypeBoList;
	}

	public List<RefLangBo> convertRefQtyTypeLangs(List<RefQuantityTypeLang> refQtyTypeLangs) {
		List<RefLangBo> refLangList = Lists.newArrayList();
		if (refQtyTypeLangs != null) {
			for (RefQuantityTypeLang refQtyTypeLang : refQtyTypeLangs) {
				RefLangBo refLangBo = new RefLangBo();
				refLangBo.setIsoLangCode(refQtyTypeLang.getId().getIsoLangCode());
				refLangBo.setValue(refQtyTypeLang.getQtyTypeName());
				refLangList.add(refLangBo);
			}
		}
		return refLangList;
	}

	public List<RefRuleTypeBo> convertRuleType(List<RefRuletype> refRuletypes) {
		List<RefRuleTypeBo> refRuleTypeBoList = Lists.newArrayList();
		for (RefRuletype refRuletype : refRuletypes) {
			RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
			refRuleTypeBo.setRuleTypeId(refRuletype.getRuletypeId());
			List<RefLangBo> refLangList = convertRefRuletypeLangs(refRuletype.getRefRuletypeLang());
			refRuleTypeBo.setCodeLang(refLangList);
			refRuleTypeBoList.add(refRuleTypeBo);
		}
		return refRuleTypeBoList;
	}

	public List<RefLangBo> convertRefRuletypeLangs(List<RefRuletypeLang> refRuletypeLangs) {
		List<RefLangBo> refLangList = Lists.newArrayList();
		if (refRuletypeLangs != null) {
			for (RefRuletypeLang refRuletypeLang : refRuletypeLangs) {
				RefLangBo refLangBo = new RefLangBo();
				refLangBo.setIsoLangCode(refRuletypeLang.getId().getIsoLangCode());
				refLangBo.setValue(refRuletypeLang.getRuletypeName());
				refLangList.add(refLangBo);
			}
		}
		return refLangList;
	}

	public List<RefFilterOutRecordingTypeBo> convertRefFltoutType(List<RefFilterOutRecordingType> refFltoutTypes) {
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeBoList = Lists.newArrayList();
		for (RefFilterOutRecordingType refFltoutType : refFltoutTypes) {
			RefFilterOutRecordingTypeBo filterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
			filterOutRecordingTypeBo.setFilterOutTypeId(refFltoutType.getFltoutTypeId());
			List<RefLangBo> refLangList = convertRefFltoutTypeLangs(refFltoutType.getRefFltoutTypeLangs());
			filterOutRecordingTypeBo.setCodeLang(refLangList);
			refFilterOutRecordingTypeBoList.add(filterOutRecordingTypeBo);
		}
		return refFilterOutRecordingTypeBoList;
	}

	public List<RefLangBo> convertRefFltoutTypeLangs(List<RefFilterOutRecordingTypeLang> refFltoutTypeLangs) {
		List<RefLangBo> refLangList = Lists.newArrayList();
		if (refFltoutTypeLangs != null) {
			for (RefFilterOutRecordingTypeLang refFltoutTypeLang : refFltoutTypeLangs) {
				RefLangBo refLangBo = new RefLangBo();
				refLangBo.setIsoLangCode(refFltoutTypeLang.getId().getIsoLangCode());
				refLangBo.setValue(refFltoutTypeLang.getFltoutTypeName());
				refLangList.add(refLangBo);
			}
		}
		return refLangList;
	}

	public List<RefActionTypeBo> convertRefReaActiontype(ActionTypeEnum[] actionTypeEnums) {
		List<RefActionTypeBo> refActionTypeBos = new ArrayList<>();
		RefActionTypeBo refActionTypeBo;
		for(ActionTypeEnum actionTypeEnum : actionTypeEnums){
			refActionTypeBo = new RefActionTypeBo();
			refActionTypeBo.setActionTypeId(actionTypeEnum.getActionTypeId());
			refActionTypeBo.setActionTypeValue(actionTypeEnum.getActionTypeValue());
			refActionTypeBo.setSequence(actionTypeEnum.getSequence());
			refActionTypeBos.add(refActionTypeBo);
		}
		return refActionTypeBos;
	}

	public List<RefSourceTypeBo> convertRefReaSource(SourceTypeEnum[] sourceTypeEnums) {
		 List<RefSourceTypeBo> refSourceTypeBos = new ArrayList<>();
		 RefSourceTypeBo refSourceTypeBo;
		 for(SourceTypeEnum sourceTypeEnum : sourceTypeEnums) {
			 refSourceTypeBo = new RefSourceTypeBo();
			 refSourceTypeBo.setSourceName(sourceTypeEnum.getSourceTypeName());
			 refSourceTypeBo.setSourceTypeId(sourceTypeEnum.getSourceTypeId());
			 refSourceTypeBos.add(refSourceTypeBo);
		 }
		return refSourceTypeBos;
	}
}
