package colruyt.rearulmgtdmnejb.exception;

public class RRMDomainException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String langCode;
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
	
	public RRMDomainException(String message){
		this.message=message;
	}

}
