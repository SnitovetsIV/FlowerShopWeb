package by.snitovets.flowershopweb.exception;

/**
 * Created by Илья on 06.07.2014.
 */
public class CommandException extends ProjectException {

    public CommandException() {
    }

    public CommandException(String s) {
        super(s);
    }

    public CommandException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CommandException(Throwable throwable) {
        super(throwable);
    }
}
