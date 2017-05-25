/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucrs.facin.sispresc.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lcaltab
 */
@Entity
@Table(name = "PRESCRICAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescricao.findAll", query = "SELECT p FROM Prescricao p")
    , @NamedQuery(name = "Prescricao.findById", query = "SELECT p FROM Prescricao p WHERE p.id = :id")})
public class Prescricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @ManyToMany(mappedBy = "prescricaoList")
    private List<Medicamento> medicamentoList;
    @JoinColumn(name = "CPF", referencedColumnName = "CPF")
    @ManyToOne
    private Paciente cpf;
    @JoinColumn(name = "MED_RESPONSAVEL", referencedColumnName = "USERNAME")
    @ManyToOne
    private User medResponsavel;

    public Prescricao() {
    }

    public Prescricao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    public Paciente getCpf() {
        return cpf;
    }

    public void setCpf(Paciente cpf) {
        this.cpf = cpf;
    }

    public User getMedResponsavel() {
        return medResponsavel;
    }

    public void setMedResponsavel(User medResponsavel) {
        this.medResponsavel = medResponsavel;
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
        if (!(object instanceof Prescricao)) {
            return false;
        }
        Prescricao other = (Prescricao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.pucrs.facin.sispresc.persistence.Prescricao[ id=" + id + " ]";
    }
    
}
