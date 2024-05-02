package server.elements;

import server.elements.interfaces.Abonne;

import java.util.Date;

public class abonne implements Abonne {

    /**
     * @return
     */
    @Override
    public int getNumberOfSubscribers() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public Date getDateOfBirth() {
        return null;
    }
}
