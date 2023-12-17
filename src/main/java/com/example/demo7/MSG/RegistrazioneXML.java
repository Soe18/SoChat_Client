package com.example.demo7.MSG;
public class RegistrazioneXML {
    private String Nickname;
    private String Password;
    private String ConfirmPassword;

    public RegistrazioneXML(String nickname, String password, String confirmPassword)
    {
        Nickname = nickname;
        Password = password;
        ConfirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}