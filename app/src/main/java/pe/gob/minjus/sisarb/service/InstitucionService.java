package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;

import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.request.InstitucionBusquedaRequest;
import pe.gob.minjus.sisarb.model.request.InstitucionRequest;
import pe.gob.minjus.sisarb.model.response.InstitucionPersonaResponse;
import pe.gob.minjus.sisarb.model.response.InstitucionResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import java.util.List;

public interface InstitucionService {

    ResponseEntity<Respuesta<List<InstitucionResponse>>> listPaginated(InstitucionBusquedaRequest request);
    ResponseEntity<Respuesta<InstitucionPersonaResponse>> insertarInstitucion(InstitucionRequest request);
    ResponseEntity<Respuesta<InstitucionPersonaResponse>> findInstitutionById(Integer institutionId);
    ResponseEntity<Respuesta<Institucion>> actualizarInstitucion(InstitucionRequest request);
    ResponseEntity<Respuesta<Institucion>> eliminarInstitucion(Institucion request);    

}
