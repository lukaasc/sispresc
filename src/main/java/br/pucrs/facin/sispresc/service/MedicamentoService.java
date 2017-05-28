package br.pucrs.facin.sispresc.service;

import br.pucrs.facin.sispresc.dao.MedicamentoRepository;
import br.pucrs.facin.sispresc.dto.MedicamentoDTO;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucas on 21/05/2017.
 */
@Component
public class MedicamentoService {

    final static Logger logger = Logger.getLogger(MedicamentoService.class);

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<MedicamentoDTO> getList() {
        try {
            return (List) medicamentoRepository.findAll();
        } catch (Exception e) {
            logger.error("Não foi possível encontrar medicamentos! ", e);
            return null;
        }
    }
}
