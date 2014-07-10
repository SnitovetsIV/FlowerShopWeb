package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.entity.FloralComposition;
import by.snitovets.flowershopweb.entity.flower.*;
import by.snitovets.flowershopweb.entity.packaging.AbstractFlowerPackaging;
import by.snitovets.flowershopweb.entity.packaging.FlowerBasket;
import by.snitovets.flowershopweb.entity.packaging.PackagingPaper;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * Created by Илья on 08.07.2014.
 */
public class FloralCompositionHandler extends DefaultHandler {

    private static final Logger LOG = Logger.getLogger(FloralCompositionHandler.class);
    private FloralComposition floralComposition;
    private AbstractFlower currentFlower;
    private AbstractFlowerPackaging currentFlowerPackaging;
    private String currentTag;


    public FloralCompositionHandler() {
        floralComposition = new FloralComposition();
    }

    public FloralComposition getFloralComposition() {
        return floralComposition;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        switch (TagConstants.valueOf(localName)) {
            case ARTIFICIAL_FLOWER_TAG:
                int id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlower = new ArtificialFlower(id);
                break;
            case NATURAL_FLOWER_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlower = new NaturalFlower(id);
                break;
            case CUT_FLOWER_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlower = new CutFlower(id);
                break;
            case FLOWER_BASKET_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlowerPackaging = new FlowerBasket(id);
                break;
            case PACKAGING_PAPER_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE.getValue()));
                currentFlowerPackaging = new PackagingPaper(id);
                break;
            default:
                currentTag = localName;
        }
    }

    private int getID(String value) {
        Scanner scanner = new Scanner(value);
        scanner.useDelimiter("S");
        return scanner.nextInt();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (TagConstants.valueOf(localName)) {
            case ARTIFICIAL_FLOWER_TAG:
            case NATURAL_FLOWER_TAG:
            case CUT_FLOWER_TAG:
                floralComposition.addFlower(currentFlower);
                currentFlower = null;
                break;
            case FLOWER_BASKET_TAG:
            case PACKAGING_PAPER_TAG:
                floralComposition.setFlowerPackaging(currentFlowerPackaging);
                currentFlowerPackaging = null;
                break;
            default:
                currentTag = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        switch (TagConstants.valueOf(currentTag)) {
            case PRICE_TAG:
                if (currentFlower != null) {
                    currentFlower.setPrice(Double.parseDouble(value));
                } else if (currentFlowerPackaging != null) {
                    currentFlowerPackaging.setPrice(Double.parseDouble(value));
                }
                break;
            case COLOR_TAG:
                try {
                    Field field = Color.class.getField(value);
                    Color color = (Color) field.get(null);
                    currentFlower.setColor(color);
                } catch (IllegalAccessException e) {
                    LOG.warn("IllegalAccessException. " + e.getMessage());
                } catch (NoSuchFieldException e) {
                    LOG.warn("NoSuchFieldException." + e.getMessage());
                }
                break;
            case STEM_LENGTH_TAG:
                currentFlower.setStemLength(Integer.parseInt(value));
                break;
            case REPRODUCTION_TAG:
                ((NaturalFlower) currentFlower).setReproductionType(ReproductionType.valueOf(value.toUpperCase()));
                break;
            case STORAGE_TIME_TAG:
                ((CutFlower) currentFlower).setStorageTime(Integer.parseInt(value));
                break;
            case MATERIAL_TAG:
                if (currentFlower != null) {
                    ((ArtificialFlower) currentFlower).setMaterial(value);
                } else if (currentFlowerPackaging != null) {
                    currentFlowerPackaging.setMaterial(value);
                }
                break;
            case HEIGHT_TAG:
                ((FlowerBasket) currentFlowerPackaging).setHeight(Integer.parseInt(value));
                break;
            case DIAMETER_TAG:
                ((FlowerBasket) currentFlowerPackaging).setDiameter(Integer.parseInt(value));
                break;
            case LENGTH_TAG:
                ((PackagingPaper) currentFlowerPackaging).setLength(Integer.parseInt(value));
                break;
            default:
                throw new SAXException("Bad id value.");
        }
    }
}
