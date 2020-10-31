/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.dao;

import br.com.controleasy.connection.JpaUtil;
import br.com.controleasy.controller.MainScreenFXMLController;
import br.com.controleasy.model.Despesas;
import br.com.controleasy.util.Alerts;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author luanp
 */
public class DespesasDAO {

    private static EntityManager em;

    public DespesasDAO() {
        try {
            em = JpaUtil.getEntityManager();
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public List<Despesas> getDespesasAPagar(Date dataInicial, Date dataFinal) {
        try {
            @SuppressWarnings("JPQLValidation")
            Query query = em.createQuery("SELECT d FROM Despesas d WHERE d.situacao = 'A PAGAR' and d.vencimento between :dataInicial and :dataFinal and d.usuariosId.id = :id ORDER BY d.vencimento, d.descricao");
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId()));
            return query.getResultList();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public List<Despesas> getDespesasPagas(Date dataInicial, Date dataFinal) {
        try {
            Query query = em.createQuery("SELECT d FROM Despesas d WHERE d.situacao = 'PAGO' and d.vencimento between :dataInicial and :dataFinal and d.usuariosId.id = :id ORDER BY d.vencimento, d.descricao");
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId()));
            return query.getResultList();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public List<Despesas> getDespesasPorVencimento(Date vencimento, String situacao) {
        try {
            @SuppressWarnings("JPQLValidation")
            Query query = em.createQuery("SELECT d FROM Despesas d WHERE d.situacao = :situacao and d.vencimento = :vencimento and d.usuariosId.id = :id ORDER BY d.vencimento, d.descricao");
            query.setParameter("situacao", situacao);
            query.setParameter("vencimento", vencimento);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId()));
            return query.getResultList();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public List<Despesas> getDespesasVencidas() {
        try {
            @SuppressWarnings("JPQLValidation")
            Query query = em.createQuery("SELECT d FROM Despesas d WHERE date(d.vencimento) < curdate() and situacao = 'A PAGAR' order by vencimento, d.descricao");
            return query.getResultList();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public BigDecimal getTotalDespesasAPagar(Date dataInicial, Date dataFinal) {
        try {
            @SuppressWarnings("JPQLValidation")
            TypedQuery<BigDecimal> query = em.createQuery("SELECT sum(d.valor) FROM Despesas d WHERE d.situacao = 'A PAGAR' and d.vencimento between :dataInicial and :dataFinal and d.usuariosId.id = :id", BigDecimal.class);
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId())).getSingleResult();
            return query.getSingleResult();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public BigDecimal getTotalDespesasPagas(Date dataInicial, Date dataFinal) {
        try {
            @SuppressWarnings("JPQLValidation")
            TypedQuery<BigDecimal> query = em.createQuery("SELECT sum(d.valor) FROM Despesas d WHERE d.situacao = 'PAGO' and d.vencimento between :dataInicial and :dataFinal and d.usuariosId.id = :id", BigDecimal.class);
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId())).getSingleResult();
            return query.getSingleResult();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public BigDecimal getDespesasTotais(Date dataInicial, Date dataFinal) {
        try {
            @SuppressWarnings("JPQLValidation")
            TypedQuery<BigDecimal> query = em.createQuery("SELECT sum(d.valor) FROM Despesas d WHERE d.vencimento between :dataInicial and :dataFinal and d.usuariosId.id = :id", BigDecimal.class);
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId())).getSingleResult();
            return query.getSingleResult();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }
    }

    public boolean cadastrarDespesa(Despesas d) {
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean atualizarDespesa(Despesas d) {
        try {
            em.getTransaction().begin();
            Despesas despesas = em.find(Despesas.class, d.getId());
            despesas.setDescricao(d.getDescricao());
            despesas.setCategoria(d.getCategoria());
            despesas.setValor(d.getValor());
            despesas.setVencimento(d.getVencimento());
            despesas.setPagamento(d.getPagamento());
            despesas.setSituacao(d.getSituacao());
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deletarDespesa(Despesas d) {
        try {
            em.getTransaction().begin();
            Despesas despesas = em.find(Despesas.class, d.getId());
            em.remove(despesas);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public boolean arquivarDespesa(Despesas d) {
        try {
            em.getTransaction().begin();
            Despesas despesas = em.find(Despesas.class, d.getId());
            despesas.setSituacao("Arquivada");
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            em.close();
        }
    }

    public List<Despesas> getGroupCategorias(Date dataInicial, Date dataFinal) {
        /*try {
            @SuppressWarnings("JPQLValidation")
            TypedQuery<Object[]> query = em.createQuery("SELECT d.categoria as categoria, SUM(d.valor) as valor FROM Despesas d WHERE d.usuariosId.id = :id and d.situacao != 'Arquivada' GROUP BY d.categoria", Object[].class);
            List<Object[]> results = query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId())).getResultList();
            List<Despesas> listDespesa = new ArrayList<>();
            results.forEach((result) -> {
                listDespesa.add(new Despesas(result[0].toString(), new BigDecimal(result[1].toString())));
            });
            return listDespesa;
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }*/

        try {
            @SuppressWarnings("JPQLValidation")
            TypedQuery<Object[]> query = em.createQuery("SELECT d.categoria as categoria, SUM(d.valor) as valor FROM Despesas d WHERE d.vencimento between :dataInicial and :dataFinal and d.usuariosId.id = :id GROUP BY d.categoria", Object[].class);
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);
            query.setParameter("id", Integer.parseInt(MainScreenFXMLController.getId()));
            List<Object[]> results = query.getResultList();
            List<Despesas> listDespesa = new ArrayList<>();
            results.forEach((result) -> {
                listDespesa.add(new Despesas(result[0].toString(), new BigDecimal(result[1].toString())));
            });
            return listDespesa;
        } catch (NumberFormatException e) {
            Alerts.showAlert("Controleasy", null, e.getMessage(), Alert.AlertType.ERROR);
            return null;
        } finally {
            em.close();
        }

    }

}
