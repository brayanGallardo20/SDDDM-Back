package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.DeclaracionInteres;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface DeclaracionInteresService {
    ResponseEntity<Respuesta<List<DeclaracionInteres>>> list() ;
}
