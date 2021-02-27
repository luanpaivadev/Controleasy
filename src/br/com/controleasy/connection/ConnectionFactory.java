/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.connection;

import br.com.controleasy.util.Alerts;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javafx.scene.control.Alert;

/**
 *
 * @author User
 */
public class ConnectionFactory {

	private static Connection connection;

	public static Properties getProperties() {
		try {
			Properties properties = new Properties();
			FileInputStream file = new FileInputStream("C:/Program Files/Controleasy/properties/config.properties");
			properties.load(file);
			return properties;
		} catch (IOException e) {
			Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
		}
		return null;
	}

	public static Connection getConnection() {
		try {
			// MYSQL -- Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
			Properties properties = getProperties();
			String url = properties.getProperty("javax.persistence.jdbc.url");
			String user = properties.getProperty("javax.persistence.jdbc.user");
			connection = DriverManager.getConnection(url, user, "admin");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
