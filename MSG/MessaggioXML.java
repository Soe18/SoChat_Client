package MSG;
public class MessaggioXML {
    private String Messaggio;
    private String Mittente;
    private String Destinatario;

    public MessaggioXML(String msg, String mit, String dest)
    {
        Messaggio = msg;
        Mittente = mit;
        Destinatario = dest;
    }

    public void setXMLMessage(String msg, String mit, String dest)
    {
        Messaggio = msg;
        Mittente = mit;
        Destinatario = dest;
    }

    public String getMessage()
    {
        return Messaggio;
    }

    public String getSender()
    {
        return Mittente;
    }

    public String getReceiver()
    {
        return Destinatario;
    }

    public void setMessage(String msg)
    {
        Messaggio = msg;
    }

    public void setSender(String mit)
    {
        Mittente = mit;
    }

    public void setReceiver(String dest)
    {
        Destinatario = dest;
    }
}