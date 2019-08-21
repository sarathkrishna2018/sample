package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.google.common.collect.Lists;

import colruyt.rearulmgtdmnejb.enums.ActionType;
import colruyt.rearulmgtdmnejb.enums.SourceType;

@Entity
@Table(name = "REA_RULE")
public class ReactionRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "REA_RULE_SEQ_GEN", allocationSize=1, sequenceName = "SEQ_REA_RULE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REA_RULE_SEQ_GEN")
	@Column(name = "REA_RULE_ID")
	private long reaRuleId;

	@Column(name = "REA_RULESET_ID")
	private long reaRulesetId;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "DIRECT_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean direct;

	@Column(name = "IC_FROM")
	private long importancecodeFrom;

	@Column(name = "IC_TO")
	private long importancecodeTo;

	@Column(name = "LST_UPDATE_BY")
	private String lastUpdateBy;

	@Column(name = "PERMENANT_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean permenant;

	@Column(name = "POSTPONED_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean postponed;

	@Column(name = "RECALCULATE_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean recalculate;

	@Column(name = "RULE_COMMENT")
	private String ruleComment;

	@Column(name = "RULE_NAME")
	private String ruleName;

	@Column(name = "RULE_PRIORITY")
	private long rulePriority;

	@Column(name = "TEMPORARY_YN")
	@ExternalValues({ "true=Y", "false=N" })
	@Type(String.class)
	private boolean temporary;

	@Temporal(TemporalType.DATE)
	@Column(name = "VALID_FROM")
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "VALID_UPTO")
	private Date validUpto;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_LOGICALLY_DELETED")
	private Date logicallyDeletedDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private FilteringRuleAction filteringRuleAction;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private ProposalNotToReactRuleAction proposalNotToReactRuleAction;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private ReactionPeriodRuleAction reactionPeriodRuleAction;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private QuantityRuleAction quantityRuleAction;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private ReactingRuleAction reactingRuleAction;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private RecordingNotFoundRuleAction recordingNotFoundRuleAction;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "REA_RULE_ID", referencedColumnName = "REA_RULE_ID")
	private List<PriceProductHierarchySet> priceProductHierarchySet;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "REA_RULE_SRC", joinColumns = @JoinColumn(name = "REA_RULE_ID"))
	@Column(name = "SOURCE_ID")
	private List<Integer> refSourceTypes;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "REA_RULE_ACTTYPE", joinColumns = @JoinColumn(name = "REA_RULE_ID"))
	@Column(name = "ACTION_TYPE_ID")
	private List<Integer> refActionTypes;

	public List<SourceType> getRefSourceTypes() {
		List<SourceType> sourceTypes = Lists.newArrayList();
		for (Integer sourceTypeId : refSourceTypes) {
			sourceTypes.add(SourceType.forValue(sourceTypeId));
		}
		return sourceTypes;
	}

	public void setRefSourceTypes(List<SourceType> sourceTypes) {
		this.refSourceTypes = Lists.newArrayList();
		for (SourceType sourceType : sourceTypes) {
			this.refSourceTypes.add(sourceType.getSourceTypeId());
		}
	}

	public List<ActionType> getRefActionTypes() {
		List<ActionType> actionTypes = Lists.newArrayList();
		for (Integer actionTypeId : refActionTypes) {
			actionTypes.add(ActionType.forValue(actionTypeId));
		}
		return actionTypes;
	}

	public void setRefActionTypes(List<ActionType> actionTypes) {
		this.refActionTypes = Lists.newArrayList();
		for (ActionType actionType : actionTypes) {
			this.refActionTypes.add(actionType.getActionTypeId());
		}
	}

	@Column(name = "LST_UPDATE_TS", insertable = false, updatable = false)
	private Date updatedOn;

	@Column(name = "C_REA_RULE_ID")
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

	public long getImportancecodeFrom() {
		return importancecodeFrom;
	}

	public void setImportancecodeFrom(long icFrom) {
		this.importancecodeFrom = icFrom;
	}

	public long getImportancecodeTo() {
		return importancecodeTo;
	}

	public void setImportancecodeTo(long icTo) {
		this.importancecodeTo = icTo;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean getDirect() {
		return this.direct;
	}

	public void setDirect(boolean directYn) {
		this.direct = directYn;
	}

	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(String lstUpdateBy) {
		this.lastUpdateBy = lstUpdateBy;
	}

	public boolean getPermenant() {
		return this.permenant;
	}

	public void setPermenant(boolean permenantYn) {
		this.permenant = permenantYn;
	}

	public boolean getPostponed() {
		return this.postponed;
	}

	public void setPostponed(boolean postponedYn) {
		this.postponed = postponedYn;
	}

	public boolean getRecalculate() {
		return this.recalculate;
	}

	public void setRecalculate(boolean recalculateYn) {
		this.recalculate = recalculateYn;
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

	public boolean getTemporary() {
		return this.temporary;
	}

	public void setTemporary(boolean temporaryYn) {
		this.temporary = temporaryYn;
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

	public FilteringRuleAction getFilteringRuleAction() {
		return this.filteringRuleAction;
	}

	public void setFilteringRuleAction(FilteringRuleAction reaFltRule) {
		this.filteringRuleAction = reaFltRule;
	}

	public ProposalNotToReactRuleAction getProposalNotToReactRuleAction() {
		return this.proposalNotToReactRuleAction;
	}

	public void setProposalNotToReactRuleAction(ProposalNotToReactRuleAction reaNreactAct) {
		this.proposalNotToReactRuleAction = reaNreactAct;
	}

	public List<PriceProductHierarchySet> getPriceProductHierarchySet() {
		return this.priceProductHierarchySet;
	}

	public void setPriceProductHierarchySet(List<PriceProductHierarchySet> reaPpdHchysets) {
		this.priceProductHierarchySet = reaPpdHchysets;
	}

	public ReactionPeriodRuleAction getReactionPeriodRuleAction() {
		return this.reactionPeriodRuleAction;
	}

	public void setReactionPeriodRuleAction(ReactionPeriodRuleAction reaPrdAct) {
		this.reactionPeriodRuleAction = reaPrdAct;
	}

	public QuantityRuleAction getQuantityRuleAction() {
		return this.quantityRuleAction;
	}

	public void setQuantityRuleAction(QuantityRuleAction reaQtyRule) {
		this.quantityRuleAction = reaQtyRule;
	}

	public ReactingRuleAction getReactingRuleAction() {
		return this.reactingRuleAction;
	}

	public void setReactingRuleAction(ReactingRuleAction reaReactingAct) {
		this.reactingRuleAction = reaReactingAct;
	}

	public RecordingNotFoundRuleAction getRecordingNotFoundRuleAction() {
		return this.recordingNotFoundRuleAction;
	}

	public void setRecordingNotFoundRuleAction(RecordingNotFoundRuleAction reaRnfAct) {
		this.recordingNotFoundRuleAction = reaRnfAct;
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