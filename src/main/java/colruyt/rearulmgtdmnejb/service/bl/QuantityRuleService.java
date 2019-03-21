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
import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.QuantityRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.QuantityRuleActionConverter;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;

@Stateless
@LocalBean
public class QuantityRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(QuantityRuleService.class);
	@EJB
	private QuantityRuleActionDlService quantityRuleActionDlService;

	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_QUANTITYRULE);
		QuantityRuleBo quantityRuleBo = (QuantityRuleBo) reactionRuleBo;
		validateRuleInputs(quantityRuleBo);
		QuantityRuleAction quantityRuleAction = QuantityRuleActionConverter.convertFromBo(quantityRuleBo);
		quantityRuleActionDlService.createOrUpdate(quantityRuleAction);
		return quantityRuleBo;
	}

	void validateRuleInputs(QuantityRuleBo quantityRuleBo) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VALIDATEQUANTITYRULE);
		if (quantityRuleBo != null) {
			if (quantityRuleBo.getConditionType() == null || quantityRuleBo.getQuantityPriceType() == null) {
				throw new ReaRuleValidationException(quantityRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
		}
	}

	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_MODIFYQUANTITYRULE);
		QuantityRuleBo quantityRuleBo = (QuantityRuleBo) reactionRuleBo;
		validateRuleInputs(quantityRuleBo);
		QuantityRuleAction quantityRuleAction = QuantityRuleActionConverter.convertFromBo(quantityRuleBo);
		quantityRuleActionDlService.createOrUpdate(quantityRuleAction);
		return quantityRuleBo;
	}

	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RETRIEVEQUANTITYRULE);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == RuleType.QUANTITY.getRuleTypeID()) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					QuantityRuleBo quantityRuleBo = new QuantityRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, quantityRuleBo);
					QuantityRuleBo quantityBo = (QuantityRuleBo) ruleBo;

					QuantityRuleAction quantityRuleAction = quantityRuleActionDlService
							.findByRuleId(rule.getReaRuleId());
					quantityBo = QuantityRuleActionConverter.convertToBo(quantityRuleAction, quantityBo);
					ruleBos.add(quantityBo);

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
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VIEWQUANTITYRULE);
		QuantityRuleBo quantityBo = (QuantityRuleBo) ruleBo;
		QuantityRuleAction quantityRuleAction = quantityRuleActionDlService.findByRuleId(quantityBo.getRuleId());
		quantityBo = QuantityRuleActionConverter.convertToBo(quantityRuleAction, quantityBo);
		quantityBo.setType(RuleType.QUANTITY.getRuleTypeName());
		return quantityBo;

	}

	@Override
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", deleteRuleInfoBo.getRuleId());
		logger.debug(debugInfo);
		quantityRuleActionDlService.physicalDeleteElements(deleteRuleInfoBo);
	}

}