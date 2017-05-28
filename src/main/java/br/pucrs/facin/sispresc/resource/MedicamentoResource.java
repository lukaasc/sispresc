package br.pucrs.facin.sispresc.resource;

import br.pucrs.facin.sispresc.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
