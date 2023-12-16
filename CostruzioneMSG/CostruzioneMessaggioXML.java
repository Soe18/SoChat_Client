package CostruzioneMSG;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import MSG.MessaggioXML;

public class CostruzioneMessaggioXML {
    
    private MessaggioXML messaggio;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean istanziato = false;

    public CostruzioneMessaggioXML(String m, String s, String r)
    {
        //create a message (text+sender+receiver)
        messaggio = new MessaggioXML(m, s, r);
        if(!istanziato)
        {
            try
            {
                docFactory = DocumentBuilderFactory.newInstance();
                docBuilder = docFactory.newDocumentBuilder();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            istanziato = true;
        }
    }

    public Document getXMLObject()
    {
        //Create an xml object (Document)
        Document documento = docBuilder.newDocument();
        //Create the root element
        Element rootElement = documento.createElement("SoChat");
        //Add the element to the xml document
        documento.appendChild(rootElement);

        //Add Content, Sender and Receiver to the ChatMessage element as children elements
        Element e = documento.createElement("Type");
        e.setTextContent("message_forward");
        rootElement.appendChild(e);
        e = documento.createElement("Content");
        e.setTextContent(messaggio.getMessage());
        rootElement.appendChild(e);
        e = documento.createElement("Sender");
        e.setTextContent(messaggio.getSender());
        rootElement.appendChild(e);
        e = documento.createElement("Receiver");
        e.setTextContent(messaggio.getReceiver());
        rootElement.appendChild(e);

        //return the document object
        return documento;
    }
}