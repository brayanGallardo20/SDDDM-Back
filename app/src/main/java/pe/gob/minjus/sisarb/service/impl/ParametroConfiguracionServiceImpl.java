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
import pe.gob.minjus.sisarb.model.domain.ParametroConfiguracion;
import pe.gob.minjus.sisarb.model.mapper.ParametroConfiguracionMapper;
import pe.gob.minjus.sisarb.model.request.ParametroConfiguracionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.ParametroConfiguracionService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class ParametroConfiguracionServiceImpl implements ParametroConfiguracionService {

    @Autowired
    ParametroConfiguracionMapper mapper;
    
    @Autowired
    AuditoriaService auditoriaService;

    public ResponseEntity<Respuesta<List<ParametroConfiguracion>>> listPaginated(ParametroConfiguracionBusquedaRequest request) {
        Respuesta<List<ParametroConfiguracion>> response = new Respuesta<>();
        List<ParametroConfiguracion> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<ParametroConfiguracion>> findById(ParametroConfiguracion request) {
        Respuesta<ParametroConfiguracion> response = new Respuesta<>();
        ParametroConfiguracion objGet = getById(request);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<ParametroConfiguracion>> update(ParametroConfiguracion request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<ParametroConfiguracion> response = new Respuesta<>();
        ParametroConfiguracion objAu = getById(request);
        mapper.update(request);
        ParametroConfiguracion objUpdate = mapper.findById(request);
        auditoriaService.insert(new Auditoria(Constantes.TABLE_GEN_PARAMETRO_CONFIGURACION,
                (request.getParametroConfiguracionId() == null) ? null : request.getParametroConfiguracionId().toString()+", "+request.getAmbienteConfiguracion(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    ParametroConfiguracion getById(ParametroConfiguracion request) {
        ParametroConfiguracion objBusq = mapper.findById(request);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + request.getParametroConfiguracionId() + " - "+request.getAmbienteConfiguracion());
        }
        return objBusq;
    }

    void validaCampoObligatorio(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }
}
