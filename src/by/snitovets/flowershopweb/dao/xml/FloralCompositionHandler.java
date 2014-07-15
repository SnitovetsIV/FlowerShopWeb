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
        switch (localName) {
            case TagConstants.ARTIFICIAL_FLOWER_TAG:
                int id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE));
                currentFlower = new ArtificialFlower(id);
                break;
            case TagConstants.NATURAL_FLOWER_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE));
                currentFlower = new NaturalFlower(id);
                break;
            case TagConstants.CUT_FLOWER_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE));
                currentFlower = new CutFlower(id);
                break;
            case TagConstants.FLOWER_BASKET_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE));
                currentFlowerPackaging = new FlowerBasket(id);
                break;
            case TagConstants.PACKAGING_PAPER_TAG:
                id = getID(attr.getValue(TagConstants.ID_ATTRIBUTE));
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
        switch (localName) {
            case TagConstants.ARTIFICIAL_FLOWER_TAG:
            case TagConstants.NATURAL_FLOWER_TAG:
            case TagConstants.CUT_FLOWER_TAG:
                floralComposition.addFlower(currentFlower);
                currentFlower = null;
                break;
            case TagConstants.FLOWER_BASKET_TAG:
            case TagConstants.PACKAGING_PAPER_TAG:
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
        switch (currentTag) {
            case TagConstants.PRICE_TAG:
                if (currentFlower != null) {
                    currentFlower.setPrice(Double.parseDouble(value));
                } else if (currentFlowerPackaging != null) {
                    currentFlowerPackaging.setPrice(Double.parseDouble(value));
                }
                break;
            case TagConstants.COLOR_TAG:
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
            case TagConstants.STEM_LENGTH_TAG:
                currentFlower.setStemLength(Integer.parseInt(value));
                break;
            case TagConstants.REPRODUCTION_TAG:
                ((NaturalFlower) currentFlower).setReproductionType(ReproductionType.valueOf(value.toUpperCase()));
                break;
            case TagConstants.STORAGE_TIME_TAG:
                ((CutFlower) currentFlower).setStorageTime(Integer.parseInt(value));
                break;
            case TagConstants.MATERIAL_TAG:
                if (currentFlower != null) {
                    ((ArtificialFlower) currentFlower).setMaterial(value);
                } else if (currentFlowerPackaging != null) {
                    currentFlowerPackaging.setMaterial(value);
                }
                break;
            case TagConstants.HEIGHT_TAG:
                ((FlowerBasket) currentFlowerPackaging).setHeight(Integer.parseInt(value));
                break;
            case TagConstants.DIAMETER_TAG:
                ((FlowerBasket) currentFlowerPackaging).setDiameter(Integer.parseInt(value));
                break;
            case TagConstants.LENGTH_TAG:
                ((PackagingPaper) currentFlowerPackaging).setLength(Integer.parseInt(value));
                break;
        }
    }
}
