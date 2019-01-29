package colruyt.rearulmgtdmnejb.exception;

public class ReaRuleManagementException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorCode; 
	private String langCode;
	
	public ReaRuleManagementException(String message){
		super(message);
	}
	
	public ReaRuleManagementException(String langCode, String errorCode) {
		this.setErrorCode(errorCode);
		this.setLangCode(langCode);
	}

	public String getLangCode() {
		return langCode;
	}

	public final void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public final void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
