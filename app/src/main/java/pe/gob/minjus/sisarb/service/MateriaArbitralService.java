package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.MateriaArbitral;
import pe.gob.minjus.sisarb.model.request.MateriaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface MateriaArbitralService {

    ResponseEntity<Respuesta<List<MateriaArbitral>>> listPaginated(MateriaArbitralBusquedaRequest request);
    ResponseEntity<Respuesta<MateriaArbitral>> findById(Integer id);
    ResponseEntity<Respuesta<MateriaArbitral>> save(MateriaArbitral request);
    ResponseEntity<Respuesta<MateriaArbitral>> update(MateriaArbitral request);
    ResponseEntity<Respuesta<MateriaArbitral>> deleteById(MateriaArbitral request);
    ResponseEntity<Respuesta<List<MateriaArbitral>>> listChoose();
}
