package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.request.ParametroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import java.util.List;

public interface ParametroService {

    ResponseEntity<Respuesta<List<Parametro>>> listPaginated(ParametroBusquedaRequest request);
    ResponseEntity<Respuesta<Parametro>> insertarParametro(Parametro request);
    ResponseEntity<Respuesta<Parametro>> actualizarParametro(Parametro request);
    ResponseEntity<Respuesta<Parametro>> eliminarParametro(Parametro request);    

}
