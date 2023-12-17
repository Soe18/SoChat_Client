package com.example.demo7.CostruzioneMSG;
import com.example.demo7.MSG.RegistrazioneXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.example.demo7.MSG.MessaggioXML;

public class CostruzioneRegistrazioneXML {

    private RegistrazioneXML registrazione;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean istanziato = false;

    public CostruzioneRegistrazioneXML(String nickname, String password, String confirmPassword)
    {
        registrazione = new RegistrazioneXML(nickname, password, confirmPassword);
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
        e.setTextContent("register");
        rootElement.appendChild(e);
        e = documento.createElement("Nickname");
        e.setTextContent(registrazione.getNickname());
        rootElement.appendChild(e);
        e = documento.createElement("Password");
        e.setTextContent(registrazione.getPassword());
        rootElement.appendChild(e);
        e = documento.createElement("ConfirmPassword");
        e.setTextContent(registrazione.getConfirmPassword());
        rootElement.appendChild(e);

        //return the document object
        return documento;
    }
}