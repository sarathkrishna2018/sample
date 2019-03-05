package colruyt.rearulmgtdmnejb.enums;

public enum SourceType {
	ONLINE(1,"Online", "Online"),
	OFFLINE(2,"Offline", "Offline"),
	FOLDER(3, "Folder", "Folder"),
	EXTERNAL(4,"External", "External"),
	ALL(5,"All", "All");
	
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
