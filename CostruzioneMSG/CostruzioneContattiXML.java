package CostruzioneMSG;
import MSG.LoginXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CostruzioneContattiXML {

    private String currentUser;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean istanziato = false;

    public CostruzioneContattiXML(String nickname)
    {
        currentUser = nickname;
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

        // Add the type of message
        Element e = documento.createElement("Type");
        e.setTextContent("loadcontacts");
        rootElement.appendChild(e);
        e = documento.createElement("Nickname");
        e.setTextContent(currentUser);
        rootElement.appendChild(e);

        //return the document object
        return documento;
    }
}