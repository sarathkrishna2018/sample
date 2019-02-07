package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.QuantityRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.QuantityRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.QuantityRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.GeneralRuleComparator;
import colruyt.rearulmgtdmnejb.util.QuantityRuleActionConverter;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;

/**
 * extends "ReactionRuleBlService"
 * 
 * @version 1.0
 * @created 28-nov-2018 9:02:33
 */
@Stateless
@LocalBean
public class QuantityRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(QuantityRuleService.class);
	@Inject
	private QuantityRuleActionConverter quantityRuleActionConvertor;
	@EJB
	private QuantityRuleActionDlService quantityRuleActionDlService;
	

	/**
	 * This method is to create Quantity Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_QUANTITYRULE);
		QuantityRuleBo quantityRuleBo = (QuantityRuleBo) reactionRuleBo;
		validateRuleInputs(quantityRuleBo);
		QuantityRuleAction quantityRuleAction = quantityRuleActionConvertor.convert(quantityRuleBo);
		quantityRuleActionDlService.createOrUpdate(quantityRuleAction);
		return quantityRuleBo;
	}

	/**
	 * This method is to validate Quantity Rule Mandatory fields
	 * 
	 * @param quantityRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void validateRuleInputs(QuantityRuleBo quantityRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATEQUANTITYRULE);
		if (quantityRuleBo != null) {
			if (quantityRuleBo.getConditionType() == null || quantityRuleBo.getQuantityPriceType() == null) {
				throw new ReaRuleValidationException(quantityRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_MANDATORY_FIELD_VALIDATION);
			}
		}
	}

	/**
	 * This method is to modify Quantity Rule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYQUANTITYRULE);
		QuantityRuleBo quantityRuleBo = (QuantityRuleBo) reactionRuleBo;
		validateRuleInputs(quantityRuleBo);
		QuantityRuleAction quantityRuleAction = quantityRuleActionConvertor.convert(quantityRuleBo);
		quantityRuleActionDlService.createOrUpdate(quantityRuleAction);
		return quantityRuleBo;
	}

	/**
	 * This method is to get Quantity Rule
	 * 
	 * @param reactionRulesetBos
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVEQUANTITYRULE);
		long ruleTypeId = super.getRuleTypeId(ReaRulMgtDmnConstants.RULE_TYPE_QUANTITY);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == ruleTypeId) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					QuantityRuleBo quantityRuleBo = new QuantityRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, quantityRuleBo);
					QuantityRuleBo quantityBo = (QuantityRuleBo) ruleBo;

					QuantityRuleAction quantityRuleAction = quantityRuleActionDlService
							.findByRuleId(rule.getReaRuleId());
					quantityBo = quantityRuleActionConvertor.addQuantityRuleAction(quantityRuleAction, quantityBo);
					ruleBos.add(quantityBo);

				}
				Collections.sort(ruleBos, new GeneralRuleComparator());
				reactionRulesetBo.setRuleLines(ruleBos);
			} 
			ruleSetBos.add(reactionRulesetBo);
		}
		return ruleSetBos;
	}

	/**
	 * This method is to view Quantity Rule
	 * 
	 * @param ruleId
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VIEWQUANTITYRULE);
		QuantityRuleBo quantityBo = (QuantityRuleBo) ruleBo;
		QuantityRuleAction quantityRuleAction = quantityRuleActionDlService.findByRuleId(quantityBo.getRuleId());
		quantityBo = quantityRuleActionConvertor.addQuantityRuleAction(quantityRuleAction, quantityBo);
		quantityBo.setType(ReaRulMgtDmnConstants.RULE_TYPE_QUANTITY);
		return quantityBo;

	}

	@Override
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", xpsRuleBo.getRuleId());
		logger.debug(debugInfo);
		return quantityRuleActionDlService.physicalDeleteElements(xpsRuleBo);
	}

}