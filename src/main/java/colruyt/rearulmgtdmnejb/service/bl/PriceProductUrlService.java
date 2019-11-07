package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import colruyt.coreutillib.system.PromotionLevel;
import org.apache.commons.lang.StringUtils;

import colruyt.coreutillib.security.ass.AssException;
import colruyt.coreutillib.security.ass.AssToolUtil;

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
	private static final String PRICE_PRODUCT_API = "api";

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
		url.append(StringUtils.join(hierarchyValueList, ","));
		url.append(QUERY_PARAM_EXCLUSION);
		return prefixBaseUrl(url.toString());
	}

	public String getPriceProductURL(List<String> productList) {
		StringBuilder url = new StringBuilder(prefixBaseUrl(URL_PRICE_PRODUCT));
		url.append(QUERY_PARAM_PREFIX);
		url.append(QUERY_PARAM_PRODUCTIDS);
		url.append(StringUtils.join(productList, ","));
		return url.toString();
	}

	public String getPriceProductURL() {
		formPriceProductBaseUrl();
		StringBuilder url = new StringBuilder(prefixBaseUrl(PRICE_PRODUCT_API));
		url.append(URL_PRICE_PRODUCT);
		return url.toString();
	}

	private void formPriceProductBaseUrl(){

		if(PromotionLevel.is(PromotionLevel.ontw) || PromotionLevel.is(PromotionLevel.test)){
			BASE_URL = "http://test-priceproductdmnmw.colruyt.int/priceproductdmnmw/";
		} else if(PromotionLevel.is(PromotionLevel.syst)){
			BASE_URL = "http://syst-priceproductdmnmw.colruyt.int/priceproductdmnmw/";
		} else {
			BASE_URL = "http://priceproductdmnmw.colruyt.int/priceproductdmnmw/";
		}
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
