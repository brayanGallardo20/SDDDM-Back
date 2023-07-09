package pe.gob.minjus.sisarb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.request.SedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.SedeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/sede")
public class SedeController {

    @Autowired
    SedeService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<Sede>>> listPaginated(@Valid @RequestBody SedeBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<Sede>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Respuesta<Sede>> save(@Valid @RequestBody Sede request) {
        return service.save(request);
    }

    @PutMapping
    public ResponseEntity<Respuesta<Sede>> update(@Valid @RequestBody Sede request) {
        return service.update(request);
    }

    @PostMapping("deleteById")
    public ResponseEntity<Respuesta<Sede>> deleteById(@RequestBody Sede request) {
        return service.deleteById(request);
    }
    @GetMapping("list-choose")
    public ResponseEntity<Respuesta<List<Sede>>> listChoose() {
        return service.listChoose();
    }
}