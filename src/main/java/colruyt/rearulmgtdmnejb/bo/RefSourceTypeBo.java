package colruyt.rearulmgtdmnejb.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefSourceTypeBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int sourceTypeId;
	private String sourceName;

	public long getSourceTypeId() {
		return sourceTypeId;
	}

	public void setSourceTypeId(int sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}