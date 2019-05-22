package colruyt.rearulmgtdmnejb.enums;

public enum SourceType {
	OFFLINE(1,"Offline", "Offline"),
	ONLINE(3,"Online", "Online"),
	EXTERNAL(4,"External", "External"),
	FOLDER(5, "Folder", "Folder"),
	CUSTOMER(6,"Customer", "Customer"),
	ALL(40,"All", "All");
	
	private final int sourceTypeId;
	private final String sourceTypeName;
	private final String description;
	
	private SourceType(int sourceTypeId, String sourceTypeName, String description) {
		this.sourceTypeId = sourceTypeId;
		this.sourceTypeName = sourceTypeName;
		this.description = description;
	}
	public int getSourceTypeId() {
		return sourceTypeId;
	}
	public String getSourceTypeName() {
		return sourceTypeName;
	}
	public String getDescription() {
		return description;
	}
	public static SourceType forValue(long sourceTypeId) {
		for (SourceType sourceType : SourceType.values()){
			if(sourceType.getSourceTypeId() == (sourceTypeId)){
				return sourceType;
			}
		}
		throw new UnsupportedOperationException("Could not find ActionType with actionTypeId : " + sourceTypeId);
	}  
	
}
