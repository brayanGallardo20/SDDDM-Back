package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.domain.EntidadFinanciera;
import pe.gob.minjus.sisarb.model.mapper.EntidadFinancieraMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.EntidadFinancieraService;
import pe.gob.minjus.sisarb.utils.Constantes;

import java.util.List;

@Service
public class EntidadFinancieraImpl implements EntidadFinancieraService {
    
    @Autowired
    EntidadFinancieraMapper mapper;
    
    @Override
    public ResponseEntity<Respuesta<List<EntidadFinanciera>>> listChoose() {
        Respuesta<List<EntidadFinanciera>> response = new Respuesta<>();
        List<EntidadFinanciera> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
