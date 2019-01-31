package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;


/**
 * The persistent class for the REA_RULESET database table.
 * 
 */
@Entity
@Table(name="REA_RULESET")
public class ReactionRuleSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REA_RULESET_ID")
	@SequenceGenerator(name="REA_RULESET_SEQ_GEN", sequenceName="SEQ_REA_RULESET")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REA_RULESET_SEQ_GEN")
	private long reaRulesetId;
	
	@Column(name="RULETYPE_ID")
	private long ruleTypeId;

	@Column(name="CG_CHN_ID")
	private long cgChnId;

	@Column(name="COMP_CHAIN_ID")
	private long compChainId;

	@Column(name="LST_UPDATE_BY")
	private String lstUpdateBy;

	@Column(name="RULESET_COMMENT")
	private String rulesetComment;

	@Column(name="RULESET_NAME")
	private String rulesetName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_LOGICALLY_DELETED")
	private Date logicallyDeletedDate;
	
	@OneToMany()
	@JoinColumn(name = "REA_RULESET_ID", referencedColumnName="REA_RULESET_ID")
	private List<ReactionRule> reaRules;

	public Long getReaRulesetId() {
		return this.reaRulesetId;
	}

	public void setReaRulesetId(Long reaRulesetId) {
		this.reaRulesetId = reaRulesetId;
	}

	public long getCgChnId() {
		return this.cgChnId;
	}

	public void setCgChnId(long cgChnId) {
		this.cgChnId = cgChnId;
	}

	public long getCompChainId() {
		return this.compChainId;
	}

	public void setCompChainId(long compChainId) {
		this.compChainId = compChainId;
	}

	public String getLstUpdateBy() {
		return this.lstUpdateBy;
	}

	public void setLstUpdateBy(String lstUpdateBy) {
		this.lstUpdateBy = lstUpdateBy;
	}

	public String getRulesetComment() {
		return this.rulesetComment;
	}

	public void setRulesetComment(String rulesetComment) {
		this.rulesetComment = rulesetComment;
	}

	public String getRulesetName() {
		return this.rulesetName;
	}

	public void setRulesetName(String rulesetName) {
		this.rulesetName = rulesetName;
	}

	public List<ReactionRule> getReaRules() {
		return this.reaRules;
	}

	public void setReaRules(List<ReactionRule> reaRules) {
		this.reaRules = reaRules;
	}

	public long getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(long ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public Date getLogicallyDeletedDate() {
		return logicallyDeletedDate;
	}

	public void setLogicallyDeletedDate(Date logicallyDeletedDate) {
		this.logicallyDeletedDate = logicallyDeletedDate;
	}

}