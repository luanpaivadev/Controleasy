/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luanp
 */
@Entity
@Table(name = "despesas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesas.findAll", query = "SELECT d FROM Despesas d"),
    @NamedQuery(name = "Despesas.findById", query = "SELECT d FROM Despesas d WHERE d.id = :id"),
    @NamedQuery(name = "Despesas.findByDescricao", query = "SELECT d FROM Despesas d WHERE d.descricao = :descricao"),
    @NamedQuery(name = "Despesas.findByCategoria", query = "SELECT d FROM Despesas d WHERE d.categoria = :categoria"),
    @NamedQuery(name = "Despesas.findByValor", query = "SELECT d FROM Despesas d WHERE d.valor = :valor"),
    @NamedQuery(name = "Despesas.findByVencimento", query = "SELECT d FROM Despesas d WHERE d.vencimento = :vencimento"),
    @NamedQuery(name = "Despesas.findByPagamento", query = "SELECT d FROM Despesas d WHERE d.pagamento = :pagamento"),
    @NamedQuery(name = "Despesas.findBySituacao", query = "SELECT d FROM Despesas d WHERE d.situacao = :situacao")})

public class Despesas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "vencimento")
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    
    @Column(name = "pagamento")
    @Temporal(TemporalType.DATE)
    private Date pagamento;
    
    @Basic(optional = false)
    @Column(name = "situacao")
    private String situacao;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuariosId;

    public Despesas() {
    }

    public Despesas(Integer id) {
        this.id = id;
    }

    public Despesas(String categoria, BigDecimal valor) {
        this.categoria = categoria;
        this.valor = valor;
    }
    
    public Despesas(Integer id, String descricao, String categoria, BigDecimal valor, Date vencimento, Date pagamento, String situacao) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.vencimento = vencimento;
        this.pagamento = pagamento;
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Usuarios getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuarios usuariosId) {
        this.usuariosId = usuariosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesas)) {
            return false;
        }
        Despesas other = (Despesas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.controleasy.model.Despesas[ id=" + id + " ]";
    }
    
}
