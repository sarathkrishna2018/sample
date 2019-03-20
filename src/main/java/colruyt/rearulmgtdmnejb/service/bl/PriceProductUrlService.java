package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.coreutillib.security.ass.AssException;
import colruyt.coreutillib.security.ass.AssToolUtil;
import colruyt.rearulmgtdmnejb.util.ReactionRuleMgtConstants;

@Stateless
@LocalBean
public class PriceProductUrlService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String URL_HIERARCHY = "/hierarchy";
	private String BASE_URL = "http://test-priceproductmw.colruyt.int/priceproductmw";
	private static final String QUERY_PARAM_LIST_CATEGORY_IDS = "?list_category_ids=";
	private static final String QUERY_PARAM_EXCLUSION = "&exclusion=false";
	private static final String QUERY_PARAM_PREFIX = "?";
	private static final String QUERY_PARAM_PRODUCTIDS = "products_ids=";
	private static final String URL_PRICE_PRODUCT = "/priceproducts";
	private static final String PATH_PARAM_PREFIX = "/";
	public static final String JAVA_PRICE_PRODUCT_MW_HOST = "java_price_product_mw_host";
	public static final String PATH_PARAM_ALLHIERARCHIES = "all-hierarchies";
	private static final String URL_PRODUCTS_HIERARCHY = "/products-hierarchy";

	@PostConstruct
	public void init() {
		try {
			BASE_URL = AssToolUtil.getNonSecureValue(JAVA_PRICE_PRODUCT_MW_HOST) + "/v1.0";
		} catch (AssException e) {
			throw new RuntimeException(e);
		}
	}

	public String getPriceProductUrlForHierarchyList(List<String> hierarchyValueList) {
		StringBuilder url = new StringBuilder(URL_HIERARCHY);
		url.append(QUERY_PARAM_LIST_CATEGORY_IDS);
		url.append(getCommaSeperatedValuesForURL(hierarchyValueList));
		url.append(QUERY_PARAM_EXCLUSION);
		return prefixBaseUrl(url.toString());
	}

	public String getPriceProductURL(List<String> productList) {
		StringBuilder url = new StringBuilder(prefixBaseUrl(URL_PRICE_PRODUCT));
		url.append(QUERY_PARAM_PREFIX);
		url.append(QUERY_PARAM_PRODUCTIDS);
		url.append(getCommaSeperatedValuesForURL(productList));
		return url.toString();
	}

	public static String getCommaSeperatedValuesForURL(List<String> hierarchyValues) {
		StringBuilder queryParam = new StringBuilder();
		int segmentlen = 0;
		for (String value : hierarchyValues) {
			queryParam.append(value);

			if (segmentlen < hierarchyValues.size() - 1) {
				queryParam.append(ReactionRuleMgtConstants.COMMA);
			}
			segmentlen++;
		}
		return queryParam.toString();

	}

	private String prefixBaseUrl(String url) {
		return BASE_URL + url;
	}

	public String getPriceProductURLForPriceProductId(String priceProductId) {
		StringBuilder url = new StringBuilder(prefixBaseUrl(URL_PRICE_PRODUCT));
		url.append(PATH_PARAM_PREFIX);
		url.append(priceProductId);
		return url.toString();
	}

	public String getAllHierarchyUrl() {
		StringBuilder url = new StringBuilder(URL_HIERARCHY);
		url.append(PATH_PARAM_PREFIX);
		return prefixBaseUrl(url.toString());

	}

	public String getAllProducts() {
		StringBuilder url = new StringBuilder(URL_HIERARCHY);
		url.append(URL_PRODUCTS_HIERARCHY);
		return prefixBaseUrl(url.toString());
	}
}
