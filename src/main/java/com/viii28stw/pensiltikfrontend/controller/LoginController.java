package com.viii28stw.pensiltikfrontend.controller;


import com.viii28stw.pensiltikfrontend.MainApp;
import com.viii28stw.pensiltikfrontend.enumeration.MenuEnum;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.viii28stw.pensiltikfrontend.util.EmailValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Plamedi L. Lusembo
 */

@NoArgsConstructor
public class LoginController implements Initializable {

    @Setter
    private Stage loginStage;
    @FXML
    private JFXTextField jtxEmail;
    @FXML
    private JFXPasswordField jpwSenha;
    @FXML
    private JFXCheckBox jchxLembrarDeMim;

    private static final RequiredFieldValidator emailValidatorCampoObrigatorio = new RequiredFieldValidator();
    private static final RequiredFieldValidator senhaValidatorCampoObrigatorio = new RequiredFieldValidator();

    @FXML
    private Label lblEmailInvalido;
    @FXML
    private ImageView imgvwEmailInvalido;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailValidatorCampoObrigatorio.setMessage("Email: Campo obrigatório");
        senhaValidatorCampoObrigatorio.setMessage("Senha: Campo obrigatório");

        jtxEmail.getValidators().add(emailValidatorCampoObrigatorio);
        jpwSenha.getValidators().add(senhaValidatorCampoObrigatorio);

        lblEmailInvalido.setVisible(false);
        lblEmailInvalido.setStyle("-fx-text-fill: #c00d0d;");
        imgvwEmailInvalido.setVisible(false);

        jtxEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if(jtxEmail.validate()) {
                        if (EmailValidator.getInstance().validaEmail(jtxEmail.getText().trim())) {
                            lblEmailInvalido.setVisible(false);
                            imgvwEmailInvalido.setVisible(false);
                        } else {
                            lblEmailInvalido.setVisible(true);
                            imgvwEmailInvalido.setVisible(true);
                        }
                    } else {
                        lblEmailInvalido.setVisible(false);
                        imgvwEmailInvalido.setVisible(false);
                    }
                }
            }
        });
        jpwSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jpwSenha.validate();
                }
            }
        });

    }

    @FXML
    private void jtxEmailOnKeyPressed(KeyEvent evt) {
        if (evt.getCode() == KeyCode.ENTER) {
            this.jbtnEntrarOnAction();
        }
    }

    @FXML
    private void jpwSenhaOnKeyPressed(KeyEvent evt) {
        if (evt.getCode() == KeyCode.ENTER) {
            this.jbtnEntrarOnAction();
        }
    }


    @FXML
    private void hlkAbrirUmaContaAction() {
        try {
            Stage cadastroUsuarioStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/cadastro-usuario.fxml"));
            StackPane cadastroUsuarioStackPane = (StackPane) loader.load();

            Scene scene = new Scene(cadastroUsuarioStackPane);
            cadastroUsuarioStage.setTitle("Cadastro de usuário");
            cadastroUsuarioStage.setResizable(false);
            cadastroUsuarioStage.setMaximized(false);
            cadastroUsuarioStage.setScene(scene);

            loginStage.close();

            CadastroUsuarioController controller = loader.getController();
            controller.setCadastroUsuarioStage(cadastroUsuarioStage);

            cadastroUsuarioStage.showAndWait();

            loginStage.show();
            limpaForm();

        } catch (IOException ex) {
            System.out.println(ex);
        }


    }

    @FXML
    private void jbtnEntrarOnAction() {
        jtxEmail.getValidators().clear();
        jtxEmail.getValidators().add(emailValidatorCampoObrigatorio);
        jpwSenha.getValidators().clear();
        jpwSenha.getValidators().add(senhaValidatorCampoObrigatorio);

        if (!jtxEmail.validate() && !jpwSenha.validate()) {
            jtxEmail.requestFocus();
            return;
        } else if (!jtxEmail.validate()) {
            jtxEmail.requestFocus();
            return;
        } else if (!jpwSenha.validate()) {
            jpwSenha.requestFocus();
            return;
        }
        try {
            Stage mDIStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/MDI.fxml"));
            StackPane mainStackPane = loader.load();

            Scene scene = new Scene(mainStackPane);
            mDIStage.setTitle("Mistersoft");
            mDIStage.setMaximized(true);
            mDIStage.setScene(scene);

            mDIStage.setOnCloseRequest((WindowEvent we) -> {
                System.exit(0);
            });

            loginStage.close();

            MDIController controller = loader.getController();
            controller.setMDIStage(mDIStage);

            mDIStage.showAndWait();

            loginStage.show();
            limpaForm();

        } catch (IOException ex) {
        }
    }

    private void limpaForm() {
        jtxEmail.resetValidation();
        jpwSenha.resetValidation();

        jtxEmail.clear();
        lblEmailInvalido.setVisible(false);
        imgvwEmailInvalido.setVisible(false);

        jpwSenha.clear();
        jtxEmail.requestFocus();
    }

}