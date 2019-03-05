package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;

@Stateless
@LocalBean
public class ReaRulesetConverter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
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
		reaRuleset.setColruytGroupChainId(rulesetBo.getColruytGroupChainId());
		reaRuleset.setPriceCompetitorChainId(rulesetBo.getPriceCompetitorChainId());
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
			ruleBo.setColruytGroupChainId(ruleSet.getColruytGroupChainId());
			ruleBo.setPriceCompetitorChainId(ruleSet.getPriceCompetitorChainId());
			RefRuleTypeBo ruleType = convertRulType(ruleSet.getRuleTypeId());
			ruleBo.setRefRuleTypeBo(ruleType);
			rulesetBoList.add(ruleBo);
		}
		return rulesetBoList;
	}

	public RefRuleTypeBo convertRulType(int ruleTypeid) {
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
			reactionRulesetBo.setColruytGroupChainId(reactionRuleSet.getColruytGroupChainId());
			reactionRulesetBo.setComments(reactionRuleSet.getRulesetComment());
			reactionRulesetBo.setName(reactionRuleSet.getRulesetName());
			reactionRulesetBo.setPriceCompetitorChainId(reactionRuleSet.getPriceCompetitorChainId());
			reactionRulesetBo.setRulesetId(reactionRuleSet.getReaRulesetId());
			reactionRulesetBo.setRuleLines(reaRuleConverter.convertRuleLine(reactionRuleSet.getReactionRules()));
			reactionRulesetBo.setRefRuleTypeBo(new RefRuleTypeBo());
			reactionRulesetBo.getRefRuleTypeBo().setRuleTypeId(reactionRuleSet.getRuleTypeId());

		}
		return reactionRulesetBo;

	}

}
