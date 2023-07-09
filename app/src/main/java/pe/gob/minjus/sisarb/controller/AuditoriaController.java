package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.request.AuditoriaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/auditoria")
public class AuditoriaController {


    @Autowired
    AuditoriaService auditoriaService;


    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<Auditoria>>> listPaginated(@Valid @RequestBody AuditoriaBusquedaRequest request){
        return auditoriaService.listPaginated(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<Auditoria>> insert(@RequestBody Auditoria request)  {
        return auditoriaService.insert(request);
    }
}
