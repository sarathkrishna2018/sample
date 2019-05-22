package colruyt.rearulmgtdmnejb.enums;

public enum ActionType {
		PRICE_PROMO(1,"Price Promo", 1L),
		DISCOUNT_IN_PERCENT(3,"Discount %",  2L),
		DISCOUNT_IN_EURO(2,"Discount Euro", 3L),
		X_PLUS_Y(11,"X+Y", 4L),
		XNTH_FOR_Y_PERCENT_OFF(5," Xnth for Y% off",  5L),
		STAMPS(7, "Stamps",  6L),
		POINTS(6,"Points",  7L),
		GADGET_OR_PREMIUM(12,"Gadget/Premium", 8L),
		CASHBACK(14,"Cashback",  9L),
		OTHER_FREE_PRODUCTS(13,"Other Free Products", 10L),
		BON_ON_PACK(15,"Bon on pack", 11L),	
		ALL(40, "All", 14L),
		NONE(50, "None", 15L),
		FREE_CONTENT(16,"Free Content",16L);

	private int actionTypeId;
	private String actionTypeValue;
	private long sequence;
		
		 
	private ActionType(int actionTypeId, String actionTypeValue, long sequence) {
		this.actionTypeId = actionTypeId;
		this.actionTypeValue = actionTypeValue;
		this.sequence = sequence;
	}


	public int getActionTypeId() {
		return actionTypeId;
	}

	public String getActionTypeValue() {
		return actionTypeValue;
	}

	public long getSequence() {
		return sequence;
	}
	public static ActionType forValue(int actionTypeId) {
		for (ActionType actionType : ActionType.values()){
			if(actionType.actionTypeId == (actionTypeId)){
				return actionType;
			}
		}
		throw new UnsupportedOperationException("Could not find ActionType with actionTypeId : " + actionTypeId);
	}  



}
