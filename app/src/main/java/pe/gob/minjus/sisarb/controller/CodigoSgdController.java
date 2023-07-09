package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.CodigoSgd;
import pe.gob.minjus.sisarb.model.request.CodigoSgdBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.CodigoSgdService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/codigo-sgd")
public class CodigoSgdController {

    @Autowired
    CodigoSgdService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<CodigoSgd>>> listPaginated(@Valid @RequestBody CodigoSgdBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<CodigoSgd>> findById(@PathVariable Integer id)  {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Respuesta<CodigoSgd>> save(@Valid @RequestBody CodigoSgd request)  {
        return service.save(request);
    }

    @PutMapping
    public ResponseEntity<Respuesta<CodigoSgd>> update(@Valid @RequestBody CodigoSgd request)  {
        return service.update(request);
    }

    @DeleteMapping
    public ResponseEntity<Respuesta<CodigoSgd>> deleteById(@RequestBody CodigoSgd request)  {
        return service.deleteById(request);
    }

}
