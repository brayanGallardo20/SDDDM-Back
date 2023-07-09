package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.request.ArbitroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface ArbitroService {

    Arbitro save (Arbitro request);

    Arbitro update(Arbitro request);
}
