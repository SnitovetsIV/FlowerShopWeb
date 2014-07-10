package by.snitovets.flowershopweb.logic.command;

import by.snitovets.flowershopweb.logic.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Илья on 08.07.2014.
 */
public class ShowFlowerCompositionCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        return page;
    }
}
