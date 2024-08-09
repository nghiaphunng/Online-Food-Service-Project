package BEAN;

public class ErrorSystem {
	private int errorId;
	private String errorType;
	private String errorDesc;
	private String errorOccurrenceTime;
	private int errorStatus;  //0: chưa xử lý 1:đang xử lý 2:xử lý xong
	public ErrorSystem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(int errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorOccurrenceTime() {
		return errorOccurrenceTime;
	}
	public void setErrorOccurrenceTime(String errorOccurrenceTime) {
		this.errorOccurrenceTime = errorOccurrenceTime;
	}
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}	
}
