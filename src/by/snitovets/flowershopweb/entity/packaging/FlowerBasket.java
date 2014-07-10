package by.snitovets.flowershopweb.entity.packaging;

import org.apache.log4j.Logger;

/**
 * @author Илья
 */
public class FlowerBasket extends AbstractFlowerPackaging {

    private static final Logger LOG = Logger.getLogger(FlowerBasket.class);
    private static final long serialVersionUID = 4920110948891371389L;

    private int height;     //sm
    private double diameter;//sm

    public FlowerBasket(int id) {
        super(id);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            LOG.warn("Height of basket must be greater than zero. Value has not been assigned.");
        }
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        if (diameter > 0) {
            this.diameter = diameter;
        } else {
            LOG.warn("Diameter of basket must be greater than zero. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowerBasket)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FlowerBasket other = (FlowerBasket) o;

        if (Double.compare(other.diameter, diameter) != 0) {
            return false;
        }
        if (height != other.height) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + height;
        temp = Double.doubleToLongBits(diameter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public String toString() {
        return "FlowerBasket{" +
                "height=" + height +
                ", diameter=" + diameter +
                "} " + super.toString();
    }
}
