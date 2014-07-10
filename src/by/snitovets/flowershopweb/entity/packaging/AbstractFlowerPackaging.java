package by.snitovets.flowershopweb.entity.packaging;

import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * @author Илья
 */
public abstract class AbstractFlowerPackaging implements Serializable {

    private static final Logger LOG = Logger.getLogger(AbstractFlowerPackaging.class);
    private static final long serialVersionUID = 8040514024292573738L;

    private final int id;
    private String material;
    private double price;

    public AbstractFlowerPackaging(int id) {
        this.id = id;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material != null) {
            this.material = material;
        } else {
            LOG.warn("Material can not be null. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractFlowerPackaging)) {
            return false;
        }

        AbstractFlowerPackaging other = (AbstractFlowerPackaging) o;

        if (id != other.id) {
            return false;
        }
        if (Double.compare(other.price, price) != 0) {
            return false;
        }
        if (material != null ? !material.equals(other.material) : other.material != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (material != null ? material.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AbstractFlowerPackaging{" +
                "id=" + id +
                ", material='" + material + '\'' +
                ", price=" + price +
                '}';
    }
}
