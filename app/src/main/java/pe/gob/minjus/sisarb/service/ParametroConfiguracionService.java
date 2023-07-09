package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.ParametroConfiguracion;
import pe.gob.minjus.sisarb.model.request.ParametroConfiguracionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface ParametroConfiguracionService {

    ResponseEntity<Respuesta<List<ParametroConfiguracion>>> listPaginated(ParametroConfiguracionBusquedaRequest request);
    ResponseEntity<Respuesta<ParametroConfiguracion>> findById(ParametroConfiguracion request);
    ResponseEntity<Respuesta<ParametroConfiguracion>> update(ParametroConfiguracion request);
}
