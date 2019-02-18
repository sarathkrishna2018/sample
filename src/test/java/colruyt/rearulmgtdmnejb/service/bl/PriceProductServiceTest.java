package colruyt.rearulmgtdmnejb.service.bl;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import colruyt.priceproduct.bo.MainCategoryBo;
import colruyt.priceproduct.bo.PriceProductHierarchyResponseBo;
import colruyt.priceproduct.bo.PriceProductHierarchyBo;
import colruyt.rearulmgtdmnejb.exception.PriceProductExternalServiceException;
import colruyt.rearulmgtdmnejb.exception.PriceProductServiceDownException;
import colruyt.rearulmgtdmnejb.exception.RRMDomainException;
import colruyt.rearulmgtdmnejb.exception.ServiceDownException;
import colruyt.rearulmgtdmnejb.util.ExternalClientService;
import colruyt.rearulmgtdmnejb.util.PriceProductConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class PriceProductServiceTest {
	@TestedObject
	private PriceProductService priceProductService;
	@InjectIntoByType
	private PriceProductUrlService priceProductUrlService = Mockito.mock(PriceProductUrlService.class);
	@InjectIntoByType
	private ExternalClientService externalClientService = Mockito.mock(ExternalClientService.class);
	@InjectIntoByType
	private PriceProductConverter priceProductConvertor = Mockito.mock(PriceProductConverter.class);

	@Test
	public void externalHierrachyValuesTest() throws UnsupportedEncodingException, PriceProductExternalServiceException,
			PriceProductServiceDownException, IOException, ParseException, ServiceDownException, RRMDomainException {
		when(priceProductUrlService.getAllHierarchyUrl()).thenReturn(getAllHierarchyUrl());
		when(externalClientService.callGetService(Mockito.anyString())).thenReturn(getJSONString());
		when(externalClientService.getGsonWithDateDeserializer()).thenReturn(getGson());
		when(priceProductConvertor.convertProducts(Mockito.anyListOf(MainCategoryBo.class), Mockito.anyString()))
				.thenReturn(getPriceProductHierarchyBoList());
		Set<String> allHierarchyValuesFromPriceProduct = priceProductService.externalHierarchyValues();
		Assert.assertNotNull(allHierarchyValuesFromPriceProduct);
	}

	@Test
	public void findHierarchyValuesTest() throws ServiceDownException, RRMDomainException,
			PriceProductExternalServiceException, PriceProductServiceDownException {
		when(priceProductUrlService.getAllHierarchyUrl()).thenReturn(getAllHierarchyUrl());
		when(externalClientService.callGetService(Mockito.anyString())).thenReturn(getJSONString());
		when(externalClientService.getGsonWithDateDeserializer()).thenReturn(getGson());
		PriceProductHierarchyResponseBo priceProductHeriarchyBo = priceProductService.findHierarchyValues();
		Assert.assertNotNull(priceProductHeriarchyBo);
	}

	@Test
	public void getPriceProductsTest() throws UnsupportedEncodingException, IOException, ParseException {
		List<PriceProductHierarchyBo> hierarchyBos = priceProductService.getPriceProducts();
		Assert.assertNotNull(hierarchyBos);
	}

	private List<PriceProductHierarchyBo> getPriceProductHierarchyBoList() {
		List<PriceProductHierarchyBo> priceProductHierarchyBos = Lists.newArrayList();
		PriceProductHierarchyBo priceProductHierarchyBo = new PriceProductHierarchyBo();
		priceProductHierarchyBo.setExcluded(false);
		priceProductHierarchyBo.setExpanded(true);
		priceProductHierarchyBo.setId(1l);
		priceProductHierarchyBo.setIncluded(true);
		priceProductHierarchyBo.setLinkedContextHierarchyId(1l);
		priceProductHierarchyBo.setName("xx");
		priceProductHierarchyBo.setParentValue("sa");
		priceProductHierarchyBo.setTypeId(1l);
		priceProductHierarchyBo.setChildHierarchy(getChildHierarchyList());
		priceProductHierarchyBos.add(priceProductHierarchyBo);
		return priceProductHierarchyBos;
	}

	private List<PriceProductHierarchyBo> getChildHierarchyList() {
		List<PriceProductHierarchyBo> hierarchyBos = new ArrayList<>();
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo();
		hierarchyBo.setId(1L);
		hierarchyBo.setValue("PRoduct");
		hierarchyBo.setChildHierarchy(getChildHierarchyListGroup());
		hierarchyBos.add(hierarchyBo);
		return hierarchyBos;
	}

	private List<PriceProductHierarchyBo> getChildHierarchyListGroup() {
		List<PriceProductHierarchyBo> hierarchyBos = new ArrayList<>();
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo();
		hierarchyBo.setId(1L);
		hierarchyBo.setValue("Group");
		hierarchyBo.setChildHierarchy(getChildHierarchyListSeg());
		hierarchyBos.add(hierarchyBo);
		return hierarchyBos;
	}

	private List<PriceProductHierarchyBo> getChildHierarchyListSeg() {
		List<PriceProductHierarchyBo> hierarchyBos = new ArrayList<>();
		PriceProductHierarchyBo hierarchyBo = new PriceProductHierarchyBo();
		hierarchyBo.setId(1L);
		hierarchyBo.setValue("Seg");
		hierarchyBos.add(hierarchyBo);
		return hierarchyBos;
	}

	private Gson getGson() {
		Gson gson = new GsonBuilder().create();
		return gson;
	}

	private String getAllHierarchyUrl() {
		return "/hierarchy";
	}

	private String getJSONString() {
		String jsonString = "{\"ruleName\":\"Propose Not To ReactRule\", \"assortmentName\":\"all\",\"catchAll\":false, \"nationalBrand\":false, \"ownBrand\":false, \"importanceCodeFrom\":4 ,\"importanceCodeTo\" : 88, \"cheapBrand\":true, \"permanentDuration\":false,\"postponedBenefit\":false, \"directBenefit\":true, \"temporaryDuration\":true,\"logonId\":\"rra1kni\",\"sourceSelectAll\":true,\"actionSelectAll\":true, \"reactionRulesetBo\" : { 		\"colruytGroupChainId\":100, 		\"priceCompetitorChainId\":212, 		\"refRuleTypeBo\" : { 			\"ruleTypeId\":5 		} 	}, \"refRuleTypeBo\": { 	\"ruleTypeId\":5, 	\"ruleTypeName\":\"Propose Not To React\" }, \"actionTypeList\" : [{ 	\"actionTypeId\" : 10, 	\"actionTypeValue\" : \"Points\", 	\"sequence\" : 1 } 	], 	\"sourceTypeList\" : [{ 		\"sourceTypeId\":4, 		\"sourceTypeValue\": \"Offline\" 	}], 	\"priceProductHierarchySet\":[{ 			\"priceProductHierarchyTypeId\":98, 		\"priceProductHierarchyValue\":\"seg1585\" 	}], 	 	\"type\" : \"Propose Not To React\", 	 \"filterOutType\":{ 	 	\"filterOutTypeId\":1 	 }, 	 \"notToReactCodes\":[{ 	 	\"notToReactCodeTypeId\":1 	 }]	 	 }";
		return jsonString;
	}
}
