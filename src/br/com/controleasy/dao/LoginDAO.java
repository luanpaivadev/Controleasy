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
import javax.persistence.TypedQuery;

/**
 *
 * @author luanp
 */
public class LoginDAO {

    private static EntityManager em;

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        LoginDAO.em = em;
    }

    public LoginDAO() {
        try {
            em = JpaUtil.getEntityManager();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @SuppressWarnings("null")
    public Usuarios login(Usuarios usuario) {
        try {
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u WHERE u.usuario = :usuario AND u.senha = :senha", Usuarios.class);
            query.setParameter("usuario", usuario.getUsuario());
            query.setParameter("senha", usuario.getSenha());
            return query.getSingleResult();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", "LOGIN", "SENHA INVÁLIDA!", Alert.AlertType.INFORMATION);
        } finally {
            em.close();
        }
        return null;
    }
}
