package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Configuracion;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ConfiguracionService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/configuracion")
public class ConfiguracionController {
    @Autowired
    ConfiguracionService service;

    @GetMapping("/get-config-default")
    public ResponseEntity<Respuesta<Configuracion>> getConfigDefault() {
        return service.getConfigDefault();
    }

    @PutMapping
    public ResponseEntity<Respuesta<Configuracion>> update(@Valid @RequestBody Configuracion request) {
        return service.update(request);
    }



}
