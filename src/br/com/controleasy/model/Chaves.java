/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "chaves")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chaves.findAll", query = "SELECT c FROM Chaves c")
    , @NamedQuery(name = "Chaves.findBySerialKey", query = "SELECT c FROM Chaves c WHERE c.serialKey = :serialKey")
    , @NamedQuery(name = "Chaves.findByValidade", query = "SELECT c FROM Chaves c WHERE c.validade = :validade")
    , @NamedQuery(name = "Chaves.findByValidoAte", query = "SELECT c FROM Chaves c WHERE c.validoAte = :validoAte")
    , @NamedQuery(name = "Chaves.findByAtivo", query = "SELECT c FROM Chaves c WHERE c.ativo = :ativo")
    , @NamedQuery(name = "Chaves.findByExpirado", query = "SELECT c FROM Chaves c WHERE c.expirado = :expirado")})
public class Chaves implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "serialKey")
    private String serialKey;
    @Basic(optional = false)
    @Column(name = "validade")
    private int validade;
    @Column(name = "validoAte")
    @Temporal(TemporalType.DATE)
    private Date validoAte;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @Basic(optional = false)
    @Column(name = "expirado")
    private boolean expirado;

    public Chaves() {
    }

    public Chaves(String serialKey) {
        this.serialKey = serialKey;
    }

    public Chaves(String serialKey, int validade, boolean ativo, boolean expirado) {
        this.serialKey = serialKey;
        this.validade = validade;
        this.ativo = ativo;
        this.expirado = expirado;
    }

    public String getSerialKey() {
        return serialKey;
    }

    public void setSerialKey(String serialKey) {
        this.serialKey = serialKey;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public Date getValidoAte() {
        return validoAte;
    }

    public void setValidoAte(Date validoAte) {
        this.validoAte = validoAte;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean getExpirado() {
        return expirado;
    }

    public void setExpirado(boolean expirado) {
        this.expirado = expirado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serialKey != null ? serialKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chaves)) {
            return false;
        }
        Chaves other = (Chaves) object;
        if ((this.serialKey == null && other.serialKey != null) || (this.serialKey != null && !this.serialKey.equals(other.serialKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.easypaper.model.Chaves[ serialKey=" + serialKey + " ]";
    }
    
}
