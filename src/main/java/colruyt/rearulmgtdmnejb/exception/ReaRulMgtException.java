package colruyt.rearulmgtdmnejb.exception;

public class ReaRulMgtException extends Exception {

	private static final long serialVersionUID = 8052530459097344948L;
	private final String errorCode;
	private final String errorMessage;
	private final Exception exception;

	public ReaRulMgtException(String errorCode, String errorMessage, Exception exception) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exception = exception;
	}
	
	public ReaRulMgtException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exception = null;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Exception getException() {
		return exception;
	}

}
