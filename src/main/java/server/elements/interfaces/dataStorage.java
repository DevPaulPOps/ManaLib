package server.elements.interfaces;

import java.sql.SQLException;

public interface dataStorage {
    int getNumber();
    String getId();
    void storeData() throws SQLException;
}
