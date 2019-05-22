package colruyt.rearulmgtdmnejb.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SourceTypeTest {
	@Test
	public void sourceTypeEnumTest() {
		SourceType[] sourceTypeEnums = SourceType.values();
		assertEquals(6,sourceTypeEnums.length);
	}

	@Test
	public void sourceTypeEnumTest2() {
		SourceType ONLINE = SourceType.ONLINE;
		assertEquals(ONLINE,SourceType.valueOf("ONLINE"));
	}
}
