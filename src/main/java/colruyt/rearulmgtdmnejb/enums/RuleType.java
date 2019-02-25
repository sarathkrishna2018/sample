package colruyt.rearulmgtdmnejb.enums;

public enum RuleType {
	REACTING(1L, "Reacting"),
	QUANTITY(2L, "Quantity"),
	FILTERING(3L, "Filtering"),
	RECORD_NOT_FOUND(4L,  "Record Not Found"),
	PROPOSE_NOT_REACT(5L,  "Propose Not To React"),
	REACTION_PERIOD(6L, "Reaction Period");
	
	private long ruleTypeID;
	private String ruleTypeName;
	
	public long getRuleTypeID() {
		return ruleTypeID;
	}
	
	public String getRuleTypeName() {
		return ruleTypeName;
	}
	
	private RuleType(long ruleTypeID, String ruleType) {
		this.ruleTypeID = ruleTypeID;	
		this.ruleTypeName = ruleType;
	}
}
