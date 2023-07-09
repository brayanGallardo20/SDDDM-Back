package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.request.ParametroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ParametroService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/parametro")
@Slf4j
public class ParametroController {

    @Autowired
    ParametroService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<Parametro>>> listPaginated(@Valid @RequestBody ParametroBusquedaRequest request){
        return service.listPaginated(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<Parametro>> insertarParametro(@Valid @RequestBody  Parametro request){
        return service.insertarParametro(request);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<Parametro>> actualizarParametro(@Valid @RequestBody  Parametro request) {
        return service.actualizarParametro(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Parametro>> eliminarParametro(@RequestBody  Parametro request){
        return service.eliminarParametro(request);
    }    
    
}
