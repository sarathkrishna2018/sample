package colruyt.rearulmgtdmnejb.enums;

public enum QuantityCondition {
	NQTY_GREATER_THAN_EQUAL_TO_BQTY(1L),
	NQTY_GREATER_THAN_BQTY(2L),
	NQTY_LESS_THAN_EQUAL_TO_BQTY(3L),
	NQTY_LESS_THAN_BQTY(4L),
	NQTY_GREATER_THAN_EQUAL_TO_BQTY_AND_NOT_1(5L),
	NQTY_LESS_THAN_EQUAL_TO_BQTY_AND_NOT_1(6L);
	
	private Long id;
	
	
	
	private QuantityCondition(Long id) {
		this.id = id;
		
	}
	public Long getId() {
		return id;
	}
	


	
	
}
