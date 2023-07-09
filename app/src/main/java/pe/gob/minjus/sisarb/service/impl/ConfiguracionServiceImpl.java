package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationConfiguracion;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Configuracion;
import pe.gob.minjus.sisarb.model.mapper.ConfiguracionMapper;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.ConfiguracionService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;


@Slf4j
@Service
public class ConfiguracionServiceImpl implements ConfiguracionService {

    @Autowired
    ConfiguracionMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Value("${sisarb.entorno.configuracion}")
    private String entornoConfiguracion;


    @Override
    public ResponseEntity<Respuesta<Configuracion>> getConfigDefault() {
        Respuesta<Configuracion> response = new Respuesta<>();
        Configuracion objGet = getById(entornoConfiguracion);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Configuracion>> update(Configuracion request) {
        request.setConfiguracionId(entornoConfiguracion);
        Respuesta<Configuracion> response = new Respuesta<>();
        Configuracion objAu = getById(request.getConfiguracionId());
        mapper.update(request);
        Configuracion objUpdate = mapper.findById(request.getConfiguracionId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_CONFIGURACION,
                (request.getConfiguracionId() == null) ? null : request.getConfiguracionId(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public String getWebServiceSeguridad(String configuracionId) {
        String webServiceSeguridad = mapper.getWebServiceSeguridad(configuracionId);
        validWebService(webServiceSeguridad,"SEGURIDAD");
        return webServiceSeguridad;
    }

    @Override
    public String getWebServicePide(String configuracionId) {
        String webServicePide = mapper.getWebServicePide(configuracionId);
        validWebService(webServicePide,"PIDE");
        return webServicePide;
    }

    void validWebService(String webServices,String field){
        if(webServices==null){
            throw new EntityValidationConfiguracion(Constantes.MSJ_VALIDACION_CONFIGURACION+field);
        }
        if(webServices.trim().isEmpty()){
            throw new EntityValidationConfiguracion(Constantes.MSJ_VALIDACION_CONFIGURACION+field);
        }
        if(!Validators.validUrl(webServices)){
            throw new EntityValidationConfiguracion("Error al consultar con el servicio, URL inválido: "+field);
        }
    }

    Configuracion getById(String id) {
        Configuracion objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException("No se encontró ninguna configuración");
        }
        return objBusq;
    }


}
