/*package colruyt.rearulmgtdmnejb.service.dl;

import java.util.List;

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

import colruyt.rearulmgtdmnejb.entity.RefRuletype;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefRuleTypeDlServiceTest {

	private RefRuleTypeDlService refRuleTypeDlService;

	@Before
	public void init() {
		refRuleTypeDlService = new RefRuleTypeDlService();
		JpaUnitils.injectEntityManagerInto(refRuleTypeDlService);
	}

	@Test
	@DataSet
	public void getAllRuleTypesTest() {

		List<RefRuletype> refRuletypeList = refRuleTypeDlService.getAllRuleTypes();
		Assert.assertEquals(refRuletypeList.size(), getAllRefRuletype().size());
	}

	public List<RefRuletype> getAllRefRuletype() {
		List<RefRuletype> refRuletypeList = Lists.newArrayList();
		RefRuletype refRuletype = new RefRuletype();
		refRuletype.setRuletypeId(1L);
		refRuletypeList.add(refRuletype);
		refRuletype = new RefRuletype();
		refRuletype.setRuletypeId(2L);
		refRuletypeList.add(refRuletype);
		return refRuletypeList;

	}
}
*/