/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.dao;

import br.com.controleasy.connection.JpaUtil;
import br.com.controleasy.controller.MainScreenFXMLController;
import br.com.controleasy.model.Categorias;
import br.com.controleasy.util.Alerts;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author luanp
 */
public class CategoriasDAO {

    private static EntityManager em;

    public CategoriasDAO() {
        try {
            em = JpaUtil.getEntityManager();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public boolean cadastrarCategoria(Categorias c) {
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean atualizarCategoria(Categorias c) {
        try {
            em.getTransaction().begin();
            Categorias categorias = em.find(Categorias.class, c.getId());
            categorias.setCategoria(c.getCategoria());
            categorias.setUsuariosId(c.getUsuariosId());
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deletarCategoria(Categorias c) {
        try {
            em.getTransaction().begin();
            Categorias categorias = em.find(Categorias.class, c.getId());
            em.remove(categorias);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public List<Categorias> getAllCategorias() {
        try {
            Query query = em.createQuery("SELECT c FROM Categorias c WHERE c.usuariosId.id = :id ORDER BY c.categoria", Categorias.class);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId()));
            return query.getResultList();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

}
