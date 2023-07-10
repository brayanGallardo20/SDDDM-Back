package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.Medico;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.MedicoService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/medico")
public class MedicoController {

	@Autowired
	MedicoService service;

	@PostMapping("list-paginated")
	public ResponseEntity<Respuesta<List<Medico>>> listPaginated(@Valid @RequestBody PersonaBusquedaRequest request){
		return service.listPaginated(request);
	}

	@GetMapping("listAll")
	public ResponseEntity<Respuesta<List<Medico>>> listAll()   {
		return service.listAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Respuesta<Medico>> findById(@PathVariable Integer id)   {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Respuesta<Medico>> save(@Valid @RequestBody Medico request)   {
		return service.save(request);
	}

	@PutMapping
	public ResponseEntity<Respuesta<Medico>> update(@Valid @RequestBody Medico request)   {
		return service.update(request);
	}

	@DeleteMapping
	public ResponseEntity<Respuesta<Medico>> deleteById(@RequestBody Medico request)  {
		return service.deleteById(request);
	}
}
