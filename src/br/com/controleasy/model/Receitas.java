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
@Table(name = "receitas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receitas.findAll", query = "SELECT r FROM Receitas r")
    , @NamedQuery(name = "Receitas.findById", query = "SELECT r FROM Receitas r WHERE r.id = :id")
    , @NamedQuery(name = "Receitas.findByDescricao", query = "SELECT r FROM Receitas r WHERE r.descricao = :descricao")
    , @NamedQuery(name = "Receitas.findByData", query = "SELECT r FROM Receitas r WHERE r.data = :data")
    , @NamedQuery(name = "Receitas.findByCategoria", query = "SELECT r FROM Receitas r WHERE r.categoria = :categoria")
    , @NamedQuery(name = "Receitas.findByValor", query = "SELECT r FROM Receitas r WHERE r.valor = :valor")
    , @NamedQuery(name = "Receitas.findBySituacao", query = "SELECT r FROM Receitas r WHERE r.situacao = :situacao")})
public class Receitas implements Serializable {

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
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "situacao")
    private String situacao;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuariosId;

    public Receitas() {
    }

    public Receitas(Integer id) {
        this.id = id;
    }

    public Receitas(Integer id, String descricao, Date data, String categoria, BigDecimal valor, String situacao) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.categoria = categoria;
        this.valor = valor;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        if (!(object instanceof Receitas)) {
            return false;
        }
        Receitas other = (Receitas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.easypaper.model.Receitas[ id=" + id + " ]";
    }
    
}
