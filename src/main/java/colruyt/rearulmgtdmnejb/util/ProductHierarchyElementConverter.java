package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;

@Stateless
@LocalBean
public class ProductHierarchyElementConverter implements Serializable {
	private static final long serialVersionUID = 1L;

	public List<PriceProductHierarchyElement> convertFromBo(List<ProductHierarchyElementBo> productHierarchyElementBos,
			String logonId) {
		List<PriceProductHierarchyElement> reaPpdHchyElmnts = Lists.newArrayList();
		for (ProductHierarchyElementBo productHierarchyElementBo : productHierarchyElementBos) {
			PriceProductHierarchyElement reaPpdHchyElmnt = new PriceProductHierarchyElement();
			if (productHierarchyElementBo.getId() != null) {
				reaPpdHchyElmnt.setProductHierarchyElementId(productHierarchyElementBo.getId());
			}
			reaPpdHchyElmnt.setPpdHchyValue(productHierarchyElementBo.getPriceProductHierarchyValue());
			reaPpdHchyElmnt.setProductHierarchyTypeId(productHierarchyElementBo.getPriceProductHierarchyTypeId());
			reaPpdHchyElmnt.setCreatedBy(logonId);
			reaPpdHchyElmnts.add(reaPpdHchyElmnt);
		}
		return reaPpdHchyElmnts;
	}

	public List<ProductHierarchyElementBo> convertToBo(
			List<PriceProductHierarchyElement> priceProductHierarchyElements) {
		List<ProductHierarchyElementBo> productHierarchyElementBolist = Lists.newArrayList();
		for (PriceProductHierarchyElement ppHchyElement : priceProductHierarchyElements) {
			ProductHierarchyElementBo productHierarchyElementBo = new ProductHierarchyElementBo();
			productHierarchyElementBo.setId(ppHchyElement.getProductHierarchyElementId());
			productHierarchyElementBo.setPriceProductHierarchyTypeId(ppHchyElement.getProductHierarchyTypeId());
			productHierarchyElementBo.setPriceProductHierarchyValue(ppHchyElement.getProdHrchyValue());
			productHierarchyElementBolist.add(productHierarchyElementBo);
		}

		return productHierarchyElementBolist;
	}

}
