package server.environment;

import config.Config;

public class Environment {
    public static final String DRIVER = Config.getJdbcDriver();
    public static final String URL = Config.getUrlJdbc();
}
