package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ProductHierarchySetDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleActionTypeDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSourceTypeDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;

/**
 * @version 1.0
 * @created 28-nov-2018 12:05:11
 */

@LocalBean
public abstract class GeneralRuleService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GeneralRuleService.class);
	@EJB
	private ReactionRuleSetService reactionRuleSetService;
	@EJB
	private ReaRuleConverter reaRuleConverter;
	@EJB
	private ReactionRuleDlService reactionRuleDlService;
	@EJB
	private ReactionRuleActionTypeDlService reactionRuleActionTypeDlService;
	@EJB
	private ReactionRuleSourceTypeDlService reactionRuleSourceTypeDlService;
	@EJB
	private PriceProductHierarchyService priceProductHierarchyService;
	@EJB
	private ReferenceDataConverter referenceDataConverter;
	@EJB
	private ReferenceDataService referenceDataService;

	@EJB
	ProductHierarchySetDlService productHierarchySetDlService;

	public abstract GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException;

	public abstract GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException;

	public abstract List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException;

	public abstract GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException;

	public abstract long physicalDeleteElements(XPSRuleBo xpsRuleBo);

	/**
	 * Template pattern to create the Reaction Rule
	 * 
	 * @param reactionRuleBo
	 * @return GeneralRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	public GeneralRuleBo createRule(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		GeneralRuleBo ruleBo = null;
		if (reactionRuleBo instanceof ReactingRuleBo) {
			ruleBo = createGeneralRuleAttributes(reactionRuleBo, true);
		} else {
			ruleBo = createGeneralRuleAttributes(reactionRuleBo, false);
		}
		return createRuleSpecificAttributes(ruleBo);
	}

	/**
	 * Template pattern to Modify Rule
	 * 
	 * @param reactionRuleBo
	 * @return GeneralRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	public GeneralRuleBo modifyRule(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		ReactionRule existingReactionRule = findByRuleId(reactionRuleBo.getRuleId());
		reactionRuleBo.setRulePriority(existingReactionRule.getRulePriority());
		boolean validToTimeSlice = checkForValidity(existingReactionRule, reactionRuleBo);
		if (validToTimeSlice) {
			GeneralRuleBo childRuleBo = createRuleLine(reactionRuleBo);
			appendTimeSliceParam(childRuleBo, existingReactionRule);
			return reactionRuleBo;
		} else {
			GeneralRuleBo ruleBo = modifyGeneralRuleAttributes(reactionRuleBo);
			return modifyRuleSpecificAttributes(ruleBo);
		}

	}

	/**
	 * Update the validTo and childRule id in Rule table
	 * 
	 * @param childRuleBo
	 * @param existingReactionRule
	 */
	private void appendTimeSliceParam(GeneralRuleBo childRuleBo, ReactionRule existingReactionRule) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date  newValidFrom = sdf.parse(sdf.format(childRuleBo.getValidFrom()));
			Date oldValidfrom = sdf.parse(sdf.format(existingReactionRule.getValidFrom()));
			existingReactionRule.setChildRuleId(childRuleBo.getRuleId());
			if(oldValidfrom.compareTo(newValidFrom)==0){
				existingReactionRule.setValidUpto(childRuleBo.getValidFrom());
			}else{
				existingReactionRule.setValidUpto(DateUtils.addDays(childRuleBo.getValidFrom(), -1));
			}
			reactionRuleDlService.createOrUpdate(existingReactionRule);
		} catch (ParseException e) {
			logger.error("Parse Exception", e);
		}
		
	}

	private GeneralRuleBo createRuleLine(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		reactionRuleBo.setRuleId(null);
		reactionRuleBo.setProductHierarchySetId(null);
		return createRule(reactionRuleBo);
	}

	/**
	 * Set Time Slice flag by validating the dates.
	 * 
	 * @param existingReactionRule
	 * @param reactionRuleBo
	 * @return
	 */
	private boolean checkForValidity(ReactionRule existingReactionRule, GeneralRuleBo reactionRuleBo) {
		Date currentDate = new Date();
		if (existingReactionRule.getValidFrom() != null && existingReactionRule.getValidFrom().compareTo(currentDate)<=0 && existingReactionRule.getValidFrom().compareTo(reactionRuleBo.getValidFrom())<=0) {
			return true;
		}
		return false;
	}

	/**
	 * Template design pattern to get the Reaction rule
	 * 
	 * @param specificReactionRuleBo
	 * @param ruleId
	 * @param ruleType
	 * @return
	 * @throws ReaRuleManagementException
	 */
	public GeneralRuleBo viewReactionRule(GeneralRuleBo specificReactionRuleBo, long ruleId, String langCode)
			throws ReaRuleManagementException {
		ReactionRule reactionRule = getReactionRule(ruleId, langCode);
		GeneralRuleBo ruleBo = getGeneralRuleAttributes(reactionRule, specificReactionRuleBo);
		return getRuleSpecificValues(ruleBo);
	}

	/**
	 * This method is to create reaction rule
	 * 
	 * @param reactionRuleBo
	 * @param isDuplicationAllowed
	 * @return GeneralRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	private GeneralRuleBo createGeneralRuleAttributes(GeneralRuleBo reactionRuleBo, boolean isDuplicationAllowed)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_REACTIONRULE);
		validateRuleInputs(reactionRuleBo);
		ReactionRulesetBo reactionRulesetBo = reactionRuleSetService.createReactionRuleSet(
				reactionRuleBo.getReactionRulesetBo(), isDuplicationAllowed, reactionRuleBo.getLogonId());
		reactionRuleBo.setRulesetId(reactionRulesetBo.getRulesetId());
		setRulePriority(reactionRulesetBo, reactionRuleBo);
		ReactionRule reaRule = reaRuleConverter.convertRuleBo(null, reactionRuleBo);
		// action
		if (reactionRuleBo.isActionSelectAll()) {
			List<Long> actionLst = reaRuleConverter
					.convertRuleTypeForAll(ReaRulMgtDmnConstants.ACTION_ALL_TYPEID);
			reaRule.setRefActionTypes(actionLst);
		} else if (!reactionRuleBo.isActionSelectAll() && reactionRuleBo.getActionTypeList() != null
				&& !reactionRuleBo.getActionTypeList().isEmpty()) {
			List<Long> actionLst = reaRuleConverter.convertRuleAction(reactionRuleBo);
			reaRule.setRefActionTypes(actionLst);
		}
		// source
		if (reactionRuleBo.isSourceSelectAll()) {
			List<Long> sourceLst = reaRuleConverter
					.convertRuleTypeForAll(ReaRulMgtDmnConstants.SOURCE_ALL_TYPEID);
			reaRule.setRefSourceTypes(sourceLst);
		} else if (!reactionRuleBo.isSourceSelectAll() && reactionRuleBo.getSourceTypeList() != null
				&& !reactionRuleBo.getSourceTypeList().isEmpty()) {
			List<Long> sourceLst = reaRuleConverter.convertRuleSource(reactionRuleBo);
			reaRule.setRefSourceTypes(sourceLst);
		}
		reaRule = reactionRuleDlService.createOrUpdate(reaRule);
		reactionRuleBo.setRuleId(reaRule.getReaRuleId());

		// product
		return priceProductHierarchyService.createProductHierarchySet(reactionRuleBo);
	}

	/**
	 * Method to set Rule Priority
	 * 
	 * @param reactionRulesetBo
	 * @param reactionRuleBo
	 * @return GeneralRuleBo
	 */
	private GeneralRuleBo setRulePriority(ReactionRulesetBo reactionRulesetBo, GeneralRuleBo reactionRuleBo) {
		Long rulePriority = getAllRulesByRuleSetId(reactionRulesetBo.getRulesetId());
			
		if (rulePriority == null && reactionRuleBo.getRulePriority()==null) 
		{
			reactionRuleBo.setRulePriority(1L);        }
		else if(rulePriority != null && reactionRuleBo.getRulePriority()==null)
		{           
			reactionRuleBo.setRulePriority(rulePriority + 1L);      
		}        
		return reactionRuleBo;
	}

	/**
	 * Get all rules
	 * 
	 * @param rulesetId
	 * @return
	 */

	private Long getAllRulesByRuleSetId(Long rulesetId) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_GETREACTRULE);
		return reactionRuleDlService.findAllByRuleSetId(rulesetId);
	}

	/**
	 * This method is to get reaction rule
	 * 
	 * @param ruleId
	 * @return ReactionRule
	 * @throws ReaRuleManagementException
	 */
	public ReactionRule getReactionRule(long ruleId, String langCode) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_GETREACTIONRULE);
		ReactionRule reactionRule = reactionRuleDlService.findByPk(ruleId);
		if (reactionRule == null) {
			throw new ReaRuleManagementException(langCode, ExceptionMessageConstants.MESSAGE_REACTION_RULE_ABSENT);
		}
		return reactionRule;
	}

	/**
	 * This method is used to validate mandatory fields
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void validateRuleInputs(GeneralRuleBo reactionRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATEREACTIONRULE);
		if (!reactionRuleBo.isPermanentDuration() && !reactionRuleBo.isTemporaryDuration()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_DURATION_ABSENT);
		}
		if (!reactionRuleBo.isCheapBrand() && !reactionRuleBo.isNationalBrand() && !reactionRuleBo.isOwnBrand()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_BRAND_LAYER_ABSENT);
		}
		if (reactionRuleBo.getPriceProductHierarchySet() == null
				|| reactionRuleBo.getPriceProductHierarchySet().isEmpty()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_PRODUCT_HIERARCHY_SET_ABSENT);
		}
		if (reactionRuleBo.getAssortmentName() == null) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_ASSORTMENT_NAME_ABSENT);
		}
		if (reactionRuleBo.getImportanceCodeFrom() == null || reactionRuleBo.getImportanceCodeTo() == null) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_IMPORTANCE_CODE_ABSENT);
		}
		if (reactionRuleBo.getRuleName() == null) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_RULE_NAME_ABSENT);
		}
		dateValidation(reactionRuleBo);
		importanceCodeValidation(reactionRuleBo);
		if (reactionRuleBo.isCatchAll()) {
			catchAllValidation(reactionRuleBo);
		}
	}

	/**
	 * This method is to validate the Catch ALL input fields
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void catchAllValidation(GeneralRuleBo reactionRuleBo) throws ReaRuleValidationException {
		if (reactionRuleBo.getValidTo() != null) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_VALIDTO);
		}
		if (!reactionRuleBo.getRuleName().equalsIgnoreCase("Catch ALL")) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_RULENAME);
		}
		if (!reactionRuleBo.isPermanentDuration() || !reactionRuleBo.isTemporaryDuration()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_PRICE_DURATAION_TYPE);
		}
		if (!reactionRuleBo.isSourceSelectAll()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_SOURCETYPE);
		}
		if (!reactionRuleBo.isActionSelectAll()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_ACTIONTYPE);
		}
		if (!reactionRuleBo.isPostponedBenefit() || !reactionRuleBo.isDirectBenefit()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_BENIFITTYPE);
		}
		if (!reactionRuleBo.getAssortmentName().equalsIgnoreCase("ALL")) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_ASSORTMENTNAME);
		}
		if (!reactionRuleBo.isCheapBrand() || !reactionRuleBo.isNationalBrand() || !reactionRuleBo.isOwnBrand()) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_CATCHALL_BRAND);
		}
	}

	/**
	 * This method is to validate date field
	 * 
	 * @param reactionRuleBo
	 */
	private void dateValidation(GeneralRuleBo reactionRuleBo) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_DATEVALIDATION);
		if (reactionRuleBo.getValidFrom() == null) {
			reactionRuleBo.setValidFrom(new Date());
		}
	}

	/**
	 * This method is to validate importance code
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void importanceCodeValidation(GeneralRuleBo reactionRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_IMPORTANCECODEVALIDATION);
		Long importanceCodeFrom = reactionRuleBo.getImportanceCodeFrom();
		Long importanceCodeTo = reactionRuleBo.getImportanceCodeTo();
		if (importanceCodeFrom > importanceCodeTo) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_FromGreaterThanTo_ImportanceCode_VALIDATION);
		}
		if (importanceCodeFrom < 0 || importanceCodeFrom > 99 || importanceCodeTo < 0 || importanceCodeTo > 99) {
			throw new ReaRuleValidationException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_Range_ImportanceCode_VALIDATION);
		}
	}

	/**
	 * This method is to edit reaction rule
	 * 
	 * @param reactionRuleBo
	 * @return GeneralRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	private GeneralRuleBo modifyGeneralRuleAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYREACTIONRULE);
		validateRuleInputs(reactionRuleBo);
		ReactionRule existingReactionRule = reactionRuleDlService.findByPk(reactionRuleBo.getRuleId());
		if (existingReactionRule == null) {
			throw new ReaRuleManagementException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_REACTION_RULE_ABSENT);
		}
		ReactionRule reaRule = reaRuleConverter.convertRuleBo(existingReactionRule, reactionRuleBo);
		reactionRuleBo.setRuleId(reaRule.getReaRuleId());
		// action
		if (reactionRuleBo.isActionSelectAll()) {
			List<Long> actionLst = reaRuleConverter
					.convertRuleTypeForAll(ReaRulMgtDmnConstants.ACTION_ALL_TYPEID);
			reaRule.setRefActionTypes(actionLst);
		} else if (!reactionRuleBo.isActionSelectAll() && reactionRuleBo.getActionTypeList() != null
				&& !reactionRuleBo.getActionTypeList().isEmpty()) {
			List<Long> actionLst = reaRuleConverter.convertRuleAction(reactionRuleBo);
			reaRule.setRefActionTypes(actionLst);
		}
		// source
		if (reactionRuleBo.isSourceSelectAll()) {
			List<Long> sourceLst = reaRuleConverter
					.convertRuleTypeForAll(ReaRulMgtDmnConstants.SOURCE_ALL_TYPEID);
			reaRule.setRefSourceTypes(sourceLst);
			
		} else if (!reactionRuleBo.isSourceSelectAll() && reactionRuleBo.getSourceTypeList() != null
				&& !reactionRuleBo.getSourceTypeList().isEmpty()) {
			List<Long> sourceLst = reaRuleConverter.convertRuleSource(reactionRuleBo);
			reaRule.setRefSourceTypes(sourceLst);
		}
		reactionRuleDlService.createOrUpdate(reaRule);
		priceProductHierarchyService.removeProductHierarchyElement(reactionRuleBo.getProductHierarchySetId());
		return priceProductHierarchyService.createProductHierarchySet(reactionRuleBo);

	}

	/**
	 * This method is to get reaction rule using rulesetId
	 * 
	 * @param ruleSetId
	 * @return
	 */
	public List<ReactionRule> getRulesByRuleSetId(long ruleSetId) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_GETREACTRULE);
		return reactionRuleDlService.findByRuleSetId(ruleSetId);
	}

	/**
	 * This method is to get general rule using
	 * 
	 * @param rule
	 * @param ruleBo
	 * @return
	 */
	public GeneralRuleBo getGeneralRuleAttributes(ReactionRule rule, GeneralRuleBo ruleBo) {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_GETGENERALRULE);
		if (rule.getRefActionTypes() != null
				&& rule.getRefActionTypes().get(0) == ReaRulMgtDmnConstants.ACTION_ALL_TYPEID) {
			List<RefActionTypeBo> actionTypes = referenceDataService.getAllActionTypes();
			List<RefActionTypeBo> actionTypesExceptAll = referenceDataService.removeActionTypeAll(actionTypes, false);
			ruleBo.setActionTypeList(actionTypesExceptAll);
			ruleBo.setActionSelectAll(true);
		} else {
			ruleBo.setActionTypeList(referenceDataConverter.convertRefReaActiontype(rule.getRefActionTypes()));
		}
		if (rule.getRefSourceTypes() != null
				&& rule.getRefSourceTypes().get(0) == ReaRulMgtDmnConstants.SOURCE_ALL_TYPEID) {
			List<RefSourceTypeBo> sourceTypes = referenceDataService.getAllSourceTypes();
			List<RefSourceTypeBo> sourceTypesExceptAll = referenceDataService.removeSourceTypeAll(sourceTypes);
			ruleBo.setSourceTypeList(sourceTypesExceptAll);
			ruleBo.setSourceSelectAll(true);
		} else {
			ruleBo.setSourceTypeList(referenceDataConverter.convertRefReaSource(rule.getRefSourceTypes()));
		}
		return reaRuleConverter.convertGeneralRuleBo(rule, ruleBo);
	}

	/**
	 * This method is to fetch Rule type id by Rule name
	 * 
	 * @param ruleTypeName
	 * @return long
	 */
	public long getRuleTypeId(String ruleTypeName) {
		return referenceDataService.findPkByType(ruleTypeName);
	}

	/**
	 * This method is used to logically delete a rule
	 * 
	 * @param ruleId
	 * @param langCode
	 * @param logonId
	 * @throws ReaRuleManagementException
	 */
	public void logicallyDeleteReactionRule(Long ruleId, String langCode, String logonId)
			throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_GETGENERALRULE);
		ReactionRule reactionRule = getReactionRule(ruleId, langCode);
		ReactionRule parentReactionRule = reactionRuleDlService.findParentRule(ruleId);
		// if rule is a child rule, then remove child entry from parent rule
		if (parentReactionRule != null) {
			parentReactionRule.setChildRuleId(null);
			reactionRuleDlService.createOrUpdate(parentReactionRule);
		}
		// if rule is a parent, then logically delete child rule
		if (reactionRule.getChildRuleId() != null) {
			ReactionRule childRule = getReactionRule(reactionRule.getChildRuleId(), langCode);
			childRule.setLstUpdateBy(logonId);
			reactionRuleDlService.updateLogicallyDeletedDate(childRule);
		}
		reactionRule.setLstUpdateBy(logonId);
		reactionRuleDlService.updateLogicallyDeletedDate(reactionRule);
	}

	/**
	 * This method is to modify Priority of rules
	 * 
	 * @param ruleLines
	 * @throws ReaRuleManagementException
	 */
	public void modifyPriorityOfRules(List<GeneralRuleBo> ruleLines) throws ReaRuleManagementException {
		for (GeneralRuleBo generalRule : ruleLines) {
			ReactionRule reactionRule = getReactionRule(generalRule.getRuleId(), generalRule.getLangCode());
			if (reactionRule.getChildRuleId() != null) {
				ReactionRule childRule = getReactionRule(reactionRule.getChildRuleId(), generalRule.getLangCode());
				childRule.setRulePriority(generalRule.getRulePriority());
				reactionRuleDlService.createOrUpdate(childRule);
			}
			reactionRule.setRulePriority(generalRule.getRulePriority());
			reactionRuleDlService.createOrUpdate(reactionRule);
		}

	}

	public void deleteReactionRule(ReactionRule reactionRule, String logonId) {
		reactionRule.setLstUpdateBy(logonId);
		reactionRuleDlService.updateLogicallyDeletedDate(reactionRule);

	}

	private ReactionRule findByRuleId(Long ruleId) {
		return reactionRuleDlService.findByPk(ruleId);
	}

	public void logicallyDeleteReactionRules(List<Long> reactionRuleIds, String logonId) {
		reactionRuleDlService.logicallyDeleteRules(reactionRuleIds, logonId);
	}

	public List<XPSRuleBo> findAllLogicallyDeletedRules(Date dateDeleteRuleBefore) {
		return  reactionRuleDlService.findAllLogicallyDeletedRules(dateDeleteRuleBefore);
	}

	public List<XPSRuleBo> findAllExpiredRules(Date dateDeleteRuleBefore) {
		return reactionRuleDlService.findAllExpiredRules(dateDeleteRuleBefore);
	}

	public long physicalDeleteRules(XPSRuleBo xpsRuleBo) {
		this.physicalDeleteElements(xpsRuleBo);
		reactionRuleActionTypeDlService.physicalDeleteActionForRules(xpsRuleBo);
		reactionRuleSourceTypeDlService.physicalDeleteSourceType(xpsRuleBo);
		priceProductHierarchyService.physicalDeleteElements(xpsRuleBo);
		reactionRuleDlService.physicalDeleteRule(xpsRuleBo);
		return 0L;
	}
}