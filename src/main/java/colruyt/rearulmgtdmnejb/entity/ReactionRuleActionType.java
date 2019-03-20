package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REA_RULE_ACTTYPE")
public class ReactionRuleActionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReactionRuleActionTypePK id;

	public ReactionRuleActionTypePK getId() {
		return this.id;
	}

	public void setId(ReactionRuleActionTypePK id) {
		this.id = id;
	}

}