package by.snitovets.flowershopweb.exception;

/**
 * Created by Илья on 05.07.2014.
 */
public class ProjectException extends Exception {

    public ProjectException() {
    }

    public ProjectException(String s) {
        super(s);
    }

    public ProjectException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProjectException(Throwable throwable) {
        super(throwable);
    }
}
