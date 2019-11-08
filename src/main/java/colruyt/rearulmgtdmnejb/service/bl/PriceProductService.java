package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.priceproduct.bo.PriceProductResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import colruyt.priceproduct.bo.PriceProductHierarchyBo;
import colruyt.priceproduct.bo.PriceProductHierarchyResponseBo;
import colruyt.rearulmgtdmnejb.exception.PriceProductExternalServiceException;
import colruyt.rearulmgtdmnejb.exception.PriceProductServiceDownException;
import colruyt.rearulmgtdmnejb.exception.RRMDomainException;
import colruyt.rearulmgtdmnejb.exception.ServiceDownException;
import colruyt.rearulmgtdmnejb.util.ExternalClientService;
import colruyt.rearulmgtdmnejb.util.PriceProductConverter;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

@Stateless
@LocalBean
public class PriceProductService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PriceProductUrlService priceProductUrlService;

	@EJB
	private ExternalClientService externalClientService;

	private static final Logger logger = LoggerFactory.getLogger(PriceProductService.class);

	public Set<String> externalHierarchyValues()
			throws PriceProductExternalServiceException, PriceProductServiceDownException {
		PriceProductHierarchyResponseBo hierarchyBo = findHierarchyValues();
		List<PriceProductHierarchyBo> priceProductHierarchyBoList = PriceProductConverter
				.convertToBo(hierarchyBo.getResult(), ReactionRuleDmnConstants.LANG_CODE_NL);
		List<PriceProductResponseBo> priceProductResponseBos = getPriceProducts();
		List<PriceProductHierarchyBo> productList = PriceProductConverter
				.convertToPriceProductHierarchyBos(priceProductResponseBos);
		return getAllHierarchyValues(priceProductHierarchyBoList, productList);
	}

	public PriceProductHierarchyResponseBo findHierarchyValues()
			throws PriceProductExternalServiceException, PriceProductServiceDownException {
		String url = priceProductUrlService.getAllHierarchyUrl();
		String jsonString;
		try {
			jsonString = externalClientService.callGetService(url);
			Gson gson = externalClientService.getGsonWithDateDeserializer();
			return gson.fromJson(jsonString, PriceProductHierarchyResponseBo.class);

		} catch (RRMDomainException e) {
			logger.info("Domain Exception", e);
			throw new PriceProductExternalServiceException(e);
		} catch (ServiceDownException e) {
			logger.info("Service down", e);
			throw new PriceProductServiceDownException(e);
		}

	}

	private Set<String> getAllHierarchyValues(List<PriceProductHierarchyBo> hierarchyValues,
			List<PriceProductHierarchyBo> productValues) {
		Set<String> allHierarchyValues = new HashSet<>();
		for (PriceProductHierarchyBo productHierarchyBo : hierarchyValues) {
			allHierarchyValues.add(productHierarchyBo.getValue());
			List<PriceProductHierarchyBo> productcatList = productHierarchyBo.getChildHierarchy();
			for (PriceProductHierarchyBo productcat : productcatList) {
				allHierarchyValues.add(productcat.getValue());
				List<PriceProductHierarchyBo> productGroupList = productcat.getChildHierarchy();
				for (PriceProductHierarchyBo productGroup : productGroupList) {
					allHierarchyValues.add(productGroup.getValue());
					List<PriceProductHierarchyBo> segmentList = productGroup.getChildHierarchy();
					for (PriceProductHierarchyBo segment : segmentList) {
						allHierarchyValues.add(segment.getValue());
					}
				}
			}
		}
		for (PriceProductHierarchyBo productBo : productValues) {
			allHierarchyValues.add(productBo.getValue());
		}
		return allHierarchyValues;
	}


	public List<PriceProductResponseBo> getPriceProducts() throws PriceProductExternalServiceException, PriceProductServiceDownException {

		String url = priceProductUrlService.getPriceProductURL();
		String jsonString;
		try {
			jsonString = externalClientService.callGetService(url);
			Gson gson = externalClientService.getGsonWithDateDeserializer();
            PriceProductResponseBo[] priceProductBOArray = gson.fromJson(jsonString, PriceProductResponseBo[].class);
            return Arrays.asList(priceProductBOArray);
		} catch (RRMDomainException e) {
			logger.info("Domain Exception", e);
			throw new PriceProductExternalServiceException(e);
		} catch (ServiceDownException e) {
			logger.info("Service down", e);
			throw new PriceProductServiceDownException(e);
		}
	}

}