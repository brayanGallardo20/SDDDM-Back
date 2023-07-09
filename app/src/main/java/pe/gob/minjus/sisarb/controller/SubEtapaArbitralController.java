package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.SubEtapaArbitral;
import pe.gob.minjus.sisarb.model.request.SubEtapaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.SubEtapaArbitralService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/sub-etapa-arbitral")
public class SubEtapaArbitralController {

    @Autowired
    SubEtapaArbitralService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<SubEtapaArbitral>>> listPaginated(@Valid @RequestBody SubEtapaArbitralBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<SubEtapaArbitral>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Respuesta<SubEtapaArbitral>> save(@Valid @RequestBody SubEtapaArbitral request){
        return service.save(request);
    }

    @PutMapping
    public ResponseEntity<Respuesta<SubEtapaArbitral>> update(@Valid @RequestBody SubEtapaArbitral request) {
        return service.update(request);
    }

    @DeleteMapping
    public ResponseEntity<Respuesta<SubEtapaArbitral>> deleteById(@RequestBody SubEtapaArbitral request)  {
        return service.deleteById(request);
    }

}
