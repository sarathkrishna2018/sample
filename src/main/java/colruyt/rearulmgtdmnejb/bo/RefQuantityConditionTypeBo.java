package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @version 1.0
 * @created 28-nov-2018 9:01:50
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefQuantityConditionTypeBo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private long codeTypeId;
	private List<RefLangBo> codeLang;
	private String description;
	
	public long getCodeTypeId() {
		return codeTypeId;
	}

	public void setCodeTypeId(long codeTypeId) {
		this.codeTypeId = codeTypeId;
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