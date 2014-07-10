package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.entity.flower.*;
import by.snitovets.flowershopweb.entity.packaging.AbstractFlowerPackaging;
import by.snitovets.flowershopweb.entity.packaging.FlowerBasket;
import by.snitovets.flowershopweb.entity.packaging.PackagingPaper;
import by.snitovets.flowershopweb.exception.DAOException;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * Created by Илья on 06.07.2014.
 */
public class FloralCompositionStAXBuilder extends AbstractFloralCompositionBuilder {

    private static final Logger LOG = Logger.getLogger(FloralCompositionStAXBuilder.class);
    AbstractFlower currentFlower;
    AbstractFlowerPackaging currentFlowerPackaging;
    String tagContent = "";
    private XMLInputFactory factory;

    public FloralCompositionStAXBuilder() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildFloralComposition(String fileName) throws DAOException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = factory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement(reader);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        endElement(reader);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("FileNotFoundException " + fileName + " : " + e.getMessage());
        } catch (XMLStreamException e) {
            throw new DAOException("XMLStreamException : " + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error("Impossible close file " + fileName + " : " + e.getMessage());
            }
        }
    }

    private void startElement(XMLStreamReader reader) throws DAOException {
        switch (TagConstants.valueOf(reader.getLocalName())) {
            case ARTIFICIAL_FLOWER_TAG:
                int id = getID(reader.getAttributeValue(null, TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlower = new ArtificialFlower(id);
                floralComposition.addFlower(currentFlower);
                break;
            case NATURAL_FLOWER_TAG:
                id = getID(reader.getAttributeValue(null, TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlower = new NaturalFlower(id);
                floralComposition.addFlower(currentFlower);
                break;
            case CUT_FLOWER_TAG:
                id = getID(reader.getAttributeValue(null, TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlower = new CutFlower(id);
                floralComposition.addFlower(currentFlower);
                break;
            case FLOWER_BASKET_TAG:
                id = getID(reader.getAttributeValue(null, TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlowerPackaging = new FlowerBasket(id);
                floralComposition.setFlowerPackaging(currentFlowerPackaging);
                break;
            case PACKAGING_PAPER_TAG:
                id = getID(reader.getAttributeValue(null, TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlowerPackaging = new PackagingPaper(id);
                floralComposition.setFlowerPackaging(currentFlowerPackaging);
                break;
            default:
                throw new DAOException("Bad start element.");
        }
    }

    private void endElement(XMLStreamReader reader) throws DAOException {
        switch (TagConstants.valueOf(reader.getLocalName())) {
            case PRICE_TAG:
                if (currentFlower != null) {
                    currentFlower.setPrice(Double.parseDouble(tagContent));
                } else if (currentFlowerPackaging != null) {
                    currentFlowerPackaging.setPrice(Double.parseDouble(tagContent));
                }
                break;
            case COLOR_TAG:
                try {
                    Field field = Color.class.getField(tagContent);
                    Color color = (Color) field.get(null);
                    currentFlower.setColor(color);
                } catch (IllegalAccessException e) {
                    LOG.warn("IllegalAccessException. " + e.getMessage());
                } catch (NoSuchFieldException e) {
                    LOG.warn("NoSuchFieldException." + e.getMessage());
                }
                break;
            case STEM_LENGTH_TAG:
                currentFlower.setStemLength(Integer.parseInt(tagContent));
                break;
            case REPRODUCTION_TAG:
                ((NaturalFlower) currentFlower).setReproductionType(ReproductionType.valueOf(tagContent.toUpperCase()));
                break;
            case STORAGE_TIME_TAG:
                ((CutFlower) currentFlower).setStorageTime(Integer.parseInt(tagContent));
                break;
            case MATERIAL_TAG:
                if (currentFlower != null) {
                    ((ArtificialFlower) currentFlower).setMaterial(tagContent);
                } else if (currentFlowerPackaging != null) {
                    currentFlowerPackaging.setMaterial(tagContent);
                }
                break;
            case HEIGHT_TAG:
                ((FlowerBasket) currentFlowerPackaging).setHeight(Integer.parseInt(tagContent));
                break;
            case DIAMETER_TAG:
                ((FlowerBasket) currentFlowerPackaging).setDiameter(Integer.parseInt(tagContent));
                break;
            case LENGTH_TAG:
                ((PackagingPaper) currentFlowerPackaging).setLength(Integer.parseInt(tagContent));
                break;
            default:
                throw new DAOException("Bad tag element.");
        }
    }

    private int getID(String value) throws DAOException {
        Scanner scanner = new Scanner(value);
        scanner.useDelimiter("S");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            throw new DAOException("Bad id value.");
        }
    }
}
