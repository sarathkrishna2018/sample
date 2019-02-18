package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.FilterOutRecordingType;
import colruyt.rearulmgtdmnejb.enums.QuantityCondition;
import colruyt.rearulmgtdmnejb.enums.QuantityType;
import colruyt.rearulmgtdmnejb.enums.ReasonType;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.enums.SourceType;

@Stateless
@LocalBean
public class ReferenceDataConverter implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<RefActionTypeBo> convertRefReaActiontype(List<Long> refActionTypes) {
		List<RefActionTypeBo> refActionTypeBoList = Lists.newArrayList();
		for (Long ruleAction : refActionTypes) {
			RefActionTypeBo actionTypeBo = new RefActionTypeBo();
			for (ActionType refActionType : ActionType.values()) {
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

	public List<RefNotToReactCodeBo> convertRefNonReactingCodeType(ReasonType[] refReasonEnums) {
		List<RefNotToReactCodeBo> refNotToReactCodeBoList = new ArrayList<>();
		Multimap<Long, RefLangBo> reasonMap1 = getReasonTypeMap1(refReasonEnums);
		Map<Long, List<RefLangBo>> reasonMap = getReasonTypeMap(refReasonEnums);
		RefNotToReactCodeBo refNotToReactCodeBo;
		for (Map.Entry<Long, List<RefLangBo>> entry : reasonMap.entrySet()) {
			refNotToReactCodeBo = new RefNotToReactCodeBo();
			refNotToReactCodeBo.setNotToReactCodeTypeId(entry.getKey());
			refNotToReactCodeBo.setCodeLang(entry.getValue());
			refNotToReactCodeBoList.add(refNotToReactCodeBo);
		}
		return refNotToReactCodeBoList;
	}

	private Multimap<Long, RefLangBo> getReasonTypeMap1(ReasonType[] refReasonEnums){
		Multimap<Long, RefLangBo> reasonTypeMap =  ArrayListMultimap.create();
		return reasonTypeMap;
	}
	private Map<Long, List<RefLangBo>> getReasonTypeMap(ReasonType[] refReasonEnums) {
		Map<Long, List<RefLangBo>> reasonTypeMap = new HashMap<>();
		for (ReasonType reasonType : refReasonEnums) {
			if (!reasonTypeMap.containsKey(reasonType.getReasonID())) {
				RefLangBo langBo = createRefLangBo(reasonType.getIsoLangCode(),
						reasonType.getReasonDescription());
				List<RefLangBo> langBos = new ArrayList<>();
				langBos.add(langBo);
				reasonTypeMap.put(reasonType.getReasonID(), langBos);
			} else {
				List<RefLangBo> existsLangBos = reasonTypeMap.get(reasonType.getReasonID());
				RefLangBo langBo = createRefLangBo(reasonType.getIsoLangCode(),
						reasonType.getReasonDescription());
				existsLangBos.add(langBo);
			}
		}
		return reasonTypeMap;
	}

	public List<RefSourceTypeBo> convertRefReaSource(List<Long> refSourceTypes) {
		List<RefSourceTypeBo> refSourceTypeBoList = Lists.newArrayList();
		for (Long sourceType : refSourceTypes) {
			RefSourceTypeBo sourceTypeBo = new RefSourceTypeBo();
			for (SourceType refSourceType : SourceType.values()) {
				if (sourceType.equals(refSourceType.getSourceTypeId())) {
					sourceTypeBo.setSourceName(refSourceType.getSourceTypeName());
					sourceTypeBo.setSourceTypeId(refSourceType.getSourceTypeId());
					refSourceTypeBoList.add(sourceTypeBo);
				}
			}
		}
		return refSourceTypeBoList;
	}

	public List<RefActionTypeBo> convertRefReaActiontype(ActionType[] actionTypeEnums) {
		List<RefActionTypeBo> refActionTypeBos = new ArrayList<>();
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

	public List<RefSourceTypeBo> convertRefReaSource(SourceType[] sourceTypeEnums) {
		List<RefSourceTypeBo> refSourceTypeBos = new ArrayList<>();
		RefSourceTypeBo refSourceTypeBo;
		for (SourceType sourceType : sourceTypeEnums) {
			refSourceTypeBo = new RefSourceTypeBo();
			refSourceTypeBo.setSourceName(sourceType.getSourceTypeName());
			refSourceTypeBo.setSourceTypeId(sourceType.getSourceTypeId());
			refSourceTypeBos.add(refSourceTypeBo);
		}
		return refSourceTypeBos;
	}

	public List<RefQuantityPriceTypeBo> convertRefQtyType(QuantityType[] refQuantityTypeValues) {
		List<RefQuantityPriceTypeBo> rrefQuantityPriceTypeBoList = Lists.newArrayList();
		Map<Long, List<RefLangBo>> refQuantityTypeMap = getRefQtyTypeMap(refQuantityTypeValues);
		for (Map.Entry<Long, List<RefLangBo>> entry : refQuantityTypeMap.entrySet()) {
			RefQuantityPriceTypeBo refNotToReactCodeBo = new RefQuantityPriceTypeBo();
			refNotToReactCodeBo.setQuantityTypeId(entry.getKey());
			refNotToReactCodeBo.setCodeLang(entry.getValue());
			rrefQuantityPriceTypeBoList.add(refNotToReactCodeBo);
		}
		return rrefQuantityPriceTypeBoList;
	}

	private Map<Long, List<RefLangBo>> getRefQtyTypeMap(QuantityType[] refQuantityTypeValues) {
		Map<Long, List<RefLangBo>> refQuantityTypeMap = new HashMap<>();
		for (QuantityType refQuantityType : refQuantityTypeValues) {
			if (!refQuantityTypeMap.containsKey(refQuantityType.getQtyTypeId())) {
				List<RefLangBo> langBos = new ArrayList<>();
				RefLangBo langBo = createRefLangBo(refQuantityType.getIsoLangCode(),
						refQuantityType.getQtyTypeDescription());
				langBos.add(langBo);
				refQuantityTypeMap.put(refQuantityType.getQtyTypeId(), langBos);
			} else {
				List<RefLangBo> existsLangBos = refQuantityTypeMap.get(refQuantityType.getQtyTypeId());
				RefLangBo langBo = createRefLangBo(refQuantityType.getIsoLangCode(),
						refQuantityType.getQtyTypeDescription());
				existsLangBos.add(langBo);
			}
		}
		return refQuantityTypeMap;
	}

	public List<RefQuantityConditionTypeBo> convertRefQtyCond(QuantityCondition[] quantityConditionEnums) {
		List<RefQuantityConditionTypeBo> refQuantityConditionTypeBoList = new ArrayList<>();
		Map<Long, List<RefLangBo>> quantityConditionMap = getQuantityConditionMap(quantityConditionEnums);
		RefQuantityConditionTypeBo refQuantityConditionTypeBo;
		for (Map.Entry<Long, List<RefLangBo>> entry : quantityConditionMap.entrySet()) {
			refQuantityConditionTypeBo = new RefQuantityConditionTypeBo();
			refQuantityConditionTypeBo.setCodeTypeId(entry.getKey());
			refQuantityConditionTypeBo.setCodeLang(entry.getValue());
			refQuantityConditionTypeBoList.add(refQuantityConditionTypeBo);
		}

		return refQuantityConditionTypeBoList;
	}

	private Map<Long, List<RefLangBo>> getQuantityConditionMap(QuantityCondition[] quantityConditionEnums) {
		Map<Long, List<RefLangBo>> quantityConditionTypeMap = new HashMap<>();
		for (QuantityCondition quantityCondition : quantityConditionEnums) {
			if (!quantityConditionTypeMap.containsKey(quantityCondition.getId())) {
				List<RefLangBo> langBos = new ArrayList<>();
				RefLangBo langBo = createRefLangBo(quantityCondition.getLangCode(),
						quantityCondition.getDescription());
				langBos.add(langBo);
				quantityConditionTypeMap.put(quantityCondition.getId(), langBos);
			} else {
				List<RefLangBo> existsLangBos = quantityConditionTypeMap.get(quantityCondition.getId());
				RefLangBo langBo = createRefLangBo(quantityCondition.getLangCode(),
						quantityCondition.getDescription());
				existsLangBos.add(langBo);
			}
		}
		return quantityConditionTypeMap;
	}

	private RefLangBo createRefLangBo(String isoLangCode, String value) {
		RefLangBo langBo = new RefLangBo();
		langBo.setIsoLangCode(isoLangCode);
		langBo.setValue(value);
		return langBo;
	}

	public List<RefFilterOutRecordingTypeBo> convertRefFltoutType(
			FilterOutRecordingType[] filterOutRecordingTypeEnums) {
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeBos = new ArrayList<>();
		Map<Long, List<RefLangBo>> filterOutRecordingTypeMap = getFilterOutRecordingTypeMap(
				filterOutRecordingTypeEnums);
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo;
		for (Map.Entry<Long, List<RefLangBo>> entry : filterOutRecordingTypeMap.entrySet()) {
			refFilterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
			refFilterOutRecordingTypeBo.setFilterOutTypeId(entry.getKey());
			refFilterOutRecordingTypeBo.setCodeLang(entry.getValue());
			refFilterOutRecordingTypeBos.add(refFilterOutRecordingTypeBo);
		}

		return refFilterOutRecordingTypeBos;
	}

	private Map<Long, List<RefLangBo>> getFilterOutRecordingTypeMap(
			FilterOutRecordingType[] filterOutRecordingTypeEnums) {
		Map<Long, List<RefLangBo>> filterOutRecordingTypeMap = new HashMap<>();
		for (FilterOutRecordingType filterOutRecordingType : filterOutRecordingTypeEnums) {
			if (!filterOutRecordingTypeMap.containsKey(filterOutRecordingType.getId())) {
				List<RefLangBo> langBos = new ArrayList<>();
				RefLangBo langBo = createRefLangBo(filterOutRecordingType.getLangCode(),
						filterOutRecordingType.getName());
				langBos.add(langBo);
				filterOutRecordingTypeMap.put(filterOutRecordingType.getId(), langBos);
			} else {
				List<RefLangBo> existsLangBos = filterOutRecordingTypeMap.get(filterOutRecordingType.getId());
				RefLangBo langBo = createRefLangBo(filterOutRecordingType.getLangCode(),
						filterOutRecordingType.getName());
				existsLangBos.add(langBo);
			}
		}
		return filterOutRecordingTypeMap;
	}

	public List<RefRuleTypeBo> convertRuleType(RuleType[] refRullTypeValues) {
		List<RefRuleTypeBo> refRuleTypeBoList = new ArrayList<>();
		Map<Long, List<RefLangBo>> reasonMap = getRuleTypeMap(refRullTypeValues);
		for (Map.Entry<Long, List<RefLangBo>> entry : reasonMap.entrySet()) {
			RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
			refRuleTypeBo.setRuleTypeId(entry.getKey());
			refRuleTypeBo.setCodeLang(entry.getValue());
			refRuleTypeBoList.add(refRuleTypeBo);
		}
		return refRuleTypeBoList;
	}

	private Map<Long, List<RefLangBo>> getRuleTypeMap(RuleType[] refRullTypeValues) {
		Map<Long, List<RefLangBo>> reasonTypeMap = new HashMap<>();
		for (RuleType refRuleType : refRullTypeValues) {
			if (!reasonTypeMap.containsKey(refRuleType.getRuleTypeID())) {
				RefLangBo langBo = createRefLangBo(refRuleType.getIsoLangCode(), refRuleType.getRuleTypeDescription());
				List<RefLangBo> langBos = new ArrayList<>();
				langBos.add(langBo);
				reasonTypeMap.put(refRuleType.getRuleTypeID(), langBos);
			} else {
				List<RefLangBo> existsLangBos = reasonTypeMap.get(refRuleType.getRuleTypeID());
				RefLangBo langBo = createRefLangBo(refRuleType.getIsoLangCode(), refRuleType.getRuleTypeDescription());
				existsLangBos.add(langBo);
			}
		}
		return reasonTypeMap;
	}

}
