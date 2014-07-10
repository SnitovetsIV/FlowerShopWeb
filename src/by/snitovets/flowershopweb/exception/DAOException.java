package by.snitovets.flowershopweb.exception;

/**
 * Created by Илья on 05.07.2014.
 */
public class DAOException extends ProjectException {

    public DAOException() {
    }

    public DAOException(String s) {
        super(s);
    }

    public DAOException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DAOException(Throwable throwable) {
        super(throwable);
    }
}
