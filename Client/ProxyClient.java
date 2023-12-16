package Client;
import java.net.*;

import CostruzioneMSG.CostruzioneLoginXML;
import CostruzioneMSG.CostruzioneMessaggioXML;
import CostruzioneMSG.CostruzioneRegistrazioneXML;
import org.w3c.dom.Document;

import java.io.*;
public class ProxyClient {
	private Socket serverConnection;
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;

	public void login(String nickname, String password) {
		try{
			serverConnection = new Socket("127.0.0.1", 8080 );
			//Creo input e output per la comunicazione con l'app server
			//output per inviare il messaggio
			toServer = new ObjectOutputStream(serverConnection.getOutputStream());
			fromServer = new ObjectInputStream(serverConnection.getInputStream());

			// chiamiamo la registrazione
			CostruzioneLoginXML log = new CostruzioneLoginXML(nickname, password);
			Document document = log.getXMLObject();
			toServer.writeObject(document);

			// checks errors

			// attesa della risposta dal server
			System.out.println ("In attesa di una risposta dal server..." );
			Document loadedContacts = (Document)fromServer.readObject();
			Document loadedChat = (Document)fromServer.readObject();

			// ok
			ClientThread clientThread = new ClientThread(serverConnection);
			clientThread.start();


		}catch ( IOException e ){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * STESSA COSA DEL LOGIN, OCCHIO
	 * @param nickname
	 * @param password
	 * @param confirmPassword
	 */
	public void register(String nickname, String password, String confirmPassword) {
		try{
			serverConnection = new Socket("127.0.0.1", 8080 );
			//Creo input e output per la comunicazione con l'app server
			//output per inviare il messaggio
			toServer = new ObjectOutputStream(serverConnection.getOutputStream());
			fromServer = new DataInputStream(serverConnection.getInputStream());

			System.out.println("Socket creata.");

			// chiamiamo la registrazione
			CostruzioneRegistrazioneXML log = new CostruzioneRegistrazioneXML(nickname, password, confirmPassword);
			Document document = log.getXMLObject();
			toServer.writeObject(document);

			// attesa della risposta dal server
			System.out.println ("In attesa di una risposta dal server...");
			String risposta = fromServer.readUTF();

			System.out.println(risposta);

		}catch ( IOException e ){
			e.printStackTrace();
		}
	}

	public void sendMsg(String text, String sender, String receiver) {
		try {
			// chiamiamo la registrazione
			CostruzioneMessaggioXML msg = new CostruzioneMessaggioXML(text, sender, receiver);
			Document document = msg.getXMLObject();
			toServer.writeObject(document);

			// attesa della risposta dal server
			System.out.println ("In attesa di una risposta dal server..." );
			String risposta = fromServer.readUTF();

			System.out.println(risposta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}