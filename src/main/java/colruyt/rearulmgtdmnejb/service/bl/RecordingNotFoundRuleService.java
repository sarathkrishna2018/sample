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
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.RecordingNotFoundRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.RecordingNotFoundRuleConverter;

@Stateless
@LocalBean
public class RecordingNotFoundRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RecordingNotFoundRuleService.class);
	@EJB
	private RecordingNotFoundRuleActionDlService recordingNotFoundRuleActionDlService;

	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RECORDNOTFOUNDRULE);
		RecordingNotFoundRuleBo recordingNotFoundRuleBo = (RecordingNotFoundRuleBo) reactionRuleBo;
		validateRuleInputs(recordingNotFoundRuleBo);
		RecordingNotFoundRuleAction recordNotFoundRuleAction = RecordingNotFoundRuleConverter
				.convertFromBo(recordingNotFoundRuleBo);
		recordingNotFoundRuleActionDlService.createOrUpdate(recordNotFoundRuleAction);
		return recordingNotFoundRuleBo;
	}

	private void validateRuleInputs(RecordingNotFoundRuleBo recordingNotFoundRuleBo) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VALIDATERECORDNOTFOUNDRULE);
		if (recordingNotFoundRuleBo != null) {
			Long numberOfNotFoundRec = recordingNotFoundRuleBo.getNoOfNotFoundRecordings();
			if (numberOfNotFoundRec == null) {
				throw new ReaRuleValidationException(recordingNotFoundRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
			if (numberOfNotFoundRec > 999 || numberOfNotFoundRec < 0) {
				throw new ReaRuleValidationException(recordingNotFoundRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_Invalid_NumberOfRecordings);
			}
		}
	}

	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_MODIFYRECORDNOTFOUNDRULE);
		RecordingNotFoundRuleBo recordingNotFoundRuleBo = (RecordingNotFoundRuleBo) reactionRuleBo;
		validateRuleInputs(recordingNotFoundRuleBo);
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = RecordingNotFoundRuleConverter
				.convertFromBo(recordingNotFoundRuleBo);
		recordingNotFoundRuleActionDlService.createOrUpdate(recordingNotFoundRuleAction);
		return recordingNotFoundRuleBo;
	}

	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RETRIEVERECORDNOTFOUNDRULE);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == RuleType.RECORD_NOT_FOUND.getRuleTypeID()) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					RecordingNotFoundRuleBo recordingNotFoundRuleBo = new RecordingNotFoundRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, recordingNotFoundRuleBo);
					RecordingNotFoundRuleBo recordingNotFoundBo = (RecordingNotFoundRuleBo) ruleBo;

					RecordingNotFoundRuleAction recordNotFoundRule = recordingNotFoundRuleActionDlService
							.findByRuleId(rule.getReaRuleId());
					recordingNotFoundBo = RecordingNotFoundRuleConverter.convertToBo(recordNotFoundRule,
							recordingNotFoundBo);
					ruleBos.add(recordingNotFoundBo);

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
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VIEWRECORDNOTFOUNDRULE);
		RecordingNotFoundRuleBo recordNotFoundRuleBo = (RecordingNotFoundRuleBo) ruleBo;
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = recordingNotFoundRuleActionDlService
				.findByRuleId(recordNotFoundRuleBo.getRuleId());
		recordNotFoundRuleBo = RecordingNotFoundRuleConverter.convertToBo(recordingNotFoundRuleAction,
				recordNotFoundRuleBo);
		recordNotFoundRuleBo.setType(RuleType.RECORD_NOT_FOUND.getRuleTypeName());
		return recordNotFoundRuleBo;
	}

	@Override
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", deleteRuleInfoBo.getRuleId());
		logger.debug(debugInfo);
		recordingNotFoundRuleActionDlService.physicalDeleteElements(deleteRuleInfoBo);
	}

}
