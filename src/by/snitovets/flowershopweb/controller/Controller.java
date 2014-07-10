package by.snitovets.flowershopweb.controller;

import by.snitovets.flowershopweb.exception.CommandException;
import by.snitovets.flowershopweb.logic.CommandFactory;
import by.snitovets.flowershopweb.logic.ConfigurationManager;
import by.snitovets.flowershopweb.logic.command.ICommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Илья on 01.07.2014.
 */
public class Controller extends HttpServlet implements javax.servlet.Servlet {

    public static final String PARAM_NAME_ERROR_MESSAGE = "errorMessage";
    public static final String PARAM_NAME_PAGE = "page";

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        //new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
        String page;
        HttpSession session = request.getSession();
        try {
            ICommand command = CommandFactory.getInstance().getCommand(request);
            page = command.execute(request, response);
            //?
        } catch (CommandException e) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            LOG.error(e);
        }
        session.setAttribute(PARAM_NAME_PAGE, page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}
