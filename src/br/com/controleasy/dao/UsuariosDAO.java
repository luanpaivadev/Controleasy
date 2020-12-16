/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.dao;

import br.com.controleasy.connection.JpaUtil;
import br.com.controleasy.model.Usuarios;
import br.com.controleasy.util.Alerts;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class UsuariosDAO {

    private static EntityManager em;

    public UsuariosDAO() {
        try {
            em = JpaUtil.getEntityManager();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public List<Usuarios> getUsuarios() {
        try {
            Query query = em.createQuery("FROM Usuarios u ORDER BY u.usuario", Usuarios.class);
            return query.getResultList();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public boolean cadastrarUsuarios(Usuarios u) {
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean atualizarUsuarios(Usuarios u) {
        try {
            em.getTransaction().begin();
            Usuarios usuario = em.find(Usuarios.class, u.getId());
            usuario.setNome(u.getNome());
            usuario.setUsuario(u.getUsuario());
            usuario.setAcesso(u.getAcesso());
            usuario.setSaldo(u.getSaldo());
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deletarUsuarios(Usuarios u) {
        try {
            em.getTransaction().begin();
            Usuarios usuarios = em.find(Usuarios.class, u.getId());
            em.remove(usuarios);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }
}
