package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Tarifa;
import pe.gob.minjus.sisarb.model.request.TarifaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.model.response.TarifaResponse;

import java.util.List;

public interface TarifaService {

    ResponseEntity<Respuesta<List<TarifaResponse>>> listPaginated(TarifaBusquedaRequest request);
    ResponseEntity<Respuesta<Tarifa>> insertar(Tarifa request);
    ResponseEntity<Respuesta<Tarifa>> actualizar(Tarifa request);
    ResponseEntity<Respuesta<Tarifa>> eliminar(Tarifa request);    

}
