package com.example.demo7;

import com.example.demo7.Client.ProxyClient;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.util.ArrayList;
import java.util.HashMap;

public class HelloController {

    private ProxyClient proxyClient;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private MFXButton addChatBtn;

    @FXML
    private MFXButton andreaBubble;

    @FXML
    private MFXScrollPane andreaChatContainer;
    @FXML
    private VBox andreaChat;

    @FXML
    private Label currentDest;

    @FXML
    private VBox chatVBoxContainer;

    @FXML
    private MFXTextField messageTextInput;

    @FXML
    private MFXButton paoloBubble;

    @FXML
    private MFXScrollPane paoloChatContainer;

    @FXML
    private VBox paoloChat;

    @FXML
    private MFXButton sendBtn;

    private ArrayList<MFXScrollPane> allChatContainers = new ArrayList<>();
    private ArrayList<MFXButton> allChatBubble = new ArrayList<>();
    private ArrayList<VBox> allChat = new ArrayList<>();

    public void setProxyClient(ProxyClient proxyClient) {
        this.proxyClient = proxyClient;
        addChatBtn.setText("@"+proxyClient.nickname);

        // load chat
        //proxyClient.loadContacts(proxyClient.nickname);
    }

    public void initialize(){
        allChatContainers.add(andreaChatContainer);
        allChatContainers.add(paoloChatContainer);
        allChat.add(andreaChat);
        allChat.add(paoloChat);
        allChatBubble.add(andreaBubble);
        allChatBubble.add(paoloBubble);
    }

    @FXML
    void show(ActionEvent event) {
        String messText = messageTextInput.getText();
        if (messText.length() > 0) {
            HBox messContainer = new HBox();
            messContainer.setAlignment(Pos.CENTER_RIGHT);
            VBox.setMargin(messContainer, new Insets(10, 10, 10 ,10));

            MFXButton messBubble = new MFXButton();
            messBubble.setText(messageTextInput.getText());
            messBubble.getStylesheets().add(getClass().getResource("materialfx/css/MFXButton.css").toExternalForm());
            messBubble.setStyle("-fx-background-color:  #0adea1");
            messBubble.setButtonType(ButtonType.RAISED);
            messBubble.setRippleRadius(0.0);

            messContainer.getChildren().add(messBubble);

            VBox currentChat = null;
            for (int i = 0; i < allChatContainers.size(); i++) {
                if (allChatContainers.get(i).isVisible()) {
                    currentChat = allChat.get(i);
                }
            }
            currentChat.getChildren().add(messContainer); // to change

            System.out.println("Message to send...");
            messageTextInput.setText("");
        }
    }

    @FXML
    void showChat(ActionEvent event) {
        MFXButton whoCalled = (MFXButton) event.getSource();
        for (int i=0; i<allChatBubble.size(); i++) {
            if (whoCalled.equals(allChatBubble.get(i))) {
                currentDest.setText(whoCalled.getText());
                allChatContainers.get(i).setVisible(true);
                allChat.get(i).setVisible(true);
            }
            else {
                allChatContainers.get(i).setVisible(false);
                allChat.get(i).setVisible(false);
            }
        }
    }


    void loadContacts(String nickname) {
        ArrayList<String> contacts = proxyClient.loadContacts(nickname);

        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(contacts.get(i));
        }

        //Add the button
        MFXButton newChatBubble = new MFXButton();
        newChatBubble.getStylesheets().add(getClass().getResource("materialfx/css/MFXButton.css").toExternalForm());
        newChatBubble.setStyle("-fx-background-radius: 20; -fx-background-color: #bba0b2; -fx-font-size: 32;");
        newChatBubble.setPrefWidth(270);
        newChatBubble.setPrefHeight(100);
        newChatBubble.setOnAction(this::showChat);
        chatVBoxContainer.getChildren().add(newChatBubble);
        allChatBubble.add(newChatBubble);

        MFXScrollPane newChatContainer = new MFXScrollPane();
        newChatContainer.getStylesheets().add(getClass().getResource("materialfx/css/MFXScrollPane.css").toExternalForm());
        newChatContainer.setPrefWidth(1030);
        newChatContainer.setPrefHeight(517);
        newChatContainer.snapPositionX(10);
        newChatContainer.snapPositionY(10);
        AnchorPane.setLeftAnchor(newChatContainer, 251.0);
        AnchorPane.setTopAnchor(newChatContainer, 100.0);
        AnchorPane.setBottomAnchor(newChatContainer, 75.0);
        anchorRoot.getChildren().add(newChatContainer);

