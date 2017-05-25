package br.pucrs.facin.sispresc.persistence;

/**
 * Created by lucas on 21/05/2017.
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Paciente {

    @Id
    private String cpf;
    private String nome;
    private Date data_nasc;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }
}