package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactingRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.ReactingRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactingRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReactingRuleConverter;

/**
 * extends "ReactionRuleBlService"
 * 
 * @version 1.0
 * @created 04-dec-2018 15:04:07
 */
@Stateless
@LocalBean
public class ReactingRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReactingRuleService.class);
	@EJB
	private ReactingRuleConverter reactingRuleConverter;
	@EJB
	private ReactingRuleActionDlService reactingRuleActionDlService;

	/**
	 * This method is to create reacting rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleManagementException
	 * @throws ReaRuleValidationException
	 */
	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_REACTINGRULE);
		ReactingRuleBo reactingRuleBo = (ReactingRuleBo) reactionRuleBo;
		validateRuleInputs(reactingRuleBo);
		ReactingRuleAction reactingRuleAction = reactingRuleConverter.convert(reactingRuleBo);
		reactingRuleActionDlService.createOrUpdate(reactingRuleAction);
		return reactingRuleBo;
	}

	/**
	 * This method is to validate reacting rule mandatory fields
	 * 
	 * @param reactingRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void validateRuleInputs(ReactingRuleBo reactingRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATEREACTINGRULE);
		if (reactingRuleBo != null) {
			Double thresholdAmount = reactingRuleBo.getThresholdAmount();
			Double thresholdPercentage = reactingRuleBo.getThresholdPercentage();
			Double reactionAmount = reactingRuleBo.getReactingAmount();
			Double reactionPercentage = reactingRuleBo.getReactingPercentage();
			if ((thresholdAmount == null && thresholdPercentage == null && reactionAmount == null
					&& reactionPercentage == null)) {
				throw new ReaRuleValidationException(reactingRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
			if ((thresholdAmount != null && thresholdAmount < 0)
					|| (thresholdPercentage != null && thresholdPercentage < 0)) {
				throw new ReaRuleValidationException(reactingRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_THRESHOLD_VALUE_VALIDATION);
			}
		}
	}

	/**
	 * This method is to modify reacting rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleManagementException
	 * @throws ReaRuleValidationException
	 */
	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYREACTINGRULE);
		ReactingRuleBo reactingRuleBo = (ReactingRuleBo) reactionRuleBo;
		validateRuleInputs(reactingRuleBo);
		ReactingRuleAction reactingRuleAction = reactingRuleConverter.convert(reactingRuleBo);
		reactingRuleActionDlService.createOrUpdate(reactingRuleAction);
		return reactingRuleBo;
	}

	/**
	 * This method is to get reacting rule
	 * 
	 * @param reactionRulesetBos
	 * @throws ReaRuleManagementException
	 * @throws ReaRuleValidationException
	 */
	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVEREACTINGRULE);
		long ruleTypeId = super.getRuleTypeId(RuleType.REACTING.getRuleTypeName());
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == ruleTypeId) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					ReactingRuleBo reactingRuleBo = new ReactingRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, reactingRuleBo);
					ReactingRuleBo reactingBo = (ReactingRuleBo) ruleBo;

					ReactingRuleAction reactingRuleAction = reactingRuleActionDlService
							.findByRuleId(rule.getReaRuleId());
					reactingBo = reactingRuleConverter.addingReactionRuleAction(reactingRuleAction, reactingBo);
					ruleBos.add(reactingBo);

				}
				Collections.sort(ruleBos, new GeneralRulePriorityComparator());
				reactionRulesetBo.setRuleLines(ruleBos);
			}
			ruleSetBos.add(reactionRulesetBo);
		}
		return ruleSetBos;
	}

	/**
	 * This method is to view reacting rule
	 * 
	 * @param ruleId
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VIEWREACTINGRULE);
		ReactingRuleBo reactRuleBo = (ReactingRuleBo) ruleBo;
		ReactingRuleAction reactingRuleAction = reactingRuleActionDlService.findByRuleId(reactRuleBo.getRuleId());
		reactRuleBo = reactingRuleConverter.addingReactionRuleAction(reactingRuleAction, reactRuleBo);
		reactRuleBo.setType(RuleType.REACTING.getRuleTypeName());
		return reactRuleBo;
	}

	@Override
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		logger.debug("physicalDeleteElements is {}", deleteRuleInfoBo.getRuleId());
		reactingRuleActionDlService.physicalDeleteElements(deleteRuleInfoBo);
	}

}