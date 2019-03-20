package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = FilteringRuleBo.class, name = ReactionRuleDmnConstants.RULE_TYPE_FILTERING),
		@Type(value = QuantityRuleBo.class, name = ReactionRuleDmnConstants.RULE_TYPE_QUANTITY),
		@Type(value = RecordingNotFoundRuleBo.class, name = ReactionRuleDmnConstants.RULE_TYPE_RECORD_NOT_FOUND),
		@Type(value = ProposeNotToReactRuleBo.class, name = ReactionRuleDmnConstants.RULE_TYPE_PROPOSED_NOT_REACT),
		@Type(value = ReactingRuleBo.class, name = ReactionRuleDmnConstants.RULE_TYPE_REACTING),
		@Type(value = ReactionPeriodRuleBo.class, name = ReactionRuleDmnConstants.RULE_TYPE_REACTION_PERIOD) })
public class GeneralRuleBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ruleName;
	private String assortmentName;
	private String comments;
	private String logonId;
	private Long ruleId;
	private Long rulesetId;
	private Long importanceCodeFrom;
	private Long importanceCodeTo;
	private Long productHierarchySetId;
	private boolean catchAll;
	private boolean cheapBrand;
	private boolean directBenefit;
	private boolean nationalBrand;
	private boolean ownBrand;
	private boolean permanentDuration;
	private boolean postponedBenefit;
	private boolean recalculate;
	private boolean temporaryDuration;
	private Date validFrom;
	private Date validTo;
	private RefRuleTypeBo refRuleTypeBo;
	private ReactionRulesetBo reactionRulesetBo;
	private List<RefActionTypeBo> actionTypeList;
	private List<ProductHierarchyElementBo> priceProductHierarchySet;
	private List<RefSourceTypeBo> sourceTypeList;
	private String langCode;
	private boolean sourceSelectAll;
	private boolean actionSelectAll;
	private String  updatedBy;
	private Date updatedOn;
	private String type;
	private Long rulePriority;
	public Long getRulePriority() {
		return rulePriority;
	}

	public void setRulePriority(Long rulePriority) {
		this.rulePriority = rulePriority;
	}

	private Long childRuleId;
	
	public Long getChildRuleId() {
		return childRuleId;
	}

	public void setChildRuleId(Long childRuleId) {
		this.childRuleId = childRuleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public boolean isSourceSelectAll() {
		return sourceSelectAll;
	}

	public void setSourceSelectAll(boolean sourceSelectAll) {
		this.sourceSelectAll = sourceSelectAll;
	}

	public boolean isActionSelectAll() {
		return actionSelectAll;
	}

	public void setActionSelectAll(boolean actionSelectAll) {
		this.actionSelectAll = actionSelectAll;
	}

	public Long getRulesetId() {
		return rulesetId;
	}

	public void setRulesetId(Long rulesetId) {
		this.rulesetId = rulesetId;
	}

	public List<RefActionTypeBo> getActionTypeList() {
		return actionTypeList;
	}

	public void setActionTypeList(List<RefActionTypeBo> actionTypeList) {
		this.actionTypeList = actionTypeList;
	}

	public String getAssortmentName() {
		return assortmentName;
	}

	public void setAssortmentName(String assortmentName) {
		this.assortmentName = assortmentName;
	}

	public boolean isCatchAll() {
		return catchAll;
	}

	public void setCatchAll(boolean catchAll) {
		this.catchAll = catchAll;
	}

	public boolean isCheapBrand() {
		return cheapBrand;
	}

	public void setCheapBrand(boolean cheapBrand) {
		this.cheapBrand = cheapBrand;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isDirectBenefit() {
		return directBenefit;
	}

	public void setDirectBenefit(boolean directBenefit) {
		this.directBenefit = directBenefit;
	}

	public Long getImportanceCodeFrom() {
		return importanceCodeFrom;
	}

	public void setImportanceCodeFrom(Long importanceCodeFrom) {
		this.importanceCodeFrom = importanceCodeFrom;
	}

	public Long getImportanceCodeTo() {
		return importanceCodeTo;
	}

	public void setImportanceCodeTo(Long importanceCodeTo) {
		this.importanceCodeTo = importanceCodeTo;
	}

	public String getLogonId() {
		return logonId;
	}

	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}

	public boolean isNationalBrand() {
		return nationalBrand;
	}

	public void setNationalBrand(boolean nationalBrand) {
		this.nationalBrand = nationalBrand;
	}

	public boolean isOwnBrand() {
		return ownBrand;
	}

	public void setOwnBrand(boolean ownBrand) {
		this.ownBrand = ownBrand;
	}

	public boolean isPermanentDuration() {
		return permanentDuration;
	}

	public void setPermanentDuration(boolean permanentDuration) {
		this.permanentDuration = permanentDuration;
	}

	public boolean isPostponedBenefit() {
		return postponedBenefit;
	}

	public void setPostponedBenefit(boolean postponedBenefit) {
		this.postponedBenefit = postponedBenefit;
	}

	public List<ProductHierarchyElementBo> getPriceProductHierarchySet() {
		return priceProductHierarchySet;
	}

	public void setPriceProductHierarchySet(List<ProductHierarchyElementBo> priceProductHierarchySet) {
		this.priceProductHierarchySet = priceProductHierarchySet;
	}

	public boolean isRecalculate() {
		return recalculate;
	}

	public void setRecalculate(boolean recalculate) {
		this.recalculate = recalculate;
	}


	public List<RefSourceTypeBo> getSourceTypeList() {
		return sourceTypeList;
	}

	public void setSourceTypeList(List<RefSourceTypeBo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}

	public boolean isTemporaryDuration() {
		return temporaryDuration;
	}

	public void setTemporaryDuration(boolean temporaryDuration) {
		this.temporaryDuration = temporaryDuration;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public RefRuleTypeBo getRefRuleTypeBo() {
		return refRuleTypeBo;
	}

	public void setRefRuleTypeBo(RefRuleTypeBo refRuleTypeBo) {
		this.refRuleTypeBo = refRuleTypeBo;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getProductHierarchySetId() {
		return productHierarchySetId;
	}

	public void setProductHierarchySetId(Long productHierarchySetId) {
		this.productHierarchySetId = productHierarchySetId;
	}

	public ReactionRulesetBo getReactionRulesetBo() {
		return reactionRulesetBo;
	}

	public void setReactionRulesetBo(ReactionRulesetBo reactionRulesetBo) {
		this.reactionRulesetBo = reactionRulesetBo;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	

}
