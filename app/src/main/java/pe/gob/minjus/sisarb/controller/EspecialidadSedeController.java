package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.EspecialidadSede;
import pe.gob.minjus.sisarb.model.request.EspecialidadSedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.EspecialidadSedeResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EspecialidadSedeService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/especialidadSede")
public class EspecialidadSedeController {

    @Autowired
    EspecialidadSedeService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<EspecialidadSedeResponse>>> listPaginated(EspecialidadSedeBusquedaRequest request)  {
        return service.listPaginated(request);
    }
    

    @PostMapping("insert")
    public ResponseEntity<Respuesta<EspecialidadSede>> insertar(@Valid @RequestBody  EspecialidadSede request){
        return service.insertar(request);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<EspecialidadSede>> actualizar(@Valid @RequestBody  EspecialidadSede request) {
        return service.actualizar(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<EspecialidadSede>> eliminar(@RequestBody  EspecialidadSede request){
        return service.eliminar(request);
    }    
    
}
