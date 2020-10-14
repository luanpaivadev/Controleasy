/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.controller;

import br.com.controleasy.dao.CategoriasDAO;
import br.com.controleasy.dao.DespesasDAO;
import br.com.controleasy.model.Categorias;
import br.com.controleasy.model.Despesas;
import br.com.controleasy.model.Usuarios;
import br.com.controleasy.util.Alerts;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanp
 */
public class DespesasFXMLController implements Initializable {

    private ResourceBundle resources;

    private URL location;

    @FXML
    private Button buttonArquivarDespesa;

    @FXML
    private Button buttonLimpar;

    @FXML
    private Button buttonCadastrarDespesa;

    @FXML
    private ComboBox<Categorias> categoriaDespesa;

    @FXML
    private DatePicker vencimentoDespesa;

    @FXML
    private CheckBox checkBoxLiquidado;

    @FXML
    private TableView<Despesas> tableViewDespesas;

    @FXML
    private TableColumn<Despesas, Integer> columnId;

    @FXML
    private TableColumn<Despesas, String> columnDescricao;

    @FXML
    private TableColumn<Despesas, String> columnCategoria;

    @FXML
    private TableColumn<Despesas, BigDecimal> columnValor;

    @FXML
    private TableColumn<Despesas, Date> columnVencimento;

    @FXML
    private TableColumn<Despesas, Date> columnPagamento;

    @FXML
    private TableColumn<Despesas, String> columnSituacao;

    @FXML
    private Button buttonAtualizarDespesa;

    @FXML
    private RadioButton radioButtonPagar;

    @FXML
    private RadioButton radioButtonPago;

    @FXML
    private ToggleGroup situacao;

    @FXML
    private Button buttonDeletarDespesa;

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldDescricao;

    @FXML
    private TextField textFieldValor;

    @FXML
    private Label lnlTotal;
    @FXML
    private DatePicker filtroVencimento;

    @FXML
    private Button buttonFiltrar;

    public Label getLnlTotal() {
        return lnlTotal;
    }

    public void setLnlTotal(Label lnlTotal) {
        this.lnlTotal = lnlTotal;
    }

    public ToggleGroup getSituacao() {
        return situacao;
    }

    public void setSituacao(ToggleGroup situacao) {
        this.situacao = situacao;
    }

