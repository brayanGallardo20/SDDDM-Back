package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.domain.DeclaracionInteres;
import pe.gob.minjus.sisarb.model.mapper.DeclaracionInteresMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.DeclaracionInteresService;
import pe.gob.minjus.sisarb.utils.Constantes;

import java.util.List;

@Slf4j
@Service
public class DeclaracionInteresServiceImpl implements DeclaracionInteresService {

    @Autowired
    DeclaracionInteresMapper mapper;

    @Override
    public ResponseEntity<Respuesta<List<DeclaracionInteres>>> list() {
        Respuesta<List<DeclaracionInteres>> response = new Respuesta<>();
        try {
            List<DeclaracionInteres> list = mapper.list();
            response.setData(list);
            response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
