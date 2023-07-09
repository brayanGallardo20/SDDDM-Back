package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.MateriaArbitral;
import pe.gob.minjus.sisarb.model.request.MateriaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.MateriaArbitralService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/material-arbitral")
@Slf4j
public class MateriaArbitralController {

    @Autowired
    MateriaArbitralService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<MateriaArbitral>>> listPaginated(@Valid @RequestBody MateriaArbitralBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Respuesta<MateriaArbitral>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("save")
    public ResponseEntity<Respuesta<MateriaArbitral>> save(@Valid @RequestBody MateriaArbitral request) {
        return service.save(request);
    }

    @PutMapping("update")
    public ResponseEntity<Respuesta<MateriaArbitral>> update(@Valid @RequestBody MateriaArbitral request) {
        return service.update(request);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<MateriaArbitral>> deleteById(@RequestBody MateriaArbitral request) {
        return service.deleteById(request);
    }

    @GetMapping("list-choose")
    public ResponseEntity<Respuesta<List<MateriaArbitral>>> listChoose() {
        return service.listChoose();
    }
}
