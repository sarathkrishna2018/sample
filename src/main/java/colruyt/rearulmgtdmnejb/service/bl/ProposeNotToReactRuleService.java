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
import colruyt.rearulmgtdmnejb.bo.ProposeNotToReactRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ProposalNotToReactActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ProposeNotToReactRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;

/**
 * extends "ReactionRuleBlService"
 * 
 * @version 1.0
 * @created 04-dec-2018 15:04:24
 */
@Stateless
@LocalBean
public class ProposeNotToReactRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProposeNotToReactRuleService.class);
	@EJB
	private ProposalNotToReactActionDlService proposalNotToReactActionDlService;
	@EJB
	private ProposeNotToReactRuleConverter proposeNotToReactRuleConverter;

	/**
	 * This method is to create ProposeNotToReactRule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_PROPOSENOTTOREACT);
		ProposeNotToReactRuleBo notToReactRule = (ProposeNotToReactRuleBo) reactionRuleBo;
		validateRuleInputs(notToReactRule);
		ProposalNotToReactRuleAction notToReactRuleAction = proposeNotToReactRuleConverter.convert(notToReactRule);
		proposalNotToReactActionDlService.createOrUpdate(notToReactRuleAction);
		return notToReactRule;
	}

	/**
	 * This Method is to validate ProposeNotToReactRule Mandatory fields
	 * 
	 * @param notToReactRule
	 * @throws ReaRuleValidationException
	 */
	private void validateRuleInputs(ProposeNotToReactRuleBo notToReactRule) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATEPROPOSENOTTOREACTRULE);
		if (notToReactRule != null) {
			if (notToReactRule.getFilterOutType() == null || notToReactRule.getNotToReactCodes() == null
					|| notToReactRule.getNotToReactCodes().isEmpty()) {
				throw new ReaRuleValidationException(notToReactRule.getLangCode(),
						ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
		}
	}

	/**
	 * This Method is to edit ProposeNotToReact Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYPROPOSENOTTOREACTRULE);
		ProposeNotToReactRuleBo proposeNotToReactRuleBo = (ProposeNotToReactRuleBo) reactionRuleBo;
		validateRuleInputs(proposeNotToReactRuleBo);
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = proposeNotToReactRuleConverter
				.convert(proposeNotToReactRuleBo);
		proposalNotToReactActionDlService.createOrUpdate(proposalNotToReactRuleAction);
		return proposeNotToReactRuleBo;
	}

	/**
	 * This Method is to get ProposeNotToReact Rule
	 * 
	 * @param reactionRulesetBos
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVEPROPOSENOTTOREACTRULE);
		long ruleTypeId = super.getRuleTypeId(RuleType.PROPOSE_NOT_REACT.getRuleTypeName());
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == ruleTypeId) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					ProposeNotToReactRuleBo proposeNotToReactRuleBo = new ProposeNotToReactRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, proposeNotToReactRuleBo);
					ProposeNotToReactRuleBo proposeNTRRuleBo = (ProposeNotToReactRuleBo) ruleBo;

					ProposalNotToReactRuleAction proposalNotToReactRuleAction = proposalNotToReactActionDlService
							.findByRuleId(rule.getReaRuleId());
					proposeNTRRuleBo = proposeNotToReactRuleConverter.convertToBo(proposalNotToReactRuleAction,
							proposeNTRRuleBo);
					ruleBos.add(proposeNTRRuleBo);
				}
				Collections.sort(ruleBos, new GeneralRulePriorityComparator());
				reactionRulesetBo.setRuleLines(ruleBos);

			}
			ruleSetBos.add(reactionRulesetBo);
		}
		return ruleSetBos;
	}

	/**
	 * This Method is to View ProposeNotToReact Rule
	 * 
	 * @param ruleId
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VIEWPROPOSENOTTOREACTRULE);
		ProposeNotToReactRuleBo proposeNotToRactRuleBo = (ProposeNotToReactRuleBo) ruleBo;
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = proposalNotToReactActionDlService
				.findByRuleId(proposeNotToRactRuleBo.getRuleId());
		proposeNotToRactRuleBo = proposeNotToReactRuleConverter.convertToBo(proposalNotToReactRuleAction,
				proposeNotToRactRuleBo);
		proposeNotToRactRuleBo.setType(RuleType.PROPOSE_NOT_REACT.getRuleTypeName());
		return proposeNotToRactRuleBo;
	}

	@Override
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", deleteRuleInfoBo.getRuleId());
		logger.debug(debugInfo);
		proposalNotToReactActionDlService.physicalDeleteElementsRsn(deleteRuleInfoBo);
		proposalNotToReactActionDlService.physicalDeleteElements(deleteRuleInfoBo);
	}

}