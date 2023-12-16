package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket serverConnection;
    private DataInputStream fromServer;
    private boolean run;

    public ClientThread(Socket serverConnection) {
        this.serverConnection = serverConnection;
        run = true;
    }

    public void run() {
        try {
            fromServer = new DataInputStream(serverConnection.getInputStream());
            while (run) {
                String r = fromServer.readUTF();
                System.out.println(r);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
