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
import pe.gob.minjus.sisarb.model.domain.EspecialidadSede;
import pe.gob.minjus.sisarb.model.mapper.EspecialidadSedeMapper;
import pe.gob.minjus.sisarb.model.request.EspecialidadSedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.EspecialidadSedeResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.EspecialidadSedeService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class EspecialidadSedeServiceImpl implements EspecialidadSedeService {

    @Autowired
    EspecialidadSedeMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<EspecialidadSedeResponse>>> listPaginated(EspecialidadSedeBusquedaRequest request) {
        Respuesta<List<EspecialidadSedeResponse>> response = new Respuesta<>();
        List<EspecialidadSedeResponse> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Respuesta<EspecialidadSede>> insertar(EspecialidadSede request) {
        Respuesta<EspecialidadSede> response = new Respuesta<>();
        EspecialidadSede institucion = mapper.findByName(request);
    	
    	if(institucion==null) {
    		mapper.insert(request);
    		EspecialidadSede objSave = getById(request.getEspecialidadSedeId());
            auditoriaService.insert(new Auditoria(request.getNameTable(),
                    (request.getEspecialidadSedeId() == null) ? null : request.getEspecialidadSedeId().toString(),
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
	public ResponseEntity<Respuesta<EspecialidadSede>> actualizar(EspecialidadSede request) {
        		 
		 Respuesta<EspecialidadSede> response = new Respuesta<>();    	
		 EspecialidadSede objAu = getById(request.getEspecialidadSedeId());
	    	
	   		mapper.update(request);
	   		EspecialidadSede objSave = getById(request.getEspecialidadSedeId());
	         auditoriaService.insert(new Auditoria(request.getNameTable(),
	                 (request.getEspecialidadSedeId() == null) ? null : request.getEspecialidadSedeId().toString(),
	                 request.getAuditUsuarioModifica(),
	                 AuditTipoEnum.U.name(),
	                 JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));    
	         response.setData(objSave);
	         response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
	         return new ResponseEntity<>(response, HttpStatus.OK); 
	}

	@Override
	public ResponseEntity<Respuesta<EspecialidadSede>> eliminar(EspecialidadSede request) {
        Respuesta<EspecialidadSede> response = new Respuesta<>();

        EspecialidadSede objSave = getById(request.getEspecialidadSedeId());   
        mapper.delete(request.getEspecialidadSedeId());         
        auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                (objSave.getEspecialidadSedeId() == null) ? null : objSave.getEspecialidadSedeId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objSave), null));                        
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	EspecialidadSede getById(Long id) {
		EspecialidadSede objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }    
}