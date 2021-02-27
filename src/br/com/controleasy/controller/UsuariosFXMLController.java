/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.controller;

import br.com.controleasy.dao.UsuariosDAO;
import br.com.controleasy.model.Usuarios;
import br.com.controleasy.util.Alerts;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author luanp
 */
public class UsuariosFXMLController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private PasswordField passwordSenha;

    @FXML
    private RadioButton acessoAdmin;

    @FXML
    private RadioButton acessoPadrao;

    @FXML
    private TextField textFieldSaldoInicial;

    @FXML
    private TextField textFieldId;

    @FXML
    private PasswordField passwordConfirmarSenha;

    @FXML
    private Button buttonLimpar;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private ToggleGroup tipoAcesso;

    @FXML
    private TableView<Usuarios> tableViewUsuarios;

    @FXML
    private TableColumn<Usuarios, Integer> columnId;

    @FXML
    private TableColumn<Usuarios, String> columnNome;

    @FXML
    private TableColumn<Usuarios, String> columnUsuario;

    @FXML
    private TableColumn<Usuarios, String> columnSenha;

    @FXML
    private TableColumn<Usuarios, String> columnAcesso;

    @FXML
    private TableColumn<Usuarios, BigDecimal> columnSaldo;

    public PasswordField getPasswordConfirmarSenha() {
        return passwordConfirmarSenha;
    }

    public void setPasswordConfirmarSenha(PasswordField passwordConfirmarSenha) {
        this.passwordConfirmarSenha = passwordConfirmarSenha;
    }

    public Button getButtonLimpar() {
        return buttonLimpar;
    }

    public void setButtonLimpar(Button buttonLimpar) {
        this.buttonLimpar = buttonLimpar;
    }

    public Button getButtonDeletar() {
        return buttonDeletar;
    }

    public void setButtonDeletar(Button buttonDeletar) {
        this.buttonDeletar = buttonDeletar;
    }

    public Button getButtonAtualizar() {
        return buttonAtualizar;
    }

    public void setButtonAtualizar(Button buttonAtualizar) {
        this.buttonAtualizar = buttonAtualizar;
    }

    public Button getButtonCadastrar() {
        return buttonCadastrar;
    }

    public void setButtonCadastrar(Button buttonCadastrar) {
        this.buttonCadastrar = buttonCadastrar;
    }

    public RadioButton getAcessoAdmin() {
        return acessoAdmin;
    }

    public void setAcessoAdmin(RadioButton acessoAdmin) {
        this.acessoAdmin = acessoAdmin;
    }

    public RadioButton getAcessoPadrao() {
        return acessoPadrao;
    }

    public void setAcessoPadrao(RadioButton acessoPadrao) {
        this.acessoPadrao = acessoPadrao;
    }

    public TextField getTextFieldNome() {
        return textFieldNome;
    }

    public void setTextFieldNome(TextField textFieldNome) {
        this.textFieldNome = textFieldNome;
    }

    public TextField getTextFieldUsuario() {
        return textFieldUsuario;
    }

    public void setTextFieldUsuario(TextField textFieldUsuario) {
        this.textFieldUsuario = textFieldUsuario;
    }

    public PasswordField getPasswordSenha() {
        return passwordSenha;
    }

    public void setPasswordSenha(PasswordField passwordSenha) {
        this.passwordSenha = passwordSenha;
    }

    public TextField getTextFieldSaldoInicial() {
        return textFieldSaldoInicial;
    }

    public void setTextFieldSaldoInicial(TextField textFieldSaldoInicial) {
        this.textFieldSaldoInicial = textFieldSaldoInicial;
    }

    public TextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(TextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public ToggleGroup getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(ToggleGroup tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public TableColumn<Usuarios, Integer> getColumnId() {
        return columnId;
    }

    public void setColumnId(TableColumn<Usuarios, Integer> columnId) {
        this.columnId = columnId;
    }

    public TableColumn<Usuarios, String> getColumnNome() {
        return columnNome;
    }

    public void setColumnNome(TableColumn<Usuarios, String> columnNome) {
        this.columnNome = columnNome;
    }

    public TableColumn<Usuarios, String> getColumnUsuario() {
        return columnUsuario;
    }

    public void setColumnUsuario(TableColumn<Usuarios, String> columnUsuario) {
        this.columnUsuario = columnUsuario;
    }

    public TableColumn<Usuarios, String> getColumnSenha() {
        return columnSenha;
    }

    public void setColumnSenha(TableColumn<Usuarios, String> columnSenha) {
        this.columnSenha = columnSenha;
    }

    public TableColumn<Usuarios, String> getColumnAcesso() {
        return columnAcesso;
    }

    public void setColumnAcesso(TableColumn<Usuarios, String> columnAcesso) {
        this.columnAcesso = columnAcesso;
    }

    public TableColumn<Usuarios, BigDecimal> getColumnSaldo() {
        return columnSaldo;
    }

    public void setColumnSaldo(TableColumn<Usuarios, BigDecimal> columnSaldo) {
        this.columnSaldo = columnSaldo;
    }

    public TableView<Usuarios> getTableViewUsuarios() {
        return tableViewUsuarios;
    }

    public void setTableViewUsuarios(TableView<Usuarios> tableViewUsuarios) {
        this.tableViewUsuarios = tableViewUsuarios;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getColumnId().setCellValueFactory(new PropertyValueFactory("id"));
        this.getColumnNome().setCellValueFactory(new PropertyValueFactory("nome"));
        this.getColumnUsuario().setCellValueFactory(new PropertyValueFactory("usuario"));
        this.getColumnSenha().setCellValueFactory(new PropertyValueFactory("senha"));
        this.getColumnAcesso().setCellValueFactory(new PropertyValueFactory("acesso"));
        this.getColumnSaldo().setCellValueFactory(new PropertyValueFactory("saldo"));
        this.getTextFieldSaldoInicial().setText("0,00");
        this.getButtonCadastrar().setDisable(false);
        this.getButtonAtualizar().setDisable(true);
        this.getButtonDeletar().setDisable(true);
        this.getButtonLimpar().setDisable(true);

        this.getUsuarios();
    }

    public void getUsuarios() {
        try {
            List<Usuarios> result = new UsuariosDAO().getUsuarios();
            List<Usuarios> listUsuarios = new ArrayList<>();
            if (!result.isEmpty()) {
                result.forEach((u) -> {
                    listUsuarios.add(new Usuarios(u.getId(), u.getNome().toUpperCase(), u.getUsuario().toUpperCase(), u.getSenha(), u.getAcesso().toUpperCase(), u.getSaldo()));
                });
                this.getTableViewUsuarios().setItems(FXCollections.observableArrayList(listUsuarios));
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void setUsuariosForm(MouseEvent event) {
        Usuarios usuario = this.getTableViewUsuarios().getItems().get(this.getTableViewUsuarios().getSelectionModel().getSelectedIndex());
        this.getTextFieldId().setText(Integer.toString(usuario.getId()));
        this.getTextFieldNome().setText(usuario.getNome());
        this.getTextFieldUsuario().setText(usuario.getUsuario());
        this.getPasswordSenha().setText(usuario.getSenha());
        this.getPasswordConfirmarSenha().setText(usuario.getSenha());
        if (usuario.getAcesso().equals("ADMIN")) {
            this.getAcessoAdmin().setSelected(true);
        } else if (usuario.getAcesso().equals("PADRÃO")) {
            this.getAcessoPadrao().setSelected(true);
        }
        this.getTextFieldSaldoInicial().setText(String.format("%.2f", usuario.getSaldo()));

        this.getButtonCadastrar().setDisable(true);
        this.getButtonAtualizar().setDisable(false);
        this.getButtonDeletar().setDisable(false);
        this.getButtonLimpar().setDisable(false);
        this.getPasswordSenha().setDisable(true);
        this.getPasswordConfirmarSenha().setDisable(true);
    }

    @FXML
    private void limparForm(ActionEvent event) {
        this.getTextFieldId().setText("");
        this.getTextFieldNome().setText("");
        this.getTextFieldUsuario().setText("");
        this.getPasswordSenha().setText("");
        this.getPasswordConfirmarSenha().setText("");
        this.getTextFieldSaldoInicial().setText("");
        this.getAcessoPadrao().setSelected(true);
        this.getTextFieldSaldoInicial().setText("0,00");
        this.getButtonCadastrar().setDisable(false);
        this.getButtonAtualizar().setDisable(true);
        this.getButtonDeletar().setDisable(true);
        this.getButtonLimpar().setDisable(true);
        this.getPasswordSenha().setDisable(false);
        this.getPasswordConfirmarSenha().setDisable(false);
    }

    @FXML
    private void buttonCadastrar(ActionEvent event) {
        if (this.getTextFieldNome().getText().isEmpty() && this.getTextFieldUsuario().getText().isEmpty() && this.getPasswordSenha().getText().isEmpty() && this.getPasswordConfirmarSenha().getText().isEmpty()) {
            Alerts.showAlert("Controleasy", "CAMPO OBRIGATÓRIO (*)", "PREENCHA OS CAMPOS OBRIGATÓRIOS", Alert.AlertType.INFORMATION);
        } else if (this.getTextFieldNome().getText().isEmpty()) {
            Alerts.showAlert("Controleasy", "CAMPO OBRIGATÓRIO (*)", "PREENCHA O CAMPO DE NOME", Alert.AlertType.INFORMATION);
        } else if (this.getTextFieldUsuario().getText().isEmpty()) {
            Alerts.showAlert("Controleasy", "CAMPO OBRIGATÓRIO (*)", "PREENCHA O CAMPO DE USUÁRIO", Alert.AlertType.INFORMATION);
        } else if (this.getPasswordSenha().getText().isEmpty() || this.getPasswordConfirmarSenha().getText().isEmpty()) {
            Alerts.showAlert("Controleasy", "CAMPO OBRIGATÓRIO (*)", "PREENCHA OS CAMPOS DE SENHA", Alert.AlertType.INFORMATION);
        } else if (this.getPasswordConfirmarSenha().getText().equals(this.getPasswordSenha().getText())) {
            Usuarios usuario = new Usuarios();
            UsuariosDAO usuariosDAO = new UsuariosDAO();
            try {
                MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = algorithm.digest(this.getPasswordSenha().getText().toUpperCase().getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }
                usuario.setSenha(hexString.toString());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            }
            usuario.setNome(this.getTextFieldNome().getText().toUpperCase());
            usuario.setUsuario(this.getTextFieldUsuario().getText().toUpperCase());
            if (this.getAcessoAdmin().isSelected()) {
                usuario.setAcesso("ADMIN");
            } else if (this.getAcessoPadrao().isSelected()) {
                usuario.setAcesso("PADRÃO");
            }
            if (this.getTextFieldSaldoInicial().getText().isEmpty()) {
                usuario.setSaldo(new BigDecimal(0));
            } else {
                usuario.setSaldo(new BigDecimal(this.getTextFieldSaldoInicial().getText().replace(",", ".")));
            }
            if (usuariosDAO.cadastrarUsuarios(usuario)) {
                Alerts.showAlert("Controleasy", null, "USUÁRIO CADASTRADO COM SUCESSO!", Alert.AlertType.INFORMATION);
                this.getTextFieldNome().setText("");
                this.getTextFieldUsuario().setText("");
                this.getPasswordSenha().setText("");
                this.getPasswordConfirmarSenha().setText("");
                this.getTextFieldSaldoInicial().setText("");
                this.getAcessoPadrao().setSelected(true);
                this.getTextFieldSaldoInicial().setText("0,00");
                this.getButtonCadastrar().setDisable(false);
                this.getButtonAtualizar().setDisable(true);
                this.getButtonDeletar().setDisable(true);
                this.getButtonLimpar().setDisable(true);
                this.getUsuarios();
            }
        } else {
            Alerts.showAlert("Controleasy", null, "SENHAS DIVERGENTES", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void buttonDeletar(ActionEvent event) {
        try {
            if (!this.getTextFieldId().getText().isEmpty()) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("DELETAR USUÁRIO");
                alert.setContentText("DESEJA DELETAR O USUÁRIO SELECIONADO?");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Usuarios usuarios = new Usuarios();
                        usuarios.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        if (new UsuariosDAO().deletarUsuarios(usuarios)) {
                            Alerts.showAlert("Controleasy", null, "USUÁRIO DELETADO COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getTextFieldNome().setText("");
                            this.getTextFieldUsuario().setText("");
                            this.getPasswordSenha().setText("");
                            this.getPasswordConfirmarSenha().setText("");
                            this.getTextFieldSaldoInicial().setText("");
                            this.getAcessoPadrao().setSelected(true);
                            this.getTextFieldSaldoInicial().setText("0,00");
                            this.getButtonCadastrar().setDisable(false);
                            this.getButtonAtualizar().setDisable(true);
                            this.getButtonDeletar().setDisable(true);
                            this.getButtonLimpar().setDisable(true);
                            this.getUsuarios();
                        }
                    } else if (result == nao) {
                        alert.close();
                    }
                });
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void buttonAtualizar(ActionEvent event) {
        try {
            if (!this.getTextFieldId().getText().isEmpty()) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("ATUALIZAR USUÁRIO");
                alert.setContentText("DESEJA ATUALIZAR O USUÁRIO SELECIONADO?");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Usuarios usuario = new Usuarios();
                        usuario.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        usuario.setNome(this.getTextFieldNome().getText());
                        usuario.setUsuario(this.getTextFieldUsuario().getText());
                        if (this.getAcessoAdmin().isSelected()) {
                            usuario.setAcesso("ADMIN");
                        } else if (this.getAcessoPadrao().isSelected()) {
                            usuario.setAcesso("PADRÃO");
                        }
                        if (this.getTextFieldSaldoInicial().getText().isEmpty()) {
                            usuario.setSaldo(new BigDecimal(0));
                        } else {
                            usuario.setSaldo(new BigDecimal(this.getTextFieldSaldoInicial().getText().replace(",", ".")));
                        }
                        if (new UsuariosDAO().atualizarUsuarios(usuario)) {
                            Alerts.showAlert("Controleasy", null, "USUÁRIO ATUALIZADO COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getTextFieldNome().setText("");
                            this.getTextFieldUsuario().setText("");
                            this.getPasswordSenha().setText("");
                            this.getPasswordConfirmarSenha().setText("");
                            this.getTextFieldSaldoInicial().setText("");
                            this.getAcessoPadrao().setSelected(true);
                            this.getTextFieldSaldoInicial().setText("0,00");
                            this.getButtonCadastrar().setDisable(false);
                            this.getButtonAtualizar().setDisable(true);
                            this.getButtonDeletar().setDisable(true);
                            this.getButtonLimpar().setDisable(true);
                            this.getUsuarios();
                        }
                    } else if (result == nao) {
                        alert.close();
                    }
                });
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", "VALOR INVÁLIDO!", "PREENCHA SOMENTE NÚMEROS NO CAMPO SALDO", Alert.AlertType.ERROR);
        }
    }
}
