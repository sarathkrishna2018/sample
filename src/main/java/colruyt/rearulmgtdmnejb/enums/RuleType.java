package colruyt.rearulmgtdmnejb.enums;

import java.util.Set;

import com.google.common.collect.Sets;

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
	
	public static Set<String> ruleTypeNames(){
		Set<String> ruleTypeNames = Sets.newHashSet();
		for(RuleType ruleTypeEnum: RuleType.values()){
			ruleTypeNames.add(ruleTypeEnum.getRuleTypeName());
		}
		return ruleTypeNames;
	}
	
	public static RuleType forValue(Integer ruleTypeID) {
		if(ruleTypeID!=null){
			for (RuleType ruleType : RuleType.values()) {
				if (ruleType.ruleTypeID == ruleTypeID.intValue()) {
					return ruleType;
				}
			}
		}
		throw new UnsupportedOperationException("Could not find ReationRuleType with ruleTypeID : " + ruleTypeID);
	}
	
	public static RuleType forValue(String ruleTypeName) {
		if(ruleTypeName!=null){
			for (RuleType ruleType : RuleType.values()) {
				if (ruleType.ruleTypeName.equalsIgnoreCase(ruleTypeName)) {
					return ruleType;
				}
			}
		}
		throw new UnsupportedOperationException("Could not find ReationRuleType with ruleTypeName : " + ruleTypeName);
	}
}
