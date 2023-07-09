package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.request.InstitucionBusquedaRequest;
import pe.gob.minjus.sisarb.model.request.InstitucionRequest;
import pe.gob.minjus.sisarb.model.response.InstitucionPersonaResponse;
import pe.gob.minjus.sisarb.model.response.InstitucionResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.rest.model.CommonUbigeo;
import pe.gob.minjus.sisarb.service.InstitucionService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/institution")
@Slf4j
public class InstitucionController {

    @Autowired
    InstitucionService service;

    @PostMapping("list-paginated")
    public ResponseEntity<Respuesta<List<InstitucionResponse>>> listPaginated(@Valid @RequestBody InstitucionBusquedaRequest request){
        return service.listPaginated(request);
    }

    @PostMapping("insert")
    public ResponseEntity<Respuesta<InstitucionPersonaResponse>> insertarInstitucion(@Valid @RequestBody  InstitucionRequest request){
        return service.insertarInstitucion(request);
    }
    
    @GetMapping("{institutionId}")
    public ResponseEntity<Respuesta<InstitucionPersonaResponse>> findInstitutionById (@Valid @PathVariable Integer institutionId){
        return  service.findInstitutionById(institutionId);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<Institucion>> actualizarInstitucion(@Valid @RequestBody  InstitucionRequest request) {
        return service.actualizarInstitucion(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<Institucion>> eliminarInstitucion(@RequestBody  Institucion request){
        return service.eliminarInstitucion(request);
    }    
    
}
