/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")})
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_animal")
    private Integer idAnimal;
    @Column(name = "nome_animal")
    private String nomeAnimal;
    @Column(name = "data_nasc_animal")
    @Temporal(TemporalType.DATE)
    private Date dataNascAnimal;
    @JoinColumn(name = "porte_id_porte", referencedColumnName = "id_porte")
    @ManyToOne(optional = false)
    private Porte porteIdPorte;

    public Animal() {
    }

    public Animal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public Date getDataNascAnimal() {
        return dataNascAnimal;
    }

    public void setDataNascAnimal(Date dataNascAnimal) {
        this.dataNascAnimal = dataNascAnimal;
    }

    public Porte getPorteIdPorte() {
        return porteIdPorte;
    }

    public void setPorteIdPorte(Porte porteIdPorte) {
        this.porteIdPorte = porteIdPorte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnimal != null ? idAnimal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.idAnimal == null && other.idAnimal != null) || (this.idAnimal != null && !this.idAnimal.equals(other.idAnimal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Animal[ idAnimal=" + idAnimal + " ]";
    }
    
}
