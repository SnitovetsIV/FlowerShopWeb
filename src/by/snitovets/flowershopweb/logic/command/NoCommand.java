package by.snitovets.flowershopweb.logic.command;

import by.snitovets.flowershopweb.exception.CommandException;
import by.snitovets.flowershopweb.logic.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Илья on 05.07.2014.
 */
public class NoCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        //String page = (String) session.getAttribute(FlowerShopController.PARAM_NAME_PAGE);
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        LOG.debug("No command: page = " + page);
        return page;
    }
}
