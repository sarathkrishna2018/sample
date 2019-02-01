package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.openjpa.persistence.ExternalValues;
import org.apache.openjpa.persistence.Type;

/**
 * The persistent class for the REA_RULE database table.
 * 
 */
@Entity
@Table(name="REA_RULE")
public class ReactionRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REA_RULE_SEQ_GEN", sequenceName="SEQ_REA_RULE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REA_RULE_SEQ_GEN")
	@Column(name="REA_RULE_ID")
	private long reaRuleId;
	
	@Column(name="REA_RULESET_ID")
	private long reaRulesetId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="DIRECT_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean directYn;

	@Column(name="IC_FROM")
	private long icFrom;

	@Column(name="IC_TO")
	private long icTo;

	@Column(name="LST_UPDATE_BY")
	private String lstUpdateBy;

	@Column(name="PERMENANT_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean permenantYn;

	@Column(name="POSTPONED_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean postponedYn;

	@Column(name="RECALCULATE_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean recalculateYn;

	@Column(name="RULE_COMMENT")
	private String ruleComment;

	@Column(name="RULE_NAME")
	private String ruleName;

	@Column(name="RULE_PRIORITY")
	private long rulePriority;

	@Column(name="TEMPORARY_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean temporaryYn;

	@Temporal(TemporalType.DATE)
	@Column(name="VALID_FROM")
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="VALID_UPTO")
	private Date validUpto;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_LOGICALLY_DELETED")
	private Date logicallyDeletedDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
    private FilteringRuleAction reaFltRule;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
	private ProposalNotToReactRuleAction reaNreactAct;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
	private ReactionPeriodRuleAction reaPrdAct;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
	private QuantityRuleAction reaQtyRule;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
	private ReactingRuleAction reaReactingAct;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
	private RecordingNotFoundRuleAction reaRnfAct;
	
	@OneToMany
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName="REA_RULE_ID")
	private List<PriceProductHierarchySet> reaPpdHchysets;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "REA_RULE_SRC", 
			joinColumns = @JoinColumn(name = "REA_RULE_ID"))
	@Column(name = "SOURCE_ID")
	private List<Long> refSourceTypes;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "REA_RULE_ACTTYPE", 
			joinColumns = @JoinColumn(name = "REA_RULE_ID"))
	@Column(name = "ACTION_TYPE_ID")
	private List<Long> refActionTypes;
	
	public List<Long> getRefSourceTypes() {
		return refSourceTypes;
	}

	public void setRefSourceTypes(List<Long> refSourceTypes) {
		this.refSourceTypes = refSourceTypes;
	}

	public List<Long> getRefActionTypes() {
		return refActionTypes;
	}

	public void setRefActionTypes(List<Long> refActionTypes) {
		this.refActionTypes = refActionTypes;
	}

	@Column(name="LST_UPDATE_TS",insertable = false, updatable = false)
	private Date updatedOn;
	
	@Column(name="C_REA_RULE_ID")
	private Long childRuleId; 
	
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public long getReaRuleId() {
		return reaRuleId;
	}

	public void setReaRuleId(long reaRuleId) {
		this.reaRuleId = reaRuleId;
	}

	public long getReaRulesetId() {
		return reaRulesetId;
	}

	public void setReaRulesetId(long reaRulesetId) {
		this.reaRulesetId = reaRulesetId;
	}

	public long getIcFrom() {
		return icFrom;
	}

	public void setIcFrom(long icFrom) {
		this.icFrom = icFrom;
	}

	public long getIcTo() {
		return icTo;
	}

	public void setIcTo(long icTo) {
		this.icTo = icTo;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean getDirectYn() {
		return this.directYn;
	}

	public void setDirectYn(boolean directYn) {
		this.directYn = directYn;
	}

	public String getLstUpdateBy() {
		return this.lstUpdateBy;
	}

	public void setLstUpdateBy(String lstUpdateBy) {
		this.lstUpdateBy = lstUpdateBy;
	}

	public boolean getPermenantYn() {
		return this.permenantYn;
	}

	public void setPermenantYn(boolean permenantYn) {
		this.permenantYn = permenantYn;
	}

	public boolean getPostponedYn() {
		return this.postponedYn;
	}

	public void setPostponedYn(boolean postponedYn) {
		this.postponedYn = postponedYn;
	}

	public boolean getRecalculateYn() {
		return this.recalculateYn;
	}

	public void setRecalculateYn(boolean recalculateYn) {
		this.recalculateYn = recalculateYn;
	}

	public String getRuleComment() {
		return this.ruleComment;
	}

	public void setRuleComment(String ruleComment) {
		this.ruleComment = ruleComment;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public long getRulePriority() {
		return this.rulePriority;
	}

	public void setRulePriority(long rulePriority) {
		this.rulePriority = rulePriority;
	}

	public boolean getTemporaryYn() {
		return this.temporaryYn;
	}

	public void setTemporaryYn(boolean temporaryYn) {
		this.temporaryYn = temporaryYn;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidUpto() {
		return this.validUpto;
	}

	public void setValidUpto(Date validUpto) {
		this.validUpto = validUpto;
	}

	public FilteringRuleAction getReaFltRule() {
		return this.reaFltRule;
	}

	public void setReaFltRule(FilteringRuleAction reaFltRule) {
		this.reaFltRule = reaFltRule;
	}

	public ProposalNotToReactRuleAction getReaNreactAct() {
		return this.reaNreactAct;
	}

	public void setReaNreactAct(ProposalNotToReactRuleAction reaNreactAct) {
		this.reaNreactAct = reaNreactAct;
	}

	public List<PriceProductHierarchySet> getReaPpdHchysets() {
		return this.reaPpdHchysets;
	}

	public void setReaPpdHchysets(List<PriceProductHierarchySet> reaPpdHchysets) {
		this.reaPpdHchysets = reaPpdHchysets;
	}

	public ReactionPeriodRuleAction getReaPrdAct() {
		return this.reaPrdAct;
	}

	public void setReaPrdAct(ReactionPeriodRuleAction reaPrdAct) {
		this.reaPrdAct = reaPrdAct;
	}

	public QuantityRuleAction getReaQtyRule() {
		return this.reaQtyRule;
	}

	public void setReaQtyRule(QuantityRuleAction reaQtyRule) {
		this.reaQtyRule = reaQtyRule;
	}

	public ReactingRuleAction getReaReactingAct() {
		return this.reaReactingAct;
	}

	public void setReaReactingAct(ReactingRuleAction reaReactingAct) {
		this.reaReactingAct = reaReactingAct;
	}

	public RecordingNotFoundRuleAction getReaRnfAct() {
		return this.reaRnfAct;
	}

	public void setReaRnfAct(RecordingNotFoundRuleAction reaRnfAct) {
		this.reaRnfAct = reaRnfAct;
	}
	
	public Date getLogicallyDeletedDate() {
		return logicallyDeletedDate;
	}

	public void setLogicallyDeletedDate(Date logicallyDeletedDate) {
		this.logicallyDeletedDate = logicallyDeletedDate;
	}

	public Long getChildRuleId() {
		return childRuleId;
	}

	public void setChildRuleId(Long childRuleId) {
		this.childRuleId = childRuleId;
	}

	
}