package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

public class ReactionPeriodRuleBo extends GeneralRuleBo implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
