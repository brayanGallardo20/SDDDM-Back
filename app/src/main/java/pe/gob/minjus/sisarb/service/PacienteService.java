package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Paciente;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import java.util.List;

public interface PacienteService {
	
	ResponseEntity<Respuesta<List<Paciente>>> listPaginated(PersonaBusquedaRequest request);

	ResponseEntity<Respuesta<List<Paciente>>> listAll();

	ResponseEntity<Respuesta<Paciente>> findById(Integer id);

	ResponseEntity<Respuesta<Paciente>> save(Paciente request);

	ResponseEntity<Respuesta<Paciente>> update(Paciente request);

	ResponseEntity<Respuesta<Paciente>> deleteById(Paciente request);	
}
