package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.model.domain.Tabla;
import pe.gob.minjus.sisarb.service.TablaService;

import java.util.List;


@RestController
@RequestMapping("api/tabla")
public class TablaController {

    @Autowired
    TablaService tablaService;

    @GetMapping
    public ResponseEntity<Respuesta<List<Tabla>>> listarTabla(){
        return tablaService.listTabla();
    }

}
