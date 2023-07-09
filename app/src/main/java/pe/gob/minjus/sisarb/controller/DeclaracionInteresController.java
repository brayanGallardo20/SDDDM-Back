package pe.gob.minjus.sisarb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.DeclaracionInteres;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.DeclaracionInteresService;

import java.util.List;

@RestController
@RequestMapping("api/declaracion-interes")
public class DeclaracionInteresController {

    @Autowired
    DeclaracionInteresService service;

    @GetMapping("list")
    public ResponseEntity<Respuesta<List<DeclaracionInteres>>> list()  {
        return service.list();
    }
}
