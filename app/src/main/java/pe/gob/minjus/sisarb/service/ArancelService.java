package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;

import pe.gob.minjus.sisarb.model.domain.Arancel;
import pe.gob.minjus.sisarb.model.request.ArancelBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.ArancelResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import java.util.List;

public interface ArancelService {

    ResponseEntity<Respuesta<List<ArancelResponse>>> listPaginated(ArancelBusquedaRequest request);
    ResponseEntity<Respuesta<Arancel>> insertarArancel(Arancel request);
    ResponseEntity<Respuesta<Arancel>> actualizarArancel(Arancel request);
    ResponseEntity<Respuesta<Arancel>> eliminarArancel(Arancel request);    

}
