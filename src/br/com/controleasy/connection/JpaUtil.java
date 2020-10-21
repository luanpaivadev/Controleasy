/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.connection;

import br.com.controleasy.util.Alerts;
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

    private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();

    private static EntityManagerFactory entityManagerFactory;

    public JpaUtil() {
    }

    public static EntityManager getEntityManager() {
        try {
            if (entityManagerFactory == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            }
            EntityManager entityManager = threadEntityManager.get();
            if (entityManager == null || !entityManager.isOpen()) {
                entityManager = entityManagerFactory.createEntityManager();
                JpaUtil.threadEntityManager.set(entityManager);
            }
            return entityManager;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", "SEM COMUNICAÇÃO COM O BANCO DE DADOS", e.getMessage(), Alert.AlertType.ERROR);
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
