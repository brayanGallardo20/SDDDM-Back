package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Medico;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import java.util.List;

public interface MedicoService {
	
	ResponseEntity<Respuesta<List<Medico>>> listPaginated(PersonaBusquedaRequest request);

	ResponseEntity<Respuesta<List<Medico>>> listAll();

	ResponseEntity<Respuesta<Medico>> findById(Integer id);

	ResponseEntity<Respuesta<Medico>> save(Medico request);

	ResponseEntity<Respuesta<Medico>> update(Medico request);

	ResponseEntity<Respuesta<Medico>> deleteById(Medico request);	
}
