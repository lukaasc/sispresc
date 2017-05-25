package br.pucrs.facin.sispresc.persistence;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * Created by lucas on 25/05/2017.
 */
@Embeddable
public class InternacaoPK {

    protected String CPF;
    protected Date data_intern;

    public InternacaoPK(String CPF, Date data_intern) {
        this.CPF = CPF;
        this.data_intern = data_intern;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getData_intern() {
        return data_intern;
    }

    public void setData_intern(Date data_intern) {
        this.data_intern = data_intern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InternacaoPK that = (InternacaoPK) o;

        if (CPF != null ? !CPF.equals(that.CPF) : that.CPF != null) return false;
        return data_intern != null ? data_intern.equals(that.data_intern) : that.data_intern == null;

    }

    @Override
    public int hashCode() {
        int result = CPF != null ? CPF.hashCode() : 0;
        result = 31 * result + (data_intern != null ? data_intern.hashCode() : 0);
        return result;
    }
}
