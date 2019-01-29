package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="REA_NREACT_SET_RSN")
public class ProposalNotToReactRuleActionRsn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProposalNotToReactRuleActionRsnPK id;
	
	@Column(name="LST_UPDATE_BY")
	private String lstUpdateBy;

	public ProposalNotToReactRuleActionRsnPK getId() {
		return id;
	}

	public void setId(ProposalNotToReactRuleActionRsnPK id) {
		this.id = id;
	}

	public String getLstUpdateBy() {
		return lstUpdateBy;
	}

	public void setLstUpdateBy(String lstUpdateBy) {
		this.lstUpdateBy = lstUpdateBy;
	}

}
