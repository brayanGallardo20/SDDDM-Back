package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.gob.minjus.sisarb.model.domain.Arancel;
import pe.gob.minjus.sisarb.model.request.ArancelBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.ArancelResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ArancelService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/arancel")
@Slf4j
public class ArancelController {

    @Autowired
    ArancelService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<ArancelResponse>>> listPaginated(@Valid @RequestBody ArancelBusquedaRequest request){
        return service.listPaginated(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<Arancel>> insertarInstitucion(@Valid @RequestBody  Arancel request){
        return service.insertarArancel(request);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<Arancel>> actualizarInstitucion(@Valid @RequestBody  Arancel request) {
        return service.actualizarArancel(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Arancel>> eliminarInstitucion(@RequestBody  Arancel request){
        return service.eliminarArancel(request);
    }    
    
}
