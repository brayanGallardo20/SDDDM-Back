package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.SubEtapaArbitral;
import pe.gob.minjus.sisarb.model.request.SubEtapaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface SubEtapaArbitralService {
    ResponseEntity<Respuesta<List<SubEtapaArbitral>>> listPaginated(SubEtapaArbitralBusquedaRequest request);

    ResponseEntity<Respuesta<SubEtapaArbitral>> findById(Integer id);

    ResponseEntity<Respuesta<SubEtapaArbitral>> save(SubEtapaArbitral request);

    ResponseEntity<Respuesta<SubEtapaArbitral>> update(SubEtapaArbitral request);

    ResponseEntity<Respuesta<SubEtapaArbitral>> deleteById(SubEtapaArbitral request);
}
