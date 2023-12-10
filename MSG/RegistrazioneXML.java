package MSG;
public class RegistrazioneXML {
    private String Nickname;
    private String Password;

    public RegistrazioneXML(String nickname)
    {
        Nickname = nickname;
        Password = "password";
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}