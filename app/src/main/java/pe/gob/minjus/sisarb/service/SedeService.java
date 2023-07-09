package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.request.SedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface SedeService {
    ResponseEntity<Respuesta<List<Sede>>> listPaginated(SedeBusquedaRequest request);
    ResponseEntity<Respuesta<Sede>> findById(Integer id);
    ResponseEntity<Respuesta<Sede>> save(Sede request);
    ResponseEntity<Respuesta<Sede>> update(Sede request);
    ResponseEntity<Respuesta<Sede>> deleteById(Sede request);
    ResponseEntity<Respuesta<List<Sede>>> listChoose();
}
