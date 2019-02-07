package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;

public class ReaRulesetConverter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ReaRuleConverter reaRuleConverter;

	@EJB
	private ReferenceDataService referenceDataService;

	public ReactionRuleSet convertReaRuleset(ReactionRuleSet existingReaRuleset, ReactionRulesetBo rulesetBo,
			String logonId) {
		ReactionRuleSet reaRuleset = null;
		if (existingReaRuleset != null) {
			reaRuleset = existingReaRuleset;
		} else {
			reaRuleset = new ReactionRuleSet();
		}
		reaRuleset.setCgChnId(rulesetBo.getColruytGroupChainId());
		reaRuleset.setCompChainId(rulesetBo.getPriceCompetitorChainId());
		reaRuleset.setRuleTypeId(rulesetBo.getRefRuleTypeBo().getRuleTypeId());
		reaRuleset.setRulesetComment(rulesetBo.getComments());
		reaRuleset.setRulesetName(rulesetBo.getName());
		reaRuleset.setLstUpdateBy(logonId);
		return reaRuleset;
	}

	public List<ReactionRulesetBo> convertRuleSetBo(List<ReactionRuleSet> ruleSetList) {
		List<ReactionRulesetBo> rulesetBoList = Lists.newArrayList();
		for (ReactionRuleSet ruleSet : ruleSetList) {
			ReactionRulesetBo ruleBo = new ReactionRulesetBo();
			ruleBo.setRulesetId(ruleSet.getReaRulesetId());
			ruleBo.setName(ruleSet.getRulesetName());
			ruleBo.setComments(ruleSet.getRulesetComment());
			ruleBo.setColruytGroupChainId(ruleSet.getCgChnId());
			ruleBo.setPriceCompetitorChainId(ruleSet.getCompChainId());
			RefRuleTypeBo ruleType = convertRulType(ruleSet.getRuleTypeId());
			ruleBo.setRefRuleTypeBo(ruleType);
			rulesetBoList.add(ruleBo);
		}
		return rulesetBoList;
	}

	public RefRuleTypeBo convertRulType(long ruleTypeid) {
		RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
		List<RefRuleTypeBo> refRuletype = referenceDataService.getAllRuleTypes();
		for (RefRuleTypeBo ref : refRuletype) {
			if (ref.getRuleTypeId() == ruleTypeid) {
				List<RefLangBo> refLangList = ref.getCodeLang();
				refRuleTypeBo.setCodeLang(refLangList);
				refRuleTypeBo.setRuleTypeId(ruleTypeid);

			}
		}

		return refRuleTypeBo;

	}

	public ReactionRulesetBo convertReactionRuleset(ReactionRuleSet reactionRuleSet) {
		ReactionRulesetBo reactionRulesetBo = new ReactionRulesetBo();
		if (reactionRuleSet != null) {
			reactionRulesetBo.setColruytGroupChainId(reactionRuleSet.getCgChnId());
			reactionRulesetBo.setComments(reactionRuleSet.getRulesetComment());
			reactionRulesetBo.setName(reactionRuleSet.getRulesetName());
			reactionRulesetBo.setPriceCompetitorChainId(reactionRuleSet.getCompChainId());
			reactionRulesetBo.setRulesetId(reactionRuleSet.getReaRulesetId());
			reactionRulesetBo.setRuleLines(reaRuleConverter.convertRuleLine(reactionRuleSet.getReaRules()));
			reactionRulesetBo.setRefRuleTypeBo(new RefRuleTypeBo());
			reactionRulesetBo.getRefRuleTypeBo().setRuleTypeId(reactionRuleSet.getRuleTypeId());

		}
		return reactionRulesetBo;

	}

}
