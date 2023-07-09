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
import pe.gob.minjus.sisarb.model.domain.EtapaArbitral;
import pe.gob.minjus.sisarb.model.mapper.EtapaArbitralMapper;
import pe.gob.minjus.sisarb.model.request.EtapaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.EtapaArbitralService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;

import java.util.List;

@Slf4j
@Service
public class EtapaArbitralServiceImpl implements EtapaArbitralService {

    @Autowired
    EtapaArbitralMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<EtapaArbitral>>> listPaginated(EtapaArbitralBusquedaRequest request) {
        Respuesta<List<EtapaArbitral>> response = new Respuesta<>();
        List<EtapaArbitral> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<EtapaArbitral>> findById(Integer id) {
        Respuesta<EtapaArbitral> response = new Respuesta<>();
        EtapaArbitral objGet = getById(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<EtapaArbitral>> save(EtapaArbitral request){
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<EtapaArbitral> response = new Respuesta<>();
        mapper.save(request);
        EtapaArbitral objSave = getById(request.getEtapaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ETAPA_ARBITRAL,
                (request.getEtapaArbitralId() == null) ? null : request.getEtapaArbitralId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<EtapaArbitral>> update(EtapaArbitral request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<EtapaArbitral> response = new Respuesta<>();
        EtapaArbitral objAu = getById(request.getEtapaArbitralId());
        validBeforeUpdate(request,objAu);
        mapper.update(request);
        EtapaArbitral objUpdate = mapper.findById(request.getEtapaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ETAPA_ARBITRAL,
                (request.getEtapaArbitralId() == null) ? null : request.getEtapaArbitralId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<EtapaArbitral>> deleteById(EtapaArbitral request){
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        if(request.getEtapaArbitralId()!=null) validBeforeDelete(request.getEtapaArbitralId());
        Respuesta<EtapaArbitral> response = new Respuesta<>();
        EtapaArbitral objGet = getById(request.getEtapaArbitralId());
        mapper.deleteById(objGet.getEtapaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_ETAPA_ARBITRAL,
                (objGet.getEtapaArbitralId() == null) ? null : objGet.getEtapaArbitralId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<List<EtapaArbitral>>> listChoose()  {
        Respuesta<List<EtapaArbitral>> response = new Respuesta<>();
        List<EtapaArbitral> list = mapper.listChoose();
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    EtapaArbitral getById(Integer id) {
        EtapaArbitral objBusq = mapper.findById(id);
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

    void validBeforeSave(EtapaArbitral request) {
        //Validación donde se permite solo alfanumericos
        if( request.getNombre()!=null && !request.getNombre().isEmpty()) validateByNombre(request);
        if(request.getOrden()!=null) validateByOrden(request);
    }

    void validBeforeUpdate(EtapaArbitral request,EtapaArbitral objAu) {
        //Validación donde se permite solo alfanumericos
        if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
            throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
        }
        if( (request.getNombre()!=null && !request.getNombre().isEmpty()) &&
                    !request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim()))  {
                validateByNombre(request);
            }
        if(request.getOrden()!=null && !(request.getOrden().equals(objAu.getOrden()))) {
                validateByOrden(request);
            }
    }

    void validateByNombre(EtapaArbitral request){
            if (!Validators.validOnlyAlfaNumericos(request.getNombre())){
                throw new EntityValidationCustom("No se permiten caracteres especiales para el nombre: " + request.getNombre());
            }
            EtapaArbitral requestNombre = new EtapaArbitral();
            requestNombre.setNombre(request.getNombre());
            Integer countByNombre = mapper.countByField(requestNombre);
            if (countByNombre >= 1) {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
            }
    }

    void validateByOrden(EtapaArbitral request){
            EtapaArbitral requestOrden = new EtapaArbitral();
            requestOrden.setOrden(request.getOrden());
            Integer countByOrden = mapper.countByField(requestOrden);
            if (countByOrden >= 1) {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
            }
    }

    void validBeforeDelete(Integer subEtapaArbitralId) {
        Integer countSubEtapArbiByEtapArbId = mapper.countSubEtapArbiByEtapArbiId(subEtapaArbitralId);
        if (countSubEtapArbiByEtapArbId > 0) {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_RELACIONADO);            
        }
    }
}
