package by.snitovets.flowershopweb.tag;

import by.snitovets.flowershopweb.entity.FloralComposition;
import by.snitovets.flowershopweb.entity.flower.AbstractFlower;
import by.snitovets.flowershopweb.entity.flower.ArtificialFlower;
import by.snitovets.flowershopweb.entity.flower.CutFlower;
import by.snitovets.flowershopweb.entity.flower.NaturalFlower;
import by.snitovets.flowershopweb.entity.packaging.AbstractFlowerPackaging;
import by.snitovets.flowershopweb.entity.packaging.FlowerBasket;
import by.snitovets.flowershopweb.entity.packaging.PackagingPaper;
import by.snitovets.flowershopweb.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Илья on 11.07.2014.
 */
public class ShowTag extends TagSupport {

    private static final Logger LOG = Logger.getLogger(ShowTag.class);

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            HttpSession session = pageContext.getSession();
            FloralComposition floralComposition = (FloralComposition) session.getAttribute(Constants.PARAM_NAME_FLORAL_COMPOSITION);
            Iterator<AbstractFlower> iterator = floralComposition.iterator();
            while (iterator.hasNext()) {
                AbstractFlower flower = iterator.next();
                out.write("<tr>");
                out.write("<td>" + flower.getClass().getSimpleName() + "</td>");
                out.write("<td>" + flower.getId() + "</td>");
                out.write("<td>" + flower.getPrice() + "</td>");
                out.write("<td>");
                out.write("Color = " + flower.getColor() + "<br/>" +
                        "Stem length = " + flower.getStemLength() + "<br/>");
                if (ArtificialFlower.class.equals(flower.getClass())) {
                    ArtificialFlower artificialFlower = (ArtificialFlower) flower;
                    out.write("Material = " + artificialFlower.getMaterial());
                } else if (NaturalFlower.class.equals(flower.getClass())) {
                    NaturalFlower naturalFlower = (NaturalFlower) flower;
                    out.write("Reproduction = " + naturalFlower.getReproductionType());
                } else if (CutFlower.class.equals(flower.getClass())) {
                    CutFlower cutFlower = (CutFlower) flower;
                    out.write("Reproduction = " + cutFlower.getReproductionType() + "<br/>" +
                            "Storage time = " + cutFlower.getStorageTime());
                } else {
                    out.write("Unknown type of flower.");
                    LOG.error("Unknown type of flower.");
                }
                out.write("</td>");
                out.write("</tr>");
            }
            AbstractFlowerPackaging packaging = floralComposition.getFlowerPackaging();
            if (packaging != null) {
                out.write("<tr>");
                out.write("<td>" + packaging.getClass().getSimpleName() + "</td>");
                out.write("<td>" + packaging.getId() + "</td>");
                out.write("<td>" + packaging.getPrice() + "</td>");
                out.write("<td>");
                if (FlowerBasket.class.equals(packaging.getClass())) {
                    FlowerBasket flowerBasket = (FlowerBasket) packaging;
                    out.write("Material = " + flowerBasket.getMaterial() + "<br/>" +
                            "Diameter = " + flowerBasket.getDiameter() + "<br/>" +
                            "Height = " + flowerBasket.getHeight());
                } else if (PackagingPaper.class.equals(packaging.getClass())) {
                    PackagingPaper packagingPaper = (PackagingPaper) packaging;
                    out.write("Material = " + packagingPaper.getMaterial() + "<br/>" +
                            "Length = " + packagingPaper.getLength());
                } else {
                    out.write("Unknown type of flower packaging.");
                    LOG.error("Unknown type of flower packaging.");
                }
                out.write("</td>");
                out.write("</tr>");
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return SKIP_BODY;
    }

}
