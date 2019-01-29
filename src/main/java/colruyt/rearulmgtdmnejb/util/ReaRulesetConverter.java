package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ReactionRulesetBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;
import colruyt.rearulmgtdmnejb.entity.ReactionRuleSet;
import colruyt.rearulmgtdmnejb.entity.RefRuletype;
import colruyt.rearulmgtdmnejb.entity.RefRuletypeLang;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;

@Stateless
@LocalBean
public class ReaRulesetConverter implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ReaRuleConverter reaRuleConverter;
	
	@EJB
	private ReferenceDataService referenceDataService;
	
	public ReactionRuleSet convertReaRuleset(ReactionRuleSet existingReaRuleset, ReactionRulesetBo rulesetBo, String logonId){
		ReactionRuleSet reaRuleset = null;
		if(existingReaRuleset != null) {
			reaRuleset  = existingReaRuleset;
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
		for(ReactionRuleSet ruleSet : ruleSetList){
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


	
	public List<RefRuleTypeBo> convertRuleType(List<RefRuletype> refRuletypes) {
		List<RefRuleTypeBo> refRuleTypeBoList = Lists.newArrayList();
		for (RefRuletype refRuletype : refRuletypes) {
			RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
			refRuleTypeBo.setRuleTypeId(refRuletype.getRuletypeId());
			List<RefLangBo> refLangList = convertRefRuletypeLangs(refRuletype.getRefRuletypeLang());
			refRuleTypeBo.setCodeLang(refLangList);
			refRuleTypeBoList.add(refRuleTypeBo);
		}
		return refRuleTypeBoList;
	}
	public RefRuleTypeBo convertRulType(long ruleTypeid){
		RefRuleTypeBo refRuleTypeBo = new RefRuleTypeBo();
		List<RefRuleTypeBo> refRuletype=referenceDataService.getAllRuleTypes();
		for(RefRuleTypeBo ref:refRuletype){
			if(ref.getRuleTypeId()==ruleTypeid){
				List<RefLangBo> refLangList = ref.getCodeLang();
				refRuleTypeBo.setCodeLang(refLangList);
				refRuleTypeBo.setRuleTypeId(ruleTypeid);
				
				
			}
		}
	
		return refRuleTypeBo;
		
	}

	public List<RefLangBo> convertRefRuletypeLangs(List<RefRuletypeLang> refRuletypeLangs) {
		List<RefLangBo> refLangList = Lists.newArrayList();
		if (refRuletypeLangs != null) {
			for (RefRuletypeLang refRuletypeLang : refRuletypeLangs) {
				RefLangBo refLangBo = new RefLangBo();
				refLangBo.setIsoLangCode(refRuletypeLang.getId().getIsoLangCode());
				refLangBo.setValue(refRuletypeLang.getRuletypeName());
				refLangList.add(refLangBo);
			}
		}
		return refLangList;
	}
	public ReactionRulesetBo convertReactionRuleset(ReactionRuleSet reactionRuleSet){
		ReactionRulesetBo reactionRulesetBo=new ReactionRulesetBo();
		if(reactionRuleSet!=null){
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


	

