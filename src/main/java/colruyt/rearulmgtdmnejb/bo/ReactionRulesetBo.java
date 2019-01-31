package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReactionRulesetBo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long rulesetId;
	private long colruytGroupChainId;
	private long priceCompetitorChainId;
	private String comments;
	private String name;
	private RefRuleTypeBo refRuleTypeBo;
	private List<GeneralRuleBo> ruleLines;
	
	public long getColruytGroupChainId() {
		return colruytGroupChainId;
	}
	public void setColruytGroupChainId(long colruytGroupChainId) {
		this.colruytGroupChainId = colruytGroupChainId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPriceCompetitorChainId() {
		return priceCompetitorChainId;
	}
	public void setPriceCompetitorChainId(long priceCompetitorChainId) {
		this.priceCompetitorChainId = priceCompetitorChainId;
	}
	
	public RefRuleTypeBo getRefRuleTypeBo() {
		return refRuleTypeBo;
	}
	public void setRefRuleTypeBo(RefRuleTypeBo refRuleTypeBo) {
		this.refRuleTypeBo = refRuleTypeBo;
	}
	public Long getRulesetId() {
		return rulesetId;
	}
	public void setRulesetId(Long rulesetId) {
		this.rulesetId = rulesetId;
	}
	public List<GeneralRuleBo> getRuleLines() {
		return ruleLines;
	}
	public void setRuleLines(List<GeneralRuleBo> ruleLines) {
		this.ruleLines = ruleLines;
	}


}
