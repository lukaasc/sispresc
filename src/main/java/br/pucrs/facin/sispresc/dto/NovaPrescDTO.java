package br.pucrs.facin.sispresc.dto;

import br.pucrs.facin.sispresc.persistence.Medicamento;
import java.util.List;

/**
 * Created by lucas on 21/05/2017.
 */


public class NovaPrescDTO {

    private String cpf = "";
    private List<Medicamento> selectedMedicamento;
    private String observacao = "";

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Medicamento> getSelectedMedicamento() {
        return selectedMedicamento;
    }

    public void setSelectedMedicamento(List<Medicamento> selectedMedicamento) {
        this.selectedMedicamento = selectedMedicamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    


    
    

}
