/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.controller;

import br.com.controleasy.dao.ChavesDAO;
import br.com.controleasy.model.Chaves;
import br.com.controleasy.util.Alerts;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author luanp
 */
public class SerialKeyFXMLController implements Initializable {

    @FXML
    private TextField textFieldSerialKey;

    @FXML
    private Button buttonValidar;

    public TextField getTextFieldSerialKey() {
        return textFieldSerialKey;
    }

    public void setTextFieldSerialKey(TextField textFieldSerialKey) {
        this.textFieldSerialKey = textFieldSerialKey;
    }

    public Button getButtonValidar() {
        return buttonValidar;
    }

    public void setButtonValidar(Button buttonValidar) {
        this.buttonValidar = buttonValidar;
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
    }

    @FXML
    private void validarSerialKey(ActionEvent event) {
        try {
            if (this.getTextFieldSerialKey().getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Controleasy");
                alert.setContentText("PREENCHA O CAMPO DE NÚMERO DE SÉRIE");
                alert.show();
            } else {
                ChavesDAO chavesDAO = new ChavesDAO();
                MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                String chave = this.getTextFieldSerialKey().getText().toUpperCase();
                byte messageDigest[] = algorithm.digest(chave.getBytes("UTF-8"));

                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }
                String serialKey = hexString.toString();
                List<Chaves> listChaves = chavesDAO.validarSerialKey(serialKey);
                if (!listChaves.isEmpty()) {
                    try {
                        if (listChaves.get(0).getValidade() != 999) {
                            ButtonType ok = new ButtonType("OK");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Controleasy");
                            alert.setHeaderText("SOFTWARE ATIVADO COM SUCESSO!");
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            alert.setContentText("O SOFTWARE EXPIRA EM (" + sdf.format(listChaves.get(0).getValidoAte()) + ")");
                            alert.getButtonTypes().setAll(ok);
                            alert.showAndWait().ifPresent(result -> {
                                if (result == ok) {
                                    System.exit(0);
                                }
                            });
                        }
                        ButtonType ok = new ButtonType("OK");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Controleasy");
                        alert.setHeaderText("SOFTWARE ATIVADO COM SUCESSO!");
                        alert.setContentText("O SOFTWARE SERÁ REINICIADO");
                        alert.getButtonTypes().setAll(ok);
                        alert.showAndWait().ifPresent(result -> {
                            if (result == ok) {
                                System.exit(0);
                            }
                        });
                    } catch (Exception e) {
                        Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            }

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NullPointerException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
