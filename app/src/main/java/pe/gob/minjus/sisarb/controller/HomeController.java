package pe.gob.minjus.sisarb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.*;
import pe.gob.minjus.sisarb.model.mapper.InstitucionSecretarioMapper;
import pe.gob.minjus.sisarb.model.mapper.PersonaNaturalMapper;
import pe.gob.minjus.sisarb.model.mapper.SecretarioMapper;
import pe.gob.minjus.sisarb.model.mapper.SedeMapper;
import pe.gob.minjus.sisarb.model.request.InstitucionPersonaRequest;
import pe.gob.minjus.sisarb.rest.model.CommonUbigeo;
import pe.gob.minjus.sisarb.rest.model.CommonWsResponse;
import pe.gob.minjus.sisarb.rest.temp.RestCommonWs;
import pe.gob.minjus.sisarb.service.InstitucionPersonaService;
import pe.gob.minjus.sisarb.service.PersonaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/home")
public class HomeController {


	@Autowired
	PersonaNaturalMapper personaNaturalMapper;


	@Autowired
	SecretarioMapper secretarioMapper;

	@Autowired
	InstitucionSecretarioMapper institucionSecretarioMapper;

	@PostMapping("persona-natural")
	public PersonaNatural guardarPersonaNatural(@RequestBody PersonaNatural personaNatural) {
		personaNaturalMapper.save(personaNatural);
		return personaNatural;
	}


	@GetMapping("secretario")
	public Secretario secretarioObtenerPorId(@PathVariable(value = "id") Integer id) {
		return secretarioMapper.findById(id);
	}

	@PostMapping("secretario")
	public Secretario secretarioGuardar(@RequestBody Secretario secretario) {
		secretarioMapper.save(secretario);
		return secretario;
	}

	@PutMapping("secretario")
	public Secretario secretarioActualizAr(@RequestBody Secretario secretario) {
		secretarioMapper.update(secretario);
		return secretario;
	}

	@DeleteMapping("secretario")
	public Integer secretarioBorrar(@PathVariable(value = "id") Integer id) {
		return secretarioMapper.deleteById(id);
	}


	@PostMapping("institucion-secretario-obntener-por-id")
	public InstitucionSecretario intitucionSecretarioObtenerPorId(@RequestBody InstitucionSecretario model) {
		return institucionSecretarioMapper.findById(model);
	}

	@PostMapping("institucion-secretario")
	public int intitucionSecretarioGuardar(@RequestBody InstitucionSecretario model) {
		return institucionSecretarioMapper.save(model);
	}

	@GetMapping("obtener-ip")
	public Map<String, Object> obtenerIp(HttpServletRequest request) {
		Map<String, Object> hasp = new HashMap<>();
		hasp.put("IP_REMOTE_ADR", request.getRemoteAddr());
		hasp.put("IP_LOCAL_ADR", request.getLocalAddr());
		hasp.put("IP_REMOTE_HOST", request.getRemoteHost());
		hasp.put("REMOTE_USER", request.getRemoteUser());
		hasp.put("USER_PRINCIPAL", request.getUserPrincipal());
		return hasp;
	}


	@Autowired
	SedeMapper sedeMapper;


	@GetMapping("sede/listar-chose")
	public List<Sede> listChose(){
		return sedeMapper.listChoose();
	}


	@Autowired
	RestCommonWs restCommonWs;

	@GetMapping("ubigeo")
	public CommonWsResponse<List<CommonUbigeo>> listDepartamentos(){
		String codigoDepartamento = "05";
		String codigoProvincia = "01";
		return restCommonWs.getUbigeo(codigoDepartamento,codigoProvincia);
	}

	@Autowired
	PersonaService personaService;

	@PostMapping("persona/save")
	public void savePersona(@RequestBody PersonaNatural personaNatural){
		personaService.saveOrUpdate(personaNatural);
	}


	@Autowired
	InstitucionPersonaService institucionPersonaService;

	@PostMapping("institucion-persona")
	public void saveIntitucionPersona(@Valid @RequestBody InstitucionPersonaRequest institucionPersona){
		institucionPersonaService.saveExtend(institucionPersona);
	}

}
