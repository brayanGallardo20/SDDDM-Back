package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.CodigoSgd;
import pe.gob.minjus.sisarb.model.mapper.CodigoSgdMapper;
import pe.gob.minjus.sisarb.model.request.CodigoSgdBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.CodigoSgdService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;


@Service
public class CodigoSgdServiceImpl implements CodigoSgdService {

    @Autowired
    CodigoSgdMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<CodigoSgd>>> listPaginated(CodigoSgdBusquedaRequest request) {
        Respuesta<List<CodigoSgd>> response = new Respuesta<>();
        List<CodigoSgd> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<CodigoSgd>> findById(Integer id) {
        Respuesta<CodigoSgd> response = new Respuesta<>();
        CodigoSgd objGet = obtenerPorId(id);
        response.setData(objGet);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<CodigoSgd>> save(CodigoSgd request) {
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        Respuesta<CodigoSgd> response = new Respuesta<>();
        mapper.save(request);
        CodigoSgd objSave = obtenerPorId(request.getCodigoSgdId());
        auditoriaService.insert(new Auditoria(request.getNameTable(),
                (request.getCodigoSgdId() == null) ? null : request.getCodigoSgdId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        response.setData(objSave);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<CodigoSgd>> update(CodigoSgd request) {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<CodigoSgd> response = new Respuesta<>();
        CodigoSgd objAu = obtenerPorId(request.getCodigoSgdId());
        mapper.update(request);
        CodigoSgd objUpdate = mapper.findById(request.getCodigoSgdId());
        auditoriaService.insert(new Auditoria(request.getNameTable(),
                (request.getCodigoSgdId() == null) ? null : request.getCodigoSgdId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        response.setData(objUpdate);
        response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<CodigoSgd>> deleteById(CodigoSgd request) {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<CodigoSgd> response = new Respuesta<>();
        CodigoSgd objGet = obtenerPorId(request.getCodigoSgdId());
        mapper.deleteById(objGet.getCodigoSgdId());
        auditoriaService.insert(new Auditoria(objGet.getNameTable(),
                (objGet.getCodigoSgdId() == null) ? null : objGet.getCodigoSgdId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    CodigoSgd obtenerPorId(Integer id)  {
        CodigoSgd objBusq = mapper.findById(id);
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
}
