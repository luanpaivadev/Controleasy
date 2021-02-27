/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.connection;

import br.com.controleasy.util.Alerts;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author luanp
 */
public class JpaUtil {

	private static final String PERSISTENCE_UNIT = "ControleasyPU";

	private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();

	private static EntityManagerFactory entityManagerFactory;

	public JpaUtil() {
	}

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

	public static EntityManager getEntityManager() {
		try {
			if (entityManagerFactory == null) {
				Properties properties = getProperties();
				Map<String, String> map = new HashMap<String, String>();
				String url = properties.getProperty("javax.persistence.jdbc.url");
				String driver = properties.getProperty("javax.persistence.jdbc.driver");
				String user = properties.getProperty("javax.persistence.jdbc.user");
				String dialect = properties.getProperty("hibernate.dialect");
				map.put("javax.persistence.jdbc.url", url);
				map.put("javax.persistence.jdbc.driver", driver);
				map.put("javax.persistence.jdbc.user", user);
				map.put("hibernate.dialect", dialect);
				entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, map);
			}
			EntityManager entityManager = threadEntityManager.get();
			if (entityManager == null || !entityManager.isOpen()) {
				entityManager = entityManagerFactory.createEntityManager();
				JpaUtil.threadEntityManager.set(entityManager);
			}
			return entityManager;
		} catch (Exception e) {
			Alerts.showAlert("Controleasy", "SEM COMUNICAÇÃO COM O BANCO DE DADOS!",
					"* VERIFIQUE OS DADOS NO ARQUIVO DE CONFIGURAÇÃO.\n"
							+ "* VERIFIQUE SE O BANCO DE DADOS ESTÁ EM EXECUÇÃO.\n" + "* DESATIVE O FIREWALL.\n\n"
							+ "CASO O ERRO PERSISTA, ENTRE EM CONTATO COM O NOSSO SUPORTE.",
					Alert.AlertType.ERROR);
		}
		return null;
	}

	public static void closeEntityManager() {
		EntityManager em = threadEntityManager.get();
		if (em != null) {
			EntityTransaction transaction = em.getTransaction();
			if (transaction.isActive()) {
				transaction.commit();
			}
			em.close();
			threadEntityManager.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		closeEntityManager();
		entityManagerFactory.close();
	}
}
