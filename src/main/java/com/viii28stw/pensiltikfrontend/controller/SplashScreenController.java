package com.viii28stw.pensiltikfrontend.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.viii28stw.pensiltikfrontend.MainApp;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;

/**
 * @author Plamedi L. Lusembo
 */

public class SplashScreenController implements Initializable {

    @Setter
    private Stage splashScreenStage;
    @FXML
    private Label lblVersao;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        longStart();
    }

    private void longStart() {
        Service<String> service = new Service() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        Thread.sleep(2750);
                        return null;
                    }
                };
            }
        };
        service.start();
        service.setOnSucceeded((WorkerStateEvent event) -> {
            try {
                Stage loginStage = new Stage();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/view/login.fxml"));
                AnchorPane loginAnchorPane = loader.load();

                Scene scene = new Scene(loginAnchorPane);
                loginStage.setTitle("Login");
                loginStage.setResizable(false);
                loginStage.setMaximized(false);
                loginStage.setScene(scene);

                splashScreenStage.close();

                LoginController controller = loader.getController();
                controller.setLoginStage(loginStage);

                loginStage.show();
            } catch (IOException ex) {
            }
        });
    }
}