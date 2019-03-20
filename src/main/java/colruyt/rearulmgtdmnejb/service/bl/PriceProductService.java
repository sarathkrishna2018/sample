package colruyt.rearulmgtdmnejb.service.bl;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import colruyt.priceproduct.bo.PriceProductHierarchyResponseBo;
import colruyt.priceproduct.bo.PriceProductHierarchyBo;
import colruyt.rearulmgtdmnejb.exception.PriceProductExternalServiceException;
import colruyt.rearulmgtdmnejb.exception.PriceProductServiceDownException;
import colruyt.rearulmgtdmnejb.exception.RRMDomainException;
import colruyt.rearulmgtdmnejb.exception.ServiceDownException;
import colruyt.rearulmgtdmnejb.util.ExternalClientService;
import colruyt.rearulmgtdmnejb.util.MockData;
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

	@EJB
	private PriceProductConverter priceProductConverter;

	private static final Logger logger = LoggerFactory.getLogger(PriceProductService.class);

	public Set<String> externalHierarchyValues()
			throws PriceProductExternalServiceException, PriceProductServiceDownException, IOException, ParseException {
		PriceProductHierarchyResponseBo heriarchyBo = findHierarchyValues();
		List<PriceProductHierarchyBo> priceProductHierarchyBoList = priceProductConverter
				.convertProducts(heriarchyBo.getResult(), ReactionRuleDmnConstants.LANG_CODE_NL);
		List<PriceProductHierarchyBo> productList = getPriceProducts();
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

	public List<PriceProductHierarchyBo> getPriceProducts() throws IOException, ParseException {
		String fileName = "products";
		JSONArray jsonobj = MockData.getInstance().getJsonValues(fileName);
		List<PriceProductHierarchyBo> priceProductHierarchyBos = Lists.newArrayList();
		for (Object jsonobj1 : jsonobj) {
			PriceProductHierarchyBo priceProductHierarchyBo = (new Gson().fromJson(jsonobj1.toString(),
					PriceProductHierarchyBo.class));
			if (priceProductHierarchyBo.getValue() != null) {
				priceProductHierarchyBos.add(priceProductHierarchyBo);
			}
		}
		return priceProductHierarchyBos;

	}

}