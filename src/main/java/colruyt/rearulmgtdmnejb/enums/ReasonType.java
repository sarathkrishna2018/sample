package colruyt.rearulmgtdmnejb.enums;

public enum ReasonType {
	TEMP_NOT_ORDERABLE_EN(1L, "EN", "Temporarily not orderable"),
	RESELLER_EN(2L, "EN", "Reseller"),
	OUTLET_ARTICLE_EN(3L, "EN", "Outlet article"),
	NO_LONGER_AVAILABLE_EN(4L, "EN", "No longer available"),
	SECOND_HAND_EN(5L, "EN", "Second hand"),
	SOLD_OUT_ONLINE_EN(6L, "EN", "Sold out online");
		
	private long reasonID;
	private String isoLangCode;
	private String reasonDescription;
	public long getReasonID() {
		return reasonID;
	}
	
	public String getIsoLangCode() {
		return isoLangCode;
	}
	
	public String getReasonDescription() {
		return reasonDescription;
	}	
	
	private ReasonType(long reasonID, String isoLangCode, String reasonDescription) {
		this.reasonID = reasonID;
		this.isoLangCode = isoLangCode;
		this.reasonDescription = reasonDescription;
	}
}
