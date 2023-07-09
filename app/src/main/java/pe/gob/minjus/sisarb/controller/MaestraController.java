package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Maestra;
import pe.gob.minjus.sisarb.model.request.MaestraBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.MaestraService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/maestra")
public class MaestraController {

    @Autowired
    MaestraService maestraService;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<Maestra>>> listPaginated(@Valid @RequestBody MaestraBusquedaRequest request) {
        return maestraService.listPaginated(request);
    }

    @PostMapping("findById")
    public ResponseEntity<Respuesta<Maestra>> findById(@RequestBody Maestra request) {
        return maestraService.findById(request);
    }

    @PostMapping("save")
    public ResponseEntity<Respuesta<Maestra>> save(@Valid @RequestBody Maestra request) {
        return maestraService.save(request);
    }

    @PutMapping("update")
    public ResponseEntity<Respuesta<Maestra>> update(@Valid @RequestBody Maestra request)   {
        return maestraService.update(request);
    }

    @DeleteMapping("deleteById")
    public ResponseEntity<Respuesta<Maestra>> deleteById(@RequestBody Maestra request) {
        return maestraService.deleteById(request);
    }

    @PostMapping("list-choose")
    public ResponseEntity<Respuesta<List<Maestra>>> listChoose(@RequestBody Maestra request) {
        return maestraService.listChoose(request);
    }

}
