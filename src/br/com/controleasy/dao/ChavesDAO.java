/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.dao;

import br.com.controleasy.connection.JpaUtil;
import br.com.controleasy.model.Chaves;
import br.com.controleasy.util.Alerts;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author luanp
 */
public class ChavesDAO {

    private static EntityManager em;

    public ChavesDAO() {
        try {
            em = JpaUtil.getEntityManager();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    public boolean verificarAtivacao() {
        try {
            @SuppressWarnings("JPQLValidation")
            Query queryOne = em.createQuery("SELECT c FROM Chaves c WHERE c.validade = '999' and c.ativo = true");
            List<Chaves> listOne = queryOne.getResultList();
            if (!listOne.isEmpty()) {
                return true;
            }

            @SuppressWarnings("JPQLValidation")
            Query queryTwo = em.createQuery("SELECT c FROM Chaves c WHERE c.ativo = true AND c.expirado = false");
            List<Chaves> listTwo = queryTwo.getResultList();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (!listTwo.isEmpty()) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                LocalDate today = LocalDate.parse(df.format(c.getTime()));
                LocalDate validUntil = LocalDate.parse(df.format(listTwo.get(0).getValidoAte()));
                if (today.isAfter(validUntil)) {
                    Chaves chave = em.find(Chaves.class, listTwo.get(0).getSerialKey());
                    em.getTransaction().begin();
                    chave.setExpirado(true);
                    em.getTransaction().commit();
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return false;
    }

    public List<Chaves> validarSerialKey(String serialKey) {
        try {
            Query query = em.createQuery("SELECT c FROM Chaves c WHERE c.serialKey = :serialKey and c.ativo = false");
            query.setParameter("serialKey", serialKey);
            List<Chaves> result = query.getResultList();
            if (!result.isEmpty()) {
                Calendar c = Calendar.getInstance();
                switch (result.get(0).getValidade()) {
                    case 10: {
                        c.setTime(new Date());
                        c.add(Calendar.DATE, +10);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Chaves chaves = em.find(Chaves.class, serialKey);
                        em.getTransaction().begin();
                        chaves.setAtivo(true);
                        chaves.setValidoAte(new java.sql.Date(sdf.parse(sdf.format(c.getTime())).getTime()));
                        em.getTransaction().commit();
                        break;
                    }
                    case 30: {
                        c.setTime(new Date());
                        c.add(Calendar.DATE, +30);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Chaves chaves = em.find(Chaves.class, serialKey);
                        em.getTransaction().begin();
                        chaves.setAtivo(true);
                        chaves.setValidoAte(new java.sql.Date(sdf.parse(sdf.format(c.getTime())).getTime()));
                        em.getTransaction().commit();
                        break;
                    }
                    case 365: {
                        c.setTime(new Date());
                        c.add(Calendar.DATE, +365);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Chaves chaves = em.find(Chaves.class, serialKey);
                        em.getTransaction().begin();
                        chaves.setAtivo(true);
                        chaves.setValidoAte(new java.sql.Date(sdf.parse(sdf.format(c.getTime())).getTime()));
                        em.getTransaction().commit();
                        break;
                    }
                    case 999: {
                        Chaves chaves = em.find(Chaves.class, serialKey);
                        em.getTransaction().begin();
                        chaves.setAtivo(true);
                        em.getTransaction().commit();
                        break;
                    }
                }
                return result;
            } else {
                Alerts.showAlert("Controleasy", "", "CHAVE SERIAL INVÁLIDA", Alert.AlertType.ERROR);
            }
        } catch (ParseException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
        return null;
    }
}
