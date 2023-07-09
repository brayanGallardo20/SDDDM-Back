package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.TipoDocumento;
import pe.gob.minjus.sisarb.model.request.TipoDocumentoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface TIpoDocumentoService {
    ResponseEntity<Respuesta<List<TipoDocumento>>> listPaginated(TipoDocumentoBusquedaRequest request);

    ResponseEntity<Respuesta<List<TipoDocumento>>> listAll();

    ResponseEntity<Respuesta<TipoDocumento>> findById(Integer id);

    ResponseEntity<Respuesta<TipoDocumento>> save(TipoDocumento request);

    ResponseEntity<Respuesta<TipoDocumento>> update(TipoDocumento request);

    ResponseEntity<Respuesta<TipoDocumento>> deleteById(TipoDocumento request);
    ResponseEntity<Respuesta<List<TipoDocumento>>> listChoose() ;
}
