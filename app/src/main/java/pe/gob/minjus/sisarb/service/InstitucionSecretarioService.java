package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.InstitucionSecretario;
import pe.gob.minjus.sisarb.model.response.Respuesta;

public interface InstitucionSecretarioService {

    //Registrar un secretario con su institucion
    ResponseEntity<Respuesta<InstitucionSecretario>> saveInstitucionSecretario(InstitucionSecretario request);
}
