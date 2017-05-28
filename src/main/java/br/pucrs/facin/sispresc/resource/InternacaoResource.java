package br.pucrs.facin.sispresc.resource;

import br.pucrs.facin.sispresc.dto.InternacaoDTO;
import br.pucrs.facin.sispresc.service.InternacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucas on 21/05/2017.
 */
@RestController
@RequestMapping("/api/internacao")
public class InternacaoResource {

    @Autowired
    private InternacaoService internacaoService;

    @RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
    public ResponseEntity getInfo(@PathVariable("cpf") String cpf) {
        InternacaoDTO internacaoDTO = internacaoService.getInfo(cpf);
        if ("".equals(internacaoDTO.getCpf())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(internacaoDTO);
    }
}
