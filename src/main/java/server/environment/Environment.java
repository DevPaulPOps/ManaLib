package server.environment;

import config.Config;

public class Environment {
    public static final String DRIVER = Config.getJdbcDriver();
    public static final String URL = Config.getUrlJdbc();
    public static final String FROM_EMAIL = "owen.rebeller@etu.u-paris.fr";
    public static final String TO_Email = "jean-francois.brette@u-paris.fr";
    public static final String TLS_PORT = "587";
    public static final String PASSWORD = "motDePasse"; // Mettre le mot de passe pour utiliser le service
}
