package Client;
import java.net.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.*;
import CostruzioneMSG.CostruzioneMessaggioXML;
public class Client {

	public static void main(String[] args) {
		
		try{
			Socket s = new Socket ("127.0.0.1", 8000 );
			//Creo input e output per la comunicazione con l'app server
			//output per inviare il messaggio 
			DataInputStream daServer = new DataInputStream ( s.getInputStream() );
			ObjectOutputStream  aServer = new ObjectOutputStream(s.getOutputStream());

			System.out.println ("Socket creata." );
			Boolean finito = false;

			while(!finito)
			{
				// input da tastiera per scrivere il messaggio da inviare
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Scrivi un messaggio da inviare: ");
				String SendingString = input.readLine();

				// creo il messaggio XML da inviare
				CostruzioneMessaggioXML mb = new CostruzioneMessaggioXML(SendingString, "LDORATO", "SDORATO");
				Document documento = mb.getXMLObject();
				
				// --- INIZIO COMUNICAZIONE CON IL SERVER ---

				// invio del messaggio al server
				aServer.writeObject(documento);
				
				// attesa della risposta dal server
				System.out.println ("In attesa di una risposta dal server..." );
				String risposta = daServer.readUTF();
				System.out.println ("Risposta dal server: " + risposta );

				// controllo che la comunicazione sia finita
				if(risposta.equals("fine"))
				{
					finito = true;
				}
				
			}
			// chiudo la socket e gli input/output
			aServer.close();
			daServer.close();
			s.close();
		
		}catch ( IOException e ){
			e.printStackTrace();
		}
	}
}