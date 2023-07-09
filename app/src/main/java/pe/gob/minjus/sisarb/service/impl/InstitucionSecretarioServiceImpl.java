package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.domain.InstitucionSecretario;
import pe.gob.minjus.sisarb.model.mapper.InstitucionSecretarioMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.InstitucionSecretarioService;

@Service
public class InstitucionSecretarioServiceImpl implements InstitucionSecretarioService {

    @Autowired
    InstitucionSecretarioMapper institucionSecretarioMapper;

    @Override
    public ResponseEntity<Respuesta<InstitucionSecretario>> saveInstitucionSecretario(InstitucionSecretario request) {
        //Pasoss
        //1. Proceso de registro del secretario
        return null;
    }
}