        VBox newChat = new VBox();
        newChat.setPrefHeight(Region.USE_COMPUTED_SIZE);
        newChat.setPrefWidth(Region.USE_COMPUTED_SIZE);
        anchorRoot.getChildren().indexOf(newChatContainer);



        allChatContainers.add(newChatContainer);
        allChat.add(newChat);

        for (int i=0; i<allChatBubble.size(); i++) {
            if (newChatBubble.equals(allChatBubble.get(i))) {
                currentDest.setText(newChatBubble.getText());
                allChatContainers.get(i).setVisible(true);
                allChat.get(i).setVisible(true);
            }
            else {
                allChatContainers.get(i).setVisible(false);
                allChat.get(i).setVisible(false);
            }
        }

        initChatMessage();
    }
    @FXML
    void addChat(ActionEvent event) {
        //Add the button
        MFXButton newChatBubble = new MFXButton();
        newChatBubble.getStylesheets().add(getClass().getResource("materialfx/css/MFXButton.css").toExternalForm());
        newChatBubble.setStyle("-fx-background-radius: 20; -fx-background-color: #bba0b2; -fx-font-size: 32;");
        newChatBubble.setPrefWidth(270);
        newChatBubble.setPrefHeight(100);
        newChatBubble.setOnAction(this::showChat);
        chatVBoxContainer.getChildren().add(newChatBubble);
        allChatBubble.add(newChatBubble);

        MFXScrollPane newChatContainer = new MFXScrollPane();
        newChatContainer.getStylesheets().add(getClass().getResource("materialfx/css/MFXScrollPane.css").toExternalForm());
        newChatContainer.setPrefWidth(1030);
        newChatContainer.setPrefHeight(517);
        newChatContainer.snapPositionX(10);
        newChatContainer.snapPositionY(10);
        AnchorPane.setLeftAnchor(newChatContainer, 251.0);
        AnchorPane.setTopAnchor(newChatContainer, 100.0);
        AnchorPane.setBottomAnchor(newChatContainer, 75.0);
        anchorRoot.getChildren().add(newChatContainer);

        VBox newChat = new VBox();
        newChat.setPrefHeight(Region.USE_COMPUTED_SIZE);
        newChat.setPrefWidth(Region.USE_COMPUTED_SIZE);
        anchorRoot.getChildren().indexOf(newChatContainer);
        


        allChatContainers.add(newChatContainer);
        allChat.add(newChat);

        for (int i=0; i<allChatBubble.size(); i++) {
            if (newChatBubble.equals(allChatBubble.get(i))) {
                currentDest.setText(newChatBubble.getText());
                allChatContainers.get(i).setVisible(true);
                allChat.get(i).setVisible(true);
            }
            else {
                allChatContainers.get(i).setVisible(false);
                allChat.get(i).setVisible(false);
            }
        }

        initChatMessage();
    }

    void initChatMessage(){
        HBox messContainer = new HBox();
        messContainer.setAlignment(Pos.CENTER);
        VBox.setMargin(messContainer, new Insets(10, 10, 10 ,10));

        MFXButton messBubble = new MFXButton();
        messBubble.setText("INVIA IL NOME DELL'UTENTE PER AVVIARE LA CONVERSAZIONE");
        messBubble.getStylesheets().add(getClass().getResource("materialfx/css/MFXButton.css").toExternalForm());
        messBubble.setStyle("-fx-background-color:  #0adea1");
        messBubble.setButtonType(ButtonType.RAISED);
        messBubble.setRippleRadius(0.0);

        messContainer.getChildren().add(messBubble);

        VBox currentChat = null;
        for (int i = 0; i < allChatContainers.size(); i++) {
            if (allChatContainers.get(i).isVisible()) {
                currentChat = allChat.get(i);
            }
        }
        currentChat.getChildren().add(messContainer); // to change

        System.out.println("First message to send...");
        messageTextInput.setText("");
    }


}