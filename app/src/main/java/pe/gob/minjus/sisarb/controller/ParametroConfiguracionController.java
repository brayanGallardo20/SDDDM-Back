package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.ParametroConfiguracion;
import pe.gob.minjus.sisarb.model.request.ParametroConfiguracionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ParametroConfiguracionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/parametro-configuracion")
public class ParametroConfiguracionController {

    @Autowired
    ParametroConfiguracionService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<ParametroConfiguracion>>> listPaginated(@Valid @RequestBody ParametroConfiguracionBusquedaRequest request){
        return service.listPaginated(request);
    }

    @PostMapping("/find-by-id")
    public ResponseEntity<Respuesta<ParametroConfiguracion>> findById(@RequestBody ParametroConfiguracion request) {
        return service.findById(request);
    }

    @PutMapping
    public ResponseEntity<Respuesta<ParametroConfiguracion>> update(@Valid @RequestBody ParametroConfiguracion request) {
        return service.update(request);
    }
       
}
