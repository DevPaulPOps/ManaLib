package server;

import java.util.Date;

public class Utils {
    final static int ADULT_AGE = 16;

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isAdult(Date dateOfThePerson) {
        return dateOfThePerson.getYear() < ADULT_AGE;
    }
}
