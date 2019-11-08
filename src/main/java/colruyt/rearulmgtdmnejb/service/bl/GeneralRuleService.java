package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleActionTypeDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleDlService;
import colruyt.rearulmgtdmnejb.service.dl.ReactionRuleSourceTypeDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.ReaRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;

@LocalBean
public abstract class GeneralRuleService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GeneralRuleService.class);
	@EJB
	private ReactionRuleSetService reactionRuleSetService;
	@EJB
	private ReactionRuleDlService reactionRuleDlService;
	@EJB
	private ReactionRuleActionTypeDlService reactionRuleActionTypeDlService;
	@EJB
	private ReactionRuleSourceTypeDlService reactionRuleSourceTypeDlService;
	@EJB
	private PriceProductHierarchyService priceProductHierarchyService;
	@EJB
	private ReferenceDataService referenceDataService;

	public abstract GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException;

	public abstract GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException;

	public abstract List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException;

	public abstract GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException;

	public abstract void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo);

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

	private void appendTimeSliceParam(GeneralRuleBo childRuleBo, ReactionRule existingReactionRule) {
		existingReactionRule.setChildRuleId(childRuleBo.getRuleId());
		existingReactionRule.setValidUpto(DateUtils.addDays(childRuleBo.getValidFrom(), -1));
		reactionRuleDlService.createOrUpdate(existingReactionRule);
	}

	private GeneralRuleBo createRuleLine(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		reactionRuleBo.setRuleId(null);
		reactionRuleBo.setProductHierarchySetId(null);
		return createRule(reactionRuleBo);
	}

	private boolean checkForValidity(ReactionRule existingReactionRule, GeneralRuleBo reactionRuleBo) {
		Date currentDate = new Date();
		if (existingReactionRule.getValidFrom() != null
				&& existingReactionRule.getValidFrom().compareTo(currentDate) <= 0
				&& existingReactionRule.getValidFrom().compareTo(reactionRuleBo.getValidFrom()) < 0) {
			return true;
		}
		return false;
	}

	public GeneralRuleBo viewReactionRule(GeneralRuleBo specificReactionRuleBo, long ruleId, String langCode)
			throws ReaRuleManagementException {
		ReactionRule reactionRule = getReactionRule(ruleId, langCode);
		GeneralRuleBo ruleBo = getGeneralRuleAttributes(reactionRule, specificReactionRuleBo, true);
		return getRuleSpecificValues(ruleBo);
	}

	private GeneralRuleBo createGeneralRuleAttributes(GeneralRuleBo reactionRuleBo, boolean isDuplicationAllowed)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_REACTIONRULE);
		validateRuleInputs(reactionRuleBo);
		ReactionRulesetBo reactionRulesetBo = reactionRuleSetService.createReactionRuleSet(
				reactionRuleBo.getReactionRulesetBo(), isDuplicationAllowed, reactionRuleBo.getLogonId());
		reactionRuleBo.setRulesetId(reactionRulesetBo.getRulesetId());
		setRulePriority(reactionRulesetBo, reactionRuleBo);
		ReactionRule reaRule = ReaRuleConverter.convertFromBo(null, reactionRuleBo);
		// action
		if (reactionRuleBo.isActionSelectAll()) {
			List<ActionType> actionLst = ReaRuleConverter.convertFromActionTypeAll(ActionType.ALL.getActionTypeId());
			reaRule.setRefActionTypes(actionLst);
		} else if (!reactionRuleBo.isActionSelectAll() && reactionRuleBo.getActionTypeList() != null
				&& !reactionRuleBo.getActionTypeList().isEmpty()) {
			List<ActionType> actionLst = ReaRuleConverter.convertFromActionTypeBo(reactionRuleBo.getActionTypeList());
			reaRule.setRefActionTypes(actionLst);
		}
		// source
		if (reactionRuleBo.isSourceSelectAll()) {
			List<SourceType> sourceLst = ReaRuleConverter.convertFromSourceTypeAll(SourceType.ALL.getSourceTypeId());
			reaRule.setRefSourceTypes(sourceLst);
		} else if (!reactionRuleBo.isSourceSelectAll() && reactionRuleBo.getSourceTypeList() != null
				&& !reactionRuleBo.getSourceTypeList().isEmpty()) {
			List<SourceType> sourceLst = ReaRuleConverter.convertFromSourceTypeBo(reactionRuleBo.getSourceTypeList());
			reaRule.setRefSourceTypes(sourceLst);
		}
		reaRule = reactionRuleDlService.createOrUpdate(reaRule);
		reactionRuleBo.setRuleId(reaRule.getReaRuleId());

		// product
		return priceProductHierarchyService.createProductHierarchySet(reactionRuleBo);
	}

	private GeneralRuleBo setRulePriority(ReactionRulesetBo reactionRulesetBo, GeneralRuleBo reactionRuleBo) {
		Long rulePriority = getAllRulesByRuleSetId(reactionRulesetBo.getRulesetId());

		if (rulePriority == null && reactionRuleBo.getRulePriority() == null) {
			reactionRuleBo.setRulePriority(1L);
		} else if (rulePriority != null && reactionRuleBo.getRulePriority() == null) {
			reactionRuleBo.setRulePriority(rulePriority + 1L);
		}
		return reactionRuleBo;
	}

	private Long getAllRulesByRuleSetId(Long rulesetId) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_GETREACTRULE);
		return reactionRuleDlService.getMaxRulePriorityByRuleSetId(rulesetId);
	}

	public ReactionRule getReactionRule(long ruleId, String langCode) throws ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_GETREACTIONRULE);
		ReactionRule reactionRule = reactionRuleDlService.findByPk(ruleId);
		if (reactionRule == null) {
			throw new ReaRuleManagementException(langCode, ExceptionMessageConstants.MESSAGE_REACTION_RULE_ABSENT);
		}
		return reactionRule;
	}

	private void validateRuleInputs(GeneralRuleBo reactionRuleBo) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VALIDATEREACTIONRULE);
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

	private void dateValidation(GeneralRuleBo reactionRuleBo) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_DATEVALIDATION);
		if (reactionRuleBo.getValidFrom() == null) {
			reactionRuleBo.setValidFrom(new Date());
		}
	}

	private void importanceCodeValidation(GeneralRuleBo reactionRuleBo) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_IMPORTANCECODEVALIDATION);
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

	private GeneralRuleBo modifyGeneralRuleAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_MODIFYREACTIONRULE);
		validateRuleInputs(reactionRuleBo);
		ReactionRule existingReactionRule = reactionRuleDlService.findByPk(reactionRuleBo.getRuleId());
		if (existingReactionRule == null) {
			throw new ReaRuleManagementException(reactionRuleBo.getLangCode(),
					ExceptionMessageConstants.MESSAGE_REACTION_RULE_ABSENT);
		}
		ReactionRule reaRule = ReaRuleConverter.convertFromBo(existingReactionRule, reactionRuleBo);
		reactionRuleBo.setRuleId(reaRule.getReaRuleId());
		// action
		if (reactionRuleBo.isActionSelectAll()) {
			List<ActionType> actionLst = ReaRuleConverter.convertFromActionTypeAll(ActionType.ALL.getActionTypeId());
			reaRule.setRefActionTypes(actionLst);
		} else if (!reactionRuleBo.isActionSelectAll() && reactionRuleBo.getActionTypeList() != null
				&& !reactionRuleBo.getActionTypeList().isEmpty()) {
			List<ActionType> actionLst = ReaRuleConverter.convertFromActionTypeBo(reactionRuleBo.getActionTypeList());
			reaRule.setRefActionTypes(actionLst);
		}
		// source
		if (reactionRuleBo.isSourceSelectAll()) {
			List<SourceType> sourceLst = ReaRuleConverter.convertFromSourceTypeAll(SourceType.ALL.getSourceTypeId());
			reaRule.setRefSourceTypes(sourceLst);

		} else if (!reactionRuleBo.isSourceSelectAll() && reactionRuleBo.getSourceTypeList() != null
				&& !reactionRuleBo.getSourceTypeList().isEmpty()) {
			List<SourceType> sourceLst = ReaRuleConverter.convertFromSourceTypeBo(reactionRuleBo.getSourceTypeList());
			reaRule.setRefSourceTypes(sourceLst);
		}
		reactionRuleDlService.createOrUpdate(reaRule);
		priceProductHierarchyService.removeProductHierarchyElement(reactionRuleBo.getProductHierarchySetId());
		return priceProductHierarchyService.createProductHierarchySet(reactionRuleBo);

	}

	public List<ReactionRule> getRulesByRuleSetId(long ruleSetId) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_GETREACTRULE);
		return reactionRuleDlService.findByRuleSetId(ruleSetId);
	}

	public GeneralRuleBo getGeneralRuleAttributes(ReactionRule rule, GeneralRuleBo ruleBo, boolean populateHierarchy) {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_GETGENERALRULE);
		if (rule.getRefActionTypes() != null && rule.getRefActionTypes().get(0) == ActionType.ALL) {
			List<RefActionTypeBo> actionTypes = referenceDataService.getAllActionTypes();
			List<RefActionTypeBo> actionTypesExceptAll = referenceDataService.removeActionTypeAll(actionTypes, false);
			ruleBo.setActionTypeList(actionTypesExceptAll);
			ruleBo.setActionSelectAll(true);
		} else {
			ruleBo.setActionTypeList(ReferenceDataConverter.convertToActionTypeBo(rule.getRefActionTypes()));
		}
		if (rule.getRefSourceTypes() != null && rule.getRefSourceTypes().get(0) == SourceType.ALL) {
			List<RefSourceTypeBo> sourceTypes = referenceDataService.getAllSourceTypes();
			List<RefSourceTypeBo> sourceTypesExceptAll = referenceDataService.removeSourceTypeAll(sourceTypes);
			ruleBo.setSourceTypeList(sourceTypesExceptAll);
			ruleBo.setSourceSelectAll(true);
		} else {
			ruleBo.setSourceTypeList(ReferenceDataConverter.convertToSourceTypeBo(rule.getRefSourceTypes()));
		}
		
		return ReaRuleConverter.convertToBo(rule, ruleBo, populateHierarchy);
	}

	public void logicallyDeleteReactionRule(Long ruleId, String langCode, String logonId)
			throws ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_GETGENERALRULE);
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
			childRule.setLastUpdateBy(logonId);
			reactionRuleDlService.updateLogicallyDeletedDate(childRule);
		}
		reactionRule.setLastUpdateBy(logonId);
		reactionRuleDlService.updateLogicallyDeletedDate(reactionRule);
	}

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
		reactionRule.setLastUpdateBy(logonId);
		reactionRuleDlService.updateLogicallyDeletedDate(reactionRule);

	}

	private ReactionRule findByRuleId(Long ruleId) {
		return reactionRuleDlService.findByPk(ruleId);
	}

	public void logicallyDeleteReactionRules(List<Long> reactionRuleIds, String logonId) {
		reactionRuleDlService.logicallyDeleteRules(reactionRuleIds, logonId);
	}

	public List<DeleteRuleInfoBo> findAllLogicallyDeletedRules(Date dateDeleteRuleBefore) {
		return reactionRuleDlService.findAllLogicallyDeletedRules(dateDeleteRuleBefore);
	}

	public List<DeleteRuleInfoBo> findAllExpiredRules(Date dateDeleteRuleBefore) {
		return reactionRuleDlService.findAllExpiredRules(dateDeleteRuleBefore);
	}

	public void physicalDeleteRules(DeleteRuleInfoBo deleteRuleInfoBo) {
		this.physicalDeleteElements(deleteRuleInfoBo);
		reactionRuleActionTypeDlService.physicalDeleteActionForRules(deleteRuleInfoBo);
		reactionRuleSourceTypeDlService.physicalDeleteSourceType(deleteRuleInfoBo);
		priceProductHierarchyService.physicalDeleteElements(deleteRuleInfoBo);
		reactionRuleDlService.physicalDeleteRule(deleteRuleInfoBo);
	}
}