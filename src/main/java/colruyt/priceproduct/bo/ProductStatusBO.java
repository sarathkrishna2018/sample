package colruyt.priceproduct.bo;

import java.io.Serializable;

public class ProductStatusBO implements Serializable {

	private static final long serialVersionUID = 1L;

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	private String code;
	private String msgKey;

	public String getCode() {
		return code;
	}

	public String getMsgKey() {
		return msgKey;
	}

}
