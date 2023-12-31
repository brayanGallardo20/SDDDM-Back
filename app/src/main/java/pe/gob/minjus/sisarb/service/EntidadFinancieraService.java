package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.EntidadFinanciera;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface EntidadFinancieraService {

    ResponseEntity<Respuesta<List<EntidadFinanciera>>> listChoose();
}
