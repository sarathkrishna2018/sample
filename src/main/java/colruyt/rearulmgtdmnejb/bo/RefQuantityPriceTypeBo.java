package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @version 1.0
 * @created 29-nov-2018 13:22:07
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefQuantityPriceTypeBo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int quantityTypeId;
	private List<RefLangBo> codeLang;
	private String description;

	public int getQuantityTypeId() {
		return quantityTypeId;
	}

	public void setQuantityTypeId(int quantityTypeId) {
		this.quantityTypeId = quantityTypeId;
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