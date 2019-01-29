package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

public class RefContentUnitBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String contentUnitCode;
    private List<LangPriceBO> contentUnitLangs;

    public String getContentUnitCode() {
        return contentUnitCode;
    }

    public void setContentUnitCode(String contentUnitCode) {
        this.contentUnitCode = contentUnitCode;
    }

    public List<LangPriceBO> getContentUnitLangs() {
        return contentUnitLangs;
    }

    public void setContentUnitLangs(List<LangPriceBO> contentUnitLangs) {
        this.contentUnitLangs = contentUnitLangs;
    }
}
