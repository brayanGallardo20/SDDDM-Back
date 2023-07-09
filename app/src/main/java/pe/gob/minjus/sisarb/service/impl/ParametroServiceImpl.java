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
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.mapper.ParametroMapper;
import pe.gob.minjus.sisarb.model.request.ParametroBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.ParametroService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import java.util.List;

@Slf4j
@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    ParametroMapper mapper;
    
    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Parametro>>> listPaginated(ParametroBusquedaRequest request) {
        Respuesta<List<Parametro>> response = new Respuesta<>();
        List<Parametro> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Respuesta<Parametro>> insertarParametro(Parametro request) {
        Respuesta<Parametro> response = new Respuesta<>();
        Parametro parametro = mapper.findByName(request);
    	if(parametro==null) {
    		mapper.insert(request);
    		Parametro objSave = getById(request.getParametroId());
            auditoriaService.insert(new Auditoria(request.getNameTable(),
                    (request.getParametroId() == null) ? null : request.getParametroId().toString(),
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
	public ResponseEntity<Respuesta<Parametro>> actualizarParametro(Parametro request) {
        Respuesta<Parametro> response = new Respuesta<>();    	
        Parametro objAu = getById(request.getParametroId());
   	 if(!request.getConcepto().toUpperCase().trim().equals(objAu.getConcepto().toUpperCase().trim())){
   		Parametro list = mapper.findByName(request);
         if(list==null) {
        	 mapper.update(request);
        	 Parametro objSave = getById(request.getParametroId());
             auditoriaService.insert(new Auditoria(request.getNameTable(),
                     (request.getParametroId() == null) ? null : request.getParametroId().toString(),
                     request.getAuditUsuarioModifica(),
                     AuditTipoEnum.U.name(),
                     JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));                 
             response.setData(objSave);
             response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
             return new ResponseEntity<>(response, HttpStatus.OK);
         }else {
             throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);              	
         }       		 
   	 
   	 } else {
   		mapper.update(request);
   		Parametro objSave = getById(request.getParametroId());
         auditoriaService.insert(new Auditoria(request.getNameTable(),
                 (request.getParametroId() == null) ? null : request.getParametroId().toString(),
                 request.getAuditUsuarioModifica(),
                 AuditTipoEnum.U.name(),
                 JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));    
         response.setData(objSave);
         response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
         return new ResponseEntity<>(response, HttpStatus.OK); 
   	 }
	}

	@Override
	public ResponseEntity<Respuesta<Parametro>> eliminarParametro(Parametro request) {
        Respuesta<Parametro> response = new Respuesta<>();
        Parametro objSave = getById(request.getParametroId());   
        mapper.delete(request.getParametroId());         
        auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                (objSave.getParametroId() == null) ? null : objSave.getParametroId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objSave), null));                        
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	Parametro getById(Integer id) {
		Parametro objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }    

}
