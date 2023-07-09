package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.model.domain.Arancel;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.mapper.ArancelMapper;
import pe.gob.minjus.sisarb.model.request.ArancelBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.ArancelResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.ArancelService;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import java.util.List;

@Slf4j
@Service
public class ArancelServiceImpl implements ArancelService {

    @Autowired
    ArancelMapper mapper;
    
    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<ArancelResponse>>> listPaginated(ArancelBusquedaRequest request) {
        Respuesta<List<ArancelResponse>> response = new Respuesta<>();
        List<ArancelResponse> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Respuesta<Arancel>> insertarArancel(Arancel request) {
        Respuesta<Arancel> response = new Respuesta<>();
        
    		mapper.insert(request);
    		Arancel objSave = getById(request.getArancelId());
            auditoriaService.insert(new Auditoria(request.getNameTable(),
                    (request.getArancelId() == null) ? null : request.getArancelId().toString(),
                    request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                    null, JsonConvert.objectToJsonString(objSave)));
            response.setData(objSave);
            response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);                
            return new ResponseEntity<>(response, HttpStatus.OK);        		
    	     
	}

	@Override
	public ResponseEntity<Respuesta<Arancel>> actualizarArancel(Arancel request) {
        Respuesta<Arancel> response = new Respuesta<>();    	
        Arancel objAu = getById(request.getArancelId());
    	
   		mapper.update(request);
   		Arancel objSave = getById(request.getArancelId());
         auditoriaService.insert(new Auditoria(request.getNameTable(),
                 (request.getArancelId() == null) ? null : request.getArancelId().toString(),
                 request.getAuditUsuarioModifica(),
                 AuditTipoEnum.U.name(),
                 JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));    
         response.setData(objSave);
         response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
         return new ResponseEntity<>(response, HttpStatus.OK); 
   	 }

	@Override
	public ResponseEntity<Respuesta<Arancel>> eliminarArancel(Arancel request) {
        Respuesta<Arancel> response = new Respuesta<>();

        Arancel objSave = getById(request.getArancelId());   
        mapper.delete(request.getArancelId());         
        auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                (objSave.getArancelId() == null) ? null : objSave.getArancelId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objSave), null));                        
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	Arancel getById(Integer id) {
		Arancel objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }    

}
