package colruyt.rearulmgtdmnejb.enums;

import java.util.ArrayList;
import java.util.List;

import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;

public enum SourceTypeEnum {
	ONLINE(1L,"Online", "Online"),
	OFFLINE(2L,"Offline", "Offline"),
	FOLDER(3L, "Folder", "Folder"),
	EXTERNAL(4L,"External", "External"),
	ALL(5L,"All", "All");
	
	private final Long sourceTypeId;
	private final String sourceTypeName;
	private final String description;
	
	private SourceTypeEnum(Long sourceTypeId, String sourceTypeName, String description) {
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
	
}
