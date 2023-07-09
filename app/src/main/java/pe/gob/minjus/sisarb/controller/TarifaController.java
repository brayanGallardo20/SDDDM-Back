package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Tarifa;
import pe.gob.minjus.sisarb.model.request.TarifaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.model.response.TarifaResponse;
import pe.gob.minjus.sisarb.service.TarifaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tarifa")
@Slf4j
public class TarifaController {

    @Autowired
    TarifaService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<TarifaResponse>>> listPaginated(@Valid @RequestBody TarifaBusquedaRequest request){
        return service.listPaginated(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<Tarifa>> insertarInstitucion(@Valid @RequestBody  Tarifa request){
        return service.insertar(request);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<Tarifa>> actualizarInstitucion(@Valid @RequestBody  Tarifa request) {
        return service.actualizar(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Tarifa>> eliminarInstitucion(@RequestBody  Tarifa request){
        return service.eliminar(request);
    }    
    
}
