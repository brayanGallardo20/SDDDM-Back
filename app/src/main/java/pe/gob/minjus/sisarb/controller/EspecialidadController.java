package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.request.EspecialidadBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EspecialidadService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/especialidad")
@Slf4j
public class EspecialidadController {

    @Autowired
    EspecialidadService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<Especialidad>>> listPaginated(@Valid @RequestBody EspecialidadBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Respuesta<Especialidad>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("save")
    public ResponseEntity<Respuesta<Especialidad>> save(@Valid @RequestBody Especialidad request) {
        return service.save(request);
    }

    @PutMapping("update")
    public ResponseEntity<Respuesta<Especialidad>> update(@Valid @RequestBody Especialidad request) {
        return service.update(request);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Especialidad>> deleteById(@RequestBody Especialidad request) {
        return service.deleteById(request);
    }

    @GetMapping("list-choose")
    public ResponseEntity<Respuesta<List<Especialidad>>> listChoose() {
        return service.listChoose();
    }

}
