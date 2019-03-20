package colruyt.priceproduct.bo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefIsoLangBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String isoLangCode;

	public String getIsoLangCode() {
		return isoLangCode;
	}

	public void setIsoLangCode(String isoLangCode) {
		this.isoLangCode = isoLangCode;
	}

}
