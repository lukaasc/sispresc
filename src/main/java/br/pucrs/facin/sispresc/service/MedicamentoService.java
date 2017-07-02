package br.pucrs.facin.sispresc.service;

import br.pucrs.facin.sispresc.dao.MedicamentoRepository;
import br.pucrs.facin.sispresc.dto.MedicamentoDTO;
import br.pucrs.facin.sispresc.dto.RankingDTO;
import br.pucrs.facin.sispresc.persistence.MedicamentoRanking;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucas on 21/05/2017.
 */
@Component
@Transactional
public class MedicamentoService {

    final static Logger logger = Logger.getLogger(MedicamentoService.class);

    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @PersistenceContext
    private EntityManager em;

    public List<MedicamentoDTO> getList() {
        try {
            return (List) medicamentoRepository.findAll();
        } catch (Exception e) {
            logger.error("Não foi possível encontrar medicamentos! ", e);
            return null;
        }
    }

    public List<RankingDTO> getRanking() {
        try {

            Query q = em.createNativeQuery(
                    "select id, nome, count(1) as count from medicamento_prescricao inner join medicamento on id_medicamento = id group by id_medicamento",
                    MedicamentoRanking.class);
            
            List<MedicamentoRanking> medRank = q.getResultList();
            List<RankingDTO> response = new ArrayList<>();
 
            medRank.forEach(item-> {
                RankingDTO d = new RankingDTO();
                d.setCount(item.getCount());
                d.setNome(item.getNome());
                
                response.add(d);
            });
            
            return response;
        } catch (Exception e) {
            logger.error("Não foi possível recuperar raking de medicamentos! ", e);
            return null;
        }
    }
}
