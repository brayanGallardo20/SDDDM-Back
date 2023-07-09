package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Directivo;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.request.DirectivoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface DirectivoService {
    ResponseEntity<Respuesta<List<Directivo>>> listBusqueda(DirectivoBusquedaRequest request);
    ResponseEntity<Respuesta<Directivo>> findById(Directivo request);
    ResponseEntity<Respuesta<Directivo>> insert(Directivo request);
    ResponseEntity<Respuesta<Directivo>> update(Directivo request);
    ResponseEntity<Respuesta<PersonaNatural>> deleteById(Directivo request);
}
