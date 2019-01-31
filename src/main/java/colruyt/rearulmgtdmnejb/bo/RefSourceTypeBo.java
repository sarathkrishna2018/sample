package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @version 1.0
 * @created 28-nov-2018 9:14:37
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefSourceTypeBo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private long sourceTypeId;
	private String sourceName;

	public long getSourceTypeId() {
		return sourceTypeId;
	}

	public void setSourceTypeId(long sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}