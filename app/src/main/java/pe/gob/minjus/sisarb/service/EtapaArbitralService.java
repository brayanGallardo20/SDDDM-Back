package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.EtapaArbitral;
import pe.gob.minjus.sisarb.model.request.EtapaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface EtapaArbitralService {

    ResponseEntity<Respuesta<List<EtapaArbitral>>> listPaginated(EtapaArbitralBusquedaRequest request);
    ResponseEntity<Respuesta<EtapaArbitral>> findById(Integer id);
    ResponseEntity<Respuesta<EtapaArbitral>> save(EtapaArbitral request);
    ResponseEntity<Respuesta<EtapaArbitral>> update(EtapaArbitral request);
    ResponseEntity<Respuesta<EtapaArbitral>> deleteById(EtapaArbitral request);
    ResponseEntity<Respuesta<List<EtapaArbitral>>> listChoose();
}
