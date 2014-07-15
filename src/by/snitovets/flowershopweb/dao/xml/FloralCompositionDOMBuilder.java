package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.entity.flower.*;
import by.snitovets.flowershopweb.entity.packaging.AbstractFlowerPackaging;
import by.snitovets.flowershopweb.entity.packaging.FlowerBasket;
import by.snitovets.flowershopweb.entity.packaging.PackagingPaper;
import by.snitovets.flowershopweb.exception.DAOException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Илья on 06.07.2014.
 */
public class FloralCompositionDOMBuilder extends AbstractFloralCompositionBuilder {

    private static final Logger LOG = Logger.getLogger(FloralCompositionDOMBuilder.class);
    private static final Lock lock = new ReentrantLock();
    private static FloralCompositionDOMBuilder instance;
    private DocumentBuilder documentBuilder;

    private FloralCompositionDOMBuilder() throws DAOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new DAOException("Parser configuration error. " + e.getMessage(), e);
        }
    }

    public static FloralCompositionDOMBuilder getInstance() throws DAOException {
        if (null == instance) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new FloralCompositionDOMBuilder();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public void buildFloralComposition(String fileName) throws DAOException {
        Document doc = null;
        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList cutFlowerList = root.getElementsByTagName(TagConstants.CUT_FLOWER_TAG);
            for (int i = 0; i < cutFlowerList.getLength(); i++) {
                Element cutFlowerElement = (Element) cutFlowerList.item(i);
                CutFlower cutFlower = buildCutFlower(cutFlowerElement);
                floralComposition.addFlower(cutFlower);
            }

            NodeList naturalFlowerList = root.getElementsByTagName(TagConstants.NATURAL_FLOWER_TAG);
            for (int i = 0; i < naturalFlowerList.getLength(); i++) {
                Element naturalFlowerElement = (Element) naturalFlowerList.item(i);
                NaturalFlower naturalFlower = buildNaturalFlower(naturalFlowerElement);
                floralComposition.addFlower(naturalFlower);
            }

            NodeList artificialFlowerList = root.getElementsByTagName(TagConstants.ARTIFICIAL_FLOWER_TAG);
            for (int i = 0; i < artificialFlowerList.getLength(); i++) {
                Element artificialFlowerElement = (Element) artificialFlowerList.item(i);
                ArtificialFlower artificialFlower = buildArtificialFlower(artificialFlowerElement);
                floralComposition.addFlower(artificialFlower);
            }

            NodeList flowerBasketList = root.getElementsByTagName(TagConstants.FLOWER_BASKET_TAG);
            if (flowerBasketList.getLength() > 0) {
                Element flowerBasketElement = (Element) flowerBasketList.item(0);
                FlowerBasket flowerBasket = buildFlowerBasket(flowerBasketElement);
                floralComposition.setFlowerPackaging(flowerBasket);
            } else {
                NodeList packagingPaperList = root.getElementsByTagName(TagConstants.PACKAGING_PAPER_TAG);
                if (packagingPaperList.getLength() > 0) {
                    Element packagingPaperElement = (Element) packagingPaperList.item(0);
                    PackagingPaper packagingPaper = buildPackagingPaper(packagingPaperElement);
                    floralComposition.setFlowerPackaging(packagingPaper);
                }
            }
        } catch (SAXException e) {
            throw new DAOException("Parsing failure. " + e.getMessage(), e);
        } catch (IOException e) {
            throw new DAOException("File or I/O error. " + e.getMessage(), e);
        }
    }

    private FlowerBasket buildFlowerBasket(Element flowerBasketElement) {
        int id = getID(flowerBasketElement);
        FlowerBasket flowerBasket = new FlowerBasket(id);
        buildAbstractFlowerPackaging(flowerBasket, flowerBasketElement);
        int height = Integer.parseInt(getElementTextContent(flowerBasketElement, TagConstants.HEIGHT_TAG));
        flowerBasket.setHeight(height);
        double diameter = Double.parseDouble(getElementTextContent(flowerBasketElement, TagConstants.DIAMETER_TAG));
        flowerBasket.setDiameter(diameter);
        return flowerBasket;
    }

    private PackagingPaper buildPackagingPaper(Element packagingPaperElement) {
        int id = getID(packagingPaperElement);
        PackagingPaper packagingPaper = new PackagingPaper(id);
        buildAbstractFlowerPackaging(packagingPaper, packagingPaperElement);
        int length = Integer.parseInt(getElementTextContent(packagingPaperElement, TagConstants.LENGTH_TAG));
        packagingPaper.setLength(length);
        return packagingPaper;
    }

    private void buildAbstractFlowerPackaging(AbstractFlowerPackaging flowerPackaging, Element abstractFlowerPackagingElement) {
        flowerPackaging.setMaterial(getElementTextContent(abstractFlowerPackagingElement, TagConstants.MATERIAL_TAG));
        double price = Double.parseDouble(getElementTextContent(abstractFlowerPackagingElement, TagConstants.PRICE_TAG));
        flowerPackaging.setPrice(price);
    }

    private CutFlower buildCutFlower(Element cutFlowerElement) {
        int id = getID(cutFlowerElement);
        CutFlower cutFlower = new CutFlower(id);
        buildAbstractFlower(cutFlower, cutFlowerElement);
        int storageTime = Integer.parseInt(getElementTextContent(cutFlowerElement, TagConstants.STORAGE_TIME_TAG));
        cutFlower.setStorageTime(storageTime);
        ReproductionType reproduction = ReproductionType.valueOf(
                getElementTextContent(cutFlowerElement, TagConstants.REPRODUCTION_TAG).toUpperCase());
        cutFlower.setReproductionType(reproduction);
        return cutFlower;
    }

    private NaturalFlower buildNaturalFlower(Element naturalFlowerElement) {
        int id = getID(naturalFlowerElement);
        NaturalFlower naturalFlower = new NaturalFlower(id);
        buildAbstractFlower(naturalFlower, naturalFlowerElement);
        ReproductionType reproduction = ReproductionType.valueOf(
                getElementTextContent(naturalFlowerElement, TagConstants.REPRODUCTION_TAG).toUpperCase());
        naturalFlower.setReproductionType(reproduction);
        return naturalFlower;
    }

    private ArtificialFlower buildArtificialFlower(Element artificialFlowerElement) {
        int id = getID(artificialFlowerElement);
        ArtificialFlower artificialFlower = new ArtificialFlower(id);
        buildAbstractFlower(artificialFlower, artificialFlowerElement);
        artificialFlower.setMaterial(getElementTextContent(artificialFlowerElement, TagConstants.MATERIAL_TAG));
        return artificialFlower;
    }

    private void buildAbstractFlower(AbstractFlower flower, Element abstractFlowerElement) {
        double price = Double.parseDouble(getElementTextContent(abstractFlowerElement, TagConstants.PRICE_TAG));
        flower.setPrice(price);
        Color color = null;
        try {
            Field field = Color.class.getField(getElementTextContent(abstractFlowerElement, TagConstants.COLOR_TAG));
            color = (Color) field.get(null);
        } catch (IllegalAccessException e) {
            LOG.warn("IllegalAccessException. " + e.getMessage());
        } catch (NoSuchFieldException e) {
            LOG.warn("NoSuchFieldException." + e.getMessage());
        }
        flower.setColor(color);
        int stemLength = Integer.parseInt(getElementTextContent(abstractFlowerElement, TagConstants.STEM_LENGTH_TAG));
        flower.setStemLength(stemLength);
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private int getID(Element element) {
        String s = element.getAttribute(TagConstants.ID_ATTRIBUTE);
        Scanner scanner = new Scanner(s);
        scanner.useDelimiter("S");
        return scanner.nextInt();
    }

}
