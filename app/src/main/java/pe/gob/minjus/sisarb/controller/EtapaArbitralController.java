package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.EtapaArbitral;
import pe.gob.minjus.sisarb.model.request.EtapaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EtapaArbitralService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/etapa-arbitral")
public class EtapaArbitralController {

    @Autowired
    EtapaArbitralService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<EtapaArbitral>>> listPaginated(@Valid @RequestBody EtapaArbitralBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<EtapaArbitral>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Respuesta<EtapaArbitral>> save(@Valid @RequestBody EtapaArbitral request) {
        return service.save(request);
    }

    @PutMapping
    public ResponseEntity<Respuesta<EtapaArbitral>> update(@Valid @RequestBody EtapaArbitral request) {
        return service.update(request);
    }

    @DeleteMapping
    public ResponseEntity<Respuesta<EtapaArbitral>> deleteById(@RequestBody EtapaArbitral request) {
        return service.deleteById(request);
    }
    @GetMapping("list-choose")
    public ResponseEntity<Respuesta<List<EtapaArbitral>>> listChoose() {
        return service.listChoose();
    }
}
