package colruyt.rearulmgtdmnejb.enums;

public enum QuantityCondition {
	NQTY_GREATER_THAN_EQUAL_TO_BQTY_EN(1L,"EN", "NQTY >= BQTY"),
	NQTY_GREATER_THAN_BQTY_EN(2L,"EN","NQTY > BQTY"),
	NQTY_LESS_THAN_EQUAL_TO_BQTY_EN(3L, "EN","NQTY <= BQTY"),
	NQTY_LESS_THAN_BQTY_EN(4L,"EN","NQTY < BQTY"),
	NQTY_GREATER_THAN_EQUAL_TO_BQTY_AND_NOT_1(5L, "EN", "NQTY >= BQTY & NQTY != 1"),
	NQTY_LESS_THAN_EQUAL_TO_BQTY_AND_NOT_1(6L,"EN","NQTY <= BQTY & NQTY != 1");
	
	private Long id;
	private String langCode;
	private String description;
	
	
	private QuantityCondition(Long id, String langCode, String description) {
		this.id = id;
		this.langCode = langCode;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public String getLangCode() {
		return langCode;
	}
	
	public String getDescription() {
		return description;
	}


	
	
}
