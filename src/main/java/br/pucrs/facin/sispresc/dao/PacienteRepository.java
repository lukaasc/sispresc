package br.pucrs.facin.sispresc.dao;

/**
 * Created by lucas on 21/05/2017.
 */
import br.pucrs.facin.sispresc.persistence.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PacienteRepository extends CrudRepository<Paciente, String> {

}