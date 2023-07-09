package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Especializacion;
import pe.gob.minjus.sisarb.model.mapper.EspecializacionMapper;
import pe.gob.minjus.sisarb.model.request.EspecializacionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.EspecializacionService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;
import java.util.List;

@Slf4j
@Service
public class EspecializacionServiceImpl implements EspecializacionService {

    @Autowired
    EspecializacionMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Especializacion>>> listPaginated(EspecializacionBusquedaRequest request) {
        Respuesta<List<Especializacion>> response = new Respuesta<>();
        List<Especializacion> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especializacion>> findById(Integer id) {
        Respuesta<Especializacion> response = new Respuesta<>();
        Especializacion objGet = getById(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especializacion>> save(Especializacion request){
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<Especializacion> response = new Respuesta<>();
        mapper.save(request);
        Especializacion objSave = getById(request.getEspecializacionId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ESPECIALIZACION,
                (request.getEspecializacionId() == null) ? null : request.getEspecializacionId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especializacion>> update(Especializacion request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<Especializacion> response = new Respuesta<>();
        Especializacion objAu = getById(request.getEspecializacionId());
        validBeforeUpdate(request,objAu);
        mapper.update(request);
        Especializacion objUpdate = mapper.findById(request.getEspecializacionId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ESPECIALIZACION,
                (request.getEspecializacionId() == null) ? null : request.getEspecializacionId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especializacion>> deleteById(Especializacion request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<Especializacion> response = new Respuesta<>();
        Especializacion objGet = getById(request.getEspecializacionId());
        mapper.deleteById(objGet.getEspecializacionId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ESPECIALIZACION,
                (objGet.getEspecializacionId() == null) ? null : objGet.getEspecializacionId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    Especializacion getById(Integer id) {
    	Especializacion objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }

    void validaCampoObligatorio(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

    void validBeforeSave(Especializacion request) {
        //Validación donde se permite solo alfanumericos
        if( request.getNombre()!=null && !request.getNombre().isEmpty()) validateByNombre(request);
    }

    void validBeforeUpdate(Especializacion request,Especializacion objAu) {
        //Validación donde se permite solo alfanumericos
        if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
            throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
        }
        if( (request.getNombre()!=null && !request.getNombre().isEmpty()) &&
                    !request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim()))  {
                validateByNombre(request);
            }
    }

    void validateByNombre(Especializacion request){
            if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
                throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
            }
            Especializacion requestNombre = new Especializacion();
            requestNombre.setNombre(request.getNombre());
            Integer countByNombre = mapper.countByField(requestNombre);
            if (countByNombre >= 1) {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
            }
    }
}
