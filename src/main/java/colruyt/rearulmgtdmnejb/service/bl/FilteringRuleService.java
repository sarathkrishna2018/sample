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
import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.FilteringRuleAction;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.exception.ReaRuleManagementException;
import colruyt.rearulmgtdmnejb.exception.ReaRuleValidationException;
import colruyt.rearulmgtdmnejb.service.dl.FilteringRuleActionDlService;
import colruyt.rearulmgtdmnejb.util.ExceptionMessageConstants;
import colruyt.rearulmgtdmnejb.util.FilteringRuleActionConverter;
import colruyt.rearulmgtdmnejb.util.GeneralRulePriorityComparator;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnDebugMessage;

@Stateless
@LocalBean
public class FilteringRuleService extends GeneralRuleService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FilteringRuleService.class);

	@EJB
	private FilteringRuleActionDlService filteringRuleActionDlService;
	@EJB
	private FilteringRuleActionConverter filteringRuleActionConverter;

	@Override
	public GeneralRuleBo createRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_FILTERINGRULE);
		FilteringRuleBo filteringRuleBo = (FilteringRuleBo) reactionRuleBo;
		validateRuleInputs(filteringRuleBo);
		FilteringRuleAction reaFltRule = filteringRuleActionConverter.convertFromBo(filteringRuleBo);
		filteringRuleActionDlService.createOrUpdate(reaFltRule);
		return filteringRuleBo;
	}

	private void validateRuleInputs(FilteringRuleBo filteringRuleBo) throws ReaRuleValidationException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VALIDATEFILTERINGRULE);
		if (filteringRuleBo != null) {
			Double maxQuantity = filteringRuleBo.getMaxCompQuantity();
			Double xTimeQuantity = filteringRuleBo.getxTimeQuantity();
			if ((maxQuantity != null && maxQuantity < 0) || (xTimeQuantity != null && xTimeQuantity < 0)) {
				throw new ReaRuleValidationException(filteringRuleBo.getLangCode(),
						ExceptionMessageConstants.MESSAGE_FILTERING_RULE_VALIDATION);
			}

		}
	}

	@Override
	public GeneralRuleBo modifyRuleSpecificAttributes(GeneralRuleBo reactionRuleBo)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_MODIFYFILTERINGRULE);
		FilteringRuleBo filteringRuleBo = (FilteringRuleBo) reactionRuleBo;
		validateRuleInputs(filteringRuleBo);
		FilteringRuleAction reaFltRule = filteringRuleActionConverter.convertFromBo(filteringRuleBo);
		filteringRuleActionDlService.createOrUpdate(reaFltRule);
		return filteringRuleBo;
	}

	@Override
	public List<ReactionRulesetBo> getReactionRules(List<ReactionRulesetBo> reactionRulesetBos)
			throws ReaRuleValidationException, ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_RETRIEVEFILTERINGRULE);
		List<ReactionRulesetBo> ruleSetBos = Lists.newArrayList();
		for (ReactionRulesetBo reactionRulesetBo : reactionRulesetBos) {
			if (reactionRulesetBo.getRefRuleTypeBo().getRuleTypeId() == RuleType.FILTERING.getRuleTypeID()) {
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
				Collections.sort(ruleBos, new GeneralRulePriorityComparator());
				reactionRulesetBo.setRuleLines(ruleBos);
			}
			ruleSetBos.add(reactionRulesetBo);
		}
		return ruleSetBos;
	}

	@Override
	public GeneralRuleBo getRuleSpecificValues(GeneralRuleBo ruleBo) throws ReaRuleManagementException {
		logger.debug(ReactionRuleDmnDebugMessage.DEBUG_VIEWFILTERINGRULE);
		FilteringRuleBo filteringRuleBo = (FilteringRuleBo) ruleBo;
		FilteringRuleAction filteringRule = filteringRuleActionDlService.findByRuleId(filteringRuleBo.getRuleId());
		filteringRuleBo = filteringRuleActionConverter.convertToBo(filteringRule, filteringRuleBo);
		filteringRuleBo.setType(RuleType.FILTERING.getRuleTypeName());
		return filteringRuleBo;
	}

	@Override
	public void physicalDeleteElements(DeleteRuleInfoBo deleteRuleInfoBo) {
		String debugInfo = String.format("physicalDeleteElements %1$d", deleteRuleInfoBo.getRuleId());
		logger.debug(debugInfo);
		filteringRuleActionDlService.physicalDeleteElements(deleteRuleInfoBo);
	}

}