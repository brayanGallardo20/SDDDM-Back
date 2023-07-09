package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.pide.response.PidePersonaJuridica;
import pe.gob.minjus.sisarb.model.pide.response.PidePersonaNatural;
import pe.gob.minjus.sisarb.model.response.Respuesta;

public interface PideService {

    ResponseEntity<Respuesta<PidePersonaNatural>> getDataPersonaNaturalByDni(String dni) ;

    ResponseEntity<Respuesta<PidePersonaJuridica>> getDataPersonaJuridicaByRuc(String ruc) ;
}
