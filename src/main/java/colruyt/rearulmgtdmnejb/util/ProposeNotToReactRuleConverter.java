package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ProposeNotToReactRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleActionRsn;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleActionRsnPK;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;

@Stateless
@LocalBean
public class ProposeNotToReactRuleConverter implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ReferenceDataService referenceDataService;

	public ProposalNotToReactRuleAction convert(ProposeNotToReactRuleBo notToReactRule ) {
		ProposalNotToReactRuleAction reaNreactAct = new ProposalNotToReactRuleAction();
		reaNreactAct.setFltoutTypeId(notToReactRule.getFilterOutType().getFilterOutTypeId());
		return reaNreactAct;
	}
	
	public List<ProposalNotToReactRuleActionRsn> convertReasonNotToReactSet(List<RefNotToReactCodeBo> refNotToReactCodeBos, Long ruleId, String logonId){
		List<ProposalNotToReactRuleActionRsn> reaNreactSetRsns = Lists.newArrayList();
		for(RefNotToReactCodeBo refNotToReactCodeBo: refNotToReactCodeBos) {
			ProposalNotToReactRuleActionRsn reaNreactSetRsn = new ProposalNotToReactRuleActionRsn();
			ProposalNotToReactRuleActionRsnPK reaNreactSetRsnPK = new ProposalNotToReactRuleActionRsnPK();
			reaNreactSetRsnPK.setReaRuleId(ruleId);
			reaNreactSetRsnPK.setReasonId(refNotToReactCodeBo.getNotToReactCodeTypeId());
			reaNreactSetRsn.setId(reaNreactSetRsnPK);
			reaNreactSetRsn.setLstUpdateBy(logonId);
			reaNreactSetRsns.add(reaNreactSetRsn);
		}
		return reaNreactSetRsns;
	}

	public ProposeNotToReactRuleBo convertToBo(ProposalNotToReactRuleAction proposalNotToReactRuleAction,
			ProposeNotToReactRuleBo proposeNTRRuleBo) {
		proposeNTRRuleBo.setFilterOutType(convertFltOutType(proposalNotToReactRuleAction.getFltoutTypeId()));
		proposeNTRRuleBo.setNotToReactCodes(convertReasonNotToReactSetBos(proposalNotToReactRuleAction.getReaNreactSetRsns()));
		return proposeNTRRuleBo;
	}

	private List<RefNotToReactCodeBo> convertReasonNotToReactSetBos(
			List<ProposalNotToReactRuleActionRsn> reaNreactSetRsns) {
		List<RefNotToReactCodeBo> refNotToReact = referenceDataService.getAllNotToReactCodeTypes();
		List<RefNotToReactCodeBo> notRoReactBos = Lists.newArrayList();
		for (int i = 0; i < reaNreactSetRsns.size(); i++) {
			RefNotToReactCodeBo reasonCode = new RefNotToReactCodeBo();
			for (int j = 0; j < refNotToReact.size(); j++) {
				if (refNotToReact.get(j).getNotToReactCodeTypeId() == reaNreactSetRsns.get(i).getId().getReasonId()) {
					reasonCode.setNotToReactCodeTypeId(refNotToReact.get(j).getNotToReactCodeTypeId());
					reasonCode.setDescription(refNotToReact.get(j).getDescription());
					reasonCode.setCodeLang(refNotToReact.get(j).getCodeLang());
					notRoReactBos.add(reasonCode);
				}
			}
		}
		return notRoReactBos;
	}

	private RefFilterOutRecordingTypeBo convertFltOutType(long fltoutTypeId) {
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeLst = referenceDataService.getAllFilterOutRecordingTypes();
		for(int i=0;i<refFilterOutRecordingTypeLst.size();i++){
			if(fltoutTypeId==refFilterOutRecordingTypeLst.get(i).getFilterOutTypeId()){
				refFilterOutRecordingTypeBo.setFilterOutTypeId(fltoutTypeId);
				refFilterOutRecordingTypeBo.setDescription(refFilterOutRecordingTypeLst.get(i).getDescription());
				refFilterOutRecordingTypeBo.setCodeLang(refFilterOutRecordingTypeLst.get(i).getCodeLang());
			}
		}
		return refFilterOutRecordingTypeBo;
	}
}
