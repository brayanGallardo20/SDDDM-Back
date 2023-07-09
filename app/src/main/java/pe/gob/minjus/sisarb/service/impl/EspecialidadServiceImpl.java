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
import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.mapper.EspecialidadMapper;
import pe.gob.minjus.sisarb.model.request.EspecialidadBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.EspecialidadService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;
import java.util.List;

@Slf4j
@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    EspecialidadMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Especialidad>>> listPaginated(EspecialidadBusquedaRequest request) {
        Respuesta<List<Especialidad>> response = new Respuesta<>();
        List<Especialidad> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especialidad>> findById(Integer id) {
        Respuesta<Especialidad> response = new Respuesta<>();
        Especialidad objGet = getById(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especialidad>> save(Especialidad request){
        validFieldRequired(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<Especialidad> response = new Respuesta<>();
        mapper.save(request);
        Especialidad objSave = getById(request.getEspecialidadId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ESPECIALIDAD,
                (request.getEspecialidadId() == null) ? null : request.getEspecialidadId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especialidad>> update(Especialidad request){
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<Especialidad> response = new Respuesta<>();
        Especialidad objAu = getById(request.getEspecialidadId());
        validBeforeUpdate(request,objAu);
        mapper.update(request);
        Especialidad objUpdate = mapper.findById(request.getEspecialidadId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ESPECIALIDAD,
                (request.getEspecialidadId() == null) ? null : request.getEspecialidadId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Especialidad>> deleteById(Especialidad request){
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<Especialidad> response = new Respuesta<>();
        Especialidad objGet = getById(request.getEspecialidadId());
        mapper.deleteById(objGet.getEspecialidadId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ESPECIALIDAD,
                (objGet.getEspecialidadId() == null) ? null : objGet.getEspecialidadId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<List<Especialidad>>> listChoose()  {
        Respuesta<List<Especialidad>> response = new Respuesta<>();
        List<Especialidad> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    Especialidad getById(Integer id) {
    	Especialidad objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }

    void validFieldRequired(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

    void validBeforeSave(Especialidad request) {
        //Validación donde se permite solo alfanumericos
        if( request.getNombre()!=null && !request.getNombre().isEmpty()) validateByNombre(request);
    }

    void validBeforeUpdate(Especialidad request,Especialidad objAu) {
        //Validación donde se permite solo alfanumericos
        if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
            throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
        }
        if( (request.getNombre()!=null && !request.getNombre().isEmpty()) &&
                    !request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim()))  {
                validateByNombre(request);
            }
    }

    void validateByNombre(Especialidad request){
            if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
                throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
            }
            Especialidad requestNombre = new Especialidad();
            requestNombre.setNombre(request.getNombre());
            Integer countByNombre = mapper.countByField(requestNombre);
            if (countByNombre >= 1) {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
            }
    }
}
