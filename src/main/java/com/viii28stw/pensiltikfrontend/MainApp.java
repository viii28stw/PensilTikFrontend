package com.viii28stw.pensiltikfrontend;

import com.viii28stw.pensiltikfrontend.controller.SplashScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Plamedi L. Lusembo
 */

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage splashScreenStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/view/splash-screen.fxml"));
        StackPane splashLayoutPane = loader.load();

        Scene scene = new Scene(splashLayoutPane);
        splashScreenStage.setTitle("Carregando...");
        splashScreenStage.setResizable(false);
        splashScreenStage.setMaximized(false);
        splashScreenStage.setScene(scene);

        SplashScreenController controller = loader.getController();
        controller.setSplashScreenStage(splashScreenStage);

        splashScreenStage.show();
    }
}