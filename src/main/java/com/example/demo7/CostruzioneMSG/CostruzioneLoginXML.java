package com.example.demo7.CostruzioneMSG;
import com.example.demo7.MSG.LoginXML;
import com.example.demo7.MSG.RegistrazioneXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.example.demo7.MSG.MessaggioXML;

public class CostruzioneLoginXML {

    private LoginXML login;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Boolean istanziato = false;

    public CostruzioneLoginXML(String nickname, String password)
    {
        login = new LoginXML(nickname, password);
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
        e.setTextContent("login");
        rootElement.appendChild(e);
        e = documento.createElement("Nickname");
        e.setTextContent(login.getNickname());
        rootElement.appendChild(e);
        e = documento.createElement("Password");
        e.setTextContent(login.getPassword());
        rootElement.appendChild(e);

        //return the document object
        return documento;
    }
}