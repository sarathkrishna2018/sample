package colruyt.rearulmgtdmnejb.enums;

public enum QuantityType {
	P1_QUANTITY(1),
	P2_NQTY(2),
	P2_BQTY(3);
	
	private int quantityTypeId;
	public int getQuantityTypeId() {
		return quantityTypeId;
	}	
	private QuantityType(int qtyTypeId) {
		this.quantityTypeId = qtyTypeId;
		
	}
}
