package colruyt.rearulmgtdmnejb.enums;

public enum SourceType {
	ONLINE(1L,"Online", "Online"),
	OFFLINE(2L,"Offline", "Offline"),
	FOLDER(3L, "Folder", "Folder"),
	EXTERNAL(4L,"External", "External"),
	ALL(5L,"All", "All");
	
	private final Long sourceTypeId;
	private final String sourceTypeName;
	private final String description;
	
	private SourceType(Long sourceTypeId, String sourceTypeName, String description) {
		this.sourceTypeId = sourceTypeId;
		this.sourceTypeName = sourceTypeName;
		this.description = description;
	}
	public Long getSourceTypeId() {
		return sourceTypeId;
	}
	public String getSourceTypeName() {
		return sourceTypeName;
	}
	public String getDescription() {
		return description;
	}
	public static SourceType forValue(Long sourceTypeId) {
		for (SourceType sourceType : SourceType.values()){
			if(sourceType.getSourceTypeId() == (sourceTypeId)){
				return sourceType;
			}
		}
		throw new UnsupportedOperationException("Could not find ActionType with actionTypeId : " + sourceTypeId);
	}  
	
}
