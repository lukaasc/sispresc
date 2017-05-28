/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucrs.facin.sispresc.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "prescricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescricao.findAll", query = "SELECT p FROM Prescricao p")
    , @NamedQuery(name = "Prescricao.findById", query = "SELECT p FROM Prescricao p WHERE p.id = :id")
    , @NamedQuery(name = "Prescricao.findBySituacao", query = "SELECT p FROM Prescricao p WHERE p.situacao = :situacao")
    , @NamedQuery(name = "Prescricao.findByDataCriacao", query = "SELECT p FROM Prescricao p WHERE p.dataCriacao = :dataCriacao")})
public class Prescricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 40)
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @ManyToMany(mappedBy = "prescricaoList", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Medicamento> medicamentoList;
    @JoinColumn(name = "med_responsavel", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User medResponsavel;
    @OneToOne(mappedBy = "idPrescricao", fetch = FetchType.LAZY)
    private Internacao internacao;

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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @XmlTransient
    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    public User getMedResponsavel() {
        return medResponsavel;
    }

    public void setMedResponsavel(User medResponsavel) {
        this.medResponsavel = medResponsavel;
    }

    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
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
