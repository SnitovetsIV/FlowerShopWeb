package by.snitovets.flowershopweb.dao.xml.validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by Илья on 07.07.2014.
 */
public class XMLValidator {
    private static final Logger LOG = Logger.getLogger(XMLValidator.class);

    //может ещё быть, допустим, DTD...
    public static boolean validateXMLbyXSD(String pathXML, String pathXSD) {
        boolean result = false;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File(pathXSD);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            FloralCompositionErrorHandler errorHandler = new FloralCompositionErrorHandler();
            validator.setErrorHandler(errorHandler);
            Source source = new StreamSource(pathXML);
            validator.validate(source);
            if (!errorHandler.isErrors()) {
                result = true;
            }
            LOG.info("Validation is ended.");
        } catch (SAXException e) {
            LOG.error("Validation " + pathXML + " is not valid because " + e.getMessage());
        } catch (IOException e) {
            LOG.error(pathXML + " is not valid because " + e.getMessage());
        }
        return result;
    }

}
