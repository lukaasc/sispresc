package br.pucrs.facin.sispresc.service;

import br.pucrs.facin.sispresc.dao.InternacaoRepository;
import br.pucrs.facin.sispresc.dao.PacienteRepository;
import br.pucrs.facin.sispresc.dto.InternacaoDTO;
import br.pucrs.facin.sispresc.persistence.Internacao;
import br.pucrs.facin.sispresc.persistence.Paciente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucas on 21/05/2017.
 */
@Component
public class InternacaoService {

    final static Logger logger = Logger.getLogger(InternacaoService.class);

    @Autowired
    private InternacaoRepository internacaoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public InternacaoDTO getInfo(String cpf) {
        try {
            Paciente paciente = pacienteRepository.findOne(cpf);
            if ("".equals(paciente.getCpf()) || paciente.getInternacaoList().isEmpty()) {
                throw new RuntimeException("Paciente sem internações em aberto!");
            }
            InternacaoDTO internacaoDTO = new InternacaoDTO();

            internacaoDTO.setCpf(paciente.getCpf());
            internacaoDTO.setNome(paciente.getNome());
            internacaoDTO.setData_nasc(paciente.getDataNasc());
            internacaoDTO.setSobrenome(paciente.getSobrenome());
            internacaoDTO.setSexo(paciente.getSexo());

            paciente.getInternacaoList().forEach(internacao -> {
                if ("aberta".equalsIgnoreCase(internacao.getSituacao())) {
                    internacaoDTO.setData_intern(internacao.getDataIntern());
                    internacaoDTO.setLeito(internacao.getLeito());
                    internacaoDTO.setPosto(internacao.getPosto());
                    internacaoDTO.setSetor(internacao.getSetor());
                    internacaoDTO.setStatus(internacao.getSituacao());
                }
            });

            return internacaoDTO;
        } catch (RuntimeException e) {
            logger.error("Paciente sem internações em aberto! ", e);
            return null;
        }
    }
}
