/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luanp
 */
@Entity
@Table(name = "licenca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Licenca.findAll", query = "SELECT l FROM Licenca l")
    , @NamedQuery(name = "Licenca.findById", query = "SELECT l FROM Licenca l WHERE l.id = :id")
    , @NamedQuery(name = "Licenca.findByLicenciado", query = "SELECT l FROM Licenca l WHERE l.licenciado = :licenciado")})
public class Licenca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "licenciado")
    private String licenciado;

    public Licenca() {
    }

    public Licenca(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicenciado() {
        return licenciado;
    }

    public void setLicenciado(String licenciado) {
        this.licenciado = licenciado;
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
        if (!(object instanceof Licenca)) {
            return false;
        }
        Licenca other = (Licenca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.easypaper.model.Licenca[ id=" + id + " ]";
    }
    
}
