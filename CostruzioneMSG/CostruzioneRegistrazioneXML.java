package CostruzioneMSG;
import MSG.RegistrazioneXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import MSG.MessaggioXML;

public class CostruzioneRegistrazioneXML {

    private RegistrazioneXML registrazione;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean istanziato = false;

    public CostruzioneRegistrazioneXML(String s)
    {
        registrazione = new RegistrazioneXML(s);
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
        Element rootElement = documento.createElement("user");
        //Add the element to the xml document
        documento.appendChild(rootElement);

        //Add Content, Sender and Receiver to the ChatMessage element as children elements
        Element e = documento.createElement("nickname");
        e.setTextContent(registrazione.getNickname());
        rootElement.appendChild(e);
        e = documento.createElement("password");
        e.setTextContent(registrazione.getPassword());
        rootElement.appendChild(e);

        //return the document object
        return documento;
    }
}