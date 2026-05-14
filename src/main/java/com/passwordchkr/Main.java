package com.passwordchkr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(Main.class.getResource("main-view.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setTitle("Kalkulator bezpieczeństwa haseł");

        stage.setScene(scene);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm()
        );

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}