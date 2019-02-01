package colruyt.rearulmgtdmnejb.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.ProposeNotToReactRuleBo;
import colruyt.rearulmgtdmnejb.bo.RefFilterOutRecordingTypeBo;
import colruyt.rearulmgtdmnejb.bo.RefLangBo;
import colruyt.rearulmgtdmnejb.bo.RefNotToReactCodeBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleActionRsnPK;
import colruyt.rearulmgtdmnejb.service.bl.ReferenceDataService;
import colruyt.rearulmgtdmnejb.util.ProposeNotToReactRuleConverter;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ProposeNotToReactRuleConverterTest {
	@TestedObject
	private ProposeNotToReactRuleConverter proposeNotToReactRuleConverter;
	@InjectIntoByType
	private ReferenceDataService referenceDataService=Mockito.mock(ReferenceDataService.class);
	
	@Test
	public void createTest(){
		ProposeNotToReactRuleBo proposeNotToReactRuleBo=getProposeNotToReactRuleBo();
		ProposalNotToReactRuleAction reaNreactAct = new ProposalNotToReactRuleAction();
		reaNreactAct.setFltoutTypeId(proposeNotToReactRuleBo.getFilterOutType().getFilterOutTypeId());
		ProposalNotToReactRuleAction expectedReaNreactAct=proposeNotToReactRuleConverter.convert(getProposeNotToReactRuleBo());	
		assertEquals(expectedReaNreactAct.getFltoutTypeId(), reaNreactAct.getFltoutTypeId());
		
	}
	@Test
	public void convertReasonNotToReactSetTest(){
		List<Long> expectedReaNreactSetRsn=proposeNotToReactRuleConverter.convertReasonNotToReactSet(getRefNotToReactCodeBo());	
		assertEquals(expectedReaNreactSetRsn.size(),getRefNotToReactCodeBo().size());
		
	}
	/*@Test
	public void convertToBoTest(){
		when(referenceDataService.getAllFilterOutRecordingTypes()).thenReturn(getRefFilterOutRecordingTypeBoList());
		when(referenceDataService.getAllNotToReactCodeTypes()).thenReturn(getRefNotToReactCodeBo());
		ProposeNotToReactRuleBo expectedProposeNotToReactRuleBo=proposeNotToReactRuleConverter.convertToBo(getProposalNotToReactRuleAction(), getProposeNotToReactRuleBo());
		Assert.assertEquals(getProposeNotToReactRuleBo().getRuleId(), expectedProposeNotToReactRuleBo.getRuleId());
	}*/
	
	private List<RefFilterOutRecordingTypeBo> getRefFilterOutRecordingTypeBoList() {
		List<RefFilterOutRecordingTypeBo> refFilterOutRecordingTypeBos=Lists.newArrayList();
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo=new RefFilterOutRecordingTypeBo();
		refFilterOutRecordingTypeBo.setFilterOutTypeId(1l);
		refFilterOutRecordingTypeBo.setDescription("xcx");
		refFilterOutRecordingTypeBo.setCodeLang(getRefLangBo());
		refFilterOutRecordingTypeBos.add(refFilterOutRecordingTypeBo);
		return refFilterOutRecordingTypeBos;
	}
	private ProposalNotToReactRuleAction getProposalNotToReactRuleAction() {
		ProposalNotToReactRuleAction proposalNotToReactRuleAction=new ProposalNotToReactRuleAction();
		proposalNotToReactRuleAction.setFltoutTypeId(1);
		proposalNotToReactRuleAction.setReaRuleId(1);
		
		return proposalNotToReactRuleAction;
	}
	
	public ProposalNotToReactRuleActionRsnPK getReaNreactSetRsnPK(){
		ProposalNotToReactRuleActionRsnPK reaNreactSetRsnPK=new ProposalNotToReactRuleActionRsnPK();
		reaNreactSetRsnPK.setReaRuleId(1l);
		reaNreactSetRsnPK.setReasonId(1l);
		return reaNreactSetRsnPK;
	}
	public ProposeNotToReactRuleBo getProposeNotToReactRuleBo(){
		ProposeNotToReactRuleBo proposeNotToReactRuleBo=new ProposeNotToReactRuleBo();
		proposeNotToReactRuleBo.setRuleId(1l);
		proposeNotToReactRuleBo.setFilterOutType(getRefFilterOutRecordingTypeBo());
		proposeNotToReactRuleBo.setNotToReactCodes(getRefNotToReactCodeBo());
		return proposeNotToReactRuleBo;
	}
	public RefFilterOutRecordingTypeBo getRefFilterOutRecordingTypeBo()
	{
		RefFilterOutRecordingTypeBo refFilterOutRecordingTypeBo=new RefFilterOutRecordingTypeBo();
		refFilterOutRecordingTypeBo.setFilterOutTypeId(1l);
		refFilterOutRecordingTypeBo.setDescription("xcx");
		refFilterOutRecordingTypeBo.setCodeLang(getRefLangBo());
		return refFilterOutRecordingTypeBo;
		
	}
	public List<RefLangBo> getRefLangBo(){
		List<RefLangBo> refLangBolist=Lists.newArrayList();
		RefLangBo refLangBo=new RefLangBo();
		refLangBo.setIsoLangCode("EN");
		refLangBo.setValue("English");;
		refLangBolist.add(refLangBo);
		return refLangBolist;
		
	}
	public List<RefNotToReactCodeBo> getRefNotToReactCodeBo(){
		List<RefNotToReactCodeBo> refNotToReactCodeBolist=Lists.newArrayList();
		RefNotToReactCodeBo refNotToReactCodeBo=new RefNotToReactCodeBo();
		refNotToReactCodeBo.setCodeLang(getRefLangBo());
		refNotToReactCodeBo.setNotToReactCodeTypeId(1l);
		refNotToReactCodeBo.setDescription("scx");
		refNotToReactCodeBolist.add(refNotToReactCodeBo);
		return refNotToReactCodeBolist;
	}

}
