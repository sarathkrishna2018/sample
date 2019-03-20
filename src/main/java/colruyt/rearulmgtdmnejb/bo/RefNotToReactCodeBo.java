package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefNotToReactCodeBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int notToReactCodeTypeId;
	private List<RefLangBo> codeLang;
	private String description;

	public int getNotToReactCodeTypeId() {
		return notToReactCodeTypeId;
	}

	public void setNotToReactCodeTypeId(int notToReactCodeTypeId) {
		this.notToReactCodeTypeId = notToReactCodeTypeId;
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