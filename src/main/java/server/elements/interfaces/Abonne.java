package server.elements.interfaces;

import java.util.Date;

public interface Abonne extends dataStorage {
    int getNumberOfSubscribers();
    Date getDateOfBirth();
}
