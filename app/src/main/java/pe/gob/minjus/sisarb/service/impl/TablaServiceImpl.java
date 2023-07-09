package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.model.domain.Tabla;
import pe.gob.minjus.sisarb.model.mapper.TablaMapper;
import pe.gob.minjus.sisarb.service.TablaService;

import java.util.List;

@Slf4j
@Service
public class TablaServiceImpl implements TablaService {

    @Autowired
    TablaMapper tablaMapper;

    @Value("${sisarb.audit.owner-user}")
    String databaseOwner;


    @Override
    public ResponseEntity<Respuesta<List<Tabla>>> listTabla() {
        Respuesta<List<Tabla>> response = new Respuesta<>();
        try {
            List<Tabla> list = tablaMapper.listTabla(databaseOwner);
            response.setData(list);
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
