package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.util.Comparator;

import colruyt.rearulmgtdmnejb.bo.GeneralRuleBo;

public class GeneralRulePriorityComparator implements Comparator<GeneralRuleBo>,Serializable {
	private static final long serialVersionUID = 1L;
	// Used for sorting in descending order of priority
	public int compare(GeneralRuleBo a, GeneralRuleBo b) 
    { 
		return b.getRulePriority().compareTo(a.getRulePriority());
    } 
}
