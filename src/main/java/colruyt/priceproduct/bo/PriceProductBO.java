package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceProductBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long priceProductId;
    private String codeBasic;
    private String codeBio;
    private String codeWaitreport;
    private Double content;
    private RefContentUnitBO refContentUnit;
    private Date logicallyDeletedDate;
    private List<LangPriceBO> priceProductLangs = new ArrayList<LangPriceBO>();
    private RefBrandLayerBO refBrandLayer;
    private String productStatusCode;
    private ProductStatusBO productStatus;
    private String prdSegmentId;
    public long getPriceProductId() {
        return priceProductId;
    }

    public void setPriceProductId(long priceProductId) {
        this.priceProductId = priceProductId;
    }

    public String getCodeBasic() {
        return codeBasic;
    }

    public void setCodeBasic(String codeBasic) {
        this.codeBasic = codeBasic;
    }

    public String getCodeBio() {
        return codeBio;
    }

    public void setCodeBio(String codeBio) {
        this.codeBio = codeBio;
    }

    public String getCodeWaitreport() {
        return codeWaitreport;
    }

    public void setCodeWaitreport(String codeWaitreport) {
        this.codeWaitreport = codeWaitreport;
    }

    public Double getContent() {
        return content;
    }

    public void setContent(Double content) {
        this.content = content;
    }

    public RefContentUnitBO getRefContentUnit() {
        return refContentUnit;
    }

    public void setRefContentUnit(RefContentUnitBO refContentUnit) {
        this.refContentUnit = refContentUnit;
    }

    public Date getLogicallyDeletedDate() {
        return logicallyDeletedDate;
    }

    public void setLogicallyDeletedDate(Date logicallyDeletedDate) {
        this.logicallyDeletedDate = logicallyDeletedDate;
    }

    public RefBrandLayerBO getRefBrandLayer() {
        return refBrandLayer;
    }

    public void setRefBrandLayer(RefBrandLayerBO refBrandLayer) {
        this.refBrandLayer = refBrandLayer;
    }

    public String getProductStatusCode() {
        return productStatusCode;
    }

    public void setProductStatusCode(String productStatusCode) {
        this.productStatusCode = productStatusCode;
    }

    public ProductStatusBO getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatusBO productStatus) {
        this.productStatus = productStatus;
    }

    public String getPrdSegmentId() {
        return prdSegmentId;
    }

    public void setPrdSegmentId(String prdSegmentId) {
        this.prdSegmentId = prdSegmentId;
    }

    public List<LangPriceBO> getPriceProductLangs() {
        return priceProductLangs;
    }

    public void setPriceProductLangs(List<LangPriceBO> priceProductLangs) {
        this.priceProductLangs = priceProductLangs;
    }

}
