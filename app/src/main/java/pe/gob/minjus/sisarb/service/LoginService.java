package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.UsuarioLogin;
import pe.gob.minjus.sisarb.model.request.LoginRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;


public interface LoginService {

    ResponseEntity<Respuesta<UsuarioLogin>> entrarSistema(LoginRequest request);

}
