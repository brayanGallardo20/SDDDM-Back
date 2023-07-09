package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.response.Respuesta;

public interface PersonaService {


    PersonaNatural saveOrUpdate(PersonaNatural request);
}
