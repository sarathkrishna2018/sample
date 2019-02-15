package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.RefActionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityConditionTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefQuantityPriceTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefRuleTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefSourceTypeBo;
import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.QuantityType;
import colruyt.rearulmgtdmnejb.enums.ReasonType;
import colruyt.rearulmgtdmnejb.enums.RuleType;
import colruyt.rearulmgtdmnejb.enums.SourceType;
import colruyt.rearulmgtdmnejb.util.ReferenceDataConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)


public class ReferenceDataConverterTest {
	@TestedObject
	private ReferenceDataConverter referenceDataConverter;
	
	/*@Test
	public void convertRefNonReactingCodeType(){
		List<RefReason> refReasonlist=createRefReason();
		List<RefNotToReactCodeBo> expectedRefNotToReactCodeBo=referenceDataConverter.convertRefNonReactingCodeType(createRefReason());
		assertEquals(refReasonlist.size(), expectedRefNotToReactCodeBo.size());
		
	}
	@Test
	public void convertRefReasonLangs(){
		List<RefReasonLang> refReasonLanglist=getRefReasonLang();
		List<RefLangBo> expectedRefLangBo=referenceDataConverter.convertRefReasonLangs(getRefReasonLang());
		assertEquals(refReasonLanglist.size(),expectedRefLangBo.size());
		
	}
	@Test
	public void convertRefQtyCondTest(){
		List<RefQuantityCond> refQtyCondlist=createRefQtyCond();
		List<RefQuantityConditionTypeBo> expectedRefQuantityConditionTypeBo=referenceDataConverter.convertRefQtyCond(createRefQtyCond());
		assertEquals(refQtyCondlist.size(),expectedRefQuantityConditionTypeBo.size());	
	}
	@Test
	public void convertRefQtyCondLangsTest(){
		List<RefQuantityCondLang> refQtyCondLanglist=getRefQtyCondLang();
		List<RefLangBo> expectedRefLangBo=referenceDataConverter.convertRefQtyCondLangs(getRefQtyCondLang());
		assertEquals(refQtyCondLanglist.size(),expectedRefLangBo.size());
	}
	
	@Test
	public void convertRefQtyTypeTest(){
		List<RefQuantityType> refQtyTypelist=createRefQtyType();
		List<RefQuantityPriceTypeBo> expectedRefQuantityPriceTypeBo=referenceDataConverter.convertRefQtyType(createRefQtyType());
		assertEquals(refQtyTypelist.size(),expectedRefQuantityPriceTypeBo.size());	
	}
	@Test
	public void convertRefQtyTypeLangsTest(){
		List<RefQuantityTypeLang> refQtyTypeLanglist=getRefQtyTypeLang();
		List<RefLangBo> expectedRefLangBo=referenceDataConverter.convertRefQtyTypeLangs(getRefQtyTypeLang());
		assertEquals(refQtyTypeLanglist.size(),expectedRefLangBo.size());	
	}
	@Test
	public void convertRuleTypeTest(){
		List<RefRuletype> refRuletypelist=getRefRuletype();
		List<RefRuleTypeBo> expectedRefRuleTypeBo=referenceDataConverter.convertRuleType(getRefRuletype());
		assertEquals(refRuletypelist.size(),expectedRefRuleTypeBo.size());	
	}
	@Test
	public void convertRefRuletypeLangsTest(){
		List<RefRuletypeLang> refRuletypeLanglist=getRefRuletypeLang();
		List<RefLangBo> expectedRefLangBo=referenceDataConverter.convertRefRuletypeLangs(getRefRuletypeLang());
		assertEquals(refRuletypeLanglist.size(),expectedRefLangBo.size());	
	}
	@Test
	public void convertRefFltoutTypeTest(){
		List<RefFilterOutRecordingType> refFltoutTypelist=getRefFltoutType();
		List<RefFilterOutRecordingTypeBo> expectedRefFilterOutRecordingTypeBo=referenceDataConverter.convertRefFltoutType(getRefFltoutType());
		assertEquals(refFltoutTypelist.size(),expectedRefFilterOutRecordingTypeBo.size());	
	}
	@Test
	public void convertRefFltoutTypeLangs(){
		List<RefFilterOutRecordingTypeLang> refFltoutTypeLanglist=getRefFltoutTypeLang();
		List<RefLangBo> expectedRefLangBo=referenceDataConverter.convertRefFltoutTypeLangs(getRefFltoutTypeLang());
		assertEquals(refFltoutTypeLanglist.size(),expectedRefLangBo.size());	
	}*/
	@Test
	public void convertRefReaActionTest(){
		ActionType[] actionTypeEnums= ActionType.values();
		List<RefActionTypeBo> expectedRefActionType=referenceDataConverter.convertRefReaActiontype(actionTypeEnums);
		Assert.assertEquals(15,expectedRefActionType.size());
	}
	@Test
	public void convertRefReaSourceTypeTest(){
		SourceType[] sourceTypeEnums=SourceType.values();
		 List<RefSourceTypeBo> expectedRefSourceType=referenceDataConverter.convertRefReaSource(sourceTypeEnums);
		 Assert.assertEquals(5,expectedRefSourceType.size());
	}
	@Test
	public void testConvertRefNonReactingCodeType() {
		ReasonType[] reasonValues = ReasonType.values();
		List<RefNotToReactCodeBo> RefNotToReactCodeBos = referenceDataConverter.convertRefNonReactingCodeType(reasonValues);
		assertEquals(6,RefNotToReactCodeBos.size());
	}
	
