package colruyt.rearulmgtdmnejb.enums;

public enum FilterOutRecordingType {
	PRICE_RECORDING_EN(1L, "EN", "Filter Out" , "Filter out the price Rrecording"), 
	FILTER_AND_CLEAR_OUT_EN(2L, "EN", "Filter And Clear Out", "Filter and Clear out the old calculated reaction prices");
	
	private Long id;
	private String langCode;
	private String name;
	private String description;

	private FilterOutRecordingType(Long id, String langCode, String name, String description) {
		this.id = id;
		this.langCode = langCode;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getLangCode() {
		return langCode;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
