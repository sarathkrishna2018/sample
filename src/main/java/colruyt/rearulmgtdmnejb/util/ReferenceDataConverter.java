package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

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
import colruyt.rearulmgtdmnejb.entity.RefQuantityConditionType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityType;
import colruyt.rearulmgtdmnejb.entity.RefReasonType;
import colruyt.rearulmgtdmnejb.entity.RefRuleType;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.FilterOutRecordingType;
import colruyt.rearulmgtdmnejb.enums.QuantityCondition;
import colruyt.rearulmgtdmnejb.enums.QuantityType;
import colruyt.rearulmgtdmnejb.enums.ReasonType;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.enums.SourceType;

public class ReferenceDataConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<RefActionTypeBo> convertToActionTypeBo(List<ActionType> refActionTypes) {
		List<RefActionTypeBo> refActionTypeBoList = Lists.newArrayList();
		for (ActionType ruleAction : refActionTypes) {
			RefActionTypeBo actionTypeBo = new RefActionTypeBo();
			for (ActionType refActionType : ActionType.values()) {
				if (ruleAction.getActionTypeId() == refActionType.getActionTypeId()) {
					actionTypeBo.setActionTypeId(refActionType.getActionTypeId());
					actionTypeBo.setActionTypeValue(refActionType.getActionTypeValue());
					actionTypeBo.setSequence(refActionType.getSequence());
					refActionTypeBoList.add(actionTypeBo);
				}
			}
		}
		return refActionTypeBoList;
	}

	public static List<RefNotToReactCodeBo> convertToProposeNotToReactReasonBo(ReasonType[] refReasonEnums,
			List<RefReasonType> refReasonTypes) {
		List<RefNotToReactCodeBo> refNotToReactCodeBoList = Lists.newArrayList();
		for (ReasonType reasonType : refReasonEnums) {
			RefNotToReactCodeBo refNotToReactCodeBo = new RefNotToReactCodeBo();
			List<RefLangBo> refLangBos = Lists.newArrayList();
			refNotToReactCodeBo.setNotToReactCodeTypeId(reasonType.getReasonID());
			for (RefReasonType reasonTypes : refReasonTypes) {
				if (reasonTypes.getId().getReasonId() == reasonType.getReasonID()) {
					RefLangBo refLangBo = new RefLangBo();
					refLangBo.setIsoLangCode(reasonTypes.getId().getIsoLangCode());
					refLangBo.setValue(reasonTypes.getReasonName());
					refNotToReactCodeBo.setDescription(reasonTypes.getDescription());
					refLangBos.add(refLangBo);
				}

			}
			refNotToReactCodeBo.setCodeLang(refLangBos);
			refNotToReactCodeBoList.add(refNotToReactCodeBo);
		}
		return refNotToReactCodeBoList;
	}

	public static List<RefSourceTypeBo> convertToSourceTypeBo(List<SourceType> refSourceTypes) {
		List<RefSourceTypeBo> refSourceTypeBoList = Lists.newArrayList();
		for (SourceType sourceType : refSourceTypes) {
			RefSourceTypeBo sourceTypeBo = new RefSourceTypeBo();
			for (SourceType refSourceType : SourceType.values()) {
				if (sourceType.getSourceTypeId() == refSourceType.getSourceTypeId()) {
					sourceTypeBo.setSourceName(refSourceType.getSourceTypeName());
					sourceTypeBo.setSourceTypeId(refSourceType.getSourceTypeId());
					refSourceTypeBoList.add(sourceTypeBo);
				}
			}
		}
		return refSourceTypeBoList;
	}

	public static List<RefActionTypeBo> convertToActionTypeBo(ActionType[] actionTypeEnums) {
		List<RefActionTypeBo> refActionTypeBos = Lists.newArrayList();
		RefActionTypeBo refActionTypeBo;
		for (ActionType actionType : actionTypeEnums) {
			refActionTypeBo = new RefActionTypeBo();
			refActionTypeBo.setActionTypeId(actionType.getActionTypeId());
			refActionTypeBo.setActionTypeValue(actionType.getActionTypeValue());
			refActionTypeBo.setSequence(actionType.getSequence());
			refActionTypeBos.add(refActionTypeBo);
		}
		return refActionTypeBos;
	}

	public static List<RefSourceTypeBo> convertToSourceTypeBo(SourceType[] sourceTypeEnums) {
		List<RefSourceTypeBo> refSourceTypeBos = Lists.newArrayList();
		RefSourceTypeBo refSourceTypeBo;
		for (SourceType sourceType : sourceTypeEnums) {
			refSourceTypeBo = new RefSourceTypeBo();
			refSourceTypeBo.setSourceName(sourceType.getSourceTypeName());
			refSourceTypeBo.setSourceTypeId(sourceType.getSourceTypeId());
			refSourceTypeBos.add(refSourceTypeBo);
		}
		return refSourceTypeBos;
	}

	public static List<RefQuantityPriceTypeBo> convertToQuantityPriceTypeBo(QuantityType[] refQuantityTypeValues,
			List<RefQuantityType> quantityTypes) {
		List<RefQuantityPriceTypeBo> refQuantityPriceTypeBoList = Lists.newArrayList();
		for (QuantityType quantityType : refQuantityTypeValues) {
			RefQuantityPriceTypeBo refQuantityPriceTypeBo = new RefQuantityPriceTypeBo();
			List<RefLangBo> refLangBos = Lists.newArrayList();
			refQuantityPriceTypeBo.setQuantityTypeId(quantityType.getQuantityTypeId());
			for (RefQuantityType refQuantityType : quantityTypes) {
				if (refQuantityType.getId().getQuantityTypeId() == quantityType.getQuantityTypeId()) {
					RefLangBo refLangBo = new RefLangBo();
					refLangBo.setIsoLangCode(refQuantityType.getId().getIsoLangCode());
					refLangBo.setValue(refQuantityType.getQuantityTypeName());
					refQuantityPriceTypeBo.setDescription(refQuantityType.getDescription());
					refLangBos.add(refLangBo);
				}
			}
			refQuantityPriceTypeBo.setCodeLang(refLangBos);
			refQuantityPriceTypeBoList.add(refQuantityPriceTypeBo);
		}
		return refQuantityPriceTypeBoList;
	}

	public static  List<RefQuantityConditionTypeBo> convertToQuantityConditionTypeBo(QuantityCondition[] quantityConditionEnums,
			List<RefQuantityConditionType> refQuantityConditionTypes) {
		List<RefQuantityConditionTypeBo> refQuantityConditionTypeBoList = Lists.newArrayList();
		for (QuantityCondition quantityCondition : quantityConditionEnums) {
			RefQuantityConditionTypeBo refQuantityConditionTypeBo = new RefQuantityConditionTypeBo();
			List<RefLangBo> refLangBos = Lists.newArrayList();
			refQuantityConditionTypeBo.setCodeTypeId(quantityCondition.getId());
			for (RefQuantityConditionType refQuantityConditionType : refQuantityConditionTypes) {
				if (refQuantityConditionType.getId().getQuantityConditionId() == quantityCondition.getId()) {
					RefLangBo refLangBo = new RefLangBo();
					refLangBo.setIsoLangCode(refQuantityConditionType.getId().getIsoLangCode());
					refLangBo.setValue(refQuantityConditionType.getQuantityConditionName());
					refQuantityConditionTypeBo.setDescription(refQuantityConditionType.getDescription());
					refLangBos.add(refLangBo);
				}
			}
			refQuantityConditionTypeBo.setCodeLang(refLangBos);
			refQuantityConditionTypeBoList.add(refQuantityConditionTypeBo);
		}

		return refQuantityConditionTypeBoList;
	}

	public static List<RefFilterOutRecordingTypeBo> convertToFilterOutRecordingTypeBo(FilterOutRecordingType[] filterOutRecordingTypeEnums,
			List<RefFilterOutRecordingType> refFilterOutRecordingTypes) {
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeBos = Lists.newArrayList();
		for (FilterOutRecordingType filterOutRecordingType : filterOutRecordingTypeEnums) {
			RefFilterOutRecordingTypeBo filterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
			List<RefLangBo> codeLangs = Lists.newArrayList();
			filterOutRecordingTypeBo.setFilterOutTypeId(filterOutRecordingType.getId());
			for (RefFilterOutRecordingType refFilterOutRecordingType : refFilterOutRecordingTypes) {
				if (refFilterOutRecordingType.getId().getFilterOutTypeId() == filterOutRecordingType.getId()) {
					RefLangBo langBo = new RefLangBo();
					langBo.setIsoLangCode(refFilterOutRecordingType.getId().getIsoLangCode());
					langBo.setValue(refFilterOutRecordingType.getFilterOutTypeName());
					filterOutRecordingTypeBo.setDescription(refFilterOutRecordingType.getDescription());
					codeLangs.add(langBo);
				}
			}
			filterOutRecordingTypeBo.setCodeLang(codeLangs);
			refFilterOutRecordingTypeBos.add(filterOutRecordingTypeBo);
		}

		return refFilterOutRecordingTypeBos;
	}

	public static List<RefRuleTypeBo> convertToRuleTypeBo(RuleType[] refRuleTypeValues, List<RefRuleType> refRuleTypes) {
		List<RefRuleTypeBo> refRuleTypeBoList = Lists.newArrayList();
		for (RuleType ruleType : refRuleTypeValues) {
			RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
			List<RefLangBo> refLangBos = Lists.newArrayList();
			refRuleTypeBo.setRuleTypeId(ruleType.getRuleTypeID());
			for (RefRuleType refRuleType : refRuleTypes) {
				if (refRuleType.getId().getRuleTypeId() == ruleType.getRuleTypeID()) {
					RefLangBo refLangBo = new RefLangBo();
					refLangBo.setIsoLangCode(refRuleType.getId().getIsoLangCode());
					refLangBo.setValue(refRuleType.getRuleTypeName());
					refRuleTypeBo.setDescription(refRuleType.getDescription());
					refLangBos.add(refLangBo);
				}
			}
			refRuleTypeBo.setCodeLang(refLangBos);
			refRuleTypeBoList.add(refRuleTypeBo);
		}
		return refRuleTypeBoList;
	}

}
