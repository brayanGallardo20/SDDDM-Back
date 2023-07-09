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
import pe.gob.minjus.sisarb.model.domain.TipoDocumento;
import pe.gob.minjus.sisarb.model.mapper.TipoDocumentoMapper;
import pe.gob.minjus.sisarb.model.request.TipoDocumentoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.TIpoDocumentoService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;

import java.util.List;

@Slf4j
@Service
public class TipoDocumentoServiceImpl implements TIpoDocumentoService {

    @Autowired
    TipoDocumentoMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listPaginated(TipoDocumentoBusquedaRequest request) {
        Respuesta<List<TipoDocumento>> response = new Respuesta<>();
        List<TipoDocumento> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listAll() {
        Respuesta<List<TipoDocumento>> response = new Respuesta<>();
        List<TipoDocumento> list = mapper.listAll();
        response.setData(list);
        response.setTotalRegistros(list.size());
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<TipoDocumento>> findById(Integer id)  {
        Respuesta<TipoDocumento> response = new Respuesta<>();
        TipoDocumento objGet = obtenerPorId(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 
    @Override
    public ResponseEntity<Respuesta<TipoDocumento>> save(TipoDocumento request)  {
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<TipoDocumento> response = new Respuesta<>();
        mapper.save(request);
        TipoDocumento objSave = obtenerPorId(request.getTipoDocumentoId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_TIPO_DOCUMENTO,
                (request.getTipoDocumentoId() == null) ? null : request.getTipoDocumentoId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<TipoDocumento>> update(TipoDocumento request)  {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<TipoDocumento> response = new Respuesta<>();
        TipoDocumento objAu = obtenerPorId(request.getTipoDocumentoId());
        validBeforeUpdate(request,objAu);
        mapper.update(request);
        TipoDocumento objUpdate = mapper.findById(request.getTipoDocumentoId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_TIPO_DOCUMENTO,
                (request.getTipoDocumentoId() == null) ? null : request.getTipoDocumentoId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<TipoDocumento>> deleteById(TipoDocumento request)  {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<TipoDocumento> response = new Respuesta<>();
        TipoDocumento objGet = obtenerPorId(request.getTipoDocumentoId());
        mapper.deleteById(objGet.getTipoDocumentoId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_TIPO_DOCUMENTO,
                (objGet.getTipoDocumentoId() == null) ? null : objGet.getTipoDocumentoId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<List<TipoDocumento>>> listChoose() {
        Respuesta<List<TipoDocumento>> response = new Respuesta<>();
        List<TipoDocumento> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    TipoDocumento obtenerPorId(Integer id) {
        TipoDocumento objBusq = mapper.findById(id);
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

    void validBeforeSave(TipoDocumento request){
        validateAlfanumerico(request.getNombre(), Constantes.MSJ_VALIDACION_SOLO_CARACTERES_ALFANUMERICOS + " para el nombre: ");
        validateByNombre(request);
    }

    void validBeforeUpdate(TipoDocumento request, TipoDocumento objAu) {
        validateAlfanumerico(request.getNombre(), Constantes.MSJ_VALIDACION_SOLO_CARACTERES_ALFANUMERICOS + " para el nombre: ");
        if(!request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim())){
            validateByNombre(request);
        }
    }
    void validateByNombre(TipoDocumento request) {
        TipoDocumento requestNombre = new TipoDocumento();
        requestNombre.setNombre(request.getNombre());
        Integer countByNombre = mapper.countByField(requestNombre);
        if (countByNombre >= 1) {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
        }
    }

    void validateAlfanumerico(String campo, String mensaje) {
        if (!Validators.validOnlyAlfaNumericos(campo)) {
            throw new EntityValidationCustom(mensaje + campo);
        }
    }
}
