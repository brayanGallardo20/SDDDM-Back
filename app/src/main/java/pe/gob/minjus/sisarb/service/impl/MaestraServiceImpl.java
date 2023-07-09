package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Maestra;
import pe.gob.minjus.sisarb.model.mapper.MaestraMapper;
import pe.gob.minjus.sisarb.model.request.MaestraBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.MaestraService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class MaestraServiceImpl implements MaestraService {

	@Autowired
    MaestraMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Maestra>>> listPaginated(MaestraBusquedaRequest request) {
        Respuesta<List<Maestra>> response = new Respuesta<>();
        try {
            List<Maestra> list = mapper.listPaginated(request);
            Integer total = mapper.listPaginatedTotal(request);
            response.setData(list);
            response.setTotalRegistros(total);
            response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMostSpecificCause().toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<Maestra>> findById(Maestra request) {
        Respuesta<Maestra> response = new Respuesta<>();
        try {
            Maestra objGet = getById(request);
            response.setData(objGet);
            response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            log.error(e.getMessage());
            response.setMensaje(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Respuesta<Maestra>> save(Maestra request) {
        validaCampoObligatorio(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        Respuesta<Maestra> response = new Respuesta<>();
        List<Maestra> objMaestra= null;
        objMaestra = mapper.findByName(request);
        if(objMaestra.isEmpty()) {
            mapper.save(request);
            Maestra objSave = mapper.findById(request);
            objSave.setTablaMaestra(request.getTablaMaestra());
            auditoriaService.insert(new Auditoria(request.getTablaMaestra(),
                    (request.getMaestraId() == null) ? null : request.getMaestraId().toString(),
                    request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                    null, JsonConvert.objectToJsonString(objSave)));
            response.setData(objSave);
            response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
            return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);     	
            }            

    }

    @Override
    public ResponseEntity<Respuesta<Maestra>> update(Maestra request) {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        Respuesta<Maestra> response = new Respuesta<>();
        	List<Maestra> objMaestra= null;
        	Maestra objAu = getById(request);
        	objAu.setTablaMaestra(request.getTablaMaestra());
       	 if(!request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim())){
       		 objMaestra = mapper.findByName(request);
            
            if(objMaestra.isEmpty()) {
                mapper.update(request);
                Maestra objUpdate = mapper.findById(request);
                objUpdate.setTablaMaestra(request.getTablaMaestra());
                auditoriaService.insert(new Auditoria(request.getTablaMaestra(),
                        (request.getMaestraId() == null) ? null : request.getMaestraId().toString(),
                        request.getAuditUsuarioModifica(),
                        AuditTipoEnum.U.name(),
                        JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
                response.setData(objUpdate);
                response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);        	         	
            }
       	 }else {
             mapper.update(request);
             Maestra objUpdate = mapper.findById(request);
             objUpdate.setTablaMaestra(request.getTablaMaestra());             
             auditoriaService.insert(new Auditoria(request.getTablaMaestra(),
                     (request.getMaestraId() == null) ? null : request.getMaestraId().toString(),
                     request.getAuditUsuarioModifica(),
                     AuditTipoEnum.U.name(),
                     JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
             response.setData(objUpdate);
             response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
             return new ResponseEntity<>(response, HttpStatus.OK);       		 
       	 }

    }

    @Override
    public ResponseEntity<Respuesta<Maestra>> deleteById(Maestra request)  {
        validaCampoObligatorio(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_ELIMINA);
        Respuesta<Maestra> response = new Respuesta<>();
            Maestra objGet = getById(request);
            objGet.setTablaMaestra(request.getTablaMaestra());                 
            mapper.deleteById(request);       
            auditoriaService.insert(new Auditoria(request.getTablaMaestra(),
                    (request.getMaestraId() == null) ? null : request.getMaestraId().toString(),
                    request.getAuditUsuarioModifica(),
                    AuditTipoEnum.D.name(),
                    JsonConvert.objectToJsonString(objGet), null));
            response.setData(null);
            response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<List<Maestra>>> listChoose(Maestra request) {
        Respuesta<List<Maestra>> response = new Respuesta<>();
        List<Maestra> list = mapper.listChoose(request);
        response.setData(list);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    Maestra getById(Maestra request){
        Maestra objBusq =  mapper.findById(request);
        if(objBusq==null){
            throw new EntityNotFoundException("NO EXISTE EL REGISTRO CON ID: "+request.getMaestraId());
        }
        return objBusq;
    }

    void validaCampoObligatorio(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

}
