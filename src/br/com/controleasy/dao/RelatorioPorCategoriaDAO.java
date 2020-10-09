/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.dao;

import br.com.controleasy.connection.ConnectionFactory;
import br.com.controleasy.controller.MainScreenFXMLController;
import br.com.controleasy.util.Alerts;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class RelatorioPorCategoriaDAO {

    public static void gerarRelatorioPorCategoria(Date dataInicial, Date dataFinal, String relatorio, String image) {
        JasperPrint jasperPrint = null;
        @SuppressWarnings("UnusedAssignment")
        JasperReport jasperReport = null;
        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/br/com/controleasy/relatorios/categoria/" + relatorio + ".jrxml");

        Map params = new HashMap();
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        params.put("idUsuario", Integer.parseInt(MainScreenFXMLController.getId()));
        params.put("image", image);
        try {
            jasperReport = JasperCompileManager.compileReport(inputStream);
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionFactory.getConnection());
        } catch (JRException ex) {
            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
        } finally {
            ConnectionFactory.closeConnection();
        }
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("RELATÓRIO - DESPESAS POR CATEGORIA");
        viewer.setVisible(true);
        viewer.toFront();
    }

    public static void gerarRelatorioPorCategoriaPorSituacao(Date dataInicial, Date dataFinal, String situacao, String relatorio, String image) {
        JasperPrint jasperPrint = null;
        @SuppressWarnings("UnusedAssignment")
        JasperReport jasperReport = null;
        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/br/com/controleasy/relatorios/categoria/" + relatorio + ".jrxml");

        Map params = new HashMap();
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        params.put("idUsuario", Integer.parseInt(MainScreenFXMLController.getId()));
        params.put("situacao", situacao);
        params.put("image", image);
        try {
            jasperReport = JasperCompileManager.compileReport(inputStream);
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionFactory.getConnection());
        } catch (JRException ex) {
            Alerts.showAlert("Controleasy", null, ex.getMessage(), Alert.AlertType.ERROR);
        } finally {
            ConnectionFactory.closeConnection();
        }
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("RELATÓRIO - DESPESAS POR CATEGORIA POR SITUACÃO");
        viewer.setVisible(true);
        viewer.toFront();
    }

}
