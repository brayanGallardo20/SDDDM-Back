package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.*;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.mapper.InstitucionPersonaMapper;
import pe.gob.minjus.sisarb.model.request.InstitucionPersonaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ArbitroService;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.InstitucionPersonaService;
import pe.gob.minjus.sisarb.service.PersonaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

@Slf4j
@Service
public class InstitucionPersonaServiceImpl implements InstitucionPersonaService {

    @Autowired
    PersonaService personaService;

    @Autowired
    ArbitroService arbitroService;

    @Autowired
    InstitucionPersonaMapper mapper;
    
    @Autowired
    AuditoriaService auditoriaService;



    @Override
    public InstitucionPersona save(InstitucionPersona request) {
        validFieldRequired(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
       // validBeforeSave(request);
        mapper.insertInstitucionPersona(request);
        InstitucionPersona objSave = getById(request.getInstitucionPersonaId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_INSTITUCION_PERSONA,
                (request.getInstitucionPersonaId() == null) ? null : request.getInstitucionPersonaId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        return objSave;
    }

    @Override
    public InstitucionPersona update(InstitucionPersona request) {
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        InstitucionPersona objAu = getById(request.getInstitucionPersonaId());
        mapper.updateInstitucionPersona(request);
        log.info("Se actualizó la institucionPersona");
        InstitucionPersona objUpdate = getById(request.getInstitucionPersonaId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_INSTITUCION_PERSONA,
                (request.getInstitucionPersonaId() == null) ? null : request.getInstitucionPersonaId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        return objUpdate;
    }

    @Transactional
    @Override
    public ResponseEntity<Respuesta<InstitucionPersona>> saveExtend(InstitucionPersonaRequest request) {
        // 1. Registrar o actualizar persona
        PersonaNatural pnSave = personaService.saveOrUpdate(request.getPersonaNatural());

        // 2. Dependiento el cargo registrar o actualizar InstitucionPersona
        InstitucionPersona ipSave = save(request.getInstitucionPersona());


        Arbitro arbSave = null;
        // 3. Dependiento el cargo vamos a registrar o actualizar el arbitro (tiene que haber un método actualizar o registrar árbitro)
        if(request.getInstitucionPersona().getPersonaCargoId().equals(Constantes.PERSONA_CARGO_ID_ARBITRO)){
             arbSave = arbitroService.save(request.getArbitro());
        }else if(
                request.getInstitucionPersona().getPersonaCargoId().equals(Constantes.PERSONA_CARGO_ID_SECRETARIO)
                || request.getInstitucionPersona().getPersonaCargoId().equals(Constantes.PERSONA_CARGO_ID_COORDINADOR)){

        }
        //
        // - Para el arbitro, llamar a los métodos de los mappers de árbitro
        // - Para el directivo, solo a persona


        // - Para el secretario y coordinador debe llamarse a los métodos de la tabla intermedia

        return null;
    }

    @Override
    public ResponseEntity<Respuesta<InstitucionPersona>> updateExtend(InstitucionPersonaRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Respuesta<InstitucionPersona>> deleteExtendById(InstitucionPersonaRequest request) {
        return null;
    }

    void validFieldRequired(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

    InstitucionPersona getById(Integer id) {
        InstitucionPersona objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }
    
    
}
