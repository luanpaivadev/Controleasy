/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.controller;

import br.com.controleasy.dao.RelatoriosDAO;
import br.com.controleasy.util.Alerts;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author luanp
 */
public class RelatorioPeriodoFXMLController implements Initializable {

    @FXML
    private ToggleGroup All;

    @FXML
    private Button buttonGerarRelatorio;

    @FXML
    private DatePicker dataFinal;

    @FXML
    private DatePicker dataInicial;

    @FXML
    private RadioButton radioButtonTodos;

    @FXML
    private RadioButton radioButtonAPagar;

    @FXML
    private RadioButton radioButtonPagas;

    @FXML
    private RadioButton radioButtonPagamento;

    @FXML
    private ToggleGroup Filtro1;

    @FXML
    private RadioButton radioButtonVencimento;

    public RadioButton getRadioButtonPagamento() {
        return radioButtonPagamento;
    }

    public void setRadioButtonPagamento(RadioButton radioButtonPagamento) {
        this.radioButtonPagamento = radioButtonPagamento;
    }

    public RadioButton getRadioButtonVencimento() {
        return radioButtonVencimento;
    }

    public void setRadioButtonVencimento(RadioButton radioButtonVencimento) {
        this.radioButtonVencimento = radioButtonVencimento;
    }

    public DatePicker getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(DatePicker dataFinal) {
        this.dataFinal = dataFinal;
    }

    public DatePicker getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(DatePicker dataInicial) {
        this.dataInicial = dataInicial;
    }

    public RadioButton getRadioButtonTodos() {
        return radioButtonTodos;
    }

    public void setRadioButtonTodos(RadioButton radioButtonTodos) {
        this.radioButtonTodos = radioButtonTodos;
    }

    public RadioButton getRadioButtonAPagar() {
        return radioButtonAPagar;
    }

    public void setRadioButtonAPagar(RadioButton radioButtonAPagar) {
        this.radioButtonAPagar = radioButtonAPagar;
    }

    public RadioButton getRadioButtonPagas() {
        return radioButtonPagas;
    }

    public void setRadioButtonPagas(RadioButton radioButtonPagas) {
        this.radioButtonPagas = radioButtonPagas;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.getDataInicial().setValue(LocalDate.now().minusDays(30));
        this.getDataFinal().setValue(LocalDate.now());
    }

    @FXML
    private void gerarRelatorio(ActionEvent event) {
        this.gerarRelatorio();
    }

    public void gerarRelatorio() {

        if (this.getDataInicial().getValue() != null && this.getDataFinal().getValue() != null) {
            String situacao;
            String relatorio;
            String image = "br/com/controleasy/images/logotipo.png";

            Date dateInicial = Date.from(this.getDataInicial().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date dateFinal = Date.from(this.getDataFinal().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (this.getRadioButtonPagamento().isSelected()) {
                if (this.getRadioButtonTodos().isSelected()) {
                    relatorio = "DespesasPorPagamento";
                    RelatoriosDAO.gerarRelatorioPorPeriodo(dateInicial, dateFinal, relatorio, image);
                }
            }
            
            if (this.getRadioButtonVencimento().isSelected()) {
                if (this.getRadioButtonAPagar().isSelected()) {
                    situacao = "A pagar";
                    relatorio = "DespesasPorVencimentoPorSituacao";
                    RelatoriosDAO.gerarRelatorioPorPeriodoPorSituacao(dateInicial, dateFinal, situacao, relatorio, image);
                } else if (this.getRadioButtonPagas().isSelected()) {
                    situacao = "Pago";
                    relatorio = "DespesasPorVencimentoPorSituacao";
                    RelatoriosDAO.gerarRelatorioPorPeriodoPorSituacao(dateInicial, dateFinal, situacao, relatorio, image);
                } else if (this.getRadioButtonTodos().isSelected()) {
                    relatorio = "DespesasPorVencimento";
                    RelatoriosDAO.gerarRelatorioPorPeriodo(dateInicial, dateFinal, relatorio, image);
                }
            }
        } else {
            Alerts.showAlert("Controleasy", "CAMPOS OBRIGATÓRIOS", "PREENCHA AS DATAS CORRETAMENTE", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void radioButtonPagamento(ActionEvent event) {
        this.getRadioButtonAPagar().setDisable(true);
        this.getRadioButtonPagas().setDisable(true);
        this.getRadioButtonTodos().setSelected(true);
    }

    @FXML
    private void radioButtonVencimento(ActionEvent event) {
        this.getRadioButtonAPagar().setDisable(false);
        this.getRadioButtonPagas().setDisable(false);
    }

}
