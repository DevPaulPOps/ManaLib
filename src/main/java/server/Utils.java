package server;

import java.util.Date;

public class Utils {
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    final int ADULT_AGE = 16;
    public boolean isAdult(Date dateOfThePerson) {
        return dateOfThePerson.getYear() < ADULT_AGE;
    }
}
