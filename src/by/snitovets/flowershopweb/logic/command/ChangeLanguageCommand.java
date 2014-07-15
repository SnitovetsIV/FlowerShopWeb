package by.snitovets.flowershopweb.logic.command;

import by.snitovets.flowershopweb.controller.FlowerShopController;
import by.snitovets.flowershopweb.logic.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Илья on 10.07.2014.
 */
public class ChangeLanguageCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);
    public static String PARAM_NAME_LANGUAGE = "lang";
    public static String PARAM_NAME_LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(PARAM_NAME_LANGUAGE);
        HttpSession session = request.getSession();
        Locale locale = new Locale(language);
        session.setAttribute(PARAM_NAME_LOCALE, locale);
        LOG.info("Language changed to " + locale);
        String page = (String) session.getAttribute(FlowerShopController.PARAM_NAME_PAGE);
        if (null == page) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        }
        return page;
    }

}
