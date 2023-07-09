package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.TipoDocumento;
import pe.gob.minjus.sisarb.model.request.TipoDocumentoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.TIpoDocumentoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tipo-documento")
public class TipoDocumentoController {

    @Autowired
    TIpoDocumentoService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listPaginated(@Valid @RequestBody TipoDocumentoBusquedaRequest request){
        return service.listPaginated(request);
    }

    @GetMapping("listAll")
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listAll()   {
        return service.listAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<TipoDocumento>> findById(@PathVariable Integer id)   {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Respuesta<TipoDocumento>> save(@Valid @RequestBody TipoDocumento request)   {
        return service.save(request);
    }

    @PutMapping
    public ResponseEntity<Respuesta<TipoDocumento>> update(@Valid @RequestBody TipoDocumento request)   {
        return service.update(request);
    }

    @DeleteMapping
    public ResponseEntity<Respuesta<TipoDocumento>> deleteById(@RequestBody TipoDocumento request)  {
        return service.deleteById(request);
    }
    @GetMapping("list-choose")
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listChoose()  {
        return service.listChoose();
    }
}
