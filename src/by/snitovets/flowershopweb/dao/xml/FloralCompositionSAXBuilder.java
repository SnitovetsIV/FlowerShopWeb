package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.exception.DAOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * Created by Илья on 06.07.2014.
 */
public class FloralCompositionSAXBuilder extends AbstractFloralCompositionBuilder {

    private FloralCompositionHandler floralCompositionHandler;
    private XMLReader reader;

    public FloralCompositionSAXBuilder() throws DAOException {
        floralCompositionHandler = new FloralCompositionHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(floralCompositionHandler);
        } catch (SAXException e) {
            throw new DAOException("Parser configuration error. ", e);
        }
    }

    @Override
    public void buildFloralComposition(String fileName) throws DAOException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new DAOException("Parsing failure. ", e);
        } catch (IOException e) {
            throw new DAOException("File or I/O error. ", e);
        }
        floralComposition = floralCompositionHandler.getFloralComposition();
    }
}
