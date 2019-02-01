/*package colruyt.rearulmgtdmnejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


*//**
 * The persistent class for the REF_REA_ACTIONTYPE database table.
 * 
 *//*
@Entity
@Table(name="REF_REA_ACTIONTYPE")
public class RefActionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_TYPE_ID")
	private long actionTypeId;

	@Column(name="ACTION_TYPE")
	private String actionType;

	private String description;

	private long seq;

	public long getActionTypeId() {
		return this.actionTypeId;
	}

	public void setActionTypeId(long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

}*/