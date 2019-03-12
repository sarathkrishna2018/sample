package colruyt.rearulmgtdmnejb.util;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

public class DBUtil {
	private static String schemaName;

	private static String getSchemaName(EntityManager entityManager) {
		if (StringUtils.isBlank(schemaName)) {
			schemaName = (String) entityManager.getProperties().get("openjpa.jdbc.Schema");
			if (StringUtils.isBlank(schemaName)) {
				throw new RuntimeException("Property 'openjpa.jdbc.Schema' is not configured on the entitymanager");
			}
		}
		return schemaName;
	}
	public static String updateQueryWithSchemaName(String query,EntityManager entityManager) {
		String schemaName = getSchemaName(entityManager);
		return query.replaceAll("SCHEMA", schemaName);
	}

}
