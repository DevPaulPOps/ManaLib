package config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private static final Map<String, Integer> ports = new HashMap<>();
    private static String host;
    private static String url_jdbc;

    private static String jdbcDriver;

    public Config() {
        loadConfig();
    }

    public static int getPort(String portName) {
        if (ports.isEmpty()) {
            Config f = new Config();
        }

        return ports.getOrDefault(portName, -1);
    }

    public static String getHost() {
        return host;
    }

    public static String getUrlJdbc() {
        return url_jdbc;
    }

    public static String getJdbcDriver() {
        return jdbcDriver;
    }

    private void loadConfig() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/main/java/config/config.json"));
            ports.put("PORT", ((Long) config.get("PORT")).intValue());
            ports.put("PORT_RESERVATION", ((Long) config.get("PORT_RESERVATION")).intValue());
            ports.put("PORT_EMPRUNT", ((Long) config.get("PORT_EMPRUNT")).intValue());
            ports.put("PORT_RETOUR", ((Long) config.get("PORT_RETOUR")).intValue());
            host = (String) config.get("HOST");
            url_jdbc = (String) config.get("URL_JDBC");
            jdbcDriver = (String) config.get("JDBC_DRIVER");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}