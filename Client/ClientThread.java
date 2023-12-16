package Client;

import Interpreter.ClientInterpreter;
import MSG.MessaggioXML;
import org.w3c.dom.Document;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {
    private ObjectInputStream fromServer;
    private ClientInterpreter clientInterpreter;
    private DataInputStream fromServerTXT;
    private boolean run;

    public ClientThread(ObjectInputStream fromServer) {
        this.fromServer = fromServer;
        clientInterpreter = new ClientInterpreter();
        run = true;
    }

    public void run() {
        try {
            while (run) {
                Document returnDoc = (Document)fromServer.readObject();
                int type = clientInterpreter.getTypeOfComunication(returnDoc);

                if (type == 0) { // load message
                    MessaggioXML message = clientInterpreter.returnMessageInfos(returnDoc);
                    System.out.println(message.getMessage());
                }
                else if (type == 1) { // load chat
                    ArrayList<MessaggioXML> chat = clientInterpreter.returnChatInfos(returnDoc);
                    System.out.println(chat.get(0).getMessage());
                }
                else if (type == 2) {
                    ArrayList<String> contacts = clientInterpreter.returnContactsInfos(returnDoc);
                    System.out.println(contacts);
                }

                //System.out.println(r);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
