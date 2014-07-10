package by.snitovets.flowershopweb.dao.xml;

import by.snitovets.flowershopweb.entity.FloralComposition;
import by.snitovets.flowershopweb.exception.DAOException;

/**
 * Created by Илья on 06.07.2014.
 */
public abstract class AbstractFloralCompositionBuilder {

    protected FloralComposition floralComposition;

    public AbstractFloralCompositionBuilder() {
        floralComposition = new FloralComposition();
    }

    public FloralComposition getFloralComposition() {
        return floralComposition;
    }

    public abstract void buildFloralComposition(String fileName) throws DAOException;

}
