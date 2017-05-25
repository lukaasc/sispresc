/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucrs.facin.sispresc.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lcaltab
 */
@Entity
@Table(name = "INTERNACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Internacao.findAll", query = "SELECT i FROM Internacao i")
    , @NamedQuery(name = "Internacao.findByCpf", query = "SELECT i FROM Internacao i WHERE i.internacaoPK.cpf = :cpf")
    , @NamedQuery(name = "Internacao.findByDataIntern", query = "SELECT i FROM Internacao i WHERE i.internacaoPK.dataIntern = :dataIntern")
    , @NamedQuery(name = "Internacao.findByLeito", query = "SELECT i FROM Internacao i WHERE i.leito = :leito")
    , @NamedQuery(name = "Internacao.findByPosto", query = "SELECT i FROM Internacao i WHERE i.posto = :posto")
    , @NamedQuery(name = "Internacao.findBySetor", query = "SELECT i FROM Internacao i WHERE i.setor = :setor")})
public class Internacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InternacaoPK internacaoPK;
    @Column(name = "LEITO")
    private Integer leito;
    @Column(name = "POSTO")
    private Integer posto;
    @Column(name = "SETOR")
    private Integer setor;
    @JoinColumn(name = "CPF", referencedColumnName = "CPF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paciente paciente;
    @JoinColumn(name = "MED_RESPONSAVEL", referencedColumnName = "USERNAME")
    @ManyToOne
    private User medResponsavel;

    public Internacao() {
    }

    public Internacao(InternacaoPK internacaoPK) {
        this.internacaoPK = internacaoPK;
    }

    public Internacao(String cpf, Date dataIntern) {
        this.internacaoPK = new InternacaoPK(cpf, dataIntern);
    }

    public InternacaoPK getInternacaoPK() {
        return internacaoPK;
    }

    public void setInternacaoPK(InternacaoPK internacaoPK) {
        this.internacaoPK = internacaoPK;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        hash += (internacaoPK != null ? internacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Internacao)) {
            return false;
        }
        Internacao other = (Internacao) object;
        if ((this.internacaoPK == null && other.internacaoPK != null) || (this.internacaoPK != null && !this.internacaoPK.equals(other.internacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.pucrs.facin.sispresc.persistence.Internacao[ internacaoPK=" + internacaoPK + " ]";
    }
    
}
