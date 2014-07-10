package by.snitovets.flowershopweb.entity.packaging;

import org.apache.log4j.Logger;

/**
 * @author Илья
 */
public class PackagingPaper extends AbstractFlowerPackaging {

    private static final Logger LOG = Logger.getLogger(PackagingPaper.class);
    private static final long serialVersionUID = 8198297971576719011L;

    private int length; //sm

    public PackagingPaper(int id) {
        super(id);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length > 0) {
            this.length = length;
        } else {
            LOG.warn("Length of paper must be greater than zero. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PackagingPaper)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PackagingPaper other = (PackagingPaper) o;

        if (length != other.length) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        return "PackagingPaper{" +
                "length=" + length +
                "} " + super.toString();
    }
}
