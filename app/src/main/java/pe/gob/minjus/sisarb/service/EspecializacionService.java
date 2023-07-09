package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Especializacion;
import pe.gob.minjus.sisarb.model.request.EspecializacionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import java.util.List;

public interface EspecializacionService {

    ResponseEntity<Respuesta<List<Especializacion>>> listPaginated(EspecializacionBusquedaRequest request);
    ResponseEntity<Respuesta<Especializacion>> findById(Integer id);
    ResponseEntity<Respuesta<Especializacion>> save(Especializacion request);
    ResponseEntity<Respuesta<Especializacion>> update(Especializacion request);
    ResponseEntity<Respuesta<Especializacion>> deleteById(Especializacion request);
}
