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
import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.mapper.InstitucionMapper;
import pe.gob.minjus.sisarb.model.mapper.InstitucionPersonaMapper;
import pe.gob.minjus.sisarb.model.mapper.PersonaNaturalMapper;
import pe.gob.minjus.sisarb.model.request.InstitucionBusquedaRequest;
import pe.gob.minjus.sisarb.model.request.InstitucionRequest;
import pe.gob.minjus.sisarb.model.response.InstitucionPersonaResponse;
import pe.gob.minjus.sisarb.model.response.InstitucionResponse;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.InstitucionService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import java.util.List;

@Slf4j
@Service
public class InstitucionServiceImpl implements InstitucionService {

    @Autowired
    InstitucionMapper mapper;
    
    @Autowired
    AuditoriaService auditoriaService;
    
    @Autowired
    PersonaNaturalMapper mapperPersonaNatural;
    
    @Autowired InstitucionPersonaMapper oInstitucionPersonaMapper;

    @Override
    public ResponseEntity<Respuesta<List<InstitucionResponse>>> listPaginated(InstitucionBusquedaRequest request) {
        Respuesta<List<InstitucionResponse>> response = new Respuesta<>();
        List<InstitucionResponse> list = mapper.listPaginated(request);
        Integer total = mapper.listPaginatedTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Respuesta<InstitucionPersonaResponse>> findInstitutionById(Integer institutionId) {
        Respuesta<InstitucionPersonaResponse> response = new Respuesta<>();
       
        InstitucionPersonaResponse institucionPersonaResponse = new InstitucionPersonaResponse();
        Institucion institucion = mapper.findById(institutionId);
        
        InstitucionPersona institucionPers = new InstitucionPersona();
        institucionPers.setPersonaCargoId(Constantes.PERSONA_CARGO_ID_REPRESENTANTE);
        institucionPers.setInstitucionId(institutionId);
        
        InstitucionPersona institucionPersona = oInstitucionPersonaMapper.findByIntitucionPersona(institucionPers);    
        PersonaNatural personaNatural = mapper.findByPersonaNatural(institucionPersona.getPersonaId());
        
        institucionPersonaResponse.setPersona(personaNatural);
        institucionPersonaResponse.setInstitucion(institucion);
        institucionPersonaResponse.setInstitucionPersona(institucionPersona);
        response.setData(institucionPersonaResponse);
        response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);                
        return new ResponseEntity<>(response, HttpStatus.OK);        		   		
	}        
  
