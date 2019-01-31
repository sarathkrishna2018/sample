package colruyt.priceproduct.bo;

import java.io.Serializable;

public class LangPriceBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String description;
    private String isoLangCode;
    private String name;

    public String getIsoLangCode() {
        return isoLangCode;
    }

    public void setIsoLangCode(String isoLangCode) {
        this.isoLangCode = isoLangCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
