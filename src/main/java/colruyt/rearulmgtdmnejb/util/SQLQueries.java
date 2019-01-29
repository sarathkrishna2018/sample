package colruyt.rearulmgtdmnejb.util;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

public class SQLQueries {
	
	private static String schemaName;
	
	public static final String XPSRuleBoMapping = "XPSRuleBoMapping";
	
	public static final String XPSRuleSetBoMapping = "XPSRuleSetBoMapping";
	
	public static final String  FIND_ALL_LOGICALLY_DELETE_RULESET = "SELECT ruleSet.REA_RULESET_ID, ruleSet.RULETYPE_ID from"
				+ " SCHEMA.REA_RULESET ruleSet LEFT JOIN SCHEMA.SOI_PPT_RULE soippt ON ruleSet.REA_RULESET_ID=soippt.REACT_RULESET_ID "
				+ " LEFT JOIN SCHEMA.SOI_CG_CHN_RULE soicg ON ruleSet.REA_RULESET_ID=soicg.REACT_RULESET_ID"
				+ " where ruleSet.DATE_LOGICALLY_DELETED IS NOT NULL AND ruleSet.DATE_LOGICALLY_DELETED < (?1) AND soippt.REACT_RULESET_ID IS NULL AND soicg.REACT_RULESET_ID IS NULL";
	
	public static final String  FIND_ALL_LOGICALLY_DELETE_RULE = "SELECT rule.REA_RULE_ID, ruleSet.RULETYPE_ID from SCHEMA.REA_RULE rule "
			+ "INNER JOIN SCHEMA.REA_RULESET ruleSet ON rule.REA_RULESET_ID =  ruleSet.REA_RULESET_ID where "
			+ "rule.DATE_LOGICALLY_DELETED IS NOT NULL and rule.DATE_LOGICALLY_DELETED < (?1)"; 

	public static final String  FIND_ALL_EXPIRED_RULE = "SELECT rule.REA_RULE_ID, ruleSet.RULETYPE_ID from SCHEMA.REA_RULE rule "
			+ "INNER JOIN SCHEMA.REA_RULESET ruleSet ON rule.REA_RULESET_ID =  ruleSet.REA_RULESET_ID where "
			+ "rule.VALID_UPTO IS NOT NULL and rule.VALID_UPTO < (?1)"; 
	
	public static String getSchemaName(EntityManager entityManager) {
		if (StringUtils.isBlank(schemaName)) {
			schemaName = (String)entityManager.getProperties().get("openjpa.jdbc.Schema");
			if (StringUtils.isBlank(schemaName)) {
				throw new RuntimeException("Property 'openjpa.jdbc.Schema' is not configured on the entitymanager");
			}
		}
		return schemaName;
	}
}
