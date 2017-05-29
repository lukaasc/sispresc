package br.pucrs.facin.sispresc.resource;

import br.pucrs.facin.sispresc.dto.NovaPrescDTO;
import br.pucrs.facin.sispresc.service.PrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
