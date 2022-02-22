package by.parakhnevich.bot.service.exception;

public class BadCurrencyException extends Exception{
    public BadCurrencyException() {
        super();
    }

    public BadCurrencyException(String message) {
        super(message);
    }

    public BadCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCurrencyException(Throwable cause) {
        super(cause);
    }

    protected BadCurrencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
