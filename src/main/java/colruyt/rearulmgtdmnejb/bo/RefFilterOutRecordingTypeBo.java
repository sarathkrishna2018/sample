package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefFilterOutRecordingTypeBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int filterOutTypeId;
	private List<RefLangBo> codeLang;
	private String description;

	public int getFilterOutTypeId() {
		return filterOutTypeId;
	}

	public void setFilterOutTypeId(int filterOutTypeId) {
		this.filterOutTypeId = filterOutTypeId;
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