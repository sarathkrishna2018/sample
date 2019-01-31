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
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleActionTypePK;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceType;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSourceTypePK;

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
	
	public List<ReactionRuleActionType> convertRuleAction(GeneralRuleBo reactionRuleBo){
		List<ReactionRuleActionType> actionTypeLst = Lists.newArrayList();
		for(RefActionTypeBo actionBo : reactionRuleBo.getActionTypeList()){
			ReactionRuleActionType actionType = new ReactionRuleActionType();
			ReactionRuleActionTypePK reaRuleSetActtypePK = new ReactionRuleActionTypePK();
			reaRuleSetActtypePK.setReaRuleId(reactionRuleBo.getRuleId());
			reaRuleSetActtypePK.setActionTypeId(actionBo.getActionTypeId());
			actionType.setId(reaRuleSetActtypePK);
			actionType.setLstUpdateBy(reactionRuleBo.getLogonId());
			actionTypeLst.add(actionType);
		}
		return actionTypeLst;
		
	}

	public List<ReactionRuleSourceType> convertRuleSource(GeneralRuleBo reactionRuleBo) {
		List<ReactionRuleSourceType> sourceList= Lists.newArrayList();
		for(RefSourceTypeBo sourceBo : reactionRuleBo.getSourceTypeList()){
			ReactionRuleSourceType source = new ReactionRuleSourceType();
			ReactionRuleSourceTypePK reaRuleSetSrcPK = new ReactionRuleSourceTypePK();
			reaRuleSetSrcPK.setReaRuleId(reactionRuleBo.getRuleId());
			reaRuleSetSrcPK.setSourceId(sourceBo.getSourceTypeId());
			source.setId(reaRuleSetSrcPK);
			source.setLstUpdateBy(reactionRuleBo.getLogonId());
			sourceList.add(source);
		}
		return sourceList;
	}

	public List<ReactionRuleActionType> convertRuleActionForAll(long actionIdForAll, GeneralRuleBo reactionRuleBo) {
		List<ReactionRuleActionType> actionTypeLst = Lists.newArrayList();
		ReactionRuleActionType actionType = new ReactionRuleActionType();
		ReactionRuleActionTypePK reaRuleSetActtypePK = new ReactionRuleActionTypePK();
		reaRuleSetActtypePK.setReaRuleId(reactionRuleBo.getRuleId());
		reaRuleSetActtypePK.setActionTypeId(actionIdForAll);
		actionType.setId(reaRuleSetActtypePK);
		actionType.setLstUpdateBy(reactionRuleBo.getLogonId());
		actionTypeLst.add(actionType);
		return actionTypeLst;
	}

	public List<ReactionRuleSourceType> convertRuleSourceForAll(long sourceIdForAll, GeneralRuleBo reactionRuleBo) {
		List<ReactionRuleSourceType> sourceList= Lists.newArrayList();
		ReactionRuleSourceType source = new ReactionRuleSourceType();
		ReactionRuleSourceTypePK reaRuleSetSrcPK = new ReactionRuleSourceTypePK();
		reaRuleSetSrcPK.setReaRuleId(reactionRuleBo.getRuleId());
		reaRuleSetSrcPK.setSourceId(sourceIdForAll);
		source.setId(reaRuleSetSrcPK);
		source.setLstUpdateBy(reactionRuleBo.getLogonId());
		sourceList.add(source);
		return sourceList;
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
