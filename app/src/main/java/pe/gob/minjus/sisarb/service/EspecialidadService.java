package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.request.EspecialidadBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface EspecialidadService {

    ResponseEntity<Respuesta<List<Especialidad>>> listPaginated(EspecialidadBusquedaRequest request);
    ResponseEntity<Respuesta<Especialidad>> findById(Integer id);
    ResponseEntity<Respuesta<Especialidad>> save(Especialidad request);
    ResponseEntity<Respuesta<Especialidad>> update(Especialidad request);
    ResponseEntity<Respuesta<Especialidad>> deleteById(Especialidad request);
    ResponseEntity<Respuesta<List<Especialidad>>> listChoose();
}
