package colruyt.rearulmgtdmnejb.enums;

public enum RuleType {
	REACTING(1, "Reacting"),
	QUANTITY(2, "Quantity"),
	FILTERING(3, "Filtering"),
	RECORD_NOT_FOUND(4,  "Record Not Found"),
	PROPOSE_NOT_REACT(5,  "Propose Not To React"),
	REACTION_PERIOD(6, "Reaction Period");
	
	private int ruleTypeID;
	private String ruleTypeName;
	
	public int getRuleTypeID() {
		return ruleTypeID;
	}
	
	public String getRuleTypeName() {
		return ruleTypeName;
	}
	
	private RuleType(int ruleTypeID, String ruleType) {
		this.ruleTypeID = ruleTypeID;	
		this.ruleTypeName = ruleType;
	}
}
