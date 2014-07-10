package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.dao.FloralCompositionDAO;
import by.snitovets.flowershopweb.dao.xml.validator.XMLValidator;
import by.snitovets.flowershopweb.entity.FloralComposition;
import by.snitovets.flowershopweb.exception.DAOException;
import by.snitovets.flowershopweb.logic.ConfigurationManager;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Илья on 07.07.2014.
 */
public class XMLFloralCompositionDAO implements FloralCompositionDAO {

    private static final Logger LOG = Logger.getLogger(XMLFloralCompositionDAO.class);
    private static final Lock lock = new ReentrantLock();
    private static XMLFloralCompositionDAO instance;
    private AbstractFloralCompositionBuilder builder;

    private XMLFloralCompositionDAO() throws DAOException {
        builder = new FloralCompositionSAXBuilder();
    }

    private XMLFloralCompositionDAO(String parserType) throws DAOException {

    }

    public static XMLFloralCompositionDAO getInstance() throws DAOException {
        if (null == instance) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new XMLFloralCompositionDAO();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static XMLFloralCompositionDAO getInstance(String parserType) throws DAOException {
        if (null == instance) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new XMLFloralCompositionDAO();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void setBuilder(AbstractFloralCompositionBuilder builder) {
        lock.lock();
        try {
            this.builder = builder;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public FloralComposition getFloralComposition() {
        FloralComposition floralComposition = null;
        lock.lock();
        try {
            String pathXML = ConfigurationManager.getInstance().getProperty(ConfigurationManager.XML_FILE_PATH);
            String pathXSD = ConfigurationManager.getInstance().getProperty(ConfigurationManager.XSD_FILE_PATH);
            if (!XMLValidator.validateXMLbyXSD(pathXML, pathXSD)) {
                LOG.error(pathXML + " is invalid by " + pathXSD);
            } else {
                builder.buildFloralComposition(pathXML);
                floralComposition = builder.getFloralComposition();
            }
        } catch (DAOException e) {
            LOG.error("DAOException = " + e.getMessage());
        } finally {
            lock.unlock();
        }
        return floralComposition;
    }

    public enum ParserType {
        SAX,
        StAX,
        DOM
    }
}
