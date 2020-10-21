/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.controller;

import br.com.controleasy.dao.DespesasDAO;
import br.com.controleasy.model.Despesas;
import br.com.controleasy.util.Alerts;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luanp
 */
public class MainScreenFXMLController implements Initializable {

    private static String id;
    private static String usuario;
    private static Date dateInicial;
    private static Date dateFinal;
    private String acesso;

    public static Date getDateInicial() {
        return dateInicial;
    }

    public static void setDateInicial(Date dateInicial) {
        MainScreenFXMLController.dateInicial = dateInicial;
    }

    public static Date getDateFinal() {
        return dateFinal;
    }

    public static void setDateFinal(Date dateFinal) {
        MainScreenFXMLController.dateFinal = dateFinal;
    }

    private static Stage stage = new Stage();

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        MainScreenFXMLController.id = id;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        MainScreenFXMLController.usuario = usuario;
    }

    @FXML
    private MenuButton menuRelatorios;

    @FXML
    private MenuItem menuItemPeriodo;

    @FXML
    private MenuButton menuCadastro;

    @FXML
    private MenuItem menuItemCategorias;

    @FXML
    private MenuItem menuItemDespesas;

    @FXML
    private MenuItem menuItemReceitas;

    @FXML
    private MenuItem menuItemUsuarios;

    @FXML
    private MenuButton menuSaldo;

    @FXML
    private MenuItem menuItemSaldoAtual;

    @FXML
    private MenuButton menuSobre;

    @FXML
    private MenuItem menuItemVersao;

    @FXML
    private MenuButton menuOpcoes;

    @FXML
    private MenuItem menuItemSair;

    @FXML
    private Label lblId;

    @FXML
    private Label lblUsuario;

    @FXML
    private PieChart pieChartDespesas;

    @FXML
    private MenuItem menuItemAtualizar;

    @FXML
    private Label labelDespesasAPagar;

    @FXML
    private MenuItem menuItemAlterarUsuario;

    @FXML
    private MenuItem menuItemDespesasPorCategoria;

    @FXML
    private DatePicker datePickerDataInicial;

    @FXML
    private DatePicker datePickerDataFinal;

    @FXML
    private Button buttonPesquisar;
    
    @FXML
    private Label labelDespesasTotais;

    public Label getLabelDespesasTotais() {
        return labelDespesasTotais;
    }

    public void setLabelDespesasTotais(Label labelDespesasTotais) {
        this.labelDespesasTotais = labelDespesasTotais;
    }
    
    public DatePicker getDatePickerDataInicial() {
        return datePickerDataInicial;
    }

    public void setDatePickerDataInicial(DatePicker datePickerDataInicial) {
        this.datePickerDataInicial = datePickerDataInicial;
    }

    public DatePicker getDatePickerDataFinal() {
        return datePickerDataFinal;
    }

    public void setDatePickerDataFinal(DatePicker datePickerDataFinal) {
        this.datePickerDataFinal = datePickerDataFinal;
    }

    public MenuItem getMenuItemAlterarUsuario() {
        return menuItemAlterarUsuario;
    }

    public void setMenuItemAlterarUsuario(MenuItem menuItemAlterarUsuario) {
        this.menuItemAlterarUsuario = menuItemAlterarUsuario;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MainScreenFXMLController.stage = stage;
    }

    public MenuButton getMenuCadastro() {
        return menuCadastro;
    }

    public void setMenuCadastro(MenuButton menuCadastro) {
        this.menuCadastro = menuCadastro;
    }

    public MenuItem getMenuItemCategorias() {
        return menuItemCategorias;
    }

    public void setMenuItemCategorias(MenuItem menuItemCategorias) {
        this.menuItemCategorias = menuItemCategorias;
    }

    public MenuItem getMenuItemDespesas() {
        return menuItemDespesas;
    }

    public void setMenuItemDespesas(MenuItem menuItemDespesas) {
        this.menuItemDespesas = menuItemDespesas;
    }

    public MenuItem getMenuItemReceitas() {
        return menuItemReceitas;
    }

    public void setMenuItemReceitas(MenuItem menuItemReceitas) {
        this.menuItemReceitas = menuItemReceitas;
    }

    public MenuItem getMenuItemUsuarios() {
        return menuItemUsuarios;
    }

    public void setMenuItemUsuarios(MenuItem menuItemUsuarios) {
        this.menuItemUsuarios = menuItemUsuarios;
    }

    public MenuButton getMenuSaldo() {
        return menuSaldo;
    }

    public void setMenuSaldo(MenuButton menuSaldo) {
        this.menuSaldo = menuSaldo;
    }

    public MenuItem getMenuItemSaldoAtual() {
        return menuItemSaldoAtual;
    }

    public void setMenuItemSaldoAtual(MenuItem menuItemSaldoAtual) {
        this.menuItemSaldoAtual = menuItemSaldoAtual;
    }

    public MenuButton getMenuSobre() {
        return menuSobre;
    }

    public void setMenuSobre(MenuButton menuSobre) {
        this.menuSobre = menuSobre;
    }

    public MenuItem getMenuItemVersao() {
        return menuItemVersao;
    }

    public void setMenuItemVersao(MenuItem menuItemVersao) {
        this.menuItemVersao = menuItemVersao;
    }

    public MenuButton getMenuOpcoes() {
        return menuOpcoes;
    }

    public void setMenuOpcoes(MenuButton menuOpcoes) {
        this.menuOpcoes = menuOpcoes;
    }

    public MenuItem getMenuItemSair() {
        return menuItemSair;
    }

    public void setMenuItemSair(MenuItem menuItemSair) {
        this.menuItemSair = menuItemSair;
    }

    public MenuItem getMenuItemAtualizar() {
        return menuItemAtualizar;
    }

    public void setMenuItemAtualizar(MenuItem menuItemAtualizar) {
        this.menuItemAtualizar = menuItemAtualizar;
    }

    public Label getLabelDespesasAPagar() {
        return labelDespesasAPagar;
    }

    public void setLabelDespesasAPagar(Label labelDespesasAPagar) {
        this.labelDespesasAPagar = labelDespesasAPagar;
    }

    public PieChart getPieChartDespesas() {
        return pieChartDespesas;
    }

    public void setPieChartDespesas(PieChart pieChartDespesas) {
        this.pieChartDespesas = pieChartDespesas;
    }

    public Label getLblId() {
        return lblId;
    }

    public void setLblId(Label lblId) {
        this.lblId = lblId;
    }

    public Label getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(Label lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public MenuItem getMenuItemPeriodo() {
        return menuItemPeriodo;
    }

    public void setMenuItemPeriodo(MenuItem menuItemPeriodo) {
        this.menuItemPeriodo = menuItemPeriodo;
    }

    public MenuItem getMenuItemDespesasPorCategoria() {
        return menuItemDespesasPorCategoria;
    }

    public void setMenuItemDespesasPorCategoria(MenuItem menuItemDespesasPorCategoria) {
        this.menuItemDespesasPorCategoria = menuItemDespesasPorCategoria;
    }

    public void listCategoriaPieChart() {
        try {
            DespesasDAO despesasDAO = new DespesasDAO();
            List<Despesas> listDespesa = despesasDAO.getGroupCategorias();

            ObservableList<PieChart.Data> listPieChart = FXCollections.observableArrayList();

            listDespesa.forEach((listDespesaPieChart) -> {
                listPieChart.add(new PieChart.Data(listDespesaPieChart.getCategoria(), listDespesaPieChart.getValor().doubleValue()));
            });

            this.getPieChartDespesas().setData(listPieChart);
            this.getPieChartDespesas().setStartAngle(90);
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void getTotalDespesasAPagar() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        try {
            BigDecimal despesasAPagar = new DespesasDAO().getTotalDespesasAPagar(MainScreenFXMLController.getDateInicial(), MainScreenFXMLController.getDateFinal());
            if (despesasAPagar == null) {
                this.getLabelDespesasAPagar().setText(df.format(0.00));
            } else {
                this.getLabelDespesasAPagar().setText(df.format(despesasAPagar));
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void getTotalDespesasTotais() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        try {
            DespesasDAO despesasDAO = new DespesasDAO();
            BigDecimal despesasTotais = despesasDAO.getDespesasTotais(MainScreenFXMLController.getDateInicial(), MainScreenFXMLController.getDateFinal());
            if (despesasTotais == null) {
                this.getLabelDespesasTotais().setText(df.format(0.00));
            } else {
                this.getLabelDespesasTotais().setText(df.format(despesasTotais));
            }
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.getDatePickerDataInicial().setValue(LocalDate.now().minusDays(30));
            this.getDatePickerDataFinal().setValue(LocalDate.now());

            MainScreenFXMLController.setDateInicial(Date.from(this.getDatePickerDataInicial().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            MainScreenFXMLController.setDateFinal(Date.from(this.getDatePickerDataFinal().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            this.getMenuItemCategorias().setGraphic(new ImageView("/br/com/controleasy/images/img2.png"));
            this.getMenuItemDespesas().setGraphic(new ImageView("/br/com/controleasy/images/img16.png"));
            this.getMenuItemReceitas().setGraphic(new ImageView("/br/com/controleasy/images/img4.png"));
            this.getMenuItemUsuarios().setGraphic(new ImageView("/br/com/controleasy/images/img17.png"));
            this.getMenuItemPeriodo().setGraphic(new ImageView("/br/com/controleasy/images/img1.png"));
            this.getMenuItemDespesasPorCategoria().setGraphic(new ImageView("/br/com/controleasy/images/img1.png"));

            MainScreenFXMLController.setId(LoginFXMLController.getId());
            MainScreenFXMLController.setUsuario(LoginFXMLController.getUsuario().toUpperCase());

            this.setAcesso(LoginFXMLController.getAcesso());

            if (this.getAcesso().equals("PADRÃO")) {
                this.getMenuItemUsuarios().setDisable(true);
            }

            this.getLblId().setText(MainScreenFXMLController.getId());
            this.getLblUsuario().setText(MainScreenFXMLController.getUsuario());

            listCategoriaPieChart();

            this.getTotalDespesasAPagar();
            this.getTotalDespesasTotais();

        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void menuItemDespesas(ActionEvent event) {
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
    }

    @FXML
    void menuItemPeriodo(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/RelatorioPeriodoFXML.fxml"));
            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
            Stage stagePeriodo = new Stage();
            stagePeriodo.getIcons().add(image);
            stagePeriodo.setScene(new Scene(parent));
            stagePeriodo.setTitle("DESPESAS POR PERÍODO");
            stagePeriodo.setResizable(false);
            stagePeriodo.initModality(Modality.APPLICATION_MODAL);
            stagePeriodo.show();
            MainScreenFXMLController.setStage(stagePeriodo);
        } catch (IOException ex) {
            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void menuItemSair(ActionEvent event) {
        try {
            ButtonType sim = new ButtonType("SIM");
            ButtonType nao = new ButtonType("NÃO");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Controleasy");
            alert.setHeaderText("EXIT:");
            alert.setContentText("DESEJA SAIR DO SISTEMA?");
            alert.getButtonTypes().setAll(sim, nao);
            alert.showAndWait().ifPresent(result -> {
                if (result == sim) {
                    System.exit(0);
                } else if (result == nao) {
                    alert.close();
                }
            });
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void menuItemAtualizar(ActionEvent event) {
        try {
            this.listCategoriaPieChart();
            this.getTotalDespesasAPagar();
            this.getTotalDespesasTotais();
            new DespesasFXMLController().buscarDespesasVencidas();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void menuItemCategorias(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/CategoriasFXML.fxml"));
            Stage stageCategorias = new Stage();
            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
            stageCategorias.getIcons().add(image);
            stageCategorias.setScene(new Scene(parent));
            stageCategorias.setTitle("CATEGORIAS");
            stageCategorias.setResizable(false);
            stageCategorias.initModality(Modality.APPLICATION_MODAL);
            stageCategorias.show();
        } catch (IOException ex) {
            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void menuItemReceitas(ActionEvent event) {

    }

    @FXML
    void menuItemUsuarios(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/UsuariosFXML.fxml"));
            Stage stageUsuarios = new Stage();
            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
            stageUsuarios.getIcons().add(image);
            stageUsuarios.setScene(new Scene(parent));
            stageUsuarios.setTitle("USUÁRIOS");
            stageUsuarios.setResizable(false);
            stageUsuarios.initModality(Modality.APPLICATION_MODAL);
            stageUsuarios.show();
        } catch (IOException ex) {
            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void menuItemSaldoAtual(ActionEvent event) {
    }

    @FXML
    void menuItemVersao(ActionEvent event) {
    }

    @FXML
    private void menuItemAlterarUsuario(ActionEvent event) {
        LoginFXMLController.getStage().close();
        LoginFXMLController.getStageLogin().show();
    }

    @FXML
    private void menuItemDespesasPorCategoria(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/RelatorioPorCategoriaFXML.fxml"));
            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
            Stage stagePeriodo = new Stage();
            stagePeriodo.getIcons().add(image);
            stagePeriodo.setScene(new Scene(parent));
            stagePeriodo.setTitle("DESPESAS POR CATEGORIA");
            stagePeriodo.setResizable(false);
            stagePeriodo.initModality(Modality.APPLICATION_MODAL);
            stagePeriodo.show();
            MainScreenFXMLController.setStage(stagePeriodo);
        } catch (IOException ex) {
            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void buttonPesquisar(ActionEvent event) {
        MainScreenFXMLController.setDateInicial(Date.from(this.getDatePickerDataInicial().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        MainScreenFXMLController.setDateFinal(Date.from(this.getDatePickerDataFinal().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        this.getTotalDespesasAPagar();
        this.getTotalDespesasTotais();
    }

}
