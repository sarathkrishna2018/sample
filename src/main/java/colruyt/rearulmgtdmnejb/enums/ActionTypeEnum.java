package colruyt.rearulmgtdmnejb.enums;

import java.util.ArrayList;
import java.util.List;

import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;

public enum ActionTypeEnum {
		PRICE_PROMO(1L,"Price Promo", 1L),
		DISCOUNT_IN_PERCENT(2L,"Discount %",  2L),
		DISCOUNT_IN_EURO(3L,"Discount Euro", 3L),
		X_PLUS_Y(4L,"X+Y", 4L),
		XNTH_FOR_Y_PERCENT_OFF(5L," Xnth for Y% off",  5L),
		STAMPS(6L, "Stamps",  6L),
		POINTS(7L,"Points",  7L),
		GADGET_OR_PREMIUM(8L,"Gadget/Premium", 8L),
		CASHBACK(9L,"Cashback",  9L),
		OTHER_FREE_PRODUCTS(10L,"Other Free Products", 10L),
		BON_ON_PACK(11L,"Bon on pack", 11L),	
		SECOND_FOR_FIFTY_PERCENT(12L,"2nd for 50% off",  12L),
		ONE_PLUS_ONE(13L,"1+1", 13L),
		ALL(14L, "All", 14L),
		NONE(15L, "None", 15L);

	private long actionTypeId;
	private String actionTypeValue;
	private long sequence;
		
		 
		private ActionTypeEnum(long actionTypeId, String actionTypeValue, long sequence) {
		this.actionTypeId = actionTypeId;
		this.actionTypeValue = actionTypeValue;
		this.sequence = sequence;
	}


		public long getActionTypeId() {
		return actionTypeId;
	}

	public String getActionTypeValue() {
		return actionTypeValue;
	}

	public long getSequence() {
		return sequence;
	}



}
