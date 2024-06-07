package server.serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class MediathequeServer implements Runnable {
    private final int PORT;
    private final ServerSocket serverSocket;
    /**
     * TypeClassDuService est une classe qui hérite de MediathequeService
     * Ceci : <? extends MediathequeService> permet de dire que TypeClassDuService est une classe qui hérite de MediathequeService
     * On appel ceci un "Type Wildcard"
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html">Type Wildcard</a>
     */
    private final Class<? extends MediathequeService> TypeClassDuService;
    private MediathequeService service;

    public MediathequeServer(int port, Class<? extends MediathequeService> typeClassDuService) throws IOException {
        this.PORT = port;
        this.serverSocket = new ServerSocket(port);
        this.TypeClassDuService = typeClassDuService;
    }

    public int getPort() {
        return PORT;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                this.service = TypeClassDuService.getConstructor(Socket.class).newInstance(socket);
                service.start();
            } catch (Exception e) {
                System.out.println("Error: " + e);
                if (socket != null && !socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

    public void close() {
        if (service != null) {
            service.getBttpProtocole().close();
        }
    }
}
