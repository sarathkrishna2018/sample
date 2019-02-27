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
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import com.google.common.collect.Lists;

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
	@DataSet
	public void createTest() {
		ProposalNotToReactRuleAction expectedProposalNotToReactRuleAction = proposalNotToReactActionDlService
				.createOrUpdate(getProposalNotToReactRuleAction());
		Assert.assertNotNull(expectedProposalNotToReactRuleAction);

	}

	ProposalNotToReactRuleAction getProposalNotToReactRuleAction() {
		ProposalNotToReactRuleAction proposalNotToReactRuleAction = new ProposalNotToReactRuleAction();
		proposalNotToReactRuleAction.setReactionRuleId(1l);
		List<Long> reaNreactSetRsns = Lists.newArrayList();
		reaNreactSetRsns.add(1l);
		reaNreactSetRsns.add(2l);
		proposalNotToReactRuleAction.setNotToReactSetReasons(reaNreactSetRsns);
		proposalNotToReactRuleAction.setFltoutTypeId(3l);
		return proposalNotToReactRuleAction;
	}
}
