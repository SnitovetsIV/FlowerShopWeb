package by.snitovets.flowershopweb.entity;

import by.snitovets.flowershopweb.entity.flower.AbstractFlower;
import by.snitovets.flowershopweb.entity.packaging.AbstractFlowerPackaging;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Илья
 */
public class FloralComposition {

    private static final Logger LOG = Logger.getLogger(FloralComposition.class);

    private String name;
    private List<AbstractFlower> allFlowers;
    private AbstractFlowerPackaging packaging;

    public FloralComposition() {
        allFlowers = new ArrayList<>();
    }

    public FloralComposition(String name) {
        this();
        this.name = name;
    }

    public AbstractFlowerPackaging getFlowerPackaging() {
        return packaging;
    }

    public void setFlowerPackaging(AbstractFlowerPackaging pack) {
        if (pack != null) {
            this.packaging = pack;
        } else {
            LOG.warn("Flower packaging can not be null. Value has not been assigned.");
        }
    }

    public void addFlower(AbstractFlower flower) {
        if (flower != null) {
            allFlowers.add(flower);
        } else {
            LOG.warn("Flower can not be null. Flower has not been added.");
        }
    }

    public Iterator<AbstractFlower> iterator() {
        return allFlowers.iterator();
    }

    public void setAllFlowers(List<AbstractFlower> newList) {
        if (newList != null) {
            this.allFlowers = newList;
        } else {
            LOG.warn("New list of flowers can not be null. Value has not been assigned.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            LOG.warn("Name of flower bouquet can not be null. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FloralComposition)) {
            return false;
        }

        FloralComposition that = (FloralComposition) o;

        if (!allFlowers.equals(that.allFlowers)) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (packaging != null ? !packaging.equals(that.packaging) : that.packaging != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + allFlowers.hashCode();
        result = 31 * result + (packaging != null ? packaging.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FloralComposition{" +
                "name='" + name + '\'' +
                ", allFlowers=" + allFlowers +
                ", packaging=" + packaging +
                '}';
    }
}
