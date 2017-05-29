package br.pucrs.facin.sispresc.dto;

import br.pucrs.facin.sispresc.persistence.Medicamento;
import br.pucrs.facin.sispresc.persistence.User;
import java.util.Date;
import java.util.List;

/**
 * Created by lucas on 21/05/2017.
 */


public class PrescricaoDTO {

    private Integer id;
    private String situacao;
    private Date dataCriacao;
    private List<Medicamento> medicamentoList;
    private String medResponsavel;
    private String observacao;

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

    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    public String getMedResponsavel() {
        return medResponsavel;
    }

    public void setMedResponsavel(String medResponsavel) {
        this.medResponsavel = medResponsavel;
    }


    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}
