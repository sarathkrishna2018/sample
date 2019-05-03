package colruyt.rearulmgtdmnejb.enums;

public enum ActionType {
		PRICE_PROMO(1,"Price Promo", 1L),
		DISCOUNT_IN_PERCENT(2,"Discount %",  2L),
		DISCOUNT_IN_EURO(3,"Discount Euro", 3L),
		X_PLUS_Y(4,"X+Y", 4L),
		XNTH_FOR_Y_PERCENT_OFF(5," Xnth for Y% off",  5L),
		STAMPS(6, "Stamps",  6L),
		POINTS(7,"Points",  7L),
		GADGET_OR_PREMIUM(8,"Gadget/Premium", 8L),
		CASHBACK(9,"Cashback",  9L),
		OTHER_FREE_PRODUCTS(10,"Other Free Products", 10L),
		BON_ON_PACK(11,"Bon on pack", 11L),	
		SECOND_FOR_FIFTY_PERCENT(12,"2nd for 50% off",  12L),
		ONE_PLUS_ONE(13,"1+1", 13L),
		FREE_CONTENT(14,"Free Content",14L),
		ALL(15, "All", 15L),
		NONE(16, "None", 16L);

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