	@Test
	public void testConvertRuleType() {
		RuleType[] reasonValues = RuleType.values();
		List<RefRuleTypeBo> refRuleTypeBoList = referenceDataConverter.convertRuleType(reasonValues);
		assertEquals(6,refRuleTypeBoList.size());
	}
	
	@Test
	public void testConvertRefQtyType(){
		QuantityType[] refQuantityTypeValues = QuantityType.values();
		List<RefQuantityPriceTypeBo> refQuantityPriceTypeBoList = referenceDataConverter.convertRefQtyType(refQuantityTypeValues);
		assertEquals(3,refQuantityPriceTypeBoList.size());
	}
	/*private List<RefFilterOutRecordingType> getRefFltoutType(){
		List<RefFilterOutRecordingType> refFltoutTypelist=Lists.newArrayList();
		RefFilterOutRecordingType refFltoutType=new RefFilterOutRecordingType();
		refFltoutType.setFltoutTypeId(1);
		refFltoutType.setRefFltoutTypeLangs(getRefFltoutTypeLang());
		refFltoutTypelist.add(refFltoutType);
		return refFltoutTypelist;
	}
	private List<RefFilterOutRecordingTypeLang> getRefFltoutTypeLang(){
		List<RefFilterOutRecordingTypeLang> refFltoutTypeLanglist=Lists.newArrayList();
		RefFilterOutRecordingTypeLang refFltoutTypeLang=new RefFilterOutRecordingTypeLang();
		refFltoutTypeLang.setDescription("zzz");
		refFltoutTypeLang.setFltoutTypeName("xyz");
		refFltoutTypeLang.setId(getRefFltoutTypeLangPK());
		refFltoutTypeLanglist.add(refFltoutTypeLang);
		return refFltoutTypeLanglist;
		
	}
	private RefFilterOutRecordingTypeLangPK getRefFltoutTypeLangPK(){
		RefFilterOutRecordingTypeLangPK refFltoutTypeLangPK=new RefFilterOutRecordingTypeLangPK();
		refFltoutTypeLangPK.setFltoutTypeId(1);
		refFltoutTypeLangPK.setIsoLangCode("EN");
		return refFltoutTypeLangPK;
	}
	private List<RefRuletype> getRefRuletype(){
		List<RefRuletype> refRuletypelist=Lists.newArrayList();
		RefRuletype refRuletype=new RefRuletype();
		refRuletype.setRuletypeId(1);
		refRuletype.setRefRuletypeLang(getRefRuletypeLang());
		refRuletypelist.add(refRuletype);
		return refRuletypelist;
		
	}
	private List<RefRuletypeLang> getRefRuletypeLang(){
		List<RefRuletypeLang> refRuletypeLanglist=Lists.newArrayList();
		RefRuletypeLang refRuletypeLang=new RefRuletypeLang();
		refRuletypeLang.setId(getRefRuleTypePK());
		refRuletypeLang.setDescription("Filtering");
		refRuletypeLang.setRuletypeName("Filtering rule");
		refRuletypeLanglist.add(refRuletypeLang);
		return refRuletypeLanglist;	
		
	}
	private RefRuleTypePK getRefRuleTypePK(){
		RefRuleTypePK refRuleTypePK=new RefRuleTypePK();
		refRuleTypePK.setRuletypeId(1);
		refRuleTypePK.setIsoLangCode("EN");
		return refRuleTypePK;
		
	}
	private List<RefQuantityType> createRefQtyType(){
		List<RefQuantityType> RefQtyTypelist=Lists.newArrayList();
		RefQuantityType refQtyType=new RefQuantityType();
		refQtyType.setRefQtyTypeLangs(getRefQtyTypeLang());
		refQtyType.setQtyTypeId(1);
		RefQtyTypelist.add(refQtyType);
		return	RefQtyTypelist;
	}
	private List<RefQuantityTypeLang> getRefQtyTypeLang(){
		List<RefQuantityTypeLang> refQtyTypeLanglist=Lists.newArrayList();
		RefQuantityTypeLang refQtyTypeLang=new RefQuantityTypeLang();
		refQtyTypeLang.setId(getRefQtyTypeLangPK());
		refQtyTypeLang.setQtyTypeName("xxx");
		refQtyTypeLang.setDescription("xcg");
		refQtyTypeLanglist.add(refQtyTypeLang);
		return refQtyTypeLanglist;
		
	}
	private RefQuantityType getRefQtyType(){
		RefQuantityType refQtyType=new RefQuantityType();
		refQtyType.setRefQtyTypeLangs(getRefQtyTypeLang());
		refQtyType.setQtyTypeId(1);
		return refQtyType;
		
	}
	private RefQuantityTypeLangPK getRefQtyTypeLangPK(){
		RefQuantityTypeLangPK refQtyTypeLangPK=new RefQuantityTypeLangPK();
		refQtyTypeLangPK.setQtyTypeId(1);
		refQtyTypeLangPK.setIsoLangCode("EN");
		return refQtyTypeLangPK;
	}*/
	private List<RefQuantityPriceTypeBo> getRefQuantityPriceTypeBo(){
		List<RefQuantityPriceTypeBo> refQuantityPriceTypeBolist=Lists.newArrayList();
		RefQuantityPriceTypeBo refQuantityPriceTypeBo=new RefQuantityPriceTypeBo();
		refQuantityPriceTypeBo.setCodeLang(createRefLangBo());
		refQuantityPriceTypeBo.setQuantityTypeId(1l);
		refQuantityPriceTypeBo.setDescription("xxx");
		refQuantityPriceTypeBolist.add(refQuantityPriceTypeBo);
		return	refQuantityPriceTypeBolist;
	}
	
