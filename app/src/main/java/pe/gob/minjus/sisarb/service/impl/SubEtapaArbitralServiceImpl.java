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
import pe.gob.minjus.sisarb.model.domain.SubEtapaArbitral;
import pe.gob.minjus.sisarb.model.mapper.EtapaArbitralMapper;
import pe.gob.minjus.sisarb.model.mapper.SubEtapaArbitralMapper;
import pe.gob.minjus.sisarb.model.request.SubEtapaArbitralBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.SubEtapaArbitralService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;

import java.util.List;

@Slf4j
@Service
public class SubEtapaArbitralServiceImpl implements SubEtapaArbitralService {

    @Autowired
    SubEtapaArbitralMapper mapper;

    @Autowired
    EtapaArbitralMapper etapaArbitralMapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<SubEtapaArbitral>>> listPaginated(SubEtapaArbitralBusquedaRequest request) {
        Respuesta<List<SubEtapaArbitral>> response = new Respuesta<>();
        List<SubEtapaArbitral> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<SubEtapaArbitral>> findById(Integer id)  {
        Respuesta<SubEtapaArbitral> response = new Respuesta<>();
        SubEtapaArbitral objGet = obtenerPorId(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<SubEtapaArbitral>> save(SubEtapaArbitral request)  {
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        validBeforeSave(request);
        Respuesta<SubEtapaArbitral> response = new Respuesta<>();
        mapper.save(request);
        SubEtapaArbitral objSave = obtenerPorId(request.getSubEtapaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_SUB_ETAPA_ARBITRAL,
                (request.getSubEtapaArbitralId() == null) ? null : request.getSubEtapaArbitralId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<SubEtapaArbitral>> update(SubEtapaArbitral request)  {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<SubEtapaArbitral> response = new Respuesta<>();
        SubEtapaArbitral objAu = obtenerPorId(request.getSubEtapaArbitralId());
        validBeforeUpdate(request, objAu);
        mapper.update(request);
        SubEtapaArbitral objUpdate = mapper.findById(request.getSubEtapaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_SUB_ETAPA_ARBITRAL,
                (request.getSubEtapaArbitralId() == null) ? null : request.getSubEtapaArbitralId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<SubEtapaArbitral>> deleteById(SubEtapaArbitral request) {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<SubEtapaArbitral> response = new Respuesta<>();
        SubEtapaArbitral objGet = obtenerPorId(request.getSubEtapaArbitralId());
        mapper.deleteById(objGet.getSubEtapaArbitralId());
        auditoriaService.insert(new Auditoria(Constantes.TABLE_MAE_SUB_ETAPA_ARBITRAL,
                (objGet.getSubEtapaArbitralId() == null) ? null : objGet.getSubEtapaArbitralId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    SubEtapaArbitral obtenerPorId(Integer id) {
        SubEtapaArbitral objBusq = mapper.findById(id);
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

    void validBeforeSave(SubEtapaArbitral request) {
        validateExistEtapaArbitral(request.getEtapaArbitralId());
        validateAlfanumerico(request.getNombre(), Constantes.MSJ_VALIDACION_SOLO_CARACTERES_ALFANUMERICOS + " para el nombre: ");
        validateByNombre(request);
        validateByOrden(request);
    }

    void validateByNombre(SubEtapaArbitral request) {
        SubEtapaArbitral requestNombre = new SubEtapaArbitral();
        requestNombre.setNombre(request.getNombre());
        requestNombre.setEtapaArbitralId(request.getEtapaArbitralId());
        Integer countByNombre = mapper.countByField(requestNombre);
        if (countByNombre >= 1) {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
        }
    }

    void validateByOrden(SubEtapaArbitral request) {
        SubEtapaArbitral requestOrden = new SubEtapaArbitral();
        requestOrden.setOrden(request.getOrden());
        requestOrden.setEtapaArbitralId(request.getEtapaArbitralId());
        Integer countByOrden = mapper.countByField(requestOrden);
        if (countByOrden >= 1) {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);
        }
    }

    void validateAlfanumerico(String campo, String mensaje) {
        if (!Validators.validOnlyAlfaNumericos(campo)) {
            throw new EntityValidationCustom(mensaje + campo);
        }
    }

    void validBeforeUpdate(SubEtapaArbitral request, SubEtapaArbitral objAu) {
        if (!request.getEtapaArbitralId().equals(objAu.getEtapaArbitralId())) {
            validateExistEtapaArbitral(request.getEtapaArbitralId());
        }
        validateAlfanumerico(request.getNombre(),Constantes.MSJ_VALIDACION_SOLO_CARACTERES_ALFANUMERICOS + " para el nombre: "+request.getNombre());
        if (!request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim()) ||
                !request.getEtapaArbitralId().equals(objAu.getEtapaArbitralId())
        ) {
            validateByNombre(request);
        }
        if (!(request.getOrden().equals(objAu.getOrden()))  ||
                !request.getEtapaArbitralId().equals(objAu.getEtapaArbitralId())) {
            validateByOrden(request);
        }
    }

    void validateExistEtapaArbitral(Integer etapaArbitralId){
        if(etapaArbitralMapper.findById(etapaArbitralId)==null){
            throw new EntityValidationCustom("No se encontró la etapa arbitral con identificador: "+etapaArbitralId);
        }
    }

}
