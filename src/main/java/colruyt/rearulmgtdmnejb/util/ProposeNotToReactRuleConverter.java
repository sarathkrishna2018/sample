package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ProposeNotToReactRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;


public class ProposeNotToReactRuleConverter implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private static ReferenceDataService referenceDataService;

	public static ProposalNotToReactRuleAction convertFromBo(ProposeNotToReactRuleBo notToReactRule) {
		ProposalNotToReactRuleAction notToReactRuleAction = new ProposalNotToReactRuleAction();
		notToReactRuleAction.setReactionRuleId(notToReactRule.getRuleId());
		notToReactRuleAction.setFltoutTypeId(notToReactRule.getFilterOutType().getFilterOutTypeId());
		notToReactRuleAction
				.setNotToReactSetReasons(getProposeNotToReactReasonIds(notToReactRule.getNotToReactCodes()));
		return notToReactRuleAction;
	}

	public static ProposeNotToReactRuleBo convertToBo(ProposalNotToReactRuleAction proposalNotToReactRuleAction,
			ProposeNotToReactRuleBo proposeNTRRuleBo) {
		proposeNTRRuleBo.setFilterOutType(convertToBo(proposalNotToReactRuleAction.getFltoutTypeId()));
		proposeNTRRuleBo.setNotToReactCodes(convertToBo(proposalNotToReactRuleAction.getNotToReactSetReasons()));
		return proposeNTRRuleBo;
	}

	private static  List<RefNotToReactCodeBo> convertToBo(List<Integer> reaNreactSetRsns) {
		List<RefNotToReactCodeBo> refNotToReact = referenceDataService.getAllNotToReactCodeTypes();
		List<RefNotToReactCodeBo> notRoReactBos = Lists.newArrayList();
		for (int i = 0; i < reaNreactSetRsns.size(); i++) {
			RefNotToReactCodeBo reasonCode = new RefNotToReactCodeBo();
			for (int j = 0; j < refNotToReact.size(); j++) {
				if (refNotToReact.get(j).getNotToReactCodeTypeId() == (reaNreactSetRsns.get(i))) {
					reasonCode.setNotToReactCodeTypeId(refNotToReact.get(j).getNotToReactCodeTypeId());
					reasonCode.setDescription(refNotToReact.get(j).getDescription());
					reasonCode.setCodeLang(refNotToReact.get(j).getCodeLang());
					notRoReactBos.add(reasonCode);
				}
			}
		}
		return notRoReactBos;
	}

	private static RefFilterOutRecordingTypeBo convertToBo(int fltoutTypeId) {
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo = new RefFilterOutRecordingTypeBo();
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeLst = referenceDataService
				.getAllFilterOutRecordingTypes();
		for (int i = 0; i < refFilterOutRecordingTypeLst.size(); i++) {
			if (fltoutTypeId == refFilterOutRecordingTypeLst.get(i).getFilterOutTypeId()) {
				refFilterOutRecordingTypeBo.setFilterOutTypeId(fltoutTypeId);
				refFilterOutRecordingTypeBo.setDescription(refFilterOutRecordingTypeLst.get(i).getDescription());
				refFilterOutRecordingTypeBo.setCodeLang(refFilterOutRecordingTypeLst.get(i).getCodeLang());
			}
		}
		return refFilterOutRecordingTypeBo;
	}

	private static List<Integer> getProposeNotToReactReasonIds(List<RefNotToReactCodeBo> notToReactCodes) {
		List<Integer> reasonIds = Lists.newArrayList();
		for (RefNotToReactCodeBo reasonCode : notToReactCodes) {
			reasonIds.add(reasonCode.getNotToReactCodeTypeId());
		}
		return reasonIds;
	}

}
