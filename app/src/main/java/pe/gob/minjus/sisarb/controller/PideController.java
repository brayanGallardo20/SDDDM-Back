package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.pide.response.PidePersonaJuridica;
import pe.gob.minjus.sisarb.model.pide.response.PidePersonaNatural;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.PideService;
import pe.gob.minjus.sisarb.utils.Constantes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("api/pide")
public class PideController {

    @Autowired
    PideService pideService;



    @GetMapping("get-data-persona-natural-by-dni/{dni}")
    public ResponseEntity<Respuesta<PidePersonaNatural>> getDataPersonaNaturalByDni(@Pattern(regexp = "\\d{8}") @PathVariable(value="dni",required = true) String dni) {
        return pideService.getDataPersonaNaturalByDni(dni);
    }

    @GetMapping("get-data-persona-juridica-by-ruc/{ruc}")
    public ResponseEntity<Respuesta<PidePersonaJuridica>> getDataPersonaJuridicaByRuc(@NotBlank @PathVariable(value="ruc",required = true) String ruc) {
        return pideService.getDataPersonaJuridicaByRuc(ruc);
    }




    @GetMapping("get-data-persona-natural-by-dni/")
    public ResponseEntity<Respuesta<PidePersonaNatural>> validDNI() {
        throw new EntityValidationCustom(Constantes.MSJ_CAMPO_REQUERIDO+": DNI" );
    }
    @GetMapping("get-data-persona-juridica-by-ruc/")
    public ResponseEntity<Respuesta<PidePersonaJuridica>> validRuc() {
        throw new EntityValidationCustom(Constantes.MSJ_CAMPO_REQUERIDO+": RUC" );
    }

}
