package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Maestra;
import pe.gob.minjus.sisarb.model.request.MaestraBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface MaestraService {

    ResponseEntity<Respuesta<List<Maestra>>> listPaginated(MaestraBusquedaRequest request);

    ResponseEntity<Respuesta<Maestra>> findById(Maestra request);

    ResponseEntity<Respuesta<Maestra>> save(Maestra request);

    ResponseEntity<Respuesta<Maestra>> update(Maestra request);

    ResponseEntity<Respuesta<Maestra>> deleteById(Maestra request);

    ResponseEntity<Respuesta<List<Maestra>>> listChoose(Maestra request);

}
