package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.domain.Sancion;
import pe.gob.minjus.sisarb.model.mapper.SancionMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.SancionService;
import pe.gob.minjus.sisarb.utils.Constantes;

import java.util.List;

@Slf4j
@Service
public class SancionServiceImpl implements SancionService {

    @Autowired
    SancionMapper mapper;

    @Override
    public ResponseEntity<Respuesta<List<Sancion>>> list() {
        Respuesta<List<Sancion>> response = new Respuesta<>();
        try {
            List<Sancion> list = mapper.list();
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
