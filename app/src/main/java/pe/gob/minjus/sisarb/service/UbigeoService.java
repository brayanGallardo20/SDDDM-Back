package pe.gob.minjus.sisarb.service;

import org.springframework.http.ResponseEntity;
import pe.gob.minjus.sisarb.model.domain.UbigeoItem;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.rest.model.CommonUbigeo;

import java.util.List;

public interface UbigeoService {

    ResponseEntity<Respuesta<List<UbigeoItem>>> obtieneUbigeos(String origen, Long ubigeoPadreId);

    ResponseEntity<Respuesta<UbigeoItem>> obtenerUbigeoPorId(String origen, Long ubigeoId);


    ResponseEntity<Respuesta<List<CommonUbigeo>>> getUbigeo(String codigoDepartamento,String codigoProvincia);
}
