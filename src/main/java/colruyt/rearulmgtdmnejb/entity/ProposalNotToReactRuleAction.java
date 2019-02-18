package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


/**
 * The persistent class for the REA_NREACT_ACT database table.
 * 
 */
@Entity
@Table(name="REA_NREACT_ACT")
public class ProposalNotToReactRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REA_RULE_ID")
	private long reactionRuleId;

	@Column(name="FLTOUT_TYPE_ID")
	private long fltoutTypeId;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "REA_NREACT_SET_RSN", 
			joinColumns = @JoinColumn(name = "REA_RULE_ID"))
	@Column(name = "REASON_ID")
	private List<Long> notToReactSetReasons;
	

	public List<Long> getNotToReactSetReasons() {
		return notToReactSetReasons;
	}

	public void setNotToReactSetReasons(List<Long> reaNreactSetRsns) {
		this.notToReactSetReasons = reaNreactSetRsns;
	}

	public long getReactionRuleId() {
		return this.reactionRuleId;
	}

	public void setReactionRuleId(long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}
	
	public long getFltoutTypeId() {
		return fltoutTypeId;
	}

	public void setFltoutTypeId(long fltoutTypeId) {
		this.fltoutTypeId = fltoutTypeId;
	}

}