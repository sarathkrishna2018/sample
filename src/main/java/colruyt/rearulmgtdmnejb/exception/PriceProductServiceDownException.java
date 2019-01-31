package colruyt.rearulmgtdmnejb.exception;

public class PriceProductServiceDownException extends Exception{

private static final long serialVersionUID = 1L;

	
	private String message = "Price Product Service is Down";
	private String langCode;
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
	
	public PriceProductServiceDownException(Exception e){
		
	}
}
