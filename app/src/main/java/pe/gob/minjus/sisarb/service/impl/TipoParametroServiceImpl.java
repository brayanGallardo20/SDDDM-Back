package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.TipoParametro;
import pe.gob.minjus.sisarb.model.mapper.TipoParametroMapper;
import pe.gob.minjus.sisarb.model.request.TipoParametroRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.TipoParametroService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class TipoParametroServiceImpl implements TipoParametroService {

    @Autowired
    TipoParametroMapper tipoParametroMapper;

    @Autowired
    AuditoriaService auditoriaService;
    
    @Transactional
    @Override
    public ResponseEntity<Respuesta<List<TipoParametro>>> listarTiposParametros(TipoParametroRequest request) {
        Respuesta<List<TipoParametro>> response = new Respuesta<>();

            List<TipoParametro> list = tipoParametroMapper.listTipoParametro();
            Integer total = tipoParametroMapper.listTipoParametroTotal(request);
            response.setData(list);
            response.setTotalRegistros(total);
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @Transactional
    @Override
    public ResponseEntity<Respuesta<List<TipoParametro>>> listarTiposParametrosActivos() {
        Respuesta<List<TipoParametro>> response = new Respuesta<>();

            List<TipoParametro> list = tipoParametroMapper.listTipoParametroAll();
            response.setData(list);
            response.setTotalRegistros(list.size());
            response.setMensaje("Se encontraron resultados");
            return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @Transactional
    @Override
    public ResponseEntity<Respuesta<TipoParametro>> insertTiposParametros(TipoParametro request) {
        Respuesta<TipoParametro> response = new Respuesta<>();

            TipoParametro list = tipoParametroMapper.find(request);
            if(list==null) {
                tipoParametroMapper.insert(request);
                TipoParametro objSave = getById(request.getTipoParametroId());                
                auditoriaService.insert(new Auditoria(request.getNameTable(),
                        (request.getTipoParametroId() == null) ? null : request.getTipoParametroId().toString(),
                        request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                        null, JsonConvert.objectToJsonString(objSave)));
                response.setData(objSave);
                response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);
                return new ResponseEntity<>(response, HttpStatus.OK);            	
            }else {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);                      	
            }           

    } 
    
    TipoParametro getById(Integer id) {
        TipoParametro objBusq = tipoParametroMapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }

    
    @Override
    public ResponseEntity<Respuesta<TipoParametro>> updateTiposParametros(TipoParametro request) {
        Respuesta<TipoParametro> response = new Respuesta<>();

        	TipoParametro objAu = getById(request.getTipoParametroId());
        	
       	 if(!request.getNombre().toUpperCase().trim().equals(objAu.getNombre().toUpperCase().trim())){
       		 
            TipoParametro list = tipoParametroMapper.find(request);
            if(list==null) {
                tipoParametroMapper.update(request);              
                TipoParametro objSave = getById(request.getTipoParametroId());                
                auditoriaService.insert(new Auditoria(request.getNameTable(),
                        (request.getTipoParametroId() == null) ? null : request.getTipoParametroId().toString(),
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
             tipoParametroMapper.update(request);              
             TipoParametro objSave = getById(request.getTipoParametroId());                
             auditoriaService.insert(new Auditoria(request.getNameTable(),
                     (request.getTipoParametroId() == null) ? null : request.getTipoParametroId().toString(),
                     request.getAuditUsuarioModifica(),
                     AuditTipoEnum.U.name(),
                     JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));
             response.setData(objSave);
             response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);                                              
             return new ResponseEntity<>(response, HttpStatus.OK);          
       	 }
    }
    
    @Override
    public ResponseEntity<Respuesta<TipoParametro>> deleteTiposParametros(TipoParametro request) {
        Respuesta<TipoParametro> response = new Respuesta<>();

        	int idTipoParametro=request.getTipoParametroId();
        	validBeforeDelete(idTipoParametro);
            TipoParametro objSave = getById(request.getTipoParametroId());
            tipoParametroMapper.delete(String.valueOf(idTipoParametro));            
            auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                    (objSave.getTipoParametroId() == null) ? null : objSave.getTipoParametroId().toString(),
                    request.getAuditUsuarioModifica(),
                    AuditTipoEnum.D.name(),
                    JsonConvert.objectToJsonString(objSave), null));          
            response.setData(null);
            response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    void validBeforeDelete(Integer parametroId) {
        Integer countParametroId = tipoParametroMapper.countTipoParametroById(parametroId);
        if (countParametroId > 0) {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_RELACIONADO);            
        }
    }
}
