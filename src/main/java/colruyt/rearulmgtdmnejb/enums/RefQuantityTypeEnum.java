package colruyt.rearulmgtdmnejb.enums;

public enum RefQuantityTypeEnum {
	P1_QUANTITY_EN(1L, "EN", "P1 Quantity 1"),
	P2_NQTY_EN(2L, "EN", "P2 NQTY"),
	P2_BQTY_EN(3L, "EN", "P2 BQTY");
	
	private long qtyTypeId;
	private String isoLangCode;
	private String qtyTypeDescription;
	
	public long getQtyTypeId() {
		return qtyTypeId;
	}	
	public String getIsoLangCode() {
		return isoLangCode;
	}	
	public String getQtyTypeDescription() {
		return qtyTypeDescription;
	}
	
	private RefQuantityTypeEnum(long qtyTypeId, String isoLangCode, String qtyTypeDescription) {
		this.qtyTypeId = qtyTypeId;
		this.isoLangCode = isoLangCode;
		this.qtyTypeDescription = qtyTypeDescription;
	}
}
