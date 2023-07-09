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
import pe.gob.minjus.sisarb.model.domain.MateriaArbitral;
import pe.gob.minjus.sisarb.model.mapper.MateriaArbitralMapper;
import pe.gob.minjus.sisarb.model.request.MateriaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.MateriaArbitralService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;

import java.util.List;

@Slf4j
@Service
public class MateriaArbitralServiceImpl implements MateriaArbitralService {

    @Autowired
    MateriaArbitralMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<MateriaArbitral>>> listPaginated(MateriaArbitralBusquedaRequest request) {
        Respuesta<List<MateriaArbitral>> response = new Respuesta<>();
        List<MateriaArbitral> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<MateriaArbitral>> findById(Integer id) {
        Respuesta<MateriaArbitral> response = new Respuesta<>();
        MateriaArbitral objGet = getById(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<MateriaArbitral>> save(MateriaArbitral request){
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<MateriaArbitral> response = new Respuesta<>();
        mapper.save(request);
        MateriaArbitral objSave = getById(request.getMateriaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_MATERIA_ARBITRAL,
                (request.getMateriaArbitralId() == null) ? null : request.getMateriaArbitralId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<MateriaArbitral>> update(MateriaArbitral request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<MateriaArbitral> response = new Respuesta<>();
        MateriaArbitral objAu = getById(request.getMateriaArbitralId());
        validBeforeUpdate(request,objAu);
        mapper.update(request);
        MateriaArbitral objUpdate = mapper.findById(request.getMateriaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_MATERIA_ARBITRAL,
                (request.getMateriaArbitralId() == null) ? null : request.getMateriaArbitralId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<MateriaArbitral>> deleteById(MateriaArbitral request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<MateriaArbitral> response = new Respuesta<>();
        MateriaArbitral objGet = getById(request.getMateriaArbitralId());
        mapper.deleteById(objGet.getMateriaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_MATERIA_ARBITRAL,
                (objGet.getMateriaArbitralId() == null) ? null : objGet.getMateriaArbitralId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<List<MateriaArbitral>>> listChoose()  {
        Respuesta<List<MateriaArbitral>> response = new Respuesta<>();
        List<MateriaArbitral> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    MateriaArbitral getById(Integer id) {
    	MateriaArbitral objBusq = mapper.findById(id);
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

    void validBeforeSave(MateriaArbitral request) {
        //Validación donde se permite solo alfanumericos
        if( request.getNombre()!=null && !request.getNombre().isEmpty()) validateByNombre(request);
    }

    void validBeforeUpdate(MateriaArbitral request,MateriaArbitral objAu) {
        //Validación donde se permite solo alfanumericos
        if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
            throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
        }
        if( (request.getNombre()!=null && !request.getNombre().isEmpty()) &&
                    !request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim()))  {
                validateByNombre(request);
            }
    }

    void validateByNombre(MateriaArbitral request){
            if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
                throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
            }
            MateriaArbitral requestNombre = new MateriaArbitral();
            requestNombre.setNombre(request.getNombre());
            Integer countByNombre = mapper.countByField(requestNombre);
            if (countByNombre >= 1) {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
            }
    }
}
