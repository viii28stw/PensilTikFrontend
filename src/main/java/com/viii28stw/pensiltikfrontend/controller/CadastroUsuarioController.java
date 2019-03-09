package com.viii28stw.pensiltikfrontend.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import com.viii28stw.pensiltikfrontend.enumeration.SexoEnum;
import com.jfoenix.validation.RequiredFieldValidator;
import com.viii28stw.pensiltikfrontend.model.dto.UsuarioDto;
import com.viii28stw.pensiltikfrontend.util.EmailValidator;
import com.viii28stw.pensiltikfrontend.util.Notificacoes;
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
    private Stage cadastroUsuarioStage;
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
    @FXML
    private Label lblNotificacao;
    @FXML
    private ImageView imgvwNotificacao;
    private Notificacoes notificacoes;

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

        lblNotificacao.setVisible(false);
        imgvwNotificacao.setVisible(false);

        notificacoes = new Notificacoes(imgvwNotificacao, lblNotificacao);

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
        if(!validaCampos()) return;

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nome(jtxNome.getText().trim())
                .sobreNome(jtxSobrenome.getText().trim())
                .sexoEnum(jcbxSexo.getValue())
                .email(jtxEmail.getText().trim())
                .senha(jpwSenha.getText().trim())
                .build();

        notificacoes.notificaSalvo();

        limpaCampos();

    }

    private boolean validaCampos() {
        int campo = 0;

        if(!jtxNome.validate()) {
            campo = 1;
        }
        if(!jtxSobrenome.validate()) {
            if(campo == 0) campo = 2;
        }
        if(jcbxSexo.getValue() == null) {
            lblSexoSelecaoObrigatorio.setVisible(true);
            imgvwSexoSelecaoObrigatorio.setVisible(true);
            if(campo == 0) campo = 3;
        }
        if(!jtxEmail.validate()) {
            if(campo == 0) campo = 4;
        } else if (!EmailValidator.getInstance().validaEmail(jtxEmail.getText().trim())) {
            lblEmailInvalido.setVisible(true);
            imgvwEmailInvalido.setVisible(true);
            if(campo == 0) campo = 4;
        }
        if(!jpwSenha.validate()) {
            if(campo == 0) campo = 5;
        }
        if(!jpwConfirmarSenha.validate()) {
            if(campo == 0) campo = 6;
        } else if (!jpwSenha.getText().equals(jpwConfirmarSenha.getText())) {
            lblConfirmarSenhaNaoCorresponde.setVisible(true);
            imgvwConfirmarSenhaNaoCorresponde.setVisible(true);
            if(campo == 0) campo = 6;
        }

        switch (campo) {
            case 1: jtxNome.requestFocus(); return false;
            case 2: jtxSobrenome.requestFocus(); return false;
            case 3: jcbxSexo.requestFocus(); return false;
            case 4: jtxEmail.requestFocus(); return false;
            case 5: jpwSenha.requestFocus(); return false;
            case 6: jpwConfirmarSenha.requestFocus(); return false;
            case 0: return true;
            default: return false;
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaCampos();
    }

    private void limpaCampos() {
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
        cadastroUsuarioStage.close();
    }

}
