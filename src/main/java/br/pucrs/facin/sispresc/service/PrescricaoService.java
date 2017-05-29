package br.pucrs.facin.sispresc.service;

import br.pucrs.facin.sispresc.dao.InternacaoRepository;
import br.pucrs.facin.sispresc.dao.PacienteRepository;
import br.pucrs.facin.sispresc.dao.PrescricaoRepository;
import br.pucrs.facin.sispresc.dto.NovaPrescDTO;
import br.pucrs.facin.sispresc.persistence.Internacao;
import br.pucrs.facin.sispresc.persistence.Paciente;
import br.pucrs.facin.sispresc.persistence.Prescricao;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucas on 21/05/2017.
 */
@Component
@Transactional
public class PrescricaoService {

    final static Logger logger = Logger.getLogger(PrescricaoService.class);

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private InternacaoRepository internacaoRepository;
    @Autowired
    private PrescricaoRepository prescricaoRepository;
    
    @PersistenceContext
    private EntityManager em;

    public boolean criarPresc(NovaPrescDTO prescDTO) {
        try {
            logger.debug("Criando nova prescrição");
            Paciente paciente = pacienteRepository.findOne(prescDTO.getCpf());
            if ("".equals(paciente.getCpf()) || paciente.getInternacaoList().isEmpty()) {
                throw new RuntimeException("Paciente sem internações em aberto!");
            }

            Internacao internacao = paciente.getInternacaoList().stream() // Convert to steam
                    .filter(x -> "aberta".equalsIgnoreCase(x.getSituacao())) // we want "jack" only
                    .findAny() // If 'findAny' then return found
                    .orElse(null);
            
            Prescricao presc = new Prescricao();
            
            presc.setDataCriacao(new Date());
            presc.setInternacao(internacao);
            presc.setMedResponsavel(internacao.getMedResponsavel());
            presc.setMedicamentoList(prescDTO.getSelectedMedicamento());
            presc.setObservacao(prescDTO.getObservacao());
            presc.setSituacao("Ativa");
            
            em.persist(presc);
            internacao.setIdPrescricao(presc);
            internacaoRepository.save(internacao);
         
            return true;
        } catch (RuntimeException e) {
            logger.error("Paciente sem internações em aberto! ", e);
            return false;
        }
    }
}
