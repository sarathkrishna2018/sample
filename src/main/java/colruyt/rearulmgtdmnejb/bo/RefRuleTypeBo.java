package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @version 1.0
 * @created 29-nov-2018 13:22:13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefRuleTypeBo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long ruleTypeId;
	private List<RefLangBo> codeLang;
	private String description;

	public long getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(long ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public List<RefLangBo> getCodeLang() {
		return codeLang;
	}

	public void setCodeLang(List<RefLangBo> codeLang) {
		this.codeLang = codeLang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}