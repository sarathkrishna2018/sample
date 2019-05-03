package colruyt.rearulmgtdmnejb.enums;

public enum FilterOutRecordingType {
	PRICE_RECORDING(1), 
	FILTER_PRICE_RECORDING_AND_STOP_REACTING(2);
	
	private int id;

	private FilterOutRecordingType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	

}
