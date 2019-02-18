package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;
import colruyt.rearulmgtdmnejb.bo.ProductHierarchyElementBo;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchyElement;
import colruyt.rearulmgtdmnejb.entity.PriceProductHierarchySet;
import colruyt.rearulmgtdmnejb.entity.ReactionRule;

@Stateless
@LocalBean
public class ProductHrchyElmntConverter implements Serializable {
	private static final long serialVersionUID = 1L;
	
		
	public List<PriceProductHierarchyElement> convertProductHierarchyElement(List<ProductHierarchyElementBo> productHierarchyElementBos, String logonId) {
		List<PriceProductHierarchyElement> reaPpdHchyElmnts=Lists.newArrayList();
		for(ProductHierarchyElementBo productHierarchyElementBo:productHierarchyElementBos){
			PriceProductHierarchyElement reaPpdHchyElmnt=new PriceProductHierarchyElement();
			if(productHierarchyElementBo.getId() != null) {
				reaPpdHchyElmnt.setProductHierarchyElementId(productHierarchyElementBo.getId());
			}
			reaPpdHchyElmnt.setPpdHchyValue(productHierarchyElementBo.getPriceProductHierarchyValue());
			reaPpdHchyElmnt.setProductHierarchyTypeId(productHierarchyElementBo.getPriceProductHierarchyTypeId());
			reaPpdHchyElmnt.setCreatedBy(logonId);
			reaPpdHchyElmnts.add(reaPpdHchyElmnt);
		}
		return reaPpdHchyElmnts;
	}
	
	public GeneralRuleBo convertAssortment(GeneralRuleBo ruleBo, ReactionRule rule) {
		for(PriceProductHierarchySet set : rule.getPriceProductHierarchySet()){
			ruleBo.setAssortmentName(set.getAssortmentName());
			ruleBo.setCheapBrand(set.getCheapBrand());
			ruleBo.setNationalBrand(set.getNationalBrand());
			ruleBo.setOwnBrand(set.getOwnBrand());
			ruleBo.setProductHierarchySetId(set.getProductHierarchySetId());
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
			 productHierarchyElementBo.setId(ppHchyElement.getProductHierarchyElementId());
			 productHierarchyElementBo.setPriceProductHierarchyTypeId(ppHchyElement.getProductHierarchyTypeId());
			 productHierarchyElementBo.setPriceProductHierarchyValue(ppHchyElement.getProdHrchyValue());
			 productHierarchyElementBolist.add(productHierarchyElementBo);
		 }
		
		return productHierarchyElementBolist;
	}
	

	
}
