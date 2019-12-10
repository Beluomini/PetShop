/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "porte")
@NamedQueries({
    @NamedQuery(name = "Porte.findAll", query = "SELECT p FROM Porte p")})
public class Porte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_porte")
    private Integer idPorte;
    @Column(name = "nome_porte")
    private String nomePorte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_max_porte")
    private Double pesoMaxPorte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porteIdPorte")
    private List<Animal> animalList;

    public Porte() {
    }

    public Porte(Integer idPorte) {
        this.idPorte = idPorte;
    }

    public Integer getIdPorte() {
        return idPorte;
    }

    public void setIdPorte(Integer idPorte) {
        this.idPorte = idPorte;
    }

    public String getNomePorte() {
        return nomePorte;
    }

    public void setNomePorte(String nomePorte) {
        this.nomePorte = nomePorte;
    }

    public Double getPesoMaxPorte() {
        return pesoMaxPorte;
    }

    public void setPesoMaxPorte(Double pesoMaxPorte) {
        this.pesoMaxPorte = pesoMaxPorte;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorte != null ? idPorte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porte)) {
            return false;
        }
        Porte other = (Porte) object;
        if ((this.idPorte == null && other.idPorte != null) || (this.idPorte != null && !this.idPorte.equals(other.idPorte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Porte[ idPorte=" + idPorte + " ]";
    }
    
}
