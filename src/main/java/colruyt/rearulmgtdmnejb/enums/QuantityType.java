package colruyt.rearulmgtdmnejb.enums;

public enum QuantityType {
	P1_QUANTITY(1),
	P2_NQTY(2),
	P2_BQTY(3);
	
	private int qtyTypeId;
	public int getQtyTypeId() {
		return qtyTypeId;
	}	
	private QuantityType(int qtyTypeId) {
		this.qtyTypeId = qtyTypeId;
		
	}
}
