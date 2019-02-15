package colruyt.rearulmgtdmnejb.enums;

public enum RuleType {
	REACTING_EN(1L, "EN", "Reacting"),
	QUANTITY_EN(2L, "EN", "Quantity"),
	FILTERING_EN(3L, "EN", "Filtering"),
	RECORD_NOT_FOUND_EN(4L, "EN", "Record Not Found"),
	PROPOSE_NOT_REACT_EN(5L, "EN", "Propose Not To React"),
	REACTION_PERIOD_EN(6L, "EN", "Reaction Period");
	
	private long ruleTypeID;
	private String isoLangCode;
	private String ruleTypeDescription;
	
	public long getRuleTypeID() {
		return ruleTypeID;
	}
	
	public String getIsoLangCode() {
		return isoLangCode;
	}
	
	public String getRuleTypeDescription() {
		return ruleTypeDescription;
	}
	
	private RuleType(long ruleTypeID, String isoLangCode, String ruleTypeDescription) {
		this.ruleTypeID = ruleTypeID;
		this.isoLangCode = isoLangCode;
		this.ruleTypeDescription = ruleTypeDescription;
	}
}
