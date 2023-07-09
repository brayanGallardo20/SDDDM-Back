package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.service.InstitucionPersonaService;

@RestController
@RequestMapping("api/institucion-persona")
public class InstitucionPersonaController {

    @Autowired
    InstitucionPersonaService institucionPersonaService;



}
