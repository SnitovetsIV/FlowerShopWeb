package by.snitovets.flowershopweb.logic.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Илья on 05.07.2014.
 */
public interface ICommand {

    public String execute(HttpServletRequest request);

}
