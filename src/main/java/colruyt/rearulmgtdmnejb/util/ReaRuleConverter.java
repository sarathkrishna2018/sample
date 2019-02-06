package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;

@Stateless
@LocalBean
public class ReaRuleConverter implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReaRuleConverter.class);
	@EJB
	private ReferenceDataConverter referenceDataConverter;
	@EJB
	private ProductHrchyElmntConverter productHrchyElmntConverter;
	
	public ReactionRule convertRuleBo(ReactionRule existingReaRule, GeneralRuleBo reactionRuleBo){
		ReactionRule reaRule =  null;
		if(existingReaRule != null){
			reaRule = existingReaRule;
		} else {
			reaRule = new ReactionRule();
			reaRule.setRulePriority(reactionRuleBo.getRulePriority());
		}
		reaRule.setReaRulesetId(reactionRuleBo.getRulesetId());
		reaRule.setRuleName(reactionRuleBo.getRuleName());
		reaRule.setIcFrom(reactionRuleBo.getImportanceCodeFrom());
		reaRule.setIcTo(reactionRuleBo.getImportanceCodeTo());
		reaRule.setDirectYn(reactionRuleBo.isDirectBenefit());
		reaRule.setPostponedYn(reactionRuleBo.isPostponedBenefit());
		reaRule.setPermenantYn(reactionRuleBo.isPermanentDuration());
		reaRule.setTemporaryYn(reactionRuleBo.isTemporaryDuration());
		reaRule.setValidFrom(reactionRuleBo.getValidFrom());
		reaRule.setValidUpto(reactionRuleBo.getValidTo());
		reaRule.setRecalculateYn(reactionRuleBo.isRecalculate());
		reaRule.setRuleComment(reactionRuleBo.getComments());
		reaRule.setCreatedBy(reactionRuleBo.getLogonId());
		reaRule.setLstUpdateBy(reactionRuleBo.getLogonId());
		reaRule.setChildRuleId(reactionRuleBo.getChildRuleId());	
		return reaRule;
	}
	
	public List<Long> convertRuleAction(GeneralRuleBo reactionRuleBo) {
		List<Long> actionTypeLst = Lists.newArrayList();
		for (RefActionTypeBo actionBo : reactionRuleBo.getActionTypeList()) {
			actionTypeLst.add(actionBo.getActionTypeId());
		}
		return actionTypeLst;
		
	}

	public List<Long> convertRuleSource(GeneralRuleBo reactionRuleBo) {
		List<Long> sourceList = Lists.newArrayList();
		for (RefSourceTypeBo sourceBo : reactionRuleBo.getSourceTypeList()) {
			sourceList.add(sourceBo.getSourceTypeId());
		}
		return sourceList;
	}

	public List<Long> convertRuleTypeForAll(long idForAll) {
		List<Long> actionTypeLst = Lists.newArrayList();
		actionTypeLst.add(idForAll);
		return actionTypeLst;
	}
	
	public List<GeneralRuleBo> convertRuleLine(List<ReactionRule> reaRules) {
		List<GeneralRuleBo>  ruleLines = Lists.newArrayList();
		for(ReactionRule rule : reaRules){
			GeneralRuleBo ruleBo = new GeneralRuleBo();
			ruleBo.setRuleId(rule.getReaRuleId());
			ruleBo.setRulesetId(rule.getReaRulesetId());
			ruleBo.setRuleName(rule.getRuleName());
			ruleBo.setImportanceCodeFrom(rule.getIcFrom());
			ruleBo.setImportanceCodeTo(rule.getIcTo());
			ruleBo.setDirectBenefit(rule.getDirectYn());
			ruleBo.setPostponedBenefit(rule.getPostponedYn());
			ruleBo.setPermanentDuration(rule.getPermenantYn());
			ruleBo.setTemporaryDuration(rule.getTemporaryYn());
			ruleBo.setValidFrom(rule.getValidFrom());
			ruleBo.setValidTo(rule.getValidUpto());
			ruleBo.setRecalculate(rule.getRecalculateYn());
			ruleBo.setComments(rule.getRuleComment());
			ruleBo.setLogonId(rule.getLstUpdateBy());
			ruleBo.setChildRuleId(rule.getChildRuleId());
			ruleLines.add(ruleBo);
		}
		return ruleLines;
	}
	
	public GeneralRuleBo convertGeneralRuleBo(ReactionRule rule, GeneralRuleBo ruleBo) {
		ruleBo.setRuleId(rule.getReaRuleId());
		ruleBo.setRulesetId(rule.getReaRulesetId());
		ruleBo.setRuleName(rule.getRuleName());
		ruleBo.setImportanceCodeFrom(rule.getIcFrom());
		ruleBo.setImportanceCodeTo(rule.getIcTo());
		ruleBo.setDirectBenefit(rule.getDirectYn());
		ruleBo.setPostponedBenefit(rule.getPostponedYn());
		ruleBo.setPermanentDuration(rule.getPermenantYn());
		ruleBo.setTemporaryDuration(rule.getTemporaryYn());
		ruleBo.setValidFrom(rule.getValidFrom());
		ruleBo.setValidTo(rule.getValidUpto());
		ruleBo.setRecalculate(rule.getRecalculateYn());
		ruleBo.setComments(rule.getRuleComment());
		ruleBo.setUpdatedBy(rule.getLstUpdateBy());
		ruleBo.setUpdatedOn(rule.getUpdatedOn());
		ruleBo.setChildRuleId(rule.getChildRuleId());
		ruleBo.setRulePriority(rule.getRulePriority());
		return productHrchyElmntConverter.convertAssortment(ruleBo,rule);
	}

	

			
}
