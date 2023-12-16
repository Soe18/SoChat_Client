package Client;
import java.net.*;

import CostruzioneMSG.*;
import Interpreter.ClientInterpreter;
import MSG.MessaggioXML;
import org.w3c.dom.Document;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;

public class ProxyClient {
	private Socket serverConnection;
	private ClientInterpreter clientInterpreter;
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;

	public ProxyClient() {
		clientInterpreter = new ClientInterpreter();
	}

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

			// ok
			ClientThread clientThread = new ClientThread(fromServer);
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
			fromServer = new ObjectInputStream(serverConnection.getInputStream());

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

	public void loadContacts(String user) {
		try {
			// chiamiamo la registrazione
			CostruzioneContattiXML msg = new CostruzioneContattiXML(user);
			Document document = msg.getXMLObject();
			toServer.writeObject(document);

			// attesa della risposta dal server
			System.out.println("In attesa di una risposta dal server...");
			Document risposta = (Document) fromServer.readObject();

			ArrayList<String> contacts = clientInterpreter.returnContactsInfos(risposta);
			System.out.println(contacts);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

	public void loadChat(String user, String requestedUser) {
		try {
			// chiamiamo la registrazione
			CostruzioneChatXML chatReq = new CostruzioneChatXML(user, requestedUser);
			Document document = chatReq.getXMLObject();
			toServer.writeObject(document);

			// attesa della risposta dal server
			System.out.println("In attesa di una risposta dal server...");
			Document risposta = (Document) fromServer.readObject();

			ArrayList<MessaggioXML> chat = clientInterpreter.returnChatInfos(risposta);
			//System.out.println(contacts);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}