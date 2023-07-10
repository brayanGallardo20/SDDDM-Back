package pe.gob.minjus.sisarb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.Usuario;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.LoginService;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping("/entrarSistema")
	public ResponseEntity<Respuesta<Usuario>> entrarSistema(@Valid @RequestBody Usuario request){
		return loginService.entrarSistema(request);
	}
}
