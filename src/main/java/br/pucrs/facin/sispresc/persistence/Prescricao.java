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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "Prescricao.findById", query = "SELECT p FROM Prescricao p WHERE p.id = :id")
    , @NamedQuery(name = "Prescricao.findByUsername", query = "SELECT p FROM Prescricao p WHERE p.username = :username")
    , @NamedQuery(name = "Prescricao.findByIdMedicamento", query = "SELECT p FROM Prescricao p WHERE p.idMedicamento = :idMedicamento")})
public class Prescricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "ID_MEDICAMENTO")
    private Integer idMedicamento;
    @ManyToMany(mappedBy = "prescricaoList", fetch = FetchType.LAZY)
    private List<Medicamento> medicamentoList;
    @JoinColumn(name = "CPF", referencedColumnName = "CPF")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente cpf;
    @JoinColumn(name = "MED_RESPONSAVEL", referencedColumnName = "USERNAME")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
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
