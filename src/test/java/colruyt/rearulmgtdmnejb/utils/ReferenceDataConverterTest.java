package colruyt.rearulmgtdmnejb.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.entity.RefFilterOutRecordingType;
import colruyt.rearulmgtdmnejb.entity.RefFilterOutRecordingTypeLangPK;
import colruyt.rearulmgtdmnejb.entity.RefQuantityConditionType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityConditionTypeLangPK;
import colruyt.rearulmgtdmnejb.entity.RefQuantityType;
import colruyt.rearulmgtdmnejb.entity.RefQuantityTypeLangPK;
import colruyt.rearulmgtdmnejb.entity.RefReasonType;
import colruyt.rearulmgtdmnejb.entity.RefReasonTypeLangPK;
import colruyt.rearulmgtdmnejb.entity.RefRuleType;
import colruyt.rearulmgtdmnejb.entity.RefRuleTypeLangPK;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.FilterOutRecordingType;
import colruyt.rearulmgtdmnejb.enums.QuantityCondition;
import colruyt.rearulmgtdmnejb.enums.QuantityType;
import colruyt.rearulmgtdmnejb.enums.ReasonType;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class ReferenceDataConverterTest {


	@Test
	public void convertRefReaActiontypeListTest() {
		List<RefActionTypeBo> expectedActionTypes = ReferenceDataConverter.convertToActionTypeBo(getRefActionType());
		Assert.assertNotNull(expectedActionTypes);
	}

	@Test
	public void convertRefNonReactingCodeTypeTest() {
		List<RefNotToReactCodeBo> expectedRefNonReactingCode = ReferenceDataConverter
				.convertToProposeNotToReactReasonBo(ReasonType.values(), getRefReasonTypes());
		Assert.assertNotNull(expectedRefNonReactingCode);
	}

	@Test
	public void convertRefReaSourceListTest() {
		List<RefSourceTypeBo> expectedRefSourceType = ReferenceDataConverter.convertToSourceTypeBo(getRefSourceTypes());
		Assert.assertNotNull(expectedRefSourceType);
	}

	@Test
	public void convertRefReaActiontypeTest() {
		List<RefActionTypeBo> expectedRefActionType = ReferenceDataConverter
				.convertToActionTypeBo(ActionType.values());
		Assert.assertNotNull(expectedRefActionType);
	}

	@Test
	public void convertRefReaSourceTest() {
		List<RefSourceTypeBo> expectedRefSourceType = ReferenceDataConverter.convertToSourceTypeBo(SourceType.values());
		Assert.assertNotNull(expectedRefSourceType);
	}

	@Test
	public void convertRefQtyTypeTest() {
		List<RefQuantityPriceTypeBo> expectedRefQuantityType = ReferenceDataConverter
				.convertToQuantityPriceTypeBo(QuantityType.values(), getQuantityTypes());
		Assert.assertNotNull(expectedRefQuantityType);
	}

	@Test
	public void convertRefQtyCondTest() {
		List<RefQuantityConditionTypeBo> expectedRefQtyCond = ReferenceDataConverter
				.convertToQuantityConditionTypeBo(QuantityCondition.values(), getRefQuantityConditionTypes());
		Assert.assertNotNull(expectedRefQtyCond);
	}

	@Test
	public void convertRefFltoutTypeTest() {
		List<RefFilterOutRecordingTypeBo> expectedRefFiltOut = ReferenceDataConverter
				.convertToFilterOutRecordingTypeBo(FilterOutRecordingType.values(), getRefFiltOutRecordingTypes());
		Assert.assertNotNull(expectedRefFiltOut);
	}

	@Test
	public void convertRuleTypeTest() {
		List<RefRuleTypeBo> expectedRefRuleType = ReferenceDataConverter.convertToRuleTypeBo(RuleType.values(),
				getRefRuleTypes());
		Assert.assertNotNull(expectedRefRuleType);
	}

	private List<RefRuleType> getRefRuleTypes() {
		List<RefRuleType> refRuleTypes = Lists.newArrayList();
		RefRuleType refRuleType = new RefRuleType();
		refRuleType.setDescription("filter");
		refRuleType.setRuleTypeName("Filtering");
		refRuleType.setId(getRefRuleId());
		refRuleTypes.add(refRuleType);
		return refRuleTypes;
	}

	private RefRuleTypeLangPK getRefRuleId() {
		RefRuleTypeLangPK refRuleTypeLangPK = new RefRuleTypeLangPK();
		refRuleTypeLangPK.setIsoLangCode("En");
		refRuleTypeLangPK.setRuleTypeId(1);
		return refRuleTypeLangPK;
	}

	private List<RefFilterOutRecordingType> getRefFiltOutRecordingTypes() {
		List<RefFilterOutRecordingType> refFilterOutRecordingTypes = Lists.newArrayList();
		RefFilterOutRecordingType refFilterOutRecordingType = new RefFilterOutRecordingType();
		refFilterOutRecordingType.setDescription("Filter");
		refFilterOutRecordingType.setFilterOutTypeName("No Filt");
		refFilterOutRecordingType.setId(getFiltOutId());
		refFilterOutRecordingTypes.add(refFilterOutRecordingType);
		return refFilterOutRecordingTypes;
	}

	private RefFilterOutRecordingTypeLangPK getFiltOutId() {
		RefFilterOutRecordingTypeLangPK refFilterOutRecordingTypeLangPK = new RefFilterOutRecordingTypeLangPK();
		refFilterOutRecordingTypeLangPK.setFltoutTypeId(1);
		refFilterOutRecordingTypeLangPK.setIsoLangCode("En");
		return refFilterOutRecordingTypeLangPK;
	}

	private List<RefQuantityConditionType> getRefQuantityConditionTypes() {
		List<RefQuantityConditionType> refQuantityConditionTypes = Lists.newArrayList();
		RefQuantityConditionType refQuantityConditionType = new RefQuantityConditionType();
		refQuantityConditionType.setDescription("NQty");
		refQuantityConditionType.setId(getRefQtyCondId());
		refQuantityConditionType.setQuantityConditionName("Qty");
		refQuantityConditionTypes.add(refQuantityConditionType);
		return refQuantityConditionTypes;
	}

	private RefQuantityConditionTypeLangPK getRefQtyCondId() {
		RefQuantityConditionTypeLangPK refQuantityConditionTypeLangPK = new RefQuantityConditionTypeLangPK();
		refQuantityConditionTypeLangPK.setIsoLangCode("En");
		refQuantityConditionTypeLangPK.setQtyCondId(1);
		return refQuantityConditionTypeLangPK;
	}

	private List<RefQuantityType> getQuantityTypes() {
		List<RefQuantityType> refQuantityTypes = Lists.newArrayList();
		RefQuantityType refQuantityType = new RefQuantityType();
		refQuantityType.setDescription("qty");
		refQuantityType.setId(getQtyId());
		refQuantityType.setQuantityTypeName("NQTY");
		refQuantityTypes.add(refQuantityType);
		return refQuantityTypes;
	}

	private RefQuantityTypeLangPK getQtyId() {
		RefQuantityTypeLangPK refQuantityTypeLangPK = new RefQuantityTypeLangPK();
		refQuantityTypeLangPK.setIsoLangCode("EN");
		refQuantityTypeLangPK.setQuantityTypeId(1);
		return refQuantityTypeLangPK;
	}

	private List<SourceType> getRefSourceTypes() {
		List<SourceType> sourceTypes = Arrays.asList(SourceType.values());
		return sourceTypes;
	}

	private List<RefReasonType> getRefReasonTypes() {
		List<RefReasonType> refReasonTypes = Lists.newArrayList();
		RefReasonType refReasonType = new RefReasonType();
		refReasonType.setDescription("Not Found");
		refReasonType.setId(getId());
		refReasonType.setReasonName("Not to react");
		refReasonTypes.add(refReasonType);
		return refReasonTypes;
	}

	private RefReasonTypeLangPK getId() {
		RefReasonTypeLangPK refReasonTypeLangPK = new RefReasonTypeLangPK();
		refReasonTypeLangPK.setIsoLangCode("En");
		refReasonTypeLangPK.setReasonId(1);
		return refReasonTypeLangPK;
	}

	private List<ActionType> getRefActionType() {
		List<ActionType> actionTypes = Arrays.asList(ActionType.values());
		return actionTypes;
	}

}
