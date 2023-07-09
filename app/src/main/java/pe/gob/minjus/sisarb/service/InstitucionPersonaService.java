package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.request.InstitucionPersonaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

public interface InstitucionPersonaService {


    InstitucionPersona save(InstitucionPersona request);

    InstitucionPersona update(InstitucionPersona request);

    ResponseEntity<Respuesta<InstitucionPersona>> saveExtend(InstitucionPersonaRequest request);

    ResponseEntity<Respuesta<InstitucionPersona>> updateExtend(InstitucionPersonaRequest request);

    ResponseEntity<Respuesta<InstitucionPersona>> deleteExtendById(InstitucionPersonaRequest request);
}