	/*private RefQuantityCond getRefQtyCond(){
		RefQuantityCond refQtyCond=new RefQuantityCond();
		refQtyCond.setQtyCondId(1);
		refQtyCond.setRefQtyCondLangs(getRefQtyCondLang());
		return refQtyCond;	
		
	}
	private List<RefQuantityCond> createRefQtyCond(){
		List<RefQuantityCond> refQtyCondlist=Lists.newArrayList();
		RefQuantityCond refQtyCond=new RefQuantityCond();
		refQtyCond.setQtyCondId(1);
		refQtyCond.setRefQtyCondLangs(getRefQtyCondLang());
		refQtyCondlist.add(refQtyCond);
		return refQtyCondlist;	
	}
	private List<RefQuantityCondLang> getRefQtyCondLang(){
		List<RefQuantityCondLang> refQtyCondLanglist=Lists.newArrayList();
		RefQuantityCondLang refQtyCondLang=new RefQuantityCondLang();
		refQtyCondLang.setId(getRefQtyCondLangPK());
		refQtyCondLang.setQtyCondName("czx");
		refQtyCondLang.setDescription("products");
		//refQtyCondLang.setRefQtyCond(getRefQtyCond());
		refQtyCondLanglist.add(refQtyCondLang);
		return refQtyCondLanglist;
		
	}
	private RefQuantityCondLangPK getRefQtyCondLangPK(){
		RefQuantityCondLangPK refQtyCondLangPK=new RefQuantityCondLangPK();
		refQtyCondLangPK.setIsoLangCode("En");;
		refQtyCondLangPK.setQtyCondId(1);
		return refQtyCondLangPK;
		
		
	}
	public RefReason getRefReason(){
		RefReason refReason=new RefReason();
		refReason.setReasonId(1);
		refReason.setRefReasonLangs(getRefReasonLang());
		return refReason;		
	}
	public List<RefReason> createRefReason(){
		List<RefReason> refReasonslist=Lists.newArrayList();
		RefReason refReason=new RefReason();
		refReason.setReasonId(1);
		refReason.setRefReasonLangs(getRefReasonLang());
		refReasonslist.add(refReason);
		return refReasonslist;
	}
	public List<RefReasonLang> getRefReasonLang(){
		List<RefReasonLang> refReasonLanglist=Lists.newArrayList();
		RefReasonLang refReasonLang=new RefReasonLang();
		refReasonLang.setId(getRefReasonLangPK());
		refReasonLang.setReasonName("saa");
		refReasonLang.setDescription("unknown");
		//refReasonLang.setRefReason( getRefReason());
		refReasonLanglist.add(refReasonLang);
		return refReasonLanglist;
		
	}
	public RefReasonLang createRefReasonLang(){
		RefReasonLang refReasonLang=new RefReasonLang();
		refReasonLang.setId(getRefReasonLangPK());
		refReasonLang.setReasonName("saa");
		refReasonLang.setDescription("unknown");
		refReasonLang.setRefReason( getRefReason());
		return refReasonLang;
		
	}
	public RefReasonLangPK getRefReasonLangPK(){
		RefReasonLangPK refReasonLangPK=new RefReasonLangPK();
		refReasonLangPK.setReasonId(1);
		refReasonLangPK.setIsoLangCode("EN");
		return refReasonLangPK;
	}*/
	public List<RefLangBo> createRefLangBo(){
		List<RefLangBo> refLangBolist=Lists.newArrayList();
		RefLangBo refLangBo=new RefLangBo();
		refLangBo.setIsoLangCode("En");
		refLangBo.setValue("English");
		refLangBolist.add(refLangBo);
		return refLangBolist;
		
	}
	
	
	public RefActionTypeBo getRefActionTypeBo(){
		RefActionTypeBo refActionTypeBo=new RefActionTypeBo();
		refActionTypeBo.setActionTypeValue("xyz");
		refActionTypeBo.setActionTypeId(1);
		refActionTypeBo.setSequence(123l);
		return refActionTypeBo;
	}
	public RefQuantityConditionTypeBo getRefQuantityConditionTypeBo(){
		RefQuantityConditionTypeBo refQuantityConditionTypeBo=new RefQuantityConditionTypeBo();
		refQuantityConditionTypeBo.setCodeLang(createRefLangBo());
		refQuantityConditionTypeBo.setCodeTypeId(1);
		refQuantityConditionTypeBo.setDescription("xyz");
		return refQuantityConditionTypeBo;
	}
}
