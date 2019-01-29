package colruyt.rearulmgtdmnejb.exception;

public class ServiceDownException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String langCode;
	private String message;
	private String errorCode; 
	
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	} 
	
	public ServiceDownException(String message){
		this.message=message;
	}

}
