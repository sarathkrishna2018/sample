package colruyt.rearulmgtdmnejb.enums;

public enum FilterOutRecordingType {
	PRICE_RECORDING(1L), 
	FILTER_AND_CLEAR_OUT(2L);
	
	private Long id;

	private FilterOutRecordingType(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	

}
