package com.viii28stw.pensiltikfrontend.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.validation.ValidationFacade;
import com.viii28stw.pensiltikfrontend.enumeration.MenuEnum;
import com.viii28stw.pensiltikfrontend.enumeration.SexoEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.viii28stw.pensiltikfrontend.model.domain.FormMenu;
import com.viii28stw.pensiltikfrontend.util.EmailValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CadastroUsuarioController implements Initializable {

    @Setter
    private Stage formStage;
    @FXML
    private JFXTextField jtxNome;
    @FXML
    private JFXTextField jtxSobrenome;
    @FXML
    private JFXComboBox<SexoEnum> jcbxSexo;
    @FXML
    private JFXTextField jtxEmail;
    @FXML
    private JFXPasswordField jpwSenha;
    @FXML
    private JFXPasswordField jpwConfirmarSenha;
    @FXML
    private JFXButton jbtnSalvar;
    @FXML
    private JFXButton jbtnLimpar;
    @FXML
    private JFXButton jbtnFechar;

    private boolean modoEdicao;

    private static final RequiredFieldValidator nomeValidatorCampoObrigatorio = new RequiredFieldValidator();
    private static final RequiredFieldValidator sobrenomeValidatorCampoObrigatorio = new RequiredFieldValidator();
    private static final RequiredFieldValidator emailValidatorCampoObrigatorio = new RequiredFieldValidator();
    private static final RequiredFieldValidator senhaValidatorCampoObrigatorio = new RequiredFieldValidator();
    private static final RequiredFieldValidator confirmarSenhaValidatorCampoObrigatorio = new RequiredFieldValidator();

    private final ObservableList<SexoEnum> obsListSexo = FXCollections.observableArrayList();

    @FXML
    private Label lblSexoSelecaoObrigatorio;
    @FXML
    private ImageView imgvwSexoSelecaoObrigatorio;
    @FXML
    private Label lblEmailInvalido;
    @FXML
    private ImageView imgvwEmailInvalido;
    @FXML
    private Label lblConfirmarSenhaNaoCorresponde;
    @FXML
    private ImageView imgvwConfirmarSenhaNaoCorresponde;

    private static CadastroUsuarioController uniqueInstance;

    public static synchronized CadastroUsuarioController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CadastroUsuarioController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeValidatorCampoObrigatorio.setMessage("Nome: Campo obrigatório");
        sobrenomeValidatorCampoObrigatorio.setMessage("Sobrenome: Campo obrigatório");
        emailValidatorCampoObrigatorio.setMessage("Email: Campo obrigatório");
        senhaValidatorCampoObrigatorio.setMessage("Senha: Campo obrigatório");
        confirmarSenhaValidatorCampoObrigatorio.setMessage("Confirmar senha: Campo obrigatório");

        jtxNome.getValidators().add(nomeValidatorCampoObrigatorio);
        jtxSobrenome.getValidators().add(sobrenomeValidatorCampoObrigatorio);
        jtxEmail.getValidators().add(emailValidatorCampoObrigatorio);
        jpwSenha.getValidators().add(senhaValidatorCampoObrigatorio);
        jpwConfirmarSenha.getValidators().add(confirmarSenhaValidatorCampoObrigatorio);

        //Enquanto o RequiredValidator para o ComboBox e alguns outros componentes ainda não é implementado
        lblSexoSelecaoObrigatorio.setVisible(false);
        lblSexoSelecaoObrigatorio.setStyle("-fx-text-fill: #c00d0d;");
        imgvwSexoSelecaoObrigatorio.setVisible(false);

        lblEmailInvalido.setVisible(false);
        lblEmailInvalido.setStyle("-fx-text-fill: #c00d0d;");
        imgvwEmailInvalido.setVisible(false);

        lblConfirmarSenhaNaoCorresponde.setVisible(false);
        lblConfirmarSenhaNaoCorresponde.setStyle("-fx-text-fill: #c00d0d;");
        imgvwConfirmarSenhaNaoCorresponde.setVisible(false);

        SexoEnum.list().forEach(obsListSexo::add);
        jcbxSexo.setItems(obsListSexo);

        jtxNome.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxNome.validate();
                }
            }
        });
        jtxSobrenome.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxSobrenome.validate();
                }
            }
        });
        jcbxSexo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jcbxSexo.getValue() == null) {
                        lblSexoSelecaoObrigatorio.setVisible(true);
                        imgvwSexoSelecaoObrigatorio.setVisible(true);
                    } else {
                        lblSexoSelecaoObrigatorio.setVisible(false);
                        imgvwSexoSelecaoObrigatorio.setVisible(false);
                    }
                }
            }
        });
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
        jpwConfirmarSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    jpwConfirmarSenha.validate();
                }


                if (oldValue) {
                    if (jpwConfirmarSenha.validate()) {
                        if (jpwSenha.getText().equals(jpwConfirmarSenha.getText())) {
                            lblConfirmarSenhaNaoCorresponde.setVisible(false);
                            imgvwConfirmarSenhaNaoCorresponde.setVisible(false);
                        } else {
                            lblConfirmarSenhaNaoCorresponde.setVisible(true);
                            imgvwConfirmarSenhaNaoCorresponde.setVisible(true);
                        }
                    } else {
                        lblConfirmarSenhaNaoCorresponde.setVisible(false);
                        imgvwConfirmarSenhaNaoCorresponde.setVisible(false);
                    }
                }
            }
        });

    }

    @FXML
    private void jbtnSalvarAction() {
        jpwConfirmarSenha.getValidators().forEach(validatorBase -> {
            System.out.println(validatorBase.getMessage());
        });



        System.out.println("Após a limpa");

        jpwConfirmarSenha.getValidators().forEach(validatorBase -> {
            System.out.println(validatorBase.getMessage());
        });

        jpwConfirmarSenha.getValidators().add(confirmarSenhaValidatorCampoObrigatorio);
        jpwConfirmarSenha.validate();
    }

    @FXML
    private void jbtnLimparAction() {
        modoEdicao = false;

        jtxNome.resetValidation();
        jtxSobrenome.resetValidation();

        lblSexoSelecaoObrigatorio.setVisible(false);
        imgvwSexoSelecaoObrigatorio.setVisible(false);

        jtxEmail.resetValidation();
        lblEmailInvalido.setVisible(false);
        imgvwEmailInvalido.setVisible(false);

        jpwSenha.resetValidation();
        jpwConfirmarSenha.resetValidation();
        lblConfirmarSenhaNaoCorresponde.setVisible(false);
        imgvwConfirmarSenhaNaoCorresponde.setVisible(false);

        jtxNome.clear();
        jtxSobrenome.clear();
        jcbxSexo.getSelectionModel().select(null);
        jtxEmail.clear();
        jpwSenha.clear();
        jpwConfirmarSenha.clear();

        jtxNome.requestFocus();

        jbtnSalvar.setText("SALVAR");

    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
    }

}
