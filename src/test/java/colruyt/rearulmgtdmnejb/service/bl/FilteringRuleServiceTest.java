package colruyt.rearulmgtdmnejb.service.bl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import com.google.common.collect.Lists;


import colruyt.rearulmgtdmnejb.service.dl.FilteringRuleActionDlService;

import junit.framework.Assert;

@Transactional
@RunWith(UnitilsJUnit4TestClassRunner.class)

public class FilteringRuleServiceTest {
	@TestedObject
	private FilteringRuleService filteringRuleBlService;

	@InjectIntoByType
	private FilteringRuleActionDlService filteringRuleActionDlService = Mockito
			.mock(FilteringRuleActionDlService.class);
	

	
	@Test
	public void physicalDeleteElementsTest() {
		Mockito.doNothing().when(filteringRuleActionDlService).physicalDeleteElements(getDeleteRuleInfoBo());
		filteringRuleBlService.physicalDeleteElements(getDeleteRuleInfoBo());
		Mockito.verify(filteringRuleActionDlService).physicalDeleteElements(getDeleteRuleInfoBo());
	}

	

}
