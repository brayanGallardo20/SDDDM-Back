package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.SedePersona;
import pe.gob.minjus.sisarb.model.mapper.SedePersonaMapper;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.SedePersonaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

@Service
public class SedePersonaImpl implements SedePersonaService {

    @Autowired
    SedePersonaMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Transactional(readOnly = true)
    @Override
    public SedePersona findById(Integer id) {
        return mapper.findById(id);
    }

    @Transactional
    @Override
    public SedePersona save(SedePersona request){
        validFieldRequired(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        mapper.save(request);
        SedePersona objSave = getById(request.getSedePersonaId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_PERSONA,
                (request.getSedeId() == null) ? null : request.getSedeId().toString(),
                request.getAuditUsuarioCreacion(),  AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
      return objSave;
    }

    @Transactional
    @Override
    public SedePersona update(SedePersona request){
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        SedePersona objAu = getById(request.getSedePersonaId());
        mapper.update(request);
        SedePersona objUpdate = mapper.findById(request.getSedePersonaId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_PERSONA,
                (request.getSedeId() == null) ? null : request.getSedeId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
         return objUpdate;
    }

    @Transactional
    @Override
    public void deleteById(Integer sedePersonaId, String auditUsuarioModifica){
        validFieldRequired(auditUsuarioModifica, Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        SedePersona objGet = getById(sedePersonaId);
        mapper.deleteById(objGet.getSedePersonaId(),auditUsuarioModifica);
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE_PERSONA,
                (objGet.getSedeId() == null) ? null : objGet.getSedeId().toString(),
                auditUsuarioModifica,
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
    }

    void validFieldRequired(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }


    SedePersona getById(Integer id) {
        SedePersona objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }
}
