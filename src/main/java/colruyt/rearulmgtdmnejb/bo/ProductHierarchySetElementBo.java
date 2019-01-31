package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

public class ProductHierarchySetElementBo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private long productHierarchySetId;
	private Long productHierarchyElementId;
	public long getProductHierarchySetId() {
		return productHierarchySetId;
	}
	public void setProductHierarchySetId(long productHierarchySetId) {
		this.productHierarchySetId = productHierarchySetId;
	}
	public Long getProductHierarchyElementId() {
		return productHierarchyElementId;
	}
	public void setproductHierarchyElementId(Long productHierarchyElementId) {
		this.productHierarchyElementId = productHierarchyElementId;
	}
	

}
