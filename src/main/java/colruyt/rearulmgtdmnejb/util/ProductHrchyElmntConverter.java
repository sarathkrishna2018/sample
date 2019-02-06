package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;


public class ProductHrchyElmntConverter implements Serializable {
	private static final long serialVersionUID = 1L;
	
		
	public List<PriceProductHierarchyElement> convertProductHierarchyElement(List<ProductHierarchyElementBo> productHierarchyElementBos, String logonId) {
		List<PriceProductHierarchyElement> reaPpdHchyElmnts=Lists.newArrayList();
		for(ProductHierarchyElementBo productHierarchyElementBo:productHierarchyElementBos){
			PriceProductHierarchyElement reaPpdHchyElmnt=new PriceProductHierarchyElement();
			if(productHierarchyElementBo.getId() != null) {
				reaPpdHchyElmnt.setPpdHchyElmntId(productHierarchyElementBo.getId());
			}
			reaPpdHchyElmnt.setPpdHchyValue(productHierarchyElementBo.getPriceProductHierarchyValue());
			reaPpdHchyElmnt.setPpdHchyTypeId(productHierarchyElementBo.getPriceProductHierarchyTypeId());
			reaPpdHchyElmnt.setCreatedBy(logonId);
			reaPpdHchyElmnts.add(reaPpdHchyElmnt);
		}
		return reaPpdHchyElmnts;
	}
	
	public GeneralRuleBo convertAssortment(GeneralRuleBo ruleBo, ReactionRule rule) {
		for(PriceProductHierarchySet set : rule.getReaPpdHchysets()){
			ruleBo.setAssortmentName(set.getAssortmentName());
			ruleBo.setCheapBrand(set.getCheapBrandYn());
			ruleBo.setNationalBrand(set.getNatBrandYn());
			ruleBo.setOwnBrand(set.getOwnBrandYn());
			ruleBo.setProductHierarchySetId(set.getPpdHchysetId());
			List<ProductHierarchyElementBo> productHierarchyElementBolist=convertProductHrchyElementBo(set.getPriceProductHierarchyElements());
			ruleBo.setPriceProductHierarchySet(productHierarchyElementBolist);
			
		}
		return ruleBo;
	}
	private List<ProductHierarchyElementBo> convertProductHrchyElementBo(
			List<PriceProductHierarchyElement> priceProductHierarchyElements) {
		 List<ProductHierarchyElementBo> productHierarchyElementBolist=Lists.newArrayList();
		 for(PriceProductHierarchyElement ppHchyElement:priceProductHierarchyElements){
			 ProductHierarchyElementBo productHierarchyElementBo=new ProductHierarchyElementBo();
			 productHierarchyElementBo.setId(ppHchyElement.getPpdHchyElmntId());
			 productHierarchyElementBo.setPriceProductHierarchyTypeId(ppHchyElement.getPpdHchyTypeId());
			 productHierarchyElementBo.setPriceProductHierarchyValue(ppHchyElement.getPpdHchyValue());
			 productHierarchyElementBolist.add(productHierarchyElementBo);
		 }
		
		return productHierarchyElementBolist;
	}
	

	
}
