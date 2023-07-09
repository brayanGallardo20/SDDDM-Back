package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.EntidadFinanciera;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EntidadFinancieraService;

import java.util.List;

@RestController
@RequestMapping("api/entidad-financiera")
public class EntidadFinancieraController {
    
    @Autowired
    EntidadFinancieraService service;

    @GetMapping("list-choose")
    public ResponseEntity<Respuesta<List<EntidadFinanciera>>> listChoose() {
        return service.listChoose();
    }
}