	@Override
	public ResponseEntity<Respuesta<InstitucionPersonaResponse>> insertarInstitucion(InstitucionRequest request) {
        Respuesta<InstitucionPersonaResponse> response = new Respuesta<>();
        InstitucionPersonaResponse institucionPersonaResponse = new InstitucionPersonaResponse();
        Institucion institucionRequest = request.getInstitucion();
        
        Institucion institucion = mapper.findByName(institucionRequest);
   	 
        if(institucion == null) {
        /* insert persona */
        PersonaNatural oPersonaNaturalRequest = request.getPersona();
        PersonaNatural persona =  mapperPersonaNatural.findByDocumentPersona(oPersonaNaturalRequest);
        PersonaNatural personaIni = persona;
        
        if(persona == null) {
        	oPersonaNaturalRequest.setAuditUsuarioCreacion(institucionRequest.getAuditUsuarioCreacion());
        	mapperPersonaNatural.save(oPersonaNaturalRequest);
        	
        	PersonaNatural objPersNat = mapperPersonaNatural.findById(oPersonaNaturalRequest.getPersonaNaturalId());
        	
            auditoriaService.insert(new Auditoria(oPersonaNaturalRequest.getNameTable(),
                    (oPersonaNaturalRequest.getPersonaNaturalId() == null) ? null : oPersonaNaturalRequest.getPersonaNaturalId().toString(),
                    		oPersonaNaturalRequest.getAuditUsuarioCreacion(),
                    AuditTipoEnum.I.name(),
                    null, JsonConvert.objectToJsonString(objPersNat)));
        	 
        } else {
        	persona.setUbigeoId(oPersonaNaturalRequest.getUbigeoId());
       	 persona.setTipoDocumentoId(oPersonaNaturalRequest.getTipoDocumentoId());
       	 persona.setTelefono(oPersonaNaturalRequest.getTelefono());
       	 persona.setApellidoMaterno(oPersonaNaturalRequest.getApellidoMaterno());
       	 persona.setApellidoPaterno(oPersonaNaturalRequest.getApellidoPaterno());
       	 persona.setNombre(oPersonaNaturalRequest.getNombre());
       	 persona.setDireccion(oPersonaNaturalRequest.getDireccion());
       	 persona.setCorreo(oPersonaNaturalRequest.getCorreo());
         persona.setAuditUsuarioModifica(institucionRequest.getAuditUsuarioCreacion());
       	 
       	 mapperPersonaNatural.update(persona);
       	 
       	PersonaNatural objPersNat = mapperPersonaNatural.findById(oPersonaNaturalRequest.getPersonaNaturalId());
    	
         auditoriaService.insert(new Auditoria(persona.getNameTable(),
                 (persona.getPersonaNaturalId() == null) ? null : persona.getPersonaNaturalId().toString(),
                		 persona.getAuditUsuarioModifica(),
                 AuditTipoEnum.U.name(),
                 JsonConvert.objectToJsonString(personaIni), JsonConvert.objectToJsonString(objPersNat)));
         
       	oPersonaNaturalRequest.setPersonaNaturalId(persona.getPersonaNaturalId());
       	oPersonaNaturalRequest.setAuditUsuarioCreacion(institucionRequest.getAuditUsuarioCreacion());
        }
        /* insert intitucion */

    		mapper.insertInstitution(institucionRequest);
    		Institucion objSave = getById(institucionRequest.getInstitucionId());

            auditoriaService.insert(new Auditoria(institucionRequest.getNameTable(),
                    (institucionRequest.getInstitucionId() == null) ? null : institucionRequest.getInstitucionId().toString(),
                    		institucionRequest.getAuditUsuarioCreacion(),
                    AuditTipoEnum.I.name(),
                    null, JsonConvert.objectToJsonString(objSave)));
            
            /* insert intitucion persona */
            InstitucionPersona institucionPersona = new InstitucionPersona();
            institucionPersona.setInstitucionId(institucionRequest.getInstitucionId());
            institucionPersona.setPersonaId(oPersonaNaturalRequest.getPersonaNaturalId());
            institucionPersona.setAuditUsuarioCreacion(institucionRequest.getAuditUsuarioCreacion());
            institucionPersona.setPersonaCargoId(Constantes.PERSONA_CARGO_ID_REPRESENTANTE);
            oInstitucionPersonaMapper.insertInstitucionPersona(institucionPersona);
            
            InstitucionPersona instPer =oInstitucionPersonaMapper.findById(institucionPersona.getInstitucionPersonaId());
            
            auditoriaService.insert(new Auditoria(institucionPersona.getNameTable(),
                    (institucionPersona.getInstitucionPersonaId() == null) ? null : institucionPersona.getInstitucionPersonaId().toString(),
                    		institucionPersona.getAuditUsuarioCreacion(),
                    AuditTipoEnum.I.name(),
                    null, JsonConvert.objectToJsonString(instPer)));
            
            institucionPersonaResponse.setPersona(oPersonaNaturalRequest);
            institucionPersonaResponse.setInstitucion(institucionRequest);
            institucionPersonaResponse.setInstitucionPersona(institucionPersona);
            response.setData(institucionPersonaResponse);
            response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);                
            return new ResponseEntity<>(response, HttpStatus.OK);        		
    	}else {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_RUC_EXISTE);     		
    	}        
 
	}

	@Override
	public ResponseEntity<Respuesta<Institucion>> actualizarInstitucion(InstitucionRequest request) {
        Respuesta<Institucion> response = new Respuesta<>();    
        Institucion institucion = request.getInstitucion();
        InstitucionPersona insPersonaIni = new InstitucionPersona();
                
        Institucion objAu = getById(institucion.getInstitucionId());
    	
   	 if(!institucion.getRuc().toUpperCase().trim().equals(objAu.getRuc().toUpperCase().trim())){
   	 
   		Institucion list = mapper.findByName(institucion);
         if(list==null) {
        	 
        	 PersonaNatural oPersonaNaturalRequest = request.getPersona();
             PersonaNatural persona =  mapperPersonaNatural.findByDocumentPersona(oPersonaNaturalRequest);
            
             if(persona == null) {
            	 mapperPersonaNatural.save(oPersonaNaturalRequest);
            	 
            	PersonaNatural objPersNat = mapperPersonaNatural.findById(oPersonaNaturalRequest.getPersonaNaturalId());
                	
                 auditoriaService.insert(new Auditoria(oPersonaNaturalRequest.getNameTable(),
                         (oPersonaNaturalRequest.getPersonaNaturalId() == null) ? null : oPersonaNaturalRequest.getPersonaNaturalId().toString(),
                        		 oPersonaNaturalRequest.getAuditUsuarioCreacion(),
                         AuditTipoEnum.I.name(),
                         null, JsonConvert.objectToJsonString(objPersNat)));
                 
             }  else {
            	 oPersonaNaturalRequest.setPersonaNaturalId(persona.getPersonaNaturalId());
             }
             
             InstitucionPersona institucionPers = new InstitucionPersona();
             institucionPers.setPersonaCargoId(Constantes.PERSONA_CARGO_ID_REPRESENTANTE);
             institucionPers.setInstitucionId(institucion.getInstitucionId());
                        
             InstitucionPersona insPersona = oInstitucionPersonaMapper.findByIntitucionPersona(institucionPers);
             insPersonaIni = insPersona;
             insPersona.setPersonaId(oPersonaNaturalRequest.getPersonaNaturalId());
             insPersona.setAuditUsuarioModifica(institucion.getAuditUsuarioModifica());
             oInstitucionPersonaMapper.updateInstitucionPersona(insPersona);
             InstitucionPersona instPer =oInstitucionPersonaMapper.findById(insPersona.getInstitucionPersonaId());
             
             auditoriaService.insert(new Auditoria(insPersona.getNameTable(),
                     (insPersona.getInstitucionPersonaId() == null) ? null : insPersona.getInstitucionPersonaId().toString(),
                    		 insPersona.getAuditUsuarioModifica(),
                     AuditTipoEnum.U.name(),
                     JsonConvert.objectToJsonString(insPersonaIni), JsonConvert.objectToJsonString(instPer)));  
             
        	 mapper.update(institucion); 
        	 
        	 Institucion objSave = getById(institucion.getInstitucionId());
        	 
        	 auditoriaService.insert(new Auditoria(institucion.getNameTable(),
                     (institucion.getInstitucionId() == null) ? null : institucion.getInstitucionId().toString(),
                    		 institucion.getAuditUsuarioModifica(),
                     AuditTipoEnum.U.name(),
                     JsonConvert.objectToJsonString(list), JsonConvert.objectToJsonString(institucion)));
        	 
             response.setData(objSave);
             response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
             return new ResponseEntity<>(response, HttpStatus.OK);
         }else {
             throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_REGISTRO_REPETIDO);              	
         }       		 
   	 
   	 } else {
   	  	 PersonaNatural oPersonaNaturalRequest = request.getPersona();
         PersonaNatural personaIni = mapperPersonaNatural.findByDocumentPersona(oPersonaNaturalRequest);
         PersonaNatural persona =  mapperPersonaNatural.findByDocumentPersona(oPersonaNaturalRequest);
     
         if(persona == null) {
        	 oPersonaNaturalRequest.setAuditUsuarioCreacion(institucion.getAuditUsuarioModifica());
        	 mapperPersonaNatural.save(oPersonaNaturalRequest);
        	 PersonaNatural objPersNat = mapperPersonaNatural.findById(oPersonaNaturalRequest.getPersonaNaturalId());
             
        	 auditoriaService.insert(new Auditoria(oPersonaNaturalRequest.getNameTable(),
                     (oPersonaNaturalRequest.getPersonaNaturalId() == null) ? null : oPersonaNaturalRequest.getPersonaNaturalId().toString(),
                    		 oPersonaNaturalRequest.getAuditUsuarioCreacion(),
                     AuditTipoEnum.I.name(),
                     null, JsonConvert.objectToJsonString(objPersNat)));
        	 
         }  else {
        	 persona.setUbigeoId(oPersonaNaturalRequest.getUbigeoId());
        	 persona.setTipoDocumentoId(oPersonaNaturalRequest.getTipoDocumentoId());
        	 persona.setTelefono(oPersonaNaturalRequest.getTelefono());
        	 persona.setApellidoMaterno(oPersonaNaturalRequest.getApellidoMaterno());
        	 persona.setApellidoPaterno(oPersonaNaturalRequest.getApellidoPaterno());
        	 persona.setNombre(oPersonaNaturalRequest.getNombre());
        	 persona.setDireccion(oPersonaNaturalRequest.getDireccion());
        	 persona.setCorreo(oPersonaNaturalRequest.getCorreo());
        	 persona.setAuditUsuarioModifica(institucion.getAuditUsuarioModifica());
        	 mapperPersonaNatural.update(persona);
        	 PersonaNatural objPersNat = mapperPersonaNatural.findById(persona.getPersonaNaturalId());
             
        	 auditoriaService.insert(new Auditoria(persona.getNameTable(),
                     (persona.getPersonaNaturalId() == null) ? null : persona.getPersonaNaturalId().toString(),
                    		 persona.getAuditUsuarioModifica(),
                     AuditTipoEnum.U.name(),
                     JsonConvert.objectToJsonString(personaIni), JsonConvert.objectToJsonString(objPersNat)));
        	 
        	 oPersonaNaturalRequest.setPersonaNaturalId(persona.getPersonaNaturalId());
         }
         
         InstitucionPersona institucionPers = new InstitucionPersona();
         institucionPers.setPersonaCargoId(Constantes.PERSONA_CARGO_ID_REPRESENTANTE);
         institucionPers.setInstitucionId(institucion.getInstitucionId());
             
         InstitucionPersona insPersona = oInstitucionPersonaMapper.findByIntitucionPersona(institucionPers);
         insPersonaIni = insPersona;
         insPersona.setPersonaId(oPersonaNaturalRequest.getPersonaNaturalId());
         insPersona.setAuditUsuarioModifica(institucion.getAuditUsuarioModifica());
         oInstitucionPersonaMapper.updateInstitucionPersona(insPersona);
         
         InstitucionPersona instPer = oInstitucionPersonaMapper.findById(insPersona.getInstitucionPersonaId());
         
         auditoriaService.insert(new Auditoria(insPersona.getNameTable(),
                 (insPersona.getInstitucionPersonaId() == null) ? null : insPersona.getInstitucionPersonaId().toString(),
                		 insPersona.getAuditUsuarioModifica(),
                 AuditTipoEnum.U.name(),
                 JsonConvert.objectToJsonString(insPersonaIni), JsonConvert.objectToJsonString(instPer)));  
         
   		 mapper.update(institucion);
   		
   		 Institucion objSave = getById(institucion.getInstitucionId());
         auditoriaService.insert(new Auditoria(institucion.getNameTable(),
                 (institucion.getInstitucionId() == null) ? null : institucion.getInstitucionId().toString(),
                		 institucion.getAuditUsuarioModifica(),
                 AuditTipoEnum.U.name(),
                 JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objSave)));    
         response.setData(objSave);
         
         response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
         return new ResponseEntity<>(response, HttpStatus.OK); 
   	 }
	}

	@Override
	public ResponseEntity<Respuesta<Institucion>> eliminarInstitucion(Institucion request) {
        Respuesta<Institucion> response = new Respuesta<>();

   
        Institucion objSave = getById(request.getInstitucionId());   
        mapper.delete(request.getInstitucionId());  
        
        InstitucionPersona institucionPers = new InstitucionPersona();
        institucionPers.setPersonaCargoId(Constantes.PERSONA_CARGO_ID_REPRESENTANTE);
        institucionPers.setInstitucionId(request.getInstitucionId());
        
        InstitucionPersona insPersona = oInstitucionPersonaMapper.findByIntitucionPersona(institucionPers); 
        
        InstitucionPersona oInstitucionPersona = new InstitucionPersona();
        oInstitucionPersona.setInstitucionId(request.getInstitucionId());
        oInstitucionPersona.setAuditUsuarioModifica(request.getAuditUsuarioModifica());
        oInstitucionPersona.setPersonaId(insPersona.getPersonaId());
        oInstitucionPersonaMapper.delete(oInstitucionPersona);
        
        auditoriaService.insert(new Auditoria(objSave.getNameTable(),
                (objSave.getInstitucionId() == null) ? null : objSave.getInstitucionId().toString(),
                		request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objSave), null));                        
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);
        return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	Institucion getById(Integer id) {
		Institucion objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }    

}
