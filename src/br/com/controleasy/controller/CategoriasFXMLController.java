package br.com.controleasy.controller;

import br.com.controleasy.dao.CategoriasDAO;
import br.com.controleasy.model.Categorias;
import br.com.controleasy.model.Usuarios;
import br.com.controleasy.util.Alerts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CategoriasFXMLController implements Initializable {

    @FXML
    private TableView<Categorias> tableViewCategorias;

    @FXML
    private TableColumn<Categorias, Integer> tableColumnId;

    @FXML
    private TableColumn<Categorias, String> tableColumnCategorias;

    @FXML
    private TextField textFieldCategoria;

    @FXML
    private Button buttonCadastrarCategoria;

    @FXML
    private Button buttonAtualizarCategoria;

    @FXML
    private Button buttonDeletarCategoria;

    @FXML
    private TextField textFieldId;

    @FXML
    private Button buttonLimpar;

    public Button getButtonLimpar() {
        return buttonLimpar;
    }

    public void setButtonLimpar(Button buttonLimpar) {
        this.buttonLimpar = buttonLimpar;
    }

    public Button getButtonAtualizarCategoria() {
        return buttonAtualizarCategoria;
    }

    public void setButtonAtualizarCategoria(Button buttonAtualizarCategoria) {
        this.buttonAtualizarCategoria = buttonAtualizarCategoria;
    }

    public Button getButtonDeletarCategoria() {
        return buttonDeletarCategoria;
    }

    public void setButtonDeletarCategoria(Button buttonDeletarCategoria) {
        this.buttonDeletarCategoria = buttonDeletarCategoria;
    }

    public TextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(TextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public TableView<Categorias> getTableViewCategorias() {
        return tableViewCategorias;
    }

    public void setTableViewCategorias(TableView<Categorias> tableViewCategorias) {
        this.tableViewCategorias = tableViewCategorias;
    }

    public TableColumn<Categorias, Integer> getTableColumnId() {
        return tableColumnId;
    }

    public void setTableColumnId(TableColumn<Categorias, Integer> tableColumnId) {
        this.tableColumnId = tableColumnId;
    }

    public TableColumn<Categorias, String> getTableColumnCategorias() {
        return tableColumnCategorias;
    }

    public void setTableColumnCategorias(TableColumn<Categorias, String> tableColumnCategorias) {
        this.tableColumnCategorias = tableColumnCategorias;
    }

    public TextField getTextFieldCategoria() {
        return textFieldCategoria;
    }

    public void setTextFieldCategoria(TextField textFieldCategoria) {
        this.textFieldCategoria = textFieldCategoria;
    }

    public Button getButtonCadastrarCategoria() {
        return buttonCadastrarCategoria;
    }

    public void setButtonCadastrarCategoria(Button buttonCadastrarCategoria) {
        this.buttonCadastrarCategoria = buttonCadastrarCategoria;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getCategorias();
    }

    public void getCategorias() {
        try {
            this.getButtonCadastrarCategoria().setDisable(false);
            this.getButtonAtualizarCategoria().setDisable(true);
            this.getButtonDeletarCategoria().setDisable(true);
            this.getButtonLimpar().setDisable(true);
            this.getTableColumnId().setCellValueFactory(new PropertyValueFactory("id"));
            this.getTableColumnCategorias().setCellValueFactory(new PropertyValueFactory("categoria"));
            this.getTableViewCategorias().setItems(FXCollections.observableArrayList(new CategoriasDAO().getAllCategorias()));
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cadastrarCategoria(ActionEvent event) {
        try {
            if (this.getTextFieldCategoria().getText().equals("")) {
                Alerts.showAlert("Controleasy", "CAMPO OBRIGATÓRIO", "PREENCHA O CAMPO DE CATEGORIA", Alert.AlertType.ERROR);
            } else {
                if (new CategoriasDAO().cadastrarCategoria(new Categorias(this.textFieldCategoria.getText().toUpperCase(), new Usuarios(Integer.parseInt(MainScreenFXMLController.getId()))))) {
                    Alerts.showAlert("Controleasy", null, "CATEGORIA CADASTRADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                    this.getCategorias();
                    this.getButtonCadastrarCategoria().setDisable(false);
                    this.getButtonAtualizarCategoria().setDisable(true);
                    this.getButtonDeletarCategoria().setDisable(true);
                    this.getButtonLimpar().setDisable(true);
                    this.getTextFieldCategoria().setText("");
                }
            }
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void atualizarCategoria(ActionEvent event) {
        try {
            if (!this.getTextFieldCategoria().getText().equals("")) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("ATUALIZAR CATEGORIA");
                alert.setContentText("DESEJA ATUALIZAR A CATEGORIA SELECIONADA?");
                alert.getButtonTypes().setAll(sim, nao);

                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Categorias categorias = new Categorias();
                        CategoriasDAO categoriasDAO = new CategoriasDAO();
                        categorias.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        categorias.setCategoria(this.getTextFieldCategoria().getText().toUpperCase());
                        categorias.setUsuariosId(new Usuarios(Integer.parseInt(MainScreenFXMLController.getId())));
                        if (categoriasDAO.atualizarCategoria(categorias)) {
                            Alerts.showAlert("Controleasy", null, "CATEGORIA ATUALIZADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getCategorias();
                            this.getTextFieldId().setText("");
                            this.getTextFieldCategoria().setText("");
                            this.getButtonCadastrarCategoria().setDisable(false);
                            this.getButtonAtualizarCategoria().setDisable(true);
                            this.getButtonDeletarCategoria().setDisable(true);
                            this.getButtonLimpar().setDisable(true);
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
    private void deletarCategoria(ActionEvent event) {
        try {
            if (!this.getTextFieldCategoria().getText().equals("")) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("DELETAR CATEGORIA");
                alert.setContentText("DESEJA DELETAR A CATEGORIA SELECIONADA?");
                alert.getButtonTypes().setAll(sim, nao);

                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Categorias categorias = new Categorias();
                        CategoriasDAO categoriasDAO = new CategoriasDAO();
                        categorias.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        if (categoriasDAO.deletarCategoria(categorias)) {
                            Alerts.showAlert("Controleasy", null, "CATEGORIA DELETADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getCategorias();
                            this.getTextFieldId().setText("");
                            this.getTextFieldCategoria().setText("");
                            this.getButtonCadastrarCategoria().setDisable(false);
                            this.getButtonAtualizarCategoria().setDisable(true);
                            this.getButtonDeletarCategoria().setDisable(true);
                            this.getButtonLimpar().setDisable(true);
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
    private void setCategoriaFormulario(MouseEvent event) {
        try {
            Categorias categoria = this.getTableViewCategorias().getItems().get(this.getTableViewCategorias().getSelectionModel().getSelectedIndex());
            this.getTextFieldId().setText(Integer.toString(categoria.getId()));
            this.getTextFieldCategoria().setText(categoria.getCategoria());
            this.getButtonCadastrarCategoria().setDisable(true);
            this.getButtonAtualizarCategoria().setDisable(false);
            this.getButtonDeletarCategoria().setDisable(false);
            this.getButtonLimpar().setDisable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void limparFormularioCategorias(ActionEvent event) {
        try {
            this.getTextFieldId().setText("");
            this.getTextFieldCategoria().setText("");
            this.getButtonCadastrarCategoria().setDisable(false);
            this.getButtonAtualizarCategoria().setDisable(true);
            this.getButtonDeletarCategoria().setDisable(true);
            this.getButtonLimpar().setDisable(true);
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
