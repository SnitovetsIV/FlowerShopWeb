package by.snitovets.flowershopweb.logic.command;

import by.snitovets.flowershopweb.dao.xml.XMLFloralCompositionDAO;
import by.snitovets.flowershopweb.entity.FloralComposition;
import by.snitovets.flowershopweb.exception.DAOException;
import by.snitovets.flowershopweb.logic.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Илья on 08.07.2014.
 */
public class ShowFlowerCompositionCommand implements ICommand {

    public static final String PARAM_NAME_PARSER = "parser";
    public static final String PARAM_NAME_FLORAL_COMPOSITION = "floralComposition";
    public static final String PARAM_NAME_WARNING = "warning";
    private static final Logger LOG = Logger.getLogger(ShowFlowerCompositionCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            String typeParser = request.getParameter(PARAM_NAME_PARSER);
            LOG.debug(typeParser);
            XMLFloralCompositionDAO dao = XMLFloralCompositionDAO.getInstance();
            dao.setBuilder(typeParser);
            FloralComposition floralComposition = dao.getFloralComposition();
            if (floralComposition == null) {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
                request.setAttribute(PARAM_NAME_WARNING, "XML is not validate by XSD!");
            } else {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_PAGE_PATH);
                HttpSession session = request.getSession();
                session.setAttribute(PARAM_NAME_FLORAL_COMPOSITION, floralComposition);
                session.setAttribute(PARAM_NAME_PARSER, typeParser);
            }
        } catch (DAOException e) {
            LOG.error(e.getMessage());
            request.setAttribute(PARAM_NAME_WARNING, e.getMessage());
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        }

        return page;
    }
}
