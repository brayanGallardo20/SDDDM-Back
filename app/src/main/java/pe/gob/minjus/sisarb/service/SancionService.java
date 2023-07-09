package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Sancion;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface SancionService {
    ResponseEntity<Respuesta<List<Sancion>>> list();
}
