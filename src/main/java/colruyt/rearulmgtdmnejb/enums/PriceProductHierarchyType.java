package colruyt.rearulmgtdmnejb.enums;

public enum PriceProductHierarchyType {
	ALL(1), MAIN_CATEGORY(2), PRODUCT_CATEGORY(3), PRODUCT_GROUP(4), PRODUCT_SEGMENT(5), PRICE_PRODUCT(6);
	private final int typeId;

	public int getTypeId() {
		return typeId;
	}

	public static PriceProductHierarchyType forValue(int type) {
		for (PriceProductHierarchyType hierarchyType : PriceProductHierarchyType.values()) {
			if (hierarchyType.typeId == type) {
				return hierarchyType;
			}
		}
		throw new UnsupportedOperationException("Could not find a price product type type with id : " + type);
	}

	public static PriceProductHierarchyType getChildType(int type) {
		return forValue(type + 1);
	}

	private PriceProductHierarchyType(int typeId) {
		this.typeId = typeId;
	}
}
