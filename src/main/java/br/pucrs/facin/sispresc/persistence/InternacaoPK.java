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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lcaltab
 */
@Embeddable
public class InternacaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CPF")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_INTERN")
    @Temporal(TemporalType.DATE)
    private Date dataIntern;

    public InternacaoPK() {
    }

    public InternacaoPK(String cpf, Date dataIntern) {
        this.cpf = cpf;
        this.dataIntern = dataIntern;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataIntern() {
        return dataIntern;
    }

    public void setDataIntern(Date dataIntern) {
        this.dataIntern = dataIntern;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        hash += (dataIntern != null ? dataIntern.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InternacaoPK)) {
            return false;
        }
        InternacaoPK other = (InternacaoPK) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        if ((this.dataIntern == null && other.dataIntern != null) || (this.dataIntern != null && !this.dataIntern.equals(other.dataIntern))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.pucrs.facin.sispresc.persistence.InternacaoPK[ cpf=" + cpf + ", dataIntern=" + dataIntern + " ]";
    }
    
}
