package colruyt.rearulmgtdmnejb.service.dl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.bo.DeleteRuleInfoBo;
import colruyt.rearulmgtdmnejb.entity.ProposalNotToReactRuleAction;

@JpaEntityManagerFactory(persistenceUnit = "in_memory_database_testing", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class ProposalNotToReactActionDlServiceTest {
	private ProposalNotToReactActionDlService proposalNotToReactActionDlService;

	@Before
	public void init() {
		proposalNotToReactActionDlService = new ProposalNotToReactActionDlService();
		JpaUnitils.injectEntityManagerInto(proposalNotToReactActionDlService);
	}

	@Test
	@ExpectedDataSet("result/ProposalNotToReactCreateTestResult.xml")
	public void createTest() {
		ProposalNotToReactRuleAction expectedProposalNotToReactRuleAction = proposalNotToReactActionDlService
				.createOrUpdate(getProposalNotToReactRuleAction());
		Assert.assertEquals(1L,expectedProposalNotToReactRuleAction.getReactionRuleId());

	}
	@Test
	@DataSet("dataset/ProposalNotToReactActionDlServiceTest.xml")
	public void findByRuleIdTest() {
		List<Integer> notToReactSetReasonsId=Lists.newArrayList();
		notToReactSetReasonsId.add(2);
		ProposalNotToReactRuleAction expectedProposalNotToReactRule = proposalNotToReactActionDlService.findByRuleId(1l);
		Assert.assertEquals(1L,expectedProposalNotToReactRule.getReactionRuleId());
		Assert.assertEquals(5,expectedProposalNotToReactRule.getFilterOutTypeId());
		Assert.assertEquals(notToReactSetReasonsId, expectedProposalNotToReactRule.getNotToReactSetReasons() );
	}

	@Test
	@DataSet("dataset/ProposalNotToReactActionDlServiceTest.xml")
	@ExpectedDataSet("result/PhysicalDeleteElementsRsnTestResult.xml")
	public void physicalDeleteElementsTest() {
		proposalNotToReactActionDlService.physicalDeleteElements(getDeleteRuleInfoBo());
		
	}
	@Test
	@DataSet("dataset/ProposalNotToReactActionDlServiceTest.xml")
	@ExpectedDataSet("result/PhysicalDeleteElementsRsnTestResult.xml")
	public void physicalDeleteElementsRsnTest() {
		proposalNotToReactActionDlService.physicalDeleteElementsRsn(getDeleteRuleInfoBo());
		
	}

	private ProposalNotToReactRuleAction getProposalNotToReactRuleAction() {
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = new ProposalNotToReactRuleAction();
		proposalNotToReactRuleAction.setReactionRuleId(1l);
		List<Integer> reaNreactSetRsns = Lists.newArrayList();
		reaNreactSetRsns.add(1);
		reaNreactSetRsns.add(2);
		proposalNotToReactRuleAction.setNotToReactSetReasons(reaNreactSetRsns);
		proposalNotToReactRuleAction.setFilterOutTypeId(5);
		return proposalNotToReactRuleAction;
	}
	private DeleteRuleInfoBo getDeleteRuleInfoBo() {
		DeleteRuleInfoBo deleteRuleInfoBo = new DeleteRuleInfoBo(1l, 1l);
		deleteRuleInfoBo.setRuleId(1l);
		deleteRuleInfoBo.setRuleType(1l);
		return deleteRuleInfoBo;
	}
}
