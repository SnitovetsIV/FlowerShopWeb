package by.snitovets.flowershopweb.entity.flower;

import org.apache.log4j.Logger;

import java.awt.Color;

/**
 * @author Илья
 */
//живые цветы
public class NaturalFlower extends AbstractFlower {

    private static final Logger LOG = Logger.getLogger(NaturalFlower.class);
    private static final long serialVersionUID = 8722413815083004258L;

    private ReproductionType reproductionType = ReproductionType.BULB;

    public NaturalFlower(int id) {
        super(id);
    }

    public NaturalFlower(int id, double price, Color color, int stemLength, ReproductionType reproductionType) {
        super(id, price, color, stemLength);
        this.reproductionType = reproductionType;
    }

    public ReproductionType getReproductionType() {
        return reproductionType;
    }

    public void setReproductionType(ReproductionType reproductionType) {
        if (reproductionType != null) {
            this.reproductionType = reproductionType;
        } else {
            LOG.warn("ReproductionType can not be null. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NaturalFlower)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        NaturalFlower other = (NaturalFlower) o;

        return reproductionType == other.reproductionType;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (reproductionType != null ? reproductionType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NaturalFlower{" +
                "typeOfReproduction=" + reproductionType +
                "} " + super.toString();
    }
}
