package br.pucrs.facin.sispresc.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lucas on 21/05/2017.
 */
@Entity
public class MedicamentoRanking {

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int count;
    private String nome;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
