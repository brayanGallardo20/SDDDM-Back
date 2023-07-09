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
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.mapper.SedeMapper;
import pe.gob.minjus.sisarb.model.request.SedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.SedeEspecialidadService;
import pe.gob.minjus.sisarb.service.SedeMateriaArbitralService;
import pe.gob.minjus.sisarb.service.SedeService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;

import java.util.List;

@Slf4j
@Service
public class SedeServiceImpl implements SedeService {
    @Autowired
    SedeMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Autowired
    SedeEspecialidadService sedeEspecialidadService;

    @Autowired
    SedeMateriaArbitralService sedeMateriaArbitralService;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Respuesta<List<Sede>>> listPaginated(SedeBusquedaRequest request) {
        Respuesta<List<Sede>> response = new Respuesta<>();
        List<Sede> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Respuesta<Sede>> findById(Integer id) {
        Respuesta<Sede> response = new Respuesta<>();
        Sede objGet = getById(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Respuesta<Sede>> save(Sede request){
        validFieldRequired(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<Sede> response = new Respuesta<>();
        mapper.save(request);
        Sede objSave = getById(request.getSedeId());
        sedeEspecialidadService.saveBySede(request,request.getAuditUsuarioCreacion());
        sedeMateriaArbitralService.saveBySede(request,request.getAuditUsuarioCreacion());
        objSave.setListadoSedeEspecialidad(null);
        objSave.setListadoSedeMateriaArbitral(null);
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE,
                (request.getSedeId() == null) ? null : request.getSedeId().toString(),
                request.getAuditUsuarioCreacion(),  AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Respuesta<Sede>> update(Sede request){
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<Sede> response = new Respuesta<>();
        Sede objAu = getById(request.getSedeId());
        validBeforeUpdate(request,objAu);
        mapper.update(request);
        Sede objUpdate = mapper.findById(request.getSedeId());
        sedeEspecialidadService.saveBySede(request,request.getAuditUsuarioModifica());
        sedeMateriaArbitralService.saveBySede(request,request.getAuditUsuarioModifica());
        objAu.setListadoSedeEspecialidad(null);
        objAu.setListadoSedeMateriaArbitral(null);
        objUpdate.setListadoSedeEspecialidad(null);
        objUpdate.setListadoSedeMateriaArbitral(null);
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE,
                (request.getSedeId() == null) ? null : request.getSedeId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Respuesta<Sede>> deleteById(Sede request){
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<Sede> response = new Respuesta<>();
        Sede objGet = getById(request.getSedeId());
        sedeEspecialidadService.deleteAllBySedeId(objGet.getSedeId(),request.getAuditUsuarioModifica());
        sedeMateriaArbitralService.deleteAllBySedeId(objGet.getSedeId(),request.getAuditUsuarioModifica());
        mapper.deleteById(objGet.getSedeId());
        objGet.setListadoSedeEspecialidad(null);
        objGet.setListadoSedeMateriaArbitral(null);
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_SEDE,
                (objGet.getSedeId() == null) ? null : objGet.getSedeId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Respuesta<List<Sede>>> listChoose()  {
        Respuesta<List<Sede>> response = new Respuesta<>();
        List<Sede> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    Sede getById(Integer id) {
        Sede objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        objBusq.setListadoSedeEspecialidad(sedeEspecialidadService.findBySede(objBusq.getSedeId()));
        objBusq.setListadoSedeMateriaArbitral(sedeMateriaArbitralService.findBySede(objBusq.getSedeId()));
        return objBusq;
    }


    void validFieldRequired(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

    void validBeforeSave(Sede request) {
        if( request.getNombre()!=null && !request.getNombre().isEmpty()) validateByNombre(request);
        //Validamos que el nombre no exista para la misma institucion
        Integer count = mapper.countNombreSedeByInstitucion(request.getInstitucionId(),request.getNombre());
        if(count>0)  throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);

    }

    void validBeforeUpdate(Sede request,Sede objAu) {
        if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
            throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
        }
        if( (request.getNombre()!=null && !request.getNombre().isEmpty()) &&
                !request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim()))  {
            validateByNombre(request);
        }
        //Validamos que el nombre no exista para la misma institucion
        Integer count = mapper.countNombreSedeByInstitucionAndExcludeSede(request.getInstitucionId(),request.getNombre(),request.getSedeId());
        if(count>0)  throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
    }

    void validateByNombre(Sede request){
        if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
            throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
        }
        Sede requestNombre = new Sede();
        requestNombre.setNombre(request.getNombre());
    }
}
