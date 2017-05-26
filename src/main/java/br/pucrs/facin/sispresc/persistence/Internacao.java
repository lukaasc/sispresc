/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucrs.facin.sispresc.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "INTERNACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Internacao.findAll", query = "SELECT i FROM Internacao i")
    , @NamedQuery(name = "Internacao.findById", query = "SELECT i FROM Internacao i WHERE i.id = :id")
    , @NamedQuery(name = "Internacao.findByDataIntern", query = "SELECT i FROM Internacao i WHERE i.dataIntern = :dataIntern")
    , @NamedQuery(name = "Internacao.findByLeito", query = "SELECT i FROM Internacao i WHERE i.leito = :leito")
    , @NamedQuery(name = "Internacao.findByPosto", query = "SELECT i FROM Internacao i WHERE i.posto = :posto")
    , @NamedQuery(name = "Internacao.findBySetor", query = "SELECT i FROM Internacao i WHERE i.setor = :setor")
    , @NamedQuery(name = "Internacao.findBySituacao", query = "SELECT i FROM Internacao i WHERE i.situacao = :situacao")})
public class Internacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATA_INTERN")
    @Temporal(TemporalType.DATE)
    private Date dataIntern;
    @Column(name = "LEITO")
    private Integer leito;
    @Column(name = "POSTO")
    private Integer posto;
    @Column(name = "SETOR")
    private Integer setor;
    @Size(max = 40)
    @Column(name = "SITUACAO")
    private String situacao;
    @JoinColumn(name = "CPF", referencedColumnName = "CPF")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente cpf;
    @JoinColumn(name = "ID_PRESCRICAO", referencedColumnName = "ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Prescricao idPrescricao;
    @JoinColumn(name = "MED_RESPONSAVEL", referencedColumnName = "USERNAME")
    @ManyToOne(fetch = FetchType.LAZY)
    private User medResponsavel;

    public Internacao() {
    }

    public Internacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataIntern() {
        return dataIntern;
    }

    public void setDataIntern(Date dataIntern) {
        this.dataIntern = dataIntern;
    }

    public Integer getLeito() {
        return leito;
    }

    public void setLeito(Integer leito) {
        this.leito = leito;
    }

    public Integer getPosto() {
        return posto;
    }

    public void setPosto(Integer posto) {
        this.posto = posto;
    }

    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor) {
        this.setor = setor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Paciente getCpf() {
        return cpf;
    }

    public void setCpf(Paciente cpf) {
        this.cpf = cpf;
    }

    public Prescricao getIdPrescricao() {
        return idPrescricao;
    }

    public void setIdPrescricao(Prescricao idPrescricao) {
        this.idPrescricao = idPrescricao;
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
        if (!(object instanceof Internacao)) {
            return false;
        }
        Internacao other = (Internacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.pucrs.facin.sispresc.persistence.Internacao[ id=" + id + " ]";
    }
    
}
