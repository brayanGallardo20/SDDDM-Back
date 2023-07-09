package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Especializacion;
import pe.gob.minjus.sisarb.model.request.EspecializacionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EspecializacionService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/especializacion")
@Slf4j
public class EspecializacionController {

    @Autowired
    EspecializacionService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<Especializacion>>> listPaginated(@Valid @RequestBody EspecializacionBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Respuesta<Especializacion>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("save")
    public ResponseEntity<Respuesta<Especializacion>> save(@Valid @RequestBody Especializacion request) {
        return service.save(request);
    }

    @PutMapping("update")
    public ResponseEntity<Respuesta<Especializacion>> update(@Valid @RequestBody Especializacion request) {
        return service.update(request);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Especializacion>> deleteById(@RequestBody Especializacion request) {
        return service.deleteById(request);
    }

}
