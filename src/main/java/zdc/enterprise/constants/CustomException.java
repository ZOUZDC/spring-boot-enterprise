package zdc.enterprise.constants;

public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
    public CustomException(ResultCode resultCode) {
        super(resultCode.msg());
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
