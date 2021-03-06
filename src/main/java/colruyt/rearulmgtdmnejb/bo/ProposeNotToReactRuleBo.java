package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import colruyt.rearulmgtdmnejb.enums.RuleType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposeNotToReactRuleBo extends GeneralRuleBo implements Serializable {
	
	public ProposeNotToReactRuleBo(){
		this.setType(RuleType.PROPOSE_NOT_REACT.getRuleTypeName());
	}

	private static final long serialVersionUID = 1L;
	private RefFilterOutRecordingTypeBo filterOutType;
	private List<RefNotToReactCodeBo> notToReactCodes;

	public RefFilterOutRecordingTypeBo getFilterOutType() {
		return filterOutType;
	}

	public void setFilterOutType(RefFilterOutRecordingTypeBo filterOutType) {
		this.filterOutType = filterOutType;
	}

	public List<RefNotToReactCodeBo> getNotToReactCodes() {
		return notToReactCodes;
	}

	public void setNotToReactCodes(List<RefNotToReactCodeBo> notToReactCodes) {
		this.notToReactCodes = notToReactCodes;
	}

}