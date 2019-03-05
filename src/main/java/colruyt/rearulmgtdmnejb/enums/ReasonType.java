package colruyt.rearulmgtdmnejb.enums;

public enum ReasonType {
	TEMP_NOT_ORDERABLE(1),
	RESELLER(2),
	OUTLET_ARTICLE(3),
	NO_LONGER_AVAILABLE(4),
	SECOND_HAND(5),
	SOLD_OUT_ONLINE(6);
		
	private int reasonID;
	public int getReasonID() {
		return reasonID;
	}
	
	private ReasonType(int reasonID) {
		this.reasonID = reasonID;
		
	}
}
