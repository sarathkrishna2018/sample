package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RULETYPE_LANG")
public class RefRuleType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefRuleTypeLangPK id;

	@Column(name = "RULETYPE_NAME")
	private String ruleTypeName;

	@Column(name = "DESCRIPTION")
	private String description;

	public RefRuleType() {

	}

	public RefRuleTypeLangPK getId() {
		return id;
	}

	public void setId(RefRuleTypeLangPK id) {
		this.id = id;
	}

	public String getRuleTypeName() {
		return ruleTypeName;
	}

	public void setRuleTypeName(String ruleTypeName) {
		this.ruleTypeName = ruleTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
