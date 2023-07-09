package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.*;
import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.mapper.ArbitroMapper;
import pe.gob.minjus.sisarb.model.request.ArbitroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ArbitroEspecializacionService;
import pe.gob.minjus.sisarb.service.ArbitroService;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class ArbitroServiceImpl implements ArbitroService {

    @Autowired
    ArbitroMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Autowired
    ArbitroEspecializacionService arbitroEspecializacionService;

    @Override
    public Arbitro save(Arbitro request) {
        validFieldRequired(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        //validBeforeSave(request);
        mapper.save(request);
        Arbitro objSave = getById(request.getArbitroId());
        arbitroEspecializacionService.saveByArbitro(request,request.getAuditUsuarioCreacion());
        objSave.setListadoArbitroEspecializacion(null);
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_ARBITRO,
                (request.getArbitroId() == null) ? null : request.getArbitroId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        return objSave;
    }

    @Override
    public Arbitro update(Arbitro request) {
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Arbitro objAu = getById(request.getArbitroId());
        //validBeforeUpdate(request,objAu);
        mapper.update(request);
        arbitroEspecializacionService.saveByArbitro(request,request.getAuditUsuarioModifica());
        Arbitro objUpdate = mapper.findById(request.getArbitroId());
        objUpdate.setListadoArbitroEspecializacion(null);
        objAu.setListadoArbitroEspecializacion(null);
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_ARBITRO,
                (request.getArbitroId() == null) ? null : request.getArbitroId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        return objUpdate;
    }

    void validFieldRequired(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

    Arbitro getById(Integer id) {
        Arbitro objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }

}
