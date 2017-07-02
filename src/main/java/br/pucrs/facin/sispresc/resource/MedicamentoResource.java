package br.pucrs.facin.sispresc.resource;

import br.pucrs.facin.sispresc.dto.RankingDTO;
import br.pucrs.facin.sispresc.service.MedicamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucas on 21/05/2017.
 */
@RestController
@RequestMapping("/api/medicamento")
public class MedicamentoResource {

    @Autowired
    private MedicamentoService medicamentoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getList() {
        return ResponseEntity.ok(medicamentoService.getList());
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public ResponseEntity getRanking() {
        List<RankingDTO> response = medicamentoService.getRanking();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(response);
    }
}
