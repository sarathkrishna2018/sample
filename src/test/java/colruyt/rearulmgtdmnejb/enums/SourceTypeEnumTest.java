package colruyt.rearulmgtdmnejb.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SourceTypeEnumTest {
	@Test
	public void sourceTypeEnumTest() {
		SourceTypeEnum[] sourceTypeEnums = SourceTypeEnum.values();
		assertEquals(5,sourceTypeEnums.length);
	}

	@Test
	public void sourceTypeEnumTest2() {
		SourceTypeEnum ONLINE = SourceTypeEnum.ONLINE;
		assertEquals(ONLINE,SourceTypeEnum.valueOf("ONLINE"));
	}
}
