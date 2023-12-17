package com.example.demo7.Interpreter;

import com.example.demo7.MSG.MessaggioXML;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ClientInterpreter {

    public ClientInterpreter() {
    }

    public int getTypeOfComunication(Document xml) {
        System.out.println(xml.getFirstChild());

        String type = xml.getElementsByTagName("Type").item(0).getTextContent();
        int result;

        switch (type) {
            case "message_to":
                result = 0;
                break;
            case "loadchat":
                result = 1;
                break;
            case "contacts":
                result = 2;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }

    public MessaggioXML returnMessageInfos(Document xml) {
        String sender = xml.getElementsByTagName("Sender").item(0).getTextContent();
        String receiver = xml.getElementsByTagName("Receiver").item(0).getTextContent();
        String text = xml.getElementsByTagName("Content").item(0).getTextContent();
        return new MessaggioXML(sender, receiver, text);
    }

    public ArrayList<String> returnContactsInfos(Document xml) {
        ArrayList<String> res = new ArrayList<>();
        NodeList users = xml.getElementsByTagName("User");
        for (int i = 0; i < users.getLength(); i++) {
            res.add(users.item(i).getTextContent());
        }
        return res;
    }

    public ArrayList<MessaggioXML> returnChatInfos(Document xml) {
        ArrayList<MessaggioXML> res = new ArrayList<>();
        NodeList msgs = xml.getElementsByTagName("ChatMSG");
        for (int i = 0; i < msgs.getLength(); i++) {
            String text = msgs.item(i).getChildNodes().item(0).getTextContent();
            String sender = msgs.item(i).getChildNodes().item(1).getTextContent();
            String receiver = msgs.item(i).getChildNodes().item(2).getTextContent();
            res.add(new MessaggioXML(text, sender, receiver));
        }
        return res;
    }

}
