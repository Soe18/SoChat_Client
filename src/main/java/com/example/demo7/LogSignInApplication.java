package com.example.demo7;

import com.example.demo7.Client.ProxyClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogSignInApplication extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LogSignInApplication.class.getResource("logregister.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        primaryStage = stage;
        stage.setMinWidth(720);
        stage.setMinHeight(200); //480
        stage.setTitle("SoChat");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String fxml, ProxyClient proxyClient) throws IOException {
        //Parent pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        //primaryStage.getScene().setRoot(pane);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            HelloController view = fxmlLoader.getController();
            view.setProxyClient(proxyClient);
            primaryStage.setScene(scene);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            System.out.println("Failed to locate view \"" + fxml + "\" in the project");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
