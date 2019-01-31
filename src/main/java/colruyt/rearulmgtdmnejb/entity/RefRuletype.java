package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the REF_RULETYPE database table.
 * 
 */
@Entity
@Table(name="REF_RULETYPE")
public class RefRuletype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RULETYPE_ID")
	private long ruletypeId;

	//bi-directional one-to-many association to RefRuletypeLang
	@OneToMany(mappedBy="refRuletype")
	private List<RefRuletypeLang> refRuletypeLang;

	public RefRuletype() {
	}

	public long getRuletypeId() {
		return this.ruletypeId;
	}

	public void setRuletypeId(long ruletypeId) {
		this.ruletypeId = ruletypeId;
	}

	public List<RefRuletypeLang> getRefRuletypeLang() {
		return refRuletypeLang;
	}

	public void setRefRuletypeLang(List<RefRuletypeLang> refRuletypeLang) {
		this.refRuletypeLang = refRuletypeLang;
	}
}