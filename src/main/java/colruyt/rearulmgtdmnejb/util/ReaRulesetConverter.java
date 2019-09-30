package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;

public class ReaRulesetConverter implements Serializable {
	private static final long serialVersionUID = 1L;

	

	public static ReactionRuleSet convertFromBo(ReactionRuleSet existingReaRuleset, ReactionRulesetBo rulesetBo,
			String logonId) {
		ReactionRuleSet reaRuleset = null;
		if (existingReaRuleset != null) {
			reaRuleset = existingReaRuleset;
		} else {
			reaRuleset = new ReactionRuleSet();
		}
		reaRuleset.setColruytGroupChainId(rulesetBo.getColruytGroupChainId());
		reaRuleset.setPriceCompetitorChainId(rulesetBo.getPriceCompetitorChainId());
		reaRuleset.setRuleTypeId(rulesetBo.getRefRuleTypeBo().getRuleTypeId());
		reaRuleset.setRulesetComment(rulesetBo.getComments());
		reaRuleset.setRulesetName(rulesetBo.getName());
		reaRuleset.setLastUpdateBy(logonId);
		return reaRuleset;
	}

	public static List<ReactionRulesetBo> convertToBo(List<ReactionRuleSet> ruleSetList,List<RefRuleTypeBo> refRuletype) {
		List<ReactionRulesetBo> rulesetBoList = Lists.newArrayList();
		for (ReactionRuleSet ruleSet : ruleSetList) {
			ReactionRulesetBo ruleBo = new ReactionRulesetBo();
			ruleBo.setRulesetId(ruleSet.getReaRulesetId());
			ruleBo.setName(ruleSet.getRulesetName());
			ruleBo.setComments(ruleSet.getRulesetComment());
			ruleBo.setColruytGroupChainId(ruleSet.getColruytGroupChainId());
			ruleBo.setPriceCompetitorChainId(ruleSet.getPriceCompetitorChainId());
			RefRuleTypeBo ruleType = convertToBo(ruleSet.getRuleTypeId(),refRuletype);
			ruleBo.setRefRuleTypeBo(ruleType);
			rulesetBoList.add(ruleBo);
		}
		return rulesetBoList;
	}

	public static RefRuleTypeBo convertToBo(int ruleTypeid,List<RefRuleTypeBo> refRuletype) {
		RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
		for (RefRuleTypeBo ref : refRuletype) {
			if (ref.getRuleTypeId() == ruleTypeid) {
				List<RefLangBo> refLangList = ref.getCodeLang();
				refRuleTypeBo.setCodeLang(refLangList);
				refRuleTypeBo.setRuleTypeId(ruleTypeid);

			}
		}

		return refRuleTypeBo;

	}

	public static ReactionRulesetBo convertToBo(ReactionRuleSet reactionRuleSet) {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		if (reactionRuleSet != null) {
			reactionRulesetBo.setColruytGroupChainId(reactionRuleSet.getColruytGroupChainId());
			reactionRulesetBo.setComments(reactionRuleSet.getRulesetComment());
			reactionRulesetBo.setName(reactionRuleSet.getRulesetName());
			reactionRulesetBo.setPriceCompetitorChainId(reactionRuleSet.getPriceCompetitorChainId());
			reactionRulesetBo.setRulesetId(reactionRuleSet.getReaRulesetId());
			reactionRulesetBo.setRefRuleTypeBo(new RefRuleTypeBo());
			reactionRulesetBo.getRefRuleTypeBo().setRuleTypeId(reactionRuleSet.getRuleTypeId());

		}
		return reactionRulesetBo;

	}

}
