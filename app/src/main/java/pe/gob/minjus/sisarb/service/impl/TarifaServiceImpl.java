package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.Tarifa;
import pe.gob.minjus.sisarb.model.mapper.TarifaMapper;
import pe.gob.minjus.sisarb.model.request.TarifaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.model.response.TarifaResponse;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.TarifaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import java.util.List;

@Slf4j
@Service
public class TarifaServiceImpl implements TarifaService {

    @Autowired
    TarifaMapper mapper;
    
    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<TarifaResponse>>> listPaginated(TarifaBusquedaRequest request) {
        Respuesta<List<TarifaResponse>> response = new Respuesta<>();
        List<TarifaResponse> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Respuesta<Tarifa>> insertar(Tarifa request) {
        Respuesta<Tarifa> response = new Respuesta<>();
        
    		mapper.insert(request);
    		Tarifa objSave = getById(request.getTarifaId());
            auditoriaService.insert(new Auditoria(request.getNameTable(),
                    (request.getTarifaId() == null) ? null : request.getTarifaId().toString(),
                    request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                    null, JsonConvert.objectToJsonString(objSave)));
            response.setData(objSave);
            response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);                
            return new ResponseEntity<>(response, HttpStatus.OK);        		
    	     
	}

	@Override
	public ResponseEntity<Respuesta<Tarifa>> actualizar(Tarifa request) {
        Respuesta<Tarifa> response = new Respuesta<>();    	
        Tarifa objAu = getById(request.getTarifaId());
    	
   		mapper.update(request);
   		Tarifa objSave = getById(request.getTarifaId());
         auditoriaService.insert(new Auditoria(request.getNameTable(),
                 (request.getTarifaId() == null) ? null : request.getTarifaId().toString(),
                 request.getAuditUsuarioModifica(),
                 AuditTipoEnum.U.name(),
                 JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));    
         response.setData(objSave);
         response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
         return new ResponseEntity<>(response, HttpStatus.OK); 
   	 }

	@Override
	public ResponseEntity<Respuesta<Tarifa>> eliminar(Tarifa request) {
        Respuesta<Tarifa> response = new Respuesta<>();

        Tarifa objSave = getById(request.getTarifaId());   
        mapper.delete(request.getTarifaId());         
        auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                (objSave.getTarifaId() == null) ? null : objSave.getTarifaId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objSave), null));                        
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	Tarifa getById(Integer id) {
		Tarifa objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }    

}
