package CostruzioneMSG;

import MSG.MessaggioXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CostruzioneChatXML {

    private String username;
    private String reqnickname;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean istanziato = false;

    public CostruzioneChatXML(String username, String reqnickname)
    {
        //create a message (text+sender+receiver)
        this.username = username;
        this.reqnickname = reqnickname;
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
        e.setTextContent("loadchat");
        rootElement.appendChild(e);
        e = documento.createElement("User");
        e.setTextContent(username);
        rootElement.appendChild(e);
        e = documento.createElement("RequestedNickname");
        e.setTextContent(reqnickname);
        rootElement.appendChild(e);

        //return the document object
        return documento;
    }
}