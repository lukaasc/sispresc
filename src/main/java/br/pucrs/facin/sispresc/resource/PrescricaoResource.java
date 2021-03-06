package br.pucrs.facin.sispresc.resource;

import br.pucrs.facin.sispresc.dto.NovaPrescDTO;
import br.pucrs.facin.sispresc.dto.PrescricaoDTO;
import br.pucrs.facin.sispresc.service.PrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lucas on 21/05/2017.
 */
@RestController
@RequestMapping("/api/prescricao")
public class PrescricaoResource {

    @Autowired
    private PrescricaoService prescricaoService;

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    public ResponseEntity criarPresc(@RequestBody NovaPrescDTO prescDTO) {
        if ("".equals(prescDTO.getCpf()) || "".equals(prescDTO.getObservacao()) || prescDTO.getSelectedMedicamento().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        boolean response = prescricaoService.criarPresc(prescDTO);
        if (!response) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @RequestMapping(value = "/historicoPaciente/{cpf}", method = RequestMethod.GET)
    public ResponseEntity getHistorico(@PathVariable("cpf") String cpf) {
        List<PrescricaoDTO> response = prescricaoService.getHistorico(cpf);
        if (response == null || response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/enviadasFarmacia", method = RequestMethod.GET)
    public ResponseEntity getEnviadasFarmacia() {
        List<PrescricaoDTO> response = prescricaoService.getEnviadasFarmacia();
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (response.isEmpty()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/despachar", method = RequestMethod.POST)
    public ResponseEntity despacharPrescricao(@RequestBody PrescricaoDTO presc) {
        boolean response = prescricaoService.despacharPrescricao(presc);
        if (!response) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
