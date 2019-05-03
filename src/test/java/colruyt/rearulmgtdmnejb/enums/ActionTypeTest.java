package colruyt.rearulmgtdmnejb.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ActionTypeTest {
	@Test
	public void actionTypeEnumTest(){
	      ActionType[] actionTypeEnums = ActionType.values();
	       assertEquals(16,actionTypeEnums.length);
	   }
	
	@Test
	public void actionTypeEnumTest2(){
		ActionType PRICE_PROMO = ActionType.PRICE_PROMO;
	       assertEquals(PRICE_PROMO,ActionType.valueOf("PRICE_PROMO") );
	   }
}
