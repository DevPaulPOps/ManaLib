package server.Operations.Retour;

import server.serv.MediathequeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class retourService extends MediathequeService {

    public retourService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        //. Lors du retour, le numéro de
        //DVD suffit
        try {

            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader sin = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter sout = new PrintWriter(getSocket().getOutputStream(), true);

            String line;
            line = sin.readLine();
            System.out.println(line);
            System.out.print(sin.readLine());

            String repAbonne = clavier.readLine();
            sout.println(repAbonne);


            System.out.print(sin.readLine());
            sout.println(clavier.readLine());

            System.out.print(sin.readLine());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            if (getSocket() != null) {
                getSocket().close();
            }
        } catch (IOException ignored) {
        }
    }
}
