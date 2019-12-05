package colruyt.rearulmgtdmnejb.enums;

public enum QuantityCondition {
	
	NQTY_GREATER_THAN_EQUAL_TO_BQTY(1),
	NQTY_GREATER_THAN_BQTY(2),
	NQTY_LESS_THAN_EQUAL_TO_BQTY(3),
	NQTY_LESS_THAN_BQTY(4),
	NQTY_GREATER_THAN_ONE(5),
	NQTY_LESS_THAN_EQUAL_TO_ONE(6),
	NQTY_LESS_THAN_EQUAL_TO_BQTY_AND_GREATER_THAN_ONE(7);
	
	private int id;
	
	
	
	private QuantityCondition(int id) {
		this.id = id;
		
	}
	public int getId() {
		return id;
	}
	


	
	
}
