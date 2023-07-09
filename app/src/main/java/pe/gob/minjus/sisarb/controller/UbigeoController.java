package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.rest.model.CommonUbigeo;
import pe.gob.minjus.sisarb.service.UbigeoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/ubigeo")
public class UbigeoController {
    @Autowired
    UbigeoService ubigeoService;

    @GetMapping
    public ResponseEntity<Respuesta<List<CommonUbigeo>>> getDepartamento() {
        return  ubigeoService.getUbigeo(null, null);
    }

    @GetMapping("{codigoDepartamento}")
    public ResponseEntity<Respuesta<List<CommonUbigeo>>> getProvincia (@Valid @PathVariable String codigoDepartamento){
        return  ubigeoService.getUbigeo(codigoDepartamento,null);
    }

    @GetMapping("{codigoDepartamento}/{codigoProvincia}")
    public ResponseEntity<Respuesta<List<CommonUbigeo>>> getDistrito (@Valid @PathVariable String codigoDepartamento,@Valid @PathVariable String codigoProvincia){
        return  ubigeoService.getUbigeo(codigoDepartamento,codigoProvincia);
    }
}
