package colruyt.rearulmgtdmnejb.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ActionTypeEnumTest {
	@Test
	public void actionTypeEnumTest(){
	      ActionTypeEnum[] actionTypeEnums = ActionTypeEnum.values();
	       assertEquals(15,actionTypeEnums.length);
	   }
	
	@Test
	public void actionTypeEnumTest2(){
		ActionTypeEnum PRICE_PROMO = ActionTypeEnum.PRICE_PROMO;
	       assertEquals(PRICE_PROMO,ActionTypeEnum.valueOf("PRICE_PROMO") );
	   }
}
