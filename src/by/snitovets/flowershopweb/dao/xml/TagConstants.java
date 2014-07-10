package by.snitovets.flowershopweb.dao.xml;

/**
 * Created by Илья on 09.07.2014.
 */
public enum TagConstants {

    CUT_FLOWER_TAG("cut-flower"),
    NATURAL_FLOWER_TAG("natural-flower"),
    ARTIFICIAL_FLOWER_TAG("artificial-flower"),
    FLOWER_BASKET_TAG("flower-basket"),
    PACKAGING_PAPER_TAG("packaging-paper"),

    ID_ATTRIBUTE("id"),

    PRICE_TAG("price"),
    COLOR_TAG("color"),
    STEM_LENGTH_TAG("stem-length"),
    REPRODUCTION_TAG("reproduction"),
    STORAGE_TIME_TAG("storage-time"),
    MATERIAL_TAG("material"),
    HEIGHT_TAG("height"),
    DIAMETER_TAG("diameter"),
    LENGTH_TAG("length");

    private String value;

    private TagConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
