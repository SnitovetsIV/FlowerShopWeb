package by.snitovets.flowershopweb.dao.xml.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Илья on 08.07.2014.
 */
public class FloralCompositionErrorHandler extends DefaultHandler {

    private static final Logger LOG = Logger.getLogger(FloralCompositionErrorHandler.class);
    private boolean errors;

    @Override
    public void warning(SAXParseException e) {
        LOG.warn(getLineAddress(e) + "-" + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        LOG.error(getLineAddress(e) + " - " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        LOG.fatal(getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        errors = true;
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

    public boolean isErrors() {
        return errors;
    }
}
