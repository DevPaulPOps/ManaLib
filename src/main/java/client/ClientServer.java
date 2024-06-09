package client;

import client.interfaces.IClient;
import config.Config;
import server.serv.bttp.BttpProtocole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientServer implements IClient {
    private final static int PORT = Config.getPort("PORT");
    private final static String HOST = Config.getHost();
    private final BttpProtocole bttpProtocole;

    public ClientServer() throws IOException {
        this(HOST, PORT);
    }

    public ClientServer(int port) throws IOException {
        this(HOST, port);
    }

    public ClientServer(String host, int port) throws IOException {
        this.bttpProtocole = new BttpProtocole(new Socket(host, port));
    }

    public String getClavierInput() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public BttpProtocole getBttpProtocole() {
        return this.bttpProtocole;
    }

    public void close() throws IOException {
        this.getBttpProtocole().close();
    }

    @Override
    public void launch(String[] args) {
        try {
            if (args[0].equalsIgnoreCase("reservation")) {
                String line;
                while (!(line = getBttpProtocole().getResponse()).equals("FIN_DU_CATALOGUE")) {
                    System.out.println(line);
                }
            } else {
                System.out.println(getBttpProtocole().getResponse());
            }

            System.out.println(getBttpProtocole().getResponse());

            String responseServer = "";
            while (!responseServer.contains("End")) {
                responseServer = getBttpProtocole().communicate(getClavierInput());
                System.out.println(responseServer);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la communication avec le serveur : " + e.getMessage());
        }
    }

}