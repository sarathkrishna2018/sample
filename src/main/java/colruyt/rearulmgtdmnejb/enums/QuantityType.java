package colruyt.rearulmgtdmnejb.enums;

public enum QuantityType {
	P1_QUANTITY(1L),
	P2_NQTY(2L),
	P2_BQTY(3L);
	
	private long qtyTypeId;
	public long getQtyTypeId() {
		return qtyTypeId;
	}	
	private QuantityType(long qtyTypeId) {
		this.qtyTypeId = qtyTypeId;
		
	}
}