    public TextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(TextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public TextField getTextFieldDescricao() {
        return textFieldDescricao;
    }

    public void setTextFieldDescricao(TextField textFieldDescricao) {
        this.textFieldDescricao = textFieldDescricao;
    }

    public TextField getTextFieldValor() {
        return textFieldValor;
    }

    public void setTextFieldValor(TextField textFieldValor) {
        this.textFieldValor = textFieldValor;
    }

    public Button getButtonDeletarDespesa() {
        return buttonDeletarDespesa;
    }

    public void setButtonDeletarDespesa(Button buttonDeletarDespesa) {
        this.buttonDeletarDespesa = buttonDeletarDespesa;
    }

    public Button getButtonArquivarDespesa() {
        return buttonArquivarDespesa;
    }

    public void setButtonArquivarDespesa(Button buttonArquivarDespesa) {
        this.buttonArquivarDespesa = buttonArquivarDespesa;
    }

    public Button getButtonAtualizarDespesa() {
        return buttonAtualizarDespesa;
    }

    public void setButtonAtualizarDespesa(Button buttonAtualizarDespesa) {
        this.buttonAtualizarDespesa = buttonAtualizarDespesa;
    }

    public RadioButton getRadioButtonPagar() {
        return radioButtonPagar;
    }

    public void setRadioButtonPagar(RadioButton radioButtonPagar) {
        this.radioButtonPagar = radioButtonPagar;
    }

    public RadioButton getRadioButtonPago() {
        return radioButtonPago;
    }

    public void setRadioButtonPago(RadioButton radioButtonPago) {
        this.radioButtonPago = radioButtonPago;
    }

    public TableColumn<Despesas, Integer> getColumnId() {
        return columnId;
    }

    public void setColumnId(TableColumn<Despesas, Integer> columnId) {
        this.columnId = columnId;
    }

    public TableColumn<Despesas, String> getColumnDescricao() {
        return columnDescricao;
    }

    public void setColumnDescricao(TableColumn<Despesas, String> columnDescricao) {
        this.columnDescricao = columnDescricao;
    }

    public TableColumn<Despesas, String> getColumnCategoria() {
        return columnCategoria;
    }

    public void setColumnCategoria(TableColumn<Despesas, String> columnCategoria) {
        this.columnCategoria = columnCategoria;
    }

    public TableColumn<Despesas, BigDecimal> getColumnValor() {
        return columnValor;
    }

    public void setColumnValor(TableColumn<Despesas, BigDecimal> columnValor) {
        this.columnValor = columnValor;
    }

    public TableColumn<Despesas, Date> getColumnVencimento() {
        return columnVencimento;
    }

    public void setColumnVencimento(TableColumn<Despesas, Date> columnVencimento) {
        this.columnVencimento = columnVencimento;
    }

    public TableColumn<Despesas, Date> getColumnPagamento() {
        return columnPagamento;
    }

    public void setColumnPagamento(TableColumn<Despesas, Date> columnPagamento) {
        this.columnPagamento = columnPagamento;
    }

    public TableColumn<Despesas, String> getColumnSituacao() {
        return columnSituacao;
    }

    public void setColumnSituacao(TableColumn<Despesas, String> columnSituacao) {
        this.columnSituacao = columnSituacao;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public Button getButtonLimpar() {
        return buttonLimpar;
    }

    public void setButtonLimpar(Button buttonLimpar) {
        this.buttonLimpar = buttonLimpar;
    }

    public Button getButtonCadastrarDespesa() {
        return buttonCadastrarDespesa;
    }

    public void setButtonCadastrarDespesa(Button buttonCadastrarDespesa) {
        this.buttonCadastrarDespesa = buttonCadastrarDespesa;
    }

    public ComboBox<Categorias> getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(ComboBox<Categorias> categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public DatePicker getVencimentoDespesa() {
        return vencimentoDespesa;
    }

    public void setVencimentoDespesa(DatePicker vencimentoDespesa) {
        this.vencimentoDespesa = vencimentoDespesa;
    }

    public CheckBox getCheckBoxLiquidado() {
        return checkBoxLiquidado;
    }

    public void setCheckBoxLiquidado(CheckBox checkBoxLiquidado) {
        this.checkBoxLiquidado = checkBoxLiquidado;
    }

    public TableView<Despesas> getTableViewDespesas() {
        return tableViewDespesas;
    }

    public void setTableViewDespesas(TableView<Despesas> tableViewDespesas) {
        this.tableViewDespesas = tableViewDespesas;
    }

    public DatePicker getFiltroVencimento() {
        return filtroVencimento;
    }

    public void setFiltroVencimento(DatePicker filtroVencimento) {
        this.filtroVencimento = filtroVencimento;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.getColumnId().setCellValueFactory(new PropertyValueFactory("id"));
        this.getColumnDescricao().setCellValueFactory(new PropertyValueFactory("descricao"));
        this.getColumnCategoria().setCellValueFactory(new PropertyValueFactory("categoria"));
        this.getColumnValor().setCellValueFactory(new PropertyValueFactory("valor"));
        this.getColumnValor().setCellFactory(column -> {
            return new TableCell<Despesas, BigDecimal>() {
                DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.getDefault());

                {
                    df.applyPattern("R$ #,##0.00");
                }

                @Override
                public void updateItem(final BigDecimal valor, boolean empty) {
                    super.updateItem(valor, empty);
                    if (valor != null) {
                        String n = df.format(valor);
                        setText(n);
                        return;
                    }
                    setText("");
                    setGraphic(null);
                }
            };
        });

        this.getColumnVencimento().setCellValueFactory(new PropertyValueFactory("vencimento"));
        this.getColumnPagamento().setCellValueFactory(new PropertyValueFactory("pagamento"));
        this.getColumnSituacao().setCellValueFactory(new PropertyValueFactory("situacao"));

        this.getVencimentoDespesa().setValue(LocalDate.now());
        this.getFiltroVencimento().setValue(LocalDate.now());

        this.getButtonCadastrarDespesa().setDisable(false);
        this.getButtonAtualizarDespesa().setDisable(true);
        this.getButtonDeletarDespesa().setDisable(true);
        this.getButtonArquivarDespesa().setDisable(true);
        this.getButtonLimpar().setDisable(true);

        this.getAllCategorias();
        this.getDespesasAPagar();

        this.getLnlTotal().setText(this.getValorTotalDespesas());

    }

    public void buscarDespesasVencidas() {
        try {
            List<Despesas> list = new DespesasDAO().getDespesasVencidas();
            if (!list.isEmpty()) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("ATENÇÃO!");
                alert.setContentText("EXISTEM DESPESAS VENCIDAS. DESEJA EFETUAR O PAGAMENTO AGORA?");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/DespesasFXML.fxml"));
                            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
                            Stage stageDespesas = new Stage();
                            stageDespesas.getIcons().add(image);
                            stageDespesas.setScene(new Scene(parent));
                            stageDespesas.setTitle("DESPESAS");
                            stageDespesas.setResizable(false);
                            stageDespesas.initModality(Modality.APPLICATION_MODAL);
                            stageDespesas.show();
                        } catch (IOException ex) {
                            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
                        }
                    } else if (result == nao) {
                        alert.close();
                    }
                });
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    public String getValorTotalDespesas() {
        if (new DespesasDAO().getValorTotal() != null) {
            return new DecimalFormat("###,##0.00").format(new DespesasDAO().getValorTotal());
        } else {
            return "0,00";
        }

    }

