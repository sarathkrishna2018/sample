/**
 * 
 */
package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


import colruyt.rearulmgtdmnejb.enums.ActionTypeEnum;
import colruyt.rearulmgtdmnejb.enums.SourceTypeEnum;
import colruyt.rearulmgtdmnejb.enums.FilterOutRecordingTypeEnum;
import colruyt.rearulmgtdmnejb.enums.QuantityConditionEnum;
import colruyt.rearulmgtdmnejb.enums.RefQuantityTypeEnum;
import colruyt.rearulmgtdmnejb.enums.RefReasonEnum;
import colruyt.rearulmgtdmnejb.enums.RefRuletypeEnum;
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

	
	
	public List<RefNotToReactCodeBo> convertRefNonReactingCodeType(RefReasonEnum[] refReasonEnums) {
		List<RefNotToReactCodeBo> refNotToReactCodeBoList = new ArrayList<>();		
		Map<Long,List<RefLangBo>> reasonMap = getReasonTypeMap(refReasonEnums);
		RefNotToReactCodeBo refNotToReactCodeBo ;		
		for (Map.Entry<Long,List<RefLangBo>> entry : reasonMap.entrySet()){
			refNotToReactCodeBo = new RefNotToReactCodeBo();
			refNotToReactCodeBo.setNotToReactCodeTypeId(entry.getKey());
			refNotToReactCodeBo.setCodeLang(entry.getValue());
			refNotToReactCodeBoList.add(refNotToReactCodeBo);
		}
		return refNotToReactCodeBoList;
	}
	
	private Map<Long, List<RefLangBo>> getReasonTypeMap(RefReasonEnum[] refReasonEnums) {
		Map<Long,List<RefLangBo>> reasonTypeMap = new HashMap<>();
		for(RefReasonEnum refReasonEnum : refReasonEnums ){
			if(!reasonTypeMap.containsKey(refReasonEnum.getReasonID())){
				RefLangBo langBo = createRefLangBo(refReasonEnum.getIsoLangCode(), refReasonEnum.getReasonDescription());
				List<RefLangBo> langBos = new ArrayList<>();
				langBos.add(langBo);
				reasonTypeMap.put(refReasonEnum.getReasonID(), langBos);
			} else {
				List<RefLangBo> existsLangBos = reasonTypeMap.get(refReasonEnum.getReasonID());				
				RefLangBo langBo = createRefLangBo(refReasonEnum.getIsoLangCode(), refReasonEnum.getReasonDescription());
				existsLangBos.add(langBo);
			}
		}
		return reasonTypeMap;
	}

	/*public List<RefQuantityConditionTypeBo> convertRefQtyCond(List<RefQuantityCond> refQtyConds) {
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
	}*/

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


	/*public List<RefQuantityPriceTypeBo> convertRefQtyType(List<RefQuantityType> refQtyTypes) {
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
*/
	/*public List<RefRuleTypeBo> convertRuleType(List<RefRuletype> refRuletypes) {
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
	}*/

	/*public List<RefFilterOutRecordingTypeBo> convertRefFltoutType(List<RefFilterOutRecordingType> refFltoutTypes) {
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
	}*/

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
public List<RefQuantityPriceTypeBo> convertRefQtyType(RefQuantityTypeEnum[] refQuantityTypeValues) {
		List<RefQuantityPriceTypeBo> rrefQuantityPriceTypeBoList = Lists.newArrayList();
		Map<Long,List<RefLangBo>> refQuantityTypeMap = getRefQtyTypeMap(refQuantityTypeValues);
		for (Map.Entry<Long,List<RefLangBo>> entry : refQuantityTypeMap.entrySet()){
			RefQuantityPriceTypeBo refNotToReactCodeBo = new RefQuantityPriceTypeBo();
			refNotToReactCodeBo.setQuantityTypeId(entry.getKey());
			refNotToReactCodeBo.setCodeLang(entry.getValue());
			rrefQuantityPriceTypeBoList.add(refNotToReactCodeBo);
		}		
		return rrefQuantityPriceTypeBoList;
	}
	private Map<Long, List<RefLangBo>> getRefQtyTypeMap(RefQuantityTypeEnum[] refQuantityTypeValues) {
		Map<Long,List<RefLangBo>> refQuantityTypeMap = new HashMap<>();
		for(RefQuantityTypeEnum refQuantityType : refQuantityTypeValues ){
			if(!refQuantityTypeMap.containsKey(refQuantityType.getQtyTypeId())){
				List<RefLangBo> langBos = new ArrayList<>();
				RefLangBo langBo = createRefLangBo(refQuantityType.getIsoLangCode(), refQuantityType.getQtyTypeDescription());
				langBos.add(langBo);				
				refQuantityTypeMap.put(refQuantityType.getQtyTypeId(), langBos);
			} else {
				List<RefLangBo> existsLangBos = refQuantityTypeMap.get(refQuantityType.getQtyTypeId());				
				RefLangBo langBo = createRefLangBo(refQuantityType.getIsoLangCode(), refQuantityType.getQtyTypeDescription());
				existsLangBos.add(langBo);
			}
		}
		return refQuantityTypeMap;
	}
	public List<RefQuantityConditionTypeBo> convertRefQtyCond(QuantityConditionEnum[] quantityConditionEnums) {
		List<RefQuantityConditionTypeBo> refQuantityConditionTypeBoList = new ArrayList<>();		
		Map<Long,List<RefLangBo>> quantityConditionMap = getQuantityConditionMap(quantityConditionEnums);
		RefQuantityConditionTypeBo refQuantityConditionTypeBo ;		
		for (Map.Entry<Long,List<RefLangBo>> entry : quantityConditionMap.entrySet()){
			refQuantityConditionTypeBo = new RefQuantityConditionTypeBo();
			refQuantityConditionTypeBo.setCodeTypeId(entry.getKey());
			refQuantityConditionTypeBo.setCodeLang(entry.getValue());
			refQuantityConditionTypeBoList.add(refQuantityConditionTypeBo);
		}
		
		return refQuantityConditionTypeBoList;
	}

	private Map<Long, List<RefLangBo>> getQuantityConditionMap(QuantityConditionEnum[] quantityConditionEnums) {
		Map<Long,List<RefLangBo>> quantityConditionTypeMap = new HashMap<>();
		for(QuantityConditionEnum quantityConditionEnum : quantityConditionEnums ){
			if(!quantityConditionTypeMap.containsKey(quantityConditionEnum.getId())){
				List<RefLangBo> langBos = new ArrayList<>();
				RefLangBo langBo = createRefLangBo(quantityConditionEnum.getLangCode(), quantityConditionEnum.getDescription());
				langBos.add(langBo);				
				quantityConditionTypeMap.put(quantityConditionEnum.getId(), langBos);
			} else {
				List<RefLangBo> existsLangBos = quantityConditionTypeMap.get(quantityConditionEnum.getId());				
				RefLangBo langBo = createRefLangBo(quantityConditionEnum.getLangCode(), quantityConditionEnum.getDescription());
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
			FilterOutRecordingTypeEnum[] filterOutRecordingTypeEnums) {
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeBos = new ArrayList<>();		
		Map<Long,List<RefLangBo>> filterOutRecordingTypeMap = getFilterOutRecordingTypeMap(filterOutRecordingTypeEnums);
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo ;		
		for (Map.Entry<Long,List<RefLangBo>> entry : filterOutRecordingTypeMap.entrySet()){
			refFilterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
			refFilterOutRecordingTypeBo.setFilterOutTypeId(entry.getKey());
			refFilterOutRecordingTypeBo.setCodeLang(entry.getValue());
			refFilterOutRecordingTypeBos.add(refFilterOutRecordingTypeBo);
		}
		
		return refFilterOutRecordingTypeBos;
	}

	private Map<Long, List<RefLangBo>> getFilterOutRecordingTypeMap(
			FilterOutRecordingTypeEnum[] filterOutRecordingTypeEnums) {
		Map<Long,List<RefLangBo>> filterOutRecordingTypeMap = new HashMap<>();
		for(FilterOutRecordingTypeEnum filterOutRecordingTypeEnum : filterOutRecordingTypeEnums ){
			if(!filterOutRecordingTypeMap.containsKey(filterOutRecordingTypeEnum.getId())){
				List<RefLangBo> langBos = new ArrayList<>();
				RefLangBo langBo = createRefLangBo(filterOutRecordingTypeEnum.getLangCode(), filterOutRecordingTypeEnum.getDescription());
				langBos.add(langBo);				
				filterOutRecordingTypeMap.put(filterOutRecordingTypeEnum.getId(), langBos);
			} else {
				List<RefLangBo> existsLangBos = filterOutRecordingTypeMap.get(filterOutRecordingTypeEnum.getId());				
				RefLangBo langBo = createRefLangBo(filterOutRecordingTypeEnum.getLangCode(), filterOutRecordingTypeEnum.getDescription());
				existsLangBos.add(langBo);
			}
		}
		return filterOutRecordingTypeMap;
	}
	
	public List<RefRuleTypeBo> convertRuleType(RefRuletypeEnum[] refRullTypeValues) {
		List<RefRuleTypeBo> refRuleTypeBoList = new ArrayList<>();		
		Map<Long,List<RefLangBo>> reasonMap = getRuleTypeMap(refRullTypeValues);
		for (Map.Entry<Long,List<RefLangBo>> entry : reasonMap.entrySet()){
			RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();			
			refRuleTypeBo.setRuleTypeId(entry.getKey());
			refRuleTypeBo.setCodeLang(entry.getValue());
			refRuleTypeBoList.add(refRuleTypeBo);
		}		
		return refRuleTypeBoList;
	}
	
	private Map<Long, List<RefLangBo>> getRuleTypeMap(RefRuletypeEnum[] refRullTypeValues) {
		Map<Long,List<RefLangBo>> reasonTypeMap = new HashMap<>();
		for(RefRuletypeEnum refRuleType : refRullTypeValues){
			if(!reasonTypeMap.containsKey(refRuleType.getRuleTypeID())){
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
