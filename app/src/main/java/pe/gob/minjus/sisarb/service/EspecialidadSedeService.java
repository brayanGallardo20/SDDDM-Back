package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.EspecialidadSede;
import pe.gob.minjus.sisarb.model.request.EspecialidadSedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.EspecialidadSedeResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface EspecialidadSedeService {

    ResponseEntity<Respuesta<List<EspecialidadSedeResponse>>> listPaginated(EspecialidadSedeBusquedaRequest request);
    ResponseEntity<Respuesta<EspecialidadSede>> insertar(EspecialidadSede request);
    ResponseEntity<Respuesta<EspecialidadSede>> actualizar(EspecialidadSede request);
    ResponseEntity<Respuesta<EspecialidadSede>> eliminar(EspecialidadSede request);    

}
