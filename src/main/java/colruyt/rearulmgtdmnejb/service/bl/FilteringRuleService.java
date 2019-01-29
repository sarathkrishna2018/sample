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

import colruyt.rearulmgtdmnejb.bo.FilteringRuleBo;
import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.XPSRuleBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.FilteringRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.FilteringRuleActionConverter;
import colruyt.rearulmgtdmnejb.util.GeneralRuleComparator;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnConstants;
import colruyt.rearulmgtdmnejb.util.ReaRulMgtDmnDebugMessage;

/**
 * extends "ReactionRuleBlService"
 * 
 * @version 1.0
 * @created 28-nov-2018 8:17:02
 */
/**
 * @author asi16ad
 *
 */
/**
 * @author asi16ad
 *
 */
@Stateless
@LocalBean
public class FilteringRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FilteringRuleService.class);

	@EJB
	private FilteringRuleActionDlService filteringRuleActionDlService;
	@EJB
	private FilteringRuleActionConverter filteringRuleActionConverter;
	
	/**
	 * This Method is to Create FilteringRule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_FILTERINGRULE);
		FilteringRuleBo filteringRuleBo = (FilteringRuleBo) reactionRuleBo;
		validateRuleInputs(filteringRuleBo);
		FilteringRuleAction reaFltRule = filteringRuleActionConverter.convert(filteringRuleBo);
		reaFltRule.setReaRuleId(filteringRuleBo.getRuleId());
		filteringRuleActionDlService.createOrUpdate(reaFltRule);
		return filteringRuleBo;
	}

	/**
	 * This Method is to Validate FilteringRule Mandatory Fields
	 * 
	 * @param filteringRuleBo
	 * @throws ReaRuleValidationException
	 */
	private void validateRuleInputs(FilteringRuleBo filteringRuleBo) throws ReaRuleValidationException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VALIDATEFILTERINGRULE);
		if (filteringRuleBo != null) {
			Double maxQuantity = filteringRuleBo.getMaxCompQuantity();
			Double xTimeQuantity = filteringRuleBo.getxTimeQuantity();
			if ((maxQuantity != null && maxQuantity < 0) || (xTimeQuantity != null && xTimeQuantity < 0)) {
				throw new ReaRuleValidationException(filteringRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_FILTERING_RULE_VALIDATION);
			}

		}
	}

	/**
	 * This Method is to edit FilteringRule
	 * 
	 * @param reactionRuleBo
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_MODIFYFILTERINGRULE);
		FilteringRuleBo filteringRuleBo = (FilteringRuleBo) reactionRuleBo;
		validateRuleInputs(filteringRuleBo);
		FilteringRuleAction reaFltRule = filteringRuleActionConverter.convert(filteringRuleBo);
		reaFltRule.setReaRuleId(filteringRuleBo.getRuleId());
		filteringRuleActionDlService.createOrUpdate(reaFltRule);
		return filteringRuleBo;
	}

	/**
	 * This Method is to get FilteringRule
	 * 
	 * @param reactionRulesetBos
	 * @throws ReaRuleValidationException
	 * @throws ReaRuleManagementException
	 */
	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_RETRIEVEFILTERINGRULE);
		long ruleTypeId = super.getRuleTypeId(ReaRulMgtDmnConstants.RULE_TYPE_FILTERING);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == ruleTypeId) {
				List<GeneralRuleBo> ruleBos = Lists.newArrayList();
				List<ReactionRule> ruleList = super.getRulesByRuleSetId(reactionRulesetBo.getRulesetId());
				for (ReactionRule rule : ruleList) {

					FilteringRuleBo filteringBo = new FilteringRuleBo();
					GeneralRuleBo ruleBo = super.getGeneralRuleAttributes(rule, filteringBo);
					FilteringRuleBo filteringRuleBo = (FilteringRuleBo) ruleBo;

					FilteringRuleAction filteringRule = filteringRuleActionDlService.findByRuleId(rule.getReaRuleId());
					filteringRuleBo = filteringRuleActionConverter.convertToBo(filteringRule, filteringRuleBo);
					ruleBos.add(filteringRuleBo);

				}
				Collections.sort(ruleBos, new GeneralRuleComparator());
				reactionRulesetBo.setRuleLines(ruleBos);
				ruleSetBos.add(reactionRulesetBo);
			} else {
				ruleSetBos.add(reactionRulesetBo);
			}
		}
		return ruleSetBos;
	}

	/**
	 * This Method is to view FilteringRule
	 * 
	 * @param ruleId
	 * @param ruleName
	 * @throws ReaRuleManagementException
	 */
	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReaRulMgtDmnDebugMessage.DEBUG_VIEWFILTERINGRULE);
		FilteringRuleBo filteringRuleBo = (FilteringRuleBo) ruleBo;
		FilteringRuleAction filteringRule = filteringRuleActionDlService.findByRuleId(filteringRuleBo.getRuleId());
		filteringRuleBo = filteringRuleActionConverter.convertToBo(filteringRule, filteringRuleBo);
		filteringRuleBo.setType(ReaRulMgtDmnConstants.RULE_TYPE_FILTERING);
		return filteringRuleBo;
	}

	@Override
	public long physicalDeleteElements(XPSRuleBo xpsRuleBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", xpsRuleBo.getRuleId());
		logger.debug(debugInfo);
		return filteringRuleActionDlService.physicalDeleteElements(xpsRuleBo);
	}

}