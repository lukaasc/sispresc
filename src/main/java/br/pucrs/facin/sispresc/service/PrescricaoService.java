package br.pucrs.facin.sispresc.service;

import br.pucrs.facin.sispresc.dao.InternacaoRepository;
import br.pucrs.facin.sispresc.dao.PacienteRepository;
import br.pucrs.facin.sispresc.dao.PrescricaoRepository;
import br.pucrs.facin.sispresc.dto.NovaPrescDTO;
import br.pucrs.facin.sispresc.dto.PrescricaoDTO;
import br.pucrs.facin.sispresc.persistence.Internacao;
import br.pucrs.facin.sispresc.persistence.Paciente;
import br.pucrs.facin.sispresc.persistence.Prescricao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

            Internacao internacao = paciente.getInternacaoList().stream()
                    .filter(x -> "aberta".equalsIgnoreCase(x.getSituacao()))
                    .findAny()
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

    public List<PrescricaoDTO> getHistorico(String cpf) {
        try {
            logger.debug("Recuperando historico de prescrições");

            Paciente paciente = pacienteRepository.findOne(cpf);
            if ("".equals(paciente.getCpf()) || paciente.getInternacaoList().isEmpty()) {
                throw new RuntimeException("Paciente sem internações!");
            }

            List<PrescricaoDTO> response = new ArrayList();
            PrescricaoDTO presc = new PrescricaoDTO();;

            paciente.getInternacaoList().forEach(internacao -> {
                if (internacao.getIdPrescricao() != null) {
                    presc.setDataCriacao(internacao.getIdPrescricao().getDataCriacao());
                    presc.setId(internacao.getIdPrescricao().getId());
                    presc.setMedResponsavel(internacao.getIdPrescricao().getMedResponsavel().getName() + " " + internacao.getIdPrescricao().getMedResponsavel().getLastname());
                    presc.setMedicamentoList(internacao.getIdPrescricao().getMedicamentoList());
                    presc.setObservacao(internacao.getIdPrescricao().getObservacao());
                    presc.setSituacao(internacao.getIdPrescricao().getSituacao());

                    response.add(presc);
                }
            });

            return response;
        } catch (Exception e) {
            logger.error("Não foi possível recuperar a lista de prescrições!", e);
            return null;
        }
    }
}