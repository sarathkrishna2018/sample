package colruyt.rearulmgtdmnejb.util;

import java.util.Comparator;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;

public class GeneralRulePriorityComparator implements Comparator<GeneralRuleBo> {
	// Used for sorting in descending order of priority
	public int compare(GeneralRuleBo a, GeneralRuleBo b) 
    { 
		return b.getRulePriority().compareTo(a.getRulePriority());
    } 
}
