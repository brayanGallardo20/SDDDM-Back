package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Configuracion;
import pe.gob.minjus.sisarb.model.response.Respuesta;

public interface ConfiguracionService {
    ResponseEntity<Respuesta<Configuracion>> getConfigDefault();
    ResponseEntity<Respuesta<Configuracion>> update(Configuracion request);

    String getWebServiceSeguridad(String configuracionId);

    String getWebServicePide(String configuracionId);
}
