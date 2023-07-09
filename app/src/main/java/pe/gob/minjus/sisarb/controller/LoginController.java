package pe.gob.minjus.sisarb.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.minjus.sisarb.model.domain.UsuarioLogin;
import pe.gob.minjus.sisarb.model.request.LoginRequest;
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
	    public ResponseEntity<Respuesta<UsuarioLogin>> entrarSistema(@Valid @RequestBody LoginRequest request){
			return loginService.entrarSistema(request);
	    }
}
