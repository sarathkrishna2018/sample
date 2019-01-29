package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceProductHierarchyBo implements Serializable, Comparable<PriceProductHierarchyBo> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long typeId;
	private List<PriceProductHierarchyBo> childHierarchy;
	private String name;
	private String value;
	private boolean expanded;
	private String parentValue;
	private Long linkedContextHierarchyId;
	private boolean isExcluded;
	private boolean isIncluded;

	public PriceProductHierarchyBo() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return typeId;
	}

	public final void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<PriceProductHierarchyBo> getChildHierarchy() {
		return childHierarchy;
	}

	public void setChildHierarchy(List<PriceProductHierarchyBo> childHierarchy) {
		this.childHierarchy = childHierarchy;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}

	public String getParentValue() {
		return parentValue;
	}

	public final void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}

	@Override
	public int compareTo(PriceProductHierarchyBo o) {

		return name.compareToIgnoreCase(o.getName());
	}

	public Long getLinkedContextHierarchyId() {
		return linkedContextHierarchyId;
	}

	public void setLinkedContextHierarchyId(Long linkedContextHierarchyId) {
		this.linkedContextHierarchyId = linkedContextHierarchyId;
	}

	public PriceProductHierarchyBo(String name, String value, String parentValue, Long typeId) {
		this.setName(name);
		this.setValue(value);
		this.setParentValue(parentValue);
		this.setTypeId(typeId);
	}

	public boolean isExcluded() {
		return isExcluded;
	}

	public void setExcluded(boolean isExcluded) {
		this.isExcluded = isExcluded;
	}

	public boolean isIncluded() {
		return isIncluded;
	}

	public void setIncluded(boolean isIncluded) {
		this.isIncluded = isIncluded;
	}
}