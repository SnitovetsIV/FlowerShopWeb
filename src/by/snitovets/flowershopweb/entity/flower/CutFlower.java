package by.snitovets.flowershopweb.entity.flower;

import org.apache.log4j.Logger;

import java.awt.Color;

/**
 * Created by Илья on 19.06.2014.
 */
public class CutFlower extends NaturalFlower {

    private static final Logger LOG = Logger.getLogger(CutFlower.class);
    private static final long serialVersionUID = 8722413815083004258L;

    private int storageTime;

    public CutFlower(int id) {
        super(id);
    }

    public CutFlower(int id, double price, Color color, int stemLength, ReproductionType reproductionType, int storageTime) {
        super(id, price, color, stemLength, reproductionType);
        this.storageTime = storageTime;
    }

    public int getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(int storageTime) {
        if (storageTime > 0) {
            this.storageTime = storageTime;
        } else {
            LOG.warn("storageTime must be greater than zero. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutFlower)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CutFlower cutFlower = (CutFlower) o;

        if (storageTime != cutFlower.storageTime) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + storageTime;
        return result;
    }

    @Override
    public String toString() {
        return "CutFlower{" + super.toString() +
                "storageTime=" + storageTime +
                "} ";
    }
}
