package server.elements.interfaces;

import java.util.Date;

public interface Abonnes extends DataStorage{

    int getNumberOfSubscribers();

    Date getDateOfBirth();
}