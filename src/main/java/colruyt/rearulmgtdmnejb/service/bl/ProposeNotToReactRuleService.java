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

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProposeNotToReactRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ProposalNotToReactActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ProposeNotToReactRuleConverter;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;

@Stateless
@LocalBean
public class ProposeNotToReactRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProposeNotToReactRuleService.class);
	@EJB
	private ProposalNotToReactActionDlService proposalNotToReactActionDlService;
	@EJB
	private static ReferenceDataService referenceDataService;

	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_PROPOSENOTTOREACT);
		ProposeNotToReactRuleBo notToReactRule = (ProposeNotToReactRuleBo) reactionRuleBo;
		validateRuleInputs(notToReactRule);
		ProposalNotToReactRuleAction notToReactRuleAction = ProposeNotToReactRuleConverter
				.convertFromBo(notToReactRule);
		proposalNotToReactActionDlService.createOrUpdate(notToReactRuleAction);
		return notToReactRule;
	}

	private void validateRuleInputs(ProposeNotToReactRuleBo notToReactRule) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VALIDATEPROPOSENOTTOREACTRULE);
		if (notToReactRule != null) {
			if (notToReactRule.getFilterOutType() == null || notToReactRule.getNotToReactCodes() == null
					|| notToReactRule.getNotToReactCodes().isEmpty()) {
				throw new ReaRuleValidationException(notToReactRule.getLangCode(),
						ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
		}
	}

	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_MODIFYPROPOSENOTTOREACTRULE);
		ProposeNotToReactRuleBo proposeNotToReactRuleBo = (ProposeNotToReactRuleBo) reactionRuleBo;
		validateRuleInputs(proposeNotToReactRuleBo);
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = ProposeNotToReactRuleConverter
				.convertFromBo(proposeNotToReactRuleBo);
		proposalNotToReactActionDlService.createOrUpdate(proposalNotToReactRuleAction);
		return proposeNotToReactRuleBo;
	}

	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RETRIEVEPROPOSENOTTOREACTRULE);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == RuleType.PROPOSE_NOT_REACT.getRuleTypeID()) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				List<RefNotToReactCodeBo> refNotToReact = referenceDataService.getAllNotToReactCodeTypes();
				List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeLst = referenceDataService
						.getAllFilterOutRecordingTypes();
				for (ReactionRule rule : ruleList) {

					ProposeNotToReactRuleBo proposeNotToReactRuleBo = new ProposeNotToReactRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, proposeNotToReactRuleBo);
					ProposeNotToReactRuleBo proposeNTRRuleBo = (ProposeNotToReactRuleBo) ruleBo;

					ProposalNotToReactRuleAction proposalNotToReactRuleAction = proposalNotToReactActionDlService
							.findByRuleId(rule.getReaRuleId());
					proposeNTRRuleBo = ProposeNotToReactRuleConverter.convertToBo(proposalNotToReactRuleAction,
							proposeNTRRuleBo,refNotToReact,refFilterOutRecordingTypeLst );
					ruleBos.add(proposeNTRRuleBo);
				}
				Collections.sort(ruleBos, new GeneralRulePriorityComparator());
				reactionRulesetBo.setRuleLines(ruleBos);

			}
			ruleSetBos.add(reactionRulesetBo);
		}
		return ruleSetBos;
	}

	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VIEWPROPOSENOTTOREACTRULE);
		ProposeNotToReactRuleBo proposeNotToRactRuleBo = (ProposeNotToReactRuleBo) ruleBo;
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = proposalNotToReactActionDlService
				.findByRuleId(proposeNotToRactRuleBo.getRuleId());
		List<RefNotToReactCodeBo> refNotToReact = referenceDataService.getAllNotToReactCodeTypes();
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeLst = referenceDataService
				.getAllFilterOutRecordingTypes();
		proposeNotToRactRuleBo = ProposeNotToReactRuleConverter.convertToBo(proposalNotToReactRuleAction,
				proposeNotToRactRuleBo,refNotToReact,refFilterOutRecordingTypeLst );
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