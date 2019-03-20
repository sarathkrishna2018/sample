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
import colruyt.rearulmgtdmnejb.bo.ReactionPeriodRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionPeriodActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReactionPeriodRuleConverter;

@Stateless
@LocalBean
public class ReactionPeriodRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReactionPeriodRuleService.class);
	@EJB
	private ReactionPeriodActionDlService reactionPeriodActionDlService;
	@EJB
	private ReactionPeriodRuleConverter reactionPeriodRuleConverter;

	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_REACTIONPERIODRULE);
		ReactionPeriodRuleBo reactionPeriodRuleBo = (ReactionPeriodRuleBo) reactionRuleBo;
		validateRuleInputs(reactionPeriodRuleBo);
		ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodRuleConverter
				.convertFromBo(reactionPeriodRuleBo);
		reactionPeriodActionDlService.createOrUpdate(reactionPeriodRuleAction);
		return reactionPeriodRuleBo;

	}

	private void validateRuleInputs(ReactionPeriodRuleBo reactionPeriodRuleBo) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VALIDATEREACTIONPERIODRULE);
		if (reactionPeriodRuleBo != null) {
			Long minDays = reactionPeriodRuleBo.getMinimumDays();
			Long endDateMinusDate = reactionPeriodRuleBo.getEndDateMinusDate();
			if (minDays == null || endDateMinusDate == null) {
				throw new ReaRuleValidationException(ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
			if (minDays < 0 || minDays > 999) {
				throw new ReaRuleValidationException(ExceptionMessageConstants.MESSAGE_INVALID_REACTION_PERIOD);
			}
		}

	}

	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_MODIFYREACTIONPERIODRULE);
		ReactionPeriodRuleBo reactionPeriodRuleBo = (ReactionPeriodRuleBo) reactionRuleBo;
		validateRuleInputs(reactionPeriodRuleBo);
		ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodRuleConverter
				.convertFromBo(reactionPeriodRuleBo);
		reactionPeriodActionDlService.createOrUpdate(reactionPeriodRuleAction);
		return reactionPeriodRuleBo;
	}

	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RETRIEVEREACTIONPERIODRULE);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == RuleType.REACTION_PERIOD.getRuleTypeID()) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					ReactionPeriodRuleBo reactionPeriodRuleBo = new ReactionPeriodRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, reactionPeriodRuleBo);
					ReactionPeriodRuleBo reacPrdRuleBo = (ReactionPeriodRuleBo) ruleBo;

					ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodActionDlService
							.findByRuleId(rule.getReaRuleId());
					reacPrdRuleBo = reactionPeriodRuleConverter.convertToBo(reactionPeriodRuleAction, reacPrdRuleBo);
					ruleBos.add(reacPrdRuleBo);

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
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VIEWREACTIONPERIODRULE);
		ReactionPeriodRuleBo reaPrdRule = (ReactionPeriodRuleBo) ruleBo;
		ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodActionDlService
				.findByRuleId(reaPrdRule.getRuleId());
		reaPrdRule = reactionPeriodRuleConverter.convertToBo(reactionPeriodRuleAction, reaPrdRule);
		reaPrdRule.setType(RuleType.REACTION_PERIOD.getRuleTypeName());
		return reaPrdRule;

	}

	@Override
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", deleteRuleInfoBo.getRuleId());
		logger.debug(debugInfo);
		reactionPeriodActionDlService.physicalDeleteElements(deleteRuleInfoBo);
	}

}