    public void getAllDespesas() {
        //this.getTableViewDespesas().setItems(FXCollections.observableArrayList(new DespesasDAO().getAllDespesas()));
    }

    public void getDespesasAPagar() {
        this.getTableViewDespesas().setItems(FXCollections.observableArrayList(new DespesasDAO().getDespesasAPagar()));
        this.getRadioButtonPagar().setSelected(true);
        this.getRadioButtonPago().setSelected(false);
    }

    public void getDespesasPagas() {
        this.getTableViewDespesas().setItems(FXCollections.observableArrayList(new DespesasDAO().getDespesasPagas()));
        this.getRadioButtonPago().setSelected(true);
        this.getRadioButtonPagar().setSelected(false);
    }

    public void getAllCategorias() {
        try {
            List<Categorias> resultList = new CategoriasDAO().getAllCategorias();
            List<Categorias> listCategorias = new ArrayList<>();
            resultList.forEach((c) -> {
                listCategorias.add(new Categorias(c.getCategoria().toUpperCase()));
            });
            ObservableList observableList = FXCollections.observableList(listCategorias);
            this.getCategoriaDespesa().setItems(observableList);
            this.getCategoriaDespesa().getSelectionModel().select(0);
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void filtrarVencimento() {
        try {
            if (this.getFiltroVencimento().getValue() != null) {
                String situacaoDespesa = null;
                Date vencimento = Date.from(this.getFiltroVencimento().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (this.getRadioButtonPagar().isSelected()) {
                    situacaoDespesa = "A PAGAR";
                }
                if (this.getRadioButtonPago().isSelected()) {
                    situacaoDespesa = "PAGO";
                }
                this.getTableViewDespesas().setItems(FXCollections.observableArrayList(new DespesasDAO().getDespesasPorVencimento(vencimento, situacaoDespesa)));
            } else {
                Alerts.showAlert("Controleasy", null, "PREENCHA O CAMPO DE VENCIMENTO", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void buttonCadastrarDespesa(ActionEvent event) {

        try {
            if (this.getTextFieldDescricao().getText().equals("") && this.getTextFieldValor().getText().equals("") && this.getVencimentoDespesa().getValue() == null) {
                Alerts.showAlert("Controleasy", "(*) CAMPOS OBRIGATÓRIOS", "PREENCHA OS CAMPOS DE DESCRIÇÃO, VALOR E VENCIMENTO", Alert.AlertType.ERROR);
            } else if (this.getTextFieldDescricao().getText().equals("")) {
                Alerts.showAlert("Controleasy", "(*) CAMPO OBRIGATÓRIO", "PREENCHA O CAMPO DE DESCRIÇÃO", Alert.AlertType.ERROR);
            } else if (this.getTextFieldValor().getText().equals("")) {
                Alerts.showAlert("Controleasy", "(*) CAMPO OBRIGATÓRIO", "PREENCHA O CAMPO DE VALOR", Alert.AlertType.ERROR);
            } else if (this.getVencimentoDespesa().getValue() == null) {
                Alerts.showAlert("Controleasy", "(*) CAMPO OBRIGATÓRIO", "PREENCHA O CAMPO DE VENCIMENTO", Alert.AlertType.ERROR);
            } else {
                Despesas despesa = new Despesas();
                despesa.setDescricao(this.getTextFieldDescricao().getText().toUpperCase());
                try {
                    despesa.setCategoria(this.getCategoriaDespesa().getSelectionModel().getSelectedItem().toString());
                } catch (Exception e) {
                    Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
                }
                despesa.setValor(new BigDecimal(this.getTextFieldValor().getText().replace(",", ".")));
                LocalDate localDate = this.getVencimentoDespesa().getValue();
                Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                despesa.setVencimento(date);

                if (this.getCheckBoxLiquidado().isSelected()) {
                    despesa.setSituacao("PAGO");
                    despesa.setPagamento(new Date());
                } else {
                    despesa.setSituacao("A PAGAR");
                }
                despesa.setUsuariosId(new Usuarios(Integer.parseInt(MainScreenFXMLController.getId())));

                if (new DespesasDAO().cadastrarDespesa(despesa)) {
                    Alerts.showAlert("Controleasy", null, "DESPESA CADASTRADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                    this.getDespesasAPagar();
                    this.getLnlTotal().setText(this.getValorTotalDespesas());
                    this.getTextFieldId().setText("");
                    this.getTextFieldDescricao().setText("");
                    this.getCategoriaDespesa().getSelectionModel().select(0);
                    this.getTextFieldValor().setText("");
                    this.getVencimentoDespesa().setValue(LocalDate.now());
                    this.getCheckBoxLiquidado().setSelected(false);
                    this.getButtonCadastrarDespesa().setDisable(false);
                    this.getButtonAtualizarDespesa().setDisable(true);
                    this.getButtonDeletarDespesa().setDisable(true);
                    this.getButtonArquivarDespesa().setDisable(true);
                    this.getButtonLimpar().setDisable(true);
                }
            }
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", "VALOR INVÁLIDO!", "PREENCHA SOMENTE NÚMEROS NO CAMPO VALOR", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void buttonAtualizarDespesa(ActionEvent event) {
        try {
            if (!this.getTextFieldId().getText().equals("")) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("ATUALIZAR DESPESA");
                alert.setContentText("DESEJA ATUALIZAR A DESPESA SELECIONADA?");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Despesas despesa = new Despesas();
                        despesa.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        despesa.setDescricao(this.getTextFieldDescricao().getText());
                        despesa.setCategoria(this.getCategoriaDespesa().getSelectionModel().getSelectedItem().toString());
                        despesa.setValor(new BigDecimal(this.getTextFieldValor().getText().replace(",", ".")));
                        LocalDate localDate = this.getVencimentoDespesa().getValue();
                        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        despesa.setVencimento(date);
                        if (this.getCheckBoxLiquidado().isSelected()) {
                            despesa.setSituacao("PAGO");
                            despesa.setPagamento(new Date());
                        } else {
                            despesa.setSituacao("A PAGAR");
                        }
                        despesa.setUsuariosId(new Usuarios(Integer.parseInt(MainScreenFXMLController.getId())));
                        if (new DespesasDAO().atualizarDespesa(despesa)) {
                            Alerts.showAlert("Controleasy", null, "DESPESA ATUALIZADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getDespesasAPagar();
                            this.getLnlTotal().setText(this.getValorTotalDespesas());
                            this.getTextFieldId().setText("");
                            this.getTextFieldDescricao().setText("");
                            this.getCategoriaDespesa().getSelectionModel().select(0);
                            this.getTextFieldValor().setText("");
                            this.getVencimentoDespesa().setValue(LocalDate.now());
                            this.getCheckBoxLiquidado().setSelected(false);
                            this.getButtonCadastrarDespesa().setDisable(false);
                            this.getButtonAtualizarDespesa().setDisable(true);
                            this.getButtonDeletarDespesa().setDisable(true);
                            this.getButtonArquivarDespesa().setDisable(true);
                            this.getButtonLimpar().setDisable(true);
                        }
                    } else if (result == nao) {
                        alert.close();
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alerts.showAlert("Controleasy", "VALOR INVÁLIDO!", "PREENCHA SOMENTE NÚMEROS NO CAMPO VALOR", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void buttonDeletarDespesa(ActionEvent event) {
        try {
            if (!this.getTextFieldId().getText().equals("")) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("DELETAR DESPESA");
                alert.setContentText("DESEJA DELETAR A DESPESA SELECIONADA?");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Despesas despesa = new Despesas();
                        despesa.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        if (new DespesasDAO().deletarDespesa(despesa)) {
                            Alerts.showAlert("Controleasy", null, "DESPESA DELETADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getDespesasAPagar();
                            this.getLnlTotal().setText(this.getValorTotalDespesas());
                            this.getTextFieldId().setText("");
                            this.getTextFieldDescricao().setText("");
                            this.getCategoriaDespesa().getSelectionModel().select(0);
                            this.getTextFieldValor().setText("");
                            this.getVencimentoDespesa().setValue(LocalDate.now());
                            this.getCheckBoxLiquidado().setSelected(false);
                            this.getButtonCadastrarDespesa().setDisable(false);
                            this.getButtonAtualizarDespesa().setDisable(true);
                            this.getButtonDeletarDespesa().setDisable(true);
                            this.getButtonArquivarDespesa().setDisable(true);
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
    void buttonArquivarDespesa(ActionEvent event) {
        try {
            if (!this.getTextFieldId().getText().equals("")) {
                ButtonType sim = new ButtonType("SIM");
                ButtonType nao = new ButtonType("NÃO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Controleasy");
                alert.setHeaderText("ARQUIVAR DESPESA");
                alert.setContentText("DESEJA ARQUIVAR A DESPESA SELECIONADA?");
                alert.getButtonTypes().setAll(sim, nao);
                alert.showAndWait().ifPresent(result -> {
                    if (result == sim) {
                        Despesas despesa = new Despesas();
                        despesa.setId(Integer.parseInt(this.getTextFieldId().getText()));
                        if (new DespesasDAO().arquivarDespesa(despesa)) {
                            Alerts.showAlert("Controleasy", null, "DESPESA ARQUIVADA COM SUCESSO!", Alert.AlertType.INFORMATION);
                            this.getDespesasAPagar();
                            this.getLnlTotal().setText(this.getValorTotalDespesas());
                            this.getTextFieldId().setText("");
                            this.getTextFieldDescricao().setText("");
                            this.getCategoriaDespesa().getSelectionModel().select(0);
                            this.getTextFieldValor().setText("");
                            this.getVencimentoDespesa().setValue(LocalDate.now());
                            this.getCheckBoxLiquidado().setSelected(false);
                            this.getButtonCadastrarDespesa().setDisable(false);
                            this.getButtonAtualizarDespesa().setDisable(true);
                            this.getButtonDeletarDespesa().setDisable(true);
                            this.getButtonArquivarDespesa().setDisable(true);
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
    void setDespesaFormulario(MouseEvent event) {
        try {
            Despesas despesa = this.getTableViewDespesas().getItems().get(this.getTableViewDespesas().getSelectionModel().getSelectedIndex());
            this.getTextFieldId().setText(Integer.toString(despesa.getId()));
            this.getTextFieldDescricao().setText(despesa.getDescricao());
            this.getCategoriaDespesa().getSelectionModel().select(new Categorias(despesa.getCategoria()));

            this.getTextFieldValor().setText(String.format("%.2f", despesa.getValor()));

            Instant instant = Instant.ofEpochMilli(despesa.getVencimento().getTime());
            LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
            this.getVencimentoDespesa().setValue(localDate);
            switch (despesa.getSituacao()) {
                case "PAGO":
                    this.getCheckBoxLiquidado().setSelected(true);
                    break;
                case "A PAGAR":
                    this.getCheckBoxLiquidado().setSelected(false);
                    break;
                default:
                    break;
            }
            this.getButtonCadastrarDespesa().setDisable(true);
            this.getButtonAtualizarDespesa().setDisable(false);
            this.getButtonDeletarDespesa().setDisable(false);
            this.getButtonArquivarDespesa().setDisable(false);
            this.getButtonLimpar().setDisable(false);
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    void limparCampos(ActionEvent event) {
        try {
            this.getTextFieldId().setText("");
            this.getTextFieldDescricao().setText("");
            this.getCategoriaDespesa().getSelectionModel().select(0);
            this.getTextFieldValor().setText("");
            this.getVencimentoDespesa().setValue(LocalDate.now());
            this.getCheckBoxLiquidado().setSelected(false);
            this.getButtonCadastrarDespesa().setDisable(false);
            this.getButtonAtualizarDespesa().setDisable(true);
            this.getButtonDeletarDespesa().setDisable(true);
            this.getButtonArquivarDespesa().setDisable(true);
            this.getButtonLimpar().setDisable(true);
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void radioButtonPagar(ActionEvent event) {
        this.getDespesasAPagar();
    }

    @FXML
    void radioButtonPago(ActionEvent event) {
        this.getDespesasPagas();
    }

    private void filtrarVencimento(ActionEvent event) {
        this.filtrarVencimento();
    }
}
