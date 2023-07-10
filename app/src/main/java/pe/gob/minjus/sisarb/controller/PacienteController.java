package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Paciente;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.PacienteService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/paciente")
public class PacienteController {

	@Autowired
	PacienteService service;

	@PostMapping("list-paginated")
	public ResponseEntity<Respuesta<List<Paciente>>> listPaginated(@Valid @RequestBody PersonaBusquedaRequest request){
		return service.listPaginated(request);
	}

	@GetMapping("listAll")
	public ResponseEntity<Respuesta<List<Paciente>>> listAll()   {
		return service.listAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Respuesta<Paciente>> findById(@PathVariable Integer id)   {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Respuesta<Paciente>> save(@Valid @RequestBody Paciente request)   {
		return service.save(request);
	}

	@PutMapping
	public ResponseEntity<Respuesta<Paciente>> update(@Valid @RequestBody Paciente request)   {
		return service.update(request);
	}

	@DeleteMapping
	public ResponseEntity<Respuesta<Paciente>> deleteById(@RequestBody Paciente request)  {
		return service.deleteById(request);
	}
}
