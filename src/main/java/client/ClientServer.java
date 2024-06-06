package client;

import config.Config;
import server.serv.bttp.BttpProtocole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientServer {
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
}