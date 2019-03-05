package colruyt.rearulmgtdmnejb.enums;

public enum FilterOutRecordingType {
	PRICE_RECORDING(1), 
	FILTER_AND_CLEAR_OUT(2);
	
	private int id;

	private FilterOutRecordingType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	

}
