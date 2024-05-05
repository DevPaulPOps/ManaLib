package server.serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class MediathequeServer implements Runnable{
    private final int PORT;
    private final ServerSocket serverSocket;
    private MediathequeService service;

    /**
     * TypeClassDuService est une classe qui hérite de MediathequeService
     * Ceci : <? extends MediathequeService> permet de dire que TypeClassDuService est une classe qui hérite de MediathequeService
     * On appel ceci un "Type Wildcard"
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html">Type Wildcard</a>
     */
    private final Class<? extends MediathequeService> TypeClassDuService;

    public MediathequeServer(int port, Class<? extends MediathequeService> typeClassDuService) throws IOException {
        this.PORT = port;
        this.serverSocket = new ServerSocket(port);
        this.TypeClassDuService = typeClassDuService;
    }

    public int getPort(){
        return PORT;
    }

    public ServerSocket getServerSocket(){
        return serverSocket;
    }

    @java.lang.Override
    public void run() {
        while(true){
            try {
                this.service = TypeClassDuService.getConstructor(Socket.class).newInstance(serverSocket.accept());
                service.start();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public void close(){
        this.service.getBttpProtocole().close();
    }
}
