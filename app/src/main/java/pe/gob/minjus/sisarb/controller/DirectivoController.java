package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.Directivo;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.request.DirectivoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.DirectivoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/directivo")
public class DirectivoController {

    @Autowired
    DirectivoService service;

    @PostMapping("list-busqueda")
    public ResponseEntity<Respuesta<List<Directivo>>> listBusqueda(@Valid @RequestBody DirectivoBusquedaRequest request){
        return service.listBusqueda(request);
    }

    @PostMapping("findById")
    public ResponseEntity<Respuesta<Directivo>> findById(@RequestBody Directivo request)  {
        return service.findById(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<Directivo>> insert(@Valid @RequestBody Directivo request)  {
            return service.insert(request);
    }

    @PostMapping("update")
    public ResponseEntity<Respuesta<Directivo>> update(@Valid @RequestBody Directivo request)  {
        return service.update(request);
    }

    @PostMapping("delete")
    public ResponseEntity<Respuesta<PersonaNatural>> delete( @RequestBody Directivo request)  {
        return service.deleteById(request);
    }
}
