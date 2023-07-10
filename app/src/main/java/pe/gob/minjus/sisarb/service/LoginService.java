package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.Usuario;
import pe.gob.minjus.sisarb.model.response.Respuesta;

public interface LoginService {

    ResponseEntity<Respuesta<Usuario>> entrarSistema(Usuario request);

}
