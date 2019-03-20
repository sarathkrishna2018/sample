package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;

@Stateless
@LocalBean
public class ReaRuleConverter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProductHierarchyElementConverter productHierarchyElementConverter;

	public ReactionRule convertFromBo(ReactionRule existingReaRule, GeneralRuleBo reactionRuleBo) {
		ReactionRule reaRule = null;
		if (existingReaRule != null) {
			reaRule = existingReaRule;
		} else {
			reaRule = new ReactionRule();
			reaRule.setRulePriority(reactionRuleBo.getRulePriority());
		}
		reaRule.setReaRulesetId(reactionRuleBo.getRulesetId());
		reaRule.setRuleName(reactionRuleBo.getRuleName());
		reaRule.setImportancecodeFrom(reactionRuleBo.getImportanceCodeFrom());
		reaRule.setImportancecodeTo(reactionRuleBo.getImportanceCodeTo());
		reaRule.setDirect(reactionRuleBo.isDirectBenefit());
		reaRule.setPostponed(reactionRuleBo.isPostponedBenefit());
		reaRule.setPermenant(reactionRuleBo.isPermanentDuration());
		reaRule.setTemporary(reactionRuleBo.isTemporaryDuration());
		reaRule.setValidFrom(reactionRuleBo.getValidFrom());
		reaRule.setValidUpto(reactionRuleBo.getValidTo());
		reaRule.setRecalculate(reactionRuleBo.isRecalculate());
		reaRule.setRuleComment(reactionRuleBo.getComments());
		reaRule.setCreatedBy(reactionRuleBo.getLogonId());
		reaRule.setLstUpdateBy(reactionRuleBo.getLogonId());
		reaRule.setChildRuleId(reactionRuleBo.getChildRuleId());
		return reaRule;
	}

	public List<ActionType> convertFromActionTypeBo(List<RefActionTypeBo> actionTypeList) {

		List<ActionType> actionTypeLst = Lists.newArrayList();
		for (RefActionTypeBo actionBo : actionTypeList) {
			actionTypeLst.add(ActionType.forValue(actionBo.getActionTypeId()));
		}
		return actionTypeLst;

	}

	public List<SourceType> convertFromSourceTypeBo(List<RefSourceTypeBo> sourceTypeList) {
		List<SourceType> sourceList = Lists.newArrayList();
		for (RefSourceTypeBo sourceBo : sourceTypeList) {
			sourceList.add(SourceType.forValue(sourceBo.getSourceTypeId()));
		}
		return sourceList;
	}

	public List<ActionType> convertFromActionTypeAll(int idForAll) {
		List<ActionType> actionTypes = Lists.newArrayList();
		actionTypes.add(ActionType.forValue(idForAll));
		return actionTypes;

	}

	public List<SourceType> convertFromSourceTypeAll(int idForAll) {
		List<SourceType> sourceTypes = Lists.newArrayList();
		sourceTypes.add(SourceType.forValue(idForAll));
		return sourceTypes;
	}

	public List<GeneralRuleBo> convertToBo(List<ReactionRule> reaRules) {
		List<GeneralRuleBo> ruleLines = Lists.newArrayList();
		for (ReactionRule rule : reaRules) {
			GeneralRuleBo ruleBo = new GeneralRuleBo();
			ruleBo.setRuleId(rule.getReaRuleId());
			ruleBo.setRulesetId(rule.getReaRulesetId());
			ruleBo.setRuleName(rule.getRuleName());
			ruleBo.setImportanceCodeFrom(rule.getImportancecodeFrom());
			ruleBo.setImportanceCodeTo(rule.getImportancecodeTo());
			ruleBo.setDirectBenefit(rule.getDirect());
			ruleBo.setPostponedBenefit(rule.getPostponed());
			ruleBo.setPermanentDuration(rule.getPermenant());
			ruleBo.setTemporaryDuration(rule.getTemporary());
			ruleBo.setValidFrom(rule.getValidFrom());
			ruleBo.setValidTo(rule.getValidUpto());
			ruleBo.setRecalculate(rule.getRecalculate());
			ruleBo.setComments(rule.getRuleComment());
			ruleBo.setLogonId(rule.getLstUpdateBy());
			ruleBo.setChildRuleId(rule.getChildRuleId());
			ruleLines.add(ruleBo);
		}
		return ruleLines;
	}

	public GeneralRuleBo convertToBo(ReactionRule rule, GeneralRuleBo ruleBo) {
		ruleBo.setRuleId(rule.getReaRuleId());
		ruleBo.setRulesetId(rule.getReaRulesetId());
		ruleBo.setRuleName(rule.getRuleName());
		ruleBo.setImportanceCodeFrom(rule.getImportancecodeFrom());
		ruleBo.setImportanceCodeTo(rule.getImportancecodeTo());
		ruleBo.setDirectBenefit(rule.getDirect());
		ruleBo.setPostponedBenefit(rule.getPostponed());
		ruleBo.setPermanentDuration(rule.getPermenant());
		ruleBo.setTemporaryDuration(rule.getTemporary());
		ruleBo.setValidFrom(rule.getValidFrom());
		ruleBo.setValidTo(rule.getValidUpto());
		ruleBo.setRecalculate(rule.getRecalculate());
		ruleBo.setComments(rule.getRuleComment());
		ruleBo.setUpdatedBy(rule.getLstUpdateBy());
		ruleBo.setUpdatedOn(rule.getUpdatedOn());
		ruleBo.setChildRuleId(rule.getChildRuleId());
		ruleBo.setRulePriority(rule.getRulePriority());
		return convertAssortment(ruleBo, rule);
	}

	private GeneralRuleBo convertAssortment(GeneralRuleBo ruleBo, ReactionRule rule) {
		for (PriceProductHierarchySet set : rule.getPriceProductHierarchySet()) {
			ruleBo.setAssortmentName(set.getAssortmentName());
			ruleBo.setCheapBrand(set.getCheapBrand());
			ruleBo.setNationalBrand(set.getNationalBrand());
			ruleBo.setOwnBrand(set.getOwnBrand());
			ruleBo.setProductHierarchySetId(set.getProductHierarchySetId());
			List<ProductHierarchyElementBo> productHierarchyElementBolist = productHierarchyElementConverter
					.convertToBo(set.getPriceProductHierarchyElements());
			ruleBo.setPriceProductHierarchySet(productHierarchyElementBolist);

		}
		return ruleBo;
	}
}
