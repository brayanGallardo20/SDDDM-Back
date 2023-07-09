package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.TipoParametro;
import pe.gob.minjus.sisarb.model.request.TipoParametroRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface TipoParametroService {

    ResponseEntity<Respuesta<List<TipoParametro>>> listarTiposParametros(TipoParametroRequest request);
    ResponseEntity<Respuesta<List<TipoParametro>>> listarTiposParametrosActivos();
    ResponseEntity<Respuesta<TipoParametro>> insertTiposParametros(TipoParametro request);
    ResponseEntity<Respuesta<TipoParametro>> updateTiposParametros(TipoParametro request);
    ResponseEntity<Respuesta<TipoParametro>> deleteTiposParametros(TipoParametro request);
}
