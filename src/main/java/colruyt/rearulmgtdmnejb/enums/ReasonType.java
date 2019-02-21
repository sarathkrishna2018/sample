package colruyt.rearulmgtdmnejb.enums;

public enum ReasonType {
	TEMP_NOT_ORDERABLE(1L),
	RESELLER(2L),
	OUTLET_ARTICLE(3L),
	NO_LONGER_AVAILABLE(4L),
	SECOND_HAND(5L),
	SOLD_OUT_ONLINE(6L);
		
	private long reasonID;
	public long getReasonID() {
		return reasonID;
	}
	
	private ReasonType(long reasonID) {
		this.reasonID = reasonID;
		
	}
}
