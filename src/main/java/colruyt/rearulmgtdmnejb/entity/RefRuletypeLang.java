package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the REF_RULETYPE_LANG database table.
 * 
 */
@Entity
@Table(name="RULETYPE_LANG")
public class RefRuletypeLang implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefRuleTypePK id;

	private String description;
	
	@Column(name="RULETYPE_NAME")
	private String ruletypeName;

	//bi-directional many-to-one association to RefRuletype
	@ManyToOne
	@JoinColumn(name="RULETYPE_ID")
	private RefRuletype refRuletype;

	public RefRuletypeLang() {
	}
	
	public RefRuleTypePK getId() {
		return id;
	}

	public void setId(RefRuleTypePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRuletypeName() {
		return this.ruletypeName;
	}

	public void setRuletypeName(String ruletypeName) {
		this.ruletypeName = ruletypeName;
	}

	public RefRuletype getRefRuletype() {
		return this.refRuletype;
	}

	public void setRefRuletype(RefRuletype refRuletype) {
		this.refRuletype = refRuletype;
	}

}