package by.snitovets.flowershopweb.logic.command;

import by.snitovets.flowershopweb.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Илья on 05.07.2014.
 */
public interface ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

}
