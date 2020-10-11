package br.com.controleasy.controller;

import br.com.controleasy.application.Main;
import br.com.controleasy.dao.ChavesDAO;
import br.com.controleasy.dao.LoginDAO;
import br.com.controleasy.dao.UsuariosDAO;
import br.com.controleasy.model.Usuarios;
import br.com.controleasy.util.Alerts;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable {

    private static Stage stage;
    private static Stage stageLogin;
    private static String id;
    private static String usuario;
    private static String acesso;
    private static final boolean CHECK_ACTIVATION = true;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        LoginFXMLController.id = id;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getAcesso() {
        return acesso;
    }

    public static void setAcesso(String acesso) {
        LoginFXMLController.acesso = acesso;
    }
    
    public static void setUsuario(String usuario) {
        LoginFXMLController.usuario = usuario;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        LoginFXMLController.stage = stage;
    }

    public static Stage getStageLogin() {
        return stageLogin;
    }

    public static void setStageLogin(Stage stageLogin) {
        LoginFXMLController.stageLogin = stageLogin;
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnEntrar;

    @FXML
    private Hyperlink redefinirSenha;

    @FXML
    private ComboBox<?> txtUsuario;

    public PasswordField getTxtSenha() {
        return txtSenha;
    }

    public void setTxtSenha(PasswordField txtSenha) {
        this.txtSenha = txtSenha;
    }

    public ComboBox<?> getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(ComboBox<?> txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    @FXML
    @SuppressWarnings("null")
    void login(ActionEvent event) {
        Usuarios user = new Usuarios();
        try {
            if (!this.getTxtSenha().getText().equals("")) {
                MessageDigest algorithm = null;
                try {
                    algorithm = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte messageDigest[] = null;
                try {
                    messageDigest = algorithm.digest(this.getTxtSenha().getText().toUpperCase().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                StringBuilder hex = new StringBuilder();
                for (byte b : messageDigest) {
                    hex.append(String.format("%02X", 0xFF & b));
                }

                user.setUsuario(this.getTxtUsuario().getSelectionModel().getSelectedItem().toString());
                user.setSenha(hex.toString().toUpperCase());
                System.out.println(hex.toString());
                List<Usuarios> listUsuarios = new LoginDAO().login(user);
                if (!listUsuarios.isEmpty()) {
                    LoginFXMLController.setId(Integer.toString(listUsuarios.get(0).getId()));
                    LoginFXMLController.setUsuario(listUsuarios.get(0).getUsuario());
                    LoginFXMLController.setAcesso(listUsuarios.get(0).getAcesso());
                    Parent main = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/MainScreenFXML.fxml"));
                    Stage mainStage = new Stage();
                    Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
                    mainStage.getIcons().add(image);
                    mainStage.setScene(new Scene(main));
                    mainStage.setTitle("CONTROLEASY - CONTROLE FINANCEIRO");
                    mainStage.setResizable(false);
                    mainStage.setMaximized(true);
                    mainStage.show();
                    LoginFXMLController.setStage(mainStage); // STAGE DA TELA PRINCIPAL
                    LoginFXMLController.setStageLogin(Main.getStage());
                    this.getTxtSenha().setText("");
                    Main.getStage().close();
                } else {
                    Alerts.showAlert("Controleasy", "LOGIN", "SENHA INVÁLIDA!", Alert.AlertType.INFORMATION);
                }
            } else {
                Alerts.showAlert("Controleasy", "LOGIN", "PREENCHA O CAMPO DE SENHA", Alert.AlertType.INFORMATION);
            }
        } catch (IOException ex) {
            Alerts.showAlert("Controleasy", "LOGIN", ex.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    public void getUsuarios() {
        try {
            List<String> usuarios = new UsuariosDAO().getUsuarios();
            List<String> list = new ArrayList<>();
            if (!usuarios.isEmpty()) {
                usuarios.forEach((u) -> {
                    list.add(u.toUpperCase());
                });
                ObservableList observableList = FXCollections.observableList(list);
                this.getTxtUsuario().setItems(observableList);
                this.getTxtUsuario().getSelectionModel().select(0);
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", "LOGIN", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void verificarAtivacao() {
        boolean chaves = new ChavesDAO().verificarAtivacao();
        if (!chaves) {
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/SerialKeyFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage serialKeyStage = new Stage();
            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
            serialKeyStage.getIcons().add(image);
            serialKeyStage.setScene(new Scene(parent));
            serialKeyStage.setTitle("ATIVAR SOFTWARE");
            serialKeyStage.setResizable(false);
            serialKeyStage.show();
            LoginFXMLController.setStage(serialKeyStage);
            Main.getStage().close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (CHECK_ACTIVATION) {
            this.verificarAtivacao();
        }
        this.getUsuarios();
    }
}
