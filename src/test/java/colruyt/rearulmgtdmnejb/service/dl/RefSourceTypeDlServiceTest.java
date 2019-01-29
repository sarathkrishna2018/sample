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

import colruyt.rearulmgtdmnejb.entity.RefReaSource;
import colruyt.rearulmgtdmnejb.entity.RefRuletype;
import junit.framework.Assert;

@JpaEntityManagerFactory(persistenceUnit = "rearulmgtdmnmwtest", configFile = "/META-INF/persistence-test.xml")
@Transactional(TransactionMode.ROLLBACK)
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class RefSourceTypeDlServiceTest {
	
		private RefSourceTypeDlService refSourceTypeDlService;

		@Before
		public void init() {
			refSourceTypeDlService = new RefSourceTypeDlService();
			JpaUnitils.injectEntityManagerInto(refSourceTypeDlService);
		}

		@Test
		@DataSet
		public void findAllSourceTypes() {
		
			List<RefReaSource> refReaSourceList  =refSourceTypeDlService.findAllSourceTypes();
			Assert.assertEquals(refReaSourceList.size(), getAllSourceTypes().size());

		}
		
		public List<RefReaSource> getAllSourceTypes() {
			List<RefReaSource> refReaSourceList = Lists.newArrayList();
			RefReaSource refReaSource = new RefReaSource();
			refReaSource.setSourceId(1L);
			refReaSource.setSourceName("Online");
			refReaSource.setDescription("Online");
			refReaSourceList.add(refReaSource);
			refReaSource = new RefReaSource();
			refReaSource.setSourceId(2L);
			refReaSource.setSourceName("Offline");
			refReaSource.setDescription("Offline");
			refReaSourceList.add(refReaSource);
			return refReaSourceList;

		}
}
*/