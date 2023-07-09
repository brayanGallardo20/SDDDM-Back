package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.request.AuditoriaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;

import java.util.List;

public interface AuditoriaService {

    ResponseEntity<Respuesta<List<Auditoria>>> listPaginated(AuditoriaBusquedaRequest request);

    ResponseEntity<Respuesta<Auditoria>> insert(Auditoria request);

    int insertNoTrans(Auditoria request);

}
