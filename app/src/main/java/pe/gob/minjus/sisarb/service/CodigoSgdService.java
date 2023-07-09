package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.CodigoSgd;
import pe.gob.minjus.sisarb.model.request.CodigoSgdBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface CodigoSgdService {
    ResponseEntity<Respuesta<List<CodigoSgd>>> listPaginated(CodigoSgdBusquedaRequest request);

    ResponseEntity<Respuesta<CodigoSgd>> findById(Integer id);

    ResponseEntity<Respuesta<CodigoSgd>> save(CodigoSgd request);

    ResponseEntity<Respuesta<CodigoSgd>> update(CodigoSgd request);

    ResponseEntity<Respuesta<CodigoSgd>> deleteById(CodigoSgd request);
}
