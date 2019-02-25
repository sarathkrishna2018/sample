package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import colruyt.rearulmgtdmnejb.service.dl.ReferenceDataDlService;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;

@Singleton
@Startup
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class ReferenceDataService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReferenceDataService.class);


	@EJB
	private ReferenceDataConverter referenceDataConvertor;
	
	@EJB
	private ReferenceDataDlService referenceDataDlService;

	private List<RefActionTypeBo> refActionTypeList = Lists.newArrayList();
	private List<RefNotToReactCodeBo> refNotToReactCodeList = Lists.newArrayList();
	private List<RefQuantityConditionTypeBo> refQuantityConditionList = Lists.newArrayList();
	private List<RefSourceTypeBo> refSourceTypeList = Lists.newArrayList();
	private List<RefQuantityPriceTypeBo> refQuantityPriceTypeList = Lists.newArrayList();
	private List<RefRuleTypeBo> refRuleTypeList = Lists.newArrayList();
	private List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeList = Lists.newArrayList();

	/**
	 * Method used to initilise the reference data lists by calling respective
	 * dl services.
	 */
	@PostConstruct
	private void init() {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_REFERENCEDATASERVICE);
		ActionType[] actionTypeEnums = ActionType.values();
		refActionTypeList = referenceDataConvertor.convertRefReaActiontype(actionTypeEnums);
		
		ReasonType[] refReasonValues = ReasonType.values();
		List<RefReasonType> refReasonTypes =referenceDataDlService.findAllRefReasonType();
		refNotToReactCodeList = referenceDataConvertor.convertRefNonReactingCodeType(refReasonValues,refReasonTypes);
		
		QuantityCondition[] quantityConditionEnum = QuantityCondition.values();
		List<RefQuantityConditionType> refQuantityConditionTypes =referenceDataDlService.findAllRefQuantityCondition();
		refQuantityConditionList = referenceDataConvertor.convertRefQtyCond(quantityConditionEnum,refQuantityConditionTypes);
		
		QuantityType[] refQuantityTypeValues = QuantityType.values();
		List<RefQuantityType> refQuantityTypes =referenceDataDlService.findAllRefQuantityType();
		refQuantityPriceTypeList = referenceDataConvertor.convertRefQtyType(refQuantityTypeValues,refQuantityTypes);

		SourceType[]  sourceTypeEnums = SourceType.values();
		refSourceTypeList = referenceDataConvertor.convertRefReaSource(sourceTypeEnums);
		
		FilterOutRecordingType[] filterOutRecordingTypeEnums = FilterOutRecordingType.values();
		List<RefFilterOutRecordingType> refFilterOutRecordingTypes =referenceDataDlService.findAllRefFilterOutRecordingTypes();
		refFilterOutRecordingTypeList = referenceDataConvertor.convertRefFltoutType(filterOutRecordingTypeEnums, refFilterOutRecordingTypes);
 		
		RuleType[] refRuleTypeValues = RuleType.values();
		List<RefRuleType> refRuleTypes=referenceDataDlService.findAllRuleType();
		refRuleTypeList = referenceDataConvertor.convertRuleType(refRuleTypeValues,refRuleTypes);
		
	}

	public List<RefActionTypeBo> getAllActionTypes() {
		return refActionTypeList;
	}

	public List<RefNotToReactCodeBo> getAllNotToReactCodeTypes() {
		return refNotToReactCodeList;
	}

	public List<RefQuantityConditionTypeBo> getAllQuantityConditionTypes() {
		return refQuantityConditionList;
	}

	public List<RefSourceTypeBo> getAllSourceTypes() {
		return refSourceTypeList;
	}

	public List<RefQuantityPriceTypeBo> getAllQuantityPriceTypes() {
		return refQuantityPriceTypeList;
	}

	public List<RefRuleTypeBo> getAllRuleTypes() {
		return refRuleTypeList;
	}

	public List<RefFilterOutRecordingTypeBo> getAllFilterOutRecordingTypes() {
		return refFilterOutRecordingTypeList;
	}

	/**This method is to remove actionTypeAll
	 * @param allActionType
	 * @return
	 */
	public List<RefActionTypeBo> removeActionTypeAll(List<RefActionTypeBo> allActionType, Boolean getAllTypes) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_REMOVEALLACTION);
		List<RefActionTypeBo> finalActionList = Lists.newArrayList();
		for(RefActionTypeBo action : allActionType){
			if(getAllTypes){
				if(!(action.getActionTypeValue().equalsIgnoreCase(ActionType.ALL.getActionTypeValue()) )){
					finalActionList.add(action);
				}	
			}
			else{
				if(!(action.getActionTypeValue().equalsIgnoreCase(ActionType.ALL.getActionTypeValue()) || action.getActionTypeValue().equalsIgnoreCase(ActionType.NONE.getActionTypeValue()))){
					finalActionList.add(action);
				}	
			}
			
		}
		return finalActionList;
	}

	/**This method is to remove sourceTypeAll
	 * @param allSourceType
	 * @return
	 */
	public List<RefSourceTypeBo> removeSourceTypeAll(List<RefSourceTypeBo> allSourceType) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_REMOVEALLSOURCE);
		List<RefSourceTypeBo> finalSourceList = Lists.newArrayList();
		for(RefSourceTypeBo source : allSourceType){
			if(!source.getSourceName().equalsIgnoreCase(SourceType.ALL.getSourceTypeName())){
				finalSourceList.add(source);
			}
		}
		return finalSourceList;
	}
	
	/**This method is to find ruleTypeId
	 * @param ruleType
	 * @return
	 */
	public Long findPkByType(String ruleType){
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_FINDRULETYPEID);
		Long ruleTypeId = null;
		for(RefRuleTypeBo refRuleTypeBo: getAllRuleTypes()) {
			for(RefLangBo refLangBo: refRuleTypeBo.getCodeLang()) {
				if(refLangBo.getIsoLangCode().equalsIgnoreCase("EN") && refLangBo.getValue().equalsIgnoreCase(ruleType)) {
					ruleTypeId = refRuleTypeBo.getRuleTypeId();
				}
			}
		}
		return ruleTypeId;
	}
	/** This method is to find ruleType 
	 * @param ruleTypeId
	 * @return
	 */
	public String findTypeByPk(long ruleTypeId){
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_FINDRULETYPE);
		String ruleType="";
		for(RefRuleTypeBo refRuleTypeBo:getAllRuleTypes()){
			for(RefLangBo refLangBo:refRuleTypeBo.getCodeLang()){
				if(refRuleTypeBo.getRuleTypeId()==ruleTypeId){
					ruleType=refLangBo.getValue();
				}
			}
		}
		return ruleType;
	}
	
}