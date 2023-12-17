package com.example.demo7;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import com.example.demo7.Client.ProxyClient;
import com.example.demo7.Client.ClientThread;

import java.io.IOException;
import java.util.ArrayList;

public class LogSignInController {
    private ProxyClient proxyClient;

    public LogSignInController() {
        proxyClient = new ProxyClient();
    }

    @FXML
    private MFXTextField signinNickFld;

    @FXML
    private MFXTextField signinPassFld;

    @FXML
    private MFXTextField signinRepassFld;

    @FXML
    private MFXButton signinBtn;

    @FXML
    private Label signinErrorLbl;

    @FXML
    private MFXTextField loginNickFld;

    @FXML
    private MFXTextField loginPassFld;

    @FXML
    private MFXButton loginBtn;

    @FXML
    private Label loginErrorLbl;

    @FXML
    void onLogIn(ActionEvent event) throws IOException {
        proxyClient.login(loginNickFld.getText(), loginPassFld.getText());
        LogSignInApplication.changeScene("hello-view.fxml", proxyClient);

    }

    @FXML
    void onSignIn(ActionEvent event) throws IOException {
        proxyClient.register(signinNickFld.getText(), signinPassFld.getText(), signinRepassFld.getText());
        LogSignInApplication.changeScene("hello-view.fxml", proxyClient);
    }













}
