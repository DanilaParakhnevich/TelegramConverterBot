package by.parakhnevich.bot.service.exception;

public class BadNumberException extends Exception{
    public BadNumberException() {
        super();
    }

    public BadNumberException(String message) {
        super(message);
    }

    public BadNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadNumberException(Throwable cause) {
        super(cause);
    }

    protected BadNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
