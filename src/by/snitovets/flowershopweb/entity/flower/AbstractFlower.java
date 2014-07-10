package by.snitovets.flowershopweb.entity.flower;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.io.Serializable;

/**
 * @author Илья
 */
public abstract class AbstractFlower implements Serializable {

    private static final Logger LOG = Logger.getLogger(AbstractFlower.class);
    private static final long serialVersionUID = -6789012779958428173L;

    private final int id;
    private double price;
    private Color color;
    private int stemLength; //in sm

    public AbstractFlower(int id) {
        this.id = id;
    }

    public AbstractFlower(int id, double price, Color color, int stemLength) {
        this.id = id;
        this.price = price;
        this.color = color;
        this.stemLength = stemLength;
    }

    public int getStemLength() {
        return stemLength;
    }

    public void setStemLength(int stemLength) {
        if (stemLength > 0) {
            this.stemLength = stemLength;
        } else {
            LOG.warn("Stem length must be greater than zero. Value has not been assigned.");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            LOG.warn("Price must be greater than zero. Value has not been assigned.");
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
        } else {
            LOG.warn("Color can not be null. Value has not been assigned.");
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractFlower)) {
            return false;
        }

        AbstractFlower other = (AbstractFlower) o;

        if (id != other.id) {
            return false;
        }
        if (Double.compare(other.price, price) != 0) {
            return false;
        }
        if (stemLength != other.stemLength) {
            return false;
        }
        if (color != null ? !color.equals(other.color) : other.color != null) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + stemLength;
        return result;
    }


    @Override
    public String toString() {
        return "AbstractFlower{" +
                "id=" + id +
                ", price=" + price +
                ", color=" + color +
                ", stemLength=" + stemLength +
                '}';
    }
}
