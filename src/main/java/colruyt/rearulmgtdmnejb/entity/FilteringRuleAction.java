package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REA_FLT_RULE")
public class FilteringRuleAction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "REA_RULE_ID")
	private long reactionRuleId;

	@Column(name = "MAX_COMP_QTY")
	private Double maxTimesRecordingProduct;

	@Column(name = "X_TIME_QTY")
	private Double maxTimesPriceArticle;

	public long getReactionRuleId() {
		return this.reactionRuleId;
	}

	public void setReactionRuleId(long reactionRuleId) {
		this.reactionRuleId = reactionRuleId;
	}

	public Double getMaxTimesRecordingProduct() {
		return this.maxTimesRecordingProduct;
	}

	public void setMaxTimesRecordingProduct(Double maxTimesRecordingProduct) {
		this.maxTimesRecordingProduct = maxTimesRecordingProduct;
	}

	public Double getMaxTimesPriceArticle() {
		return this.maxTimesPriceArticle;
	}

	public void setMaxTimesPriceArticle(Double maxTimesPriceArticle) {
		this.maxTimesPriceArticle = maxTimesPriceArticle;
	}

}