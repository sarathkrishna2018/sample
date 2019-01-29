package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @version 1.0
 * @created 29-nov-2018 13:21:52
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefFilterOutRecordingTypeBo implements Serializable{


	private static final long serialVersionUID = 1L;
	private Long filterOutTypeId;
	private List<RefLangBo> codeLang;
	private String description;

	public Long getFilterOutTypeId() {
		return filterOutTypeId;
	}

	public void setFilterOutTypeId(Long filterOutTypeId) {
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