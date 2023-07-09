package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.request.ArbitroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ArbitroService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/arbitro")
public class ArbitroController {
 /*   @Autowired
    ArbitroService service;

    @PostMapping("list-busqueda")
    public ResponseEntity<Respuesta<List<Arbitro>>> listBusqueda(@Valid @RequestBody ArbitroBusquedaRequest request){
        return service.listBusqueda(request);
    }

    @PostMapping("findById")
    public ResponseEntity<Respuesta<Arbitro>> findById(@RequestBody Arbitro request)  {
        return service.findById(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<Arbitro>> insert(@Valid @RequestBody Arbitro request)  {
        return service.insert(request);
    }

    @PostMapping("update")
    public ResponseEntity<Respuesta<Arbitro>> update(@Valid @RequestBody Arbitro request)  {
        return service.update(request);
    }

    @PostMapping("deleteById")
    public ResponseEntity<Respuesta<Arbitro>> deleteById( @RequestBody Arbitro request)  {
        return service.deleteById(request);
    }*/
    
}
