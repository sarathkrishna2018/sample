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

@Entity
@Table(name = "REA_RULESET")
public class ReactionRuleSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REA_RULESET_ID")
	@SequenceGenerator(name = "REA_RULESET_SEQ_GEN", allocationSize=1, sequenceName = "SEQ_REA_RULESET")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REA_RULESET_SEQ_GEN")
	private long reaRulesetId;

	@Column(name = "RULETYPE_ID")
	private int ruleTypeId;

	@Column(name = "CG_CHN_ID")
	private long colruytGroupChainId;

	@Column(name = "COMP_CHAIN_ID")
	private long priceCompetitorChainId;

	@Column(name = "LST_UPDATE_BY")
	private String lastUpdateBy;

	@Column(name = "RULESET_COMMENT")
	private String rulesetComment;

	@Column(name = "RULESET_NAME")
	private String rulesetName;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_LOGICALLY_DELETED")
	private Date logicallyDeletedDate;

	@OneToMany()
	@JoinColumn(name = "REA_RULESET_ID", referencedColumnName = "REA_RULESET_ID")
	private List<ReactionRule> reactionRules;

	public Long getReaRulesetId() {
		return this.reaRulesetId;
	}

	public void setReaRulesetId(Long reaRulesetId) {
		this.reaRulesetId = reaRulesetId;
	}

	public long getColruytGroupChainId() {
		return this.colruytGroupChainId;
	}

	public void setColruytGroupChainId(long cgChnId) {
		this.colruytGroupChainId = cgChnId;
	}

	public long getPriceCompetitorChainId() {
		return this.priceCompetitorChainId;
	}

	public void setPriceCompetitorChainId(long compChainId) {
		this.priceCompetitorChainId = compChainId;
	}

	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(String lstUpdateBy) {
		this.lastUpdateBy = lstUpdateBy;
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

	public List<ReactionRule> getReactionRules() {
		return this.reactionRules;
	}

	public void setReactionRules(List<ReactionRule> reaRules) {
		this.reactionRules = reaRules;
	}

	public int getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(int ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public Date getLogicallyDeletedDate() {
		return logicallyDeletedDate;
	}

	public void setLogicallyDeletedDate(Date logicallyDeletedDate) {
		this.logicallyDeletedDate = logicallyDeletedDate;
	}

}