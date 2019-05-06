package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import colruyt.rearulmgtdmnejb.enums.RuleType;

public class ReactionPeriodRuleBo extends GeneralRuleBo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public ReactionPeriodRuleBo(){
		this.setType(RuleType.REACTION_PERIOD.getRuleTypeName());
	}
	
	private Long endDateMinusDate;
	private Long minimumDays;


	public Long getEndDateMinusDate() {
		return endDateMinusDate;
	}

	public void setEndDateMinusDate(Long endDateMinusDate) {
		this.endDateMinusDate = endDateMinusDate;
	}

	public Long getMinimumDays() {
		return minimumDays;
	}

	public void setMinimumDays(Long minimumDays) {
		this.minimumDays = minimumDays;
	}
	

}
