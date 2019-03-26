package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="REA_NREACT_SET_RSN")
public class ProposalNotToReactRuleReason implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProposalNotToReactRuleReasonPK id;
	
	@Column(name="LST_UPDATE_BY")
	private String lastUpdateBy;

	public ProposalNotToReactRuleReasonPK getId() {
		return id;
	}

	public void setId(ProposalNotToReactRuleReasonPK id) {
		this.id = id;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lstUpdateBy) {
		this.lastUpdateBy = lstUpdateBy;
	}

}
