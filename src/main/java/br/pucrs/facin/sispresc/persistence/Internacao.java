package br.pucrs.facin.sispresc.persistence;

/**
 * Created by lucas on 21/05/2017.
 */

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Internacao {

    @EmbeddedId
    private InternacaoPK internacaoPK;
    private int leito;
    private int posto;
    private int setor;
    private String med_responsavel;

    public int getLeito() {
        return leito;
    }

    public void setLeito(int leito) {
        this.leito = leito;
    }

    public int getPosto() {
        return posto;
    }

    public void setPosto(int posto) {
        this.posto = posto;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public String getMed_responsavel() {
        return med_responsavel;
    }

    public void setMed_responsavel(String med_responsavel) {
        this.med_responsavel = med_responsavel;
    }

//
//    CPF VARCHAR(15),
//    DATA_INTERN DATE,
//    LEITO INT,
//    POSTO INT,
//    SETOR INT,
//    MED_RESPONSAVEL VARCHAR(40),
//    FOREIGN KEY(CPF) REFERENCES PACIENTE(CPF),
//    FOREIGN KEY(MED_RESPONSAVEL) REFERENCES USER(USERNAME),
//    PRIMARY KEY (CPF, DATA_INTERN)
}