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
import colruyt.rearulmgtdmnejb.bo.ReactionPeriodRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.ReactionPeriodRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.ReactionPeriodActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRuleComparator;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;
import colruyt.rearulmgtdmnejb.util.ReactionPeriodRuleConverter;

@Stateless
@LocalBean
public class ReactionPeriodRuleService extends GeneralRuleService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReactionPeriodRuleService.class);
	@EJB
	private ReactionPeriodActionDlService reactionPeriodActionDlService;
	@EJB
	private ReactionPeriodRuleConverter reactionPeriodRuleConverter;
	

	/**
	 * This method is to create Reaction Period Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_REACTIONPERIODRULE);
		ReactionPeriodRuleBo reactionPeriodRuleBo = (ReactionPeriodRuleBo) reactionRuleBo;
		validateRuleInputs(reactionPeriodRuleBo);
		ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodRuleConverter.convert(reactionPeriodRuleBo);
		reactionPeriodActionDlService.createOrUpdate(reactionPeriodRuleAction);
		return reactionPeriodRuleBo;

	}

	/**
	 * This method is to validate Reaction Period Rule
	 * 
	 * @param reactionPeriodRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void validateRuleInputs(ReactionPeriodRuleBo reactionPeriodRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATEREACTIONPERIODRULE);
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

	/**
	 * This method is to modify Reaction Period Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYREACTIONPERIODRULE);
		ReactionPeriodRuleBo reactionPeriodRuleBo = (ReactionPeriodRuleBo) reactionRuleBo;
		validateRuleInputs(reactionPeriodRuleBo);
		ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodRuleConverter.convert(reactionPeriodRuleBo);
		reactionPeriodActionDlService.createOrUpdate(reactionPeriodRuleAction);
		return reactionPeriodRuleBo;
	}

	/**
	 * This method is to get Reaction Period Rule
	 * 
	 * @param reactionRulesetBos
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */

	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVEREACTIONPERIODRULE);
		long ruleTypeId = super.getRuleTypeId(ReaRulMgtDmnConstants.RULE_TYPE_REACTION_PERIOD);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == ruleTypeId) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					ReactionPeriodRuleBo reactionPeriodRuleBo = new ReactionPeriodRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, reactionPeriodRuleBo);
					ReactionPeriodRuleBo reacPrdRuleBo = (ReactionPeriodRuleBo) ruleBo;

					ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodActionDlService
							.findByRuleId(rule.getReaRuleId());
					reacPrdRuleBo = reactionPeriodRuleConverter.addReactionPeriodRuleAction(reactionPeriodRuleAction, reacPrdRuleBo);
					ruleBos.add(reacPrdRuleBo);

				}
				Collections.sort(ruleBos, new GeneralRuleComparator());
				reactionRulesetBo.setRuleLines(ruleBos);
			} 
			ruleSetBos.add(reactionRulesetBo);
		}
		return ruleSetBos;
	}

	/**
	 * This method is to view Reaction Period Rule
	 * 
	 * @param ruleId
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VIEWREACTIONPERIODRULE);
		ReactionPeriodRuleBo reaPrdRule = (ReactionPeriodRuleBo) ruleBo;
		ReactionPeriodRuleAction reactionPeriodRuleAction = reactionPeriodActionDlService.findByRuleId(reaPrdRule.getRuleId());
		reaPrdRule = reactionPeriodRuleConverter.addReactionPeriodRuleAction(reactionPeriodRuleAction, reaPrdRule);
		reaPrdRule.setType(ReaRulMgtDmnConstants.RULE_TYPE_REACTION_PERIOD);
		return reaPrdRule;

	}

	@Override
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", xpsRuleBo.getRuleId());
		logger.debug(debugInfo);
		return reactionPeriodActionDlService.physicalDeleteElements(xpsRuleBo);
	}

}
