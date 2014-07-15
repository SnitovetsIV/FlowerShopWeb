package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.exception.DAOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Илья on 06.07.2014.
 */
public class FloralCompositionSAXBuilder extends AbstractFloralCompositionBuilder {

    private static final Lock lock = new ReentrantLock();
    private static FloralCompositionSAXBuilder instance;
    private FloralCompositionHandler floralCompositionHandler;
    private XMLReader reader;

    private FloralCompositionSAXBuilder() throws DAOException {
        floralCompositionHandler = new FloralCompositionHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(floralCompositionHandler);
        } catch (SAXException e) {
            throw new DAOException("Parser configuration error. ", e);
        }
    }

    public static FloralCompositionSAXBuilder getInstance() throws DAOException {
        if (null == instance) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new FloralCompositionSAXBuilder();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public void buildFloralComposition(String fileName) throws DAOException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new DAOException("Parsing failure. " + e.getMessage(), e);
        } catch (IOException e) {
            throw new DAOException("File or I/O error. " + e.getMessage(), e);
        }
        floralComposition = floralCompositionHandler.getFloralComposition();
    }
}
