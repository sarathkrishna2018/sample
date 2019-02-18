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
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RecordingNotFoundRuleBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.RecordingNotFoundRuleAction;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.RecordingNotFoundRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.RecordingNotFoundRuleConverter;

@Stateless
@LocalBean
public class RecordingNotFoundRuleService extends GeneralRuleService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RecordingNotFoundRuleService.class);
	@EJB
	private RecordingNotFoundRuleConverter recordingNotFoundRuleConverter;
	@EJB
	private RecordingNotFoundRuleActionDlService recordingNotFoundRuleActionDlService;
	

	/**
	 * This method is to create Recording Not Found Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleManagementException
	 * @throws ReaRuleValidationException
	 */
	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RECORDNOTFOUNDRULE);
		RecordingNotFoundRuleBo recordingNotFoundRuleBo = (RecordingNotFoundRuleBo) reactionRuleBo;
		validateRuleInputs(recordingNotFoundRuleBo);
		RecordingNotFoundRuleAction recordNotFoundRuleAction = recordingNotFoundRuleConverter
				.convert(recordingNotFoundRuleBo);
		recordingNotFoundRuleActionDlService.createOrUpdate(recordNotFoundRuleAction);
		return recordingNotFoundRuleBo;
	}

	/**
	 * This method is to validate Recording Not Found Rule
	 * 
	 * @param recordingNotFoundRuleBo
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	private void validateRuleInputs(RecordingNotFoundRuleBo recordingNotFoundRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATERECORDNOTFOUNDRULE);
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

	/**
	 * This method is to modify Recording Not Found Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYRECORDNOTFOUNDRULE);
		RecordingNotFoundRuleBo recordingNotFoundRuleBo = (RecordingNotFoundRuleBo) reactionRuleBo;
		validateRuleInputs(recordingNotFoundRuleBo);
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = recordingNotFoundRuleConverter
				.convert(recordingNotFoundRuleBo);
		recordingNotFoundRuleActionDlService.createOrUpdate(recordingNotFoundRuleAction);
		return recordingNotFoundRuleBo;
	}

	/**
	 * This method is to get Recording Not Found Rule
	 * 
	 * @param reactionRulesetBos
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVERECORDNOTFOUNDRULE);
		long ruleTypeId = super.getRuleTypeId(ReaRulMgtDmnConstants.RULE_TYPE_RECORD_NOT_FOUND);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == ruleTypeId) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					RecordingNotFoundRuleBo recordingNotFoundRuleBo = new RecordingNotFoundRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, recordingNotFoundRuleBo);
					RecordingNotFoundRuleBo recordingNotFoundBo = (RecordingNotFoundRuleBo) ruleBo;

					RecordingNotFoundRuleAction recordNotFoundRule = recordingNotFoundRuleActionDlService
							.findByRuleId(rule.getReaRuleId());
					recordingNotFoundBo = recordingNotFoundRuleConverter.addRecordingNotFoundRuleAction(recordNotFoundRule,
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

	/**
	 * This method is to view Recording Not Found Rule
	 * 
	 * @param ruleId
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VIEWRECORDNOTFOUNDRULE);
		RecordingNotFoundRuleBo recordNotFoundRuleBo = (RecordingNotFoundRuleBo) ruleBo;
		RecordingNotFoundRuleAction recordingNotFoundRuleAction = recordingNotFoundRuleActionDlService
				.findByRuleId(recordNotFoundRuleBo.getRuleId());
		recordNotFoundRuleBo = recordingNotFoundRuleConverter.addRecordingNotFoundRuleAction(recordingNotFoundRuleAction,
				recordNotFoundRuleBo);
		recordNotFoundRuleBo.setType(ReaRulMgtDmnConstants.RULE_TYPE_RECORD_NOT_FOUND);
		return recordNotFoundRuleBo;
	}

	@Override
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", xpsRuleBo.getRuleId());
		logger.debug(debugInfo);
		return recordingNotFoundRuleActionDlService.physicalDeleteElements(xpsRuleBo);
	}

}
