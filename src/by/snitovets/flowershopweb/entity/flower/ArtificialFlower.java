package by.snitovets.flowershopweb.entity.flower;

import org.apache.log4j.Logger;

import java.awt.Color;

/**
 * @author Илья
 */
//искусственные цветы
public class ArtificialFlower extends AbstractFlower {

    private static final Logger LOG = Logger.getLogger(ArtificialFlower.class);
    private static final long serialVersionUID = -8315084913736400165L;

    private String material;

    public ArtificialFlower(int id) {
        super(id);
    }

    public ArtificialFlower(int id, double price, Color color, int stemLength, String material) {
        super(id, price, color, stemLength);
        this.material = material;
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
        if (!(o instanceof ArtificialFlower)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ArtificialFlower other = (ArtificialFlower) o;

        return !(material != null ? !material.equals(other.material) : other.material != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArtificialFlower{" + super.toString() + "material='" + material + '\'' + "} ";
    }
}
