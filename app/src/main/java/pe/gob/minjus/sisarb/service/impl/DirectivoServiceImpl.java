package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.*;
import pe.gob.minjus.sisarb.model.mapper.DirectivoMapper;
import pe.gob.minjus.sisarb.model.request.DirectivoBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.DirectivoService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;

import java.util.List;

@Slf4j
@Service
public class DirectivoServiceImpl implements DirectivoService {

    @Autowired
    DirectivoMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ResponseEntity<Respuesta<List<Directivo>>> listBusqueda(DirectivoBusquedaRequest request) {
        Respuesta<List<Directivo>> response = new Respuesta<>();

        List<Directivo> list = mapper.listBusqueda(request);
        Integer total = mapper.listBusquedaTotal(request);
        response.setData(list);
        response.setTotalRegistros(total);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Directivo>> findById(Directivo request) {
        Respuesta<Directivo> response = new Respuesta<>();

        Directivo directivo = mapper.findByIdInstitutePerson(request.getInstitucionPersonaId());
        response.setData(directivo);
        response.setMensaje(Constantes.MSJ_CRUD_LISTADO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Directivo>> insert(Directivo request)  {
        Respuesta<Directivo> response = new Respuesta<>();
        PersonaNatural persona = findByDocumentPersona(request);
        PersonaNatural existeDirectivo =  countDirectivo(request);
        PersonaNatural existeDirectivoGlobal =  countDirectivoGlobal(request);

        if(existeDirectivoGlobal == null) {
            if (existeDirectivo == null) {
                if(persona == null){
                    persona = new PersonaNatural();
                    persona.setActivo(1);
                    persona.setNombre(request.getNombre());
                    persona.setApellidoMaterno(request.getApellidoMaterno());
                    persona.setApellidoPaterno(request.getApellidoPaterno());
                    persona.setNumeroDocumento(request.getNumeroDocumento());
                    persona.setTipoDocumentoId(request.getTipoDocumentoId());
                    persona.setAuditUsuarioCreacion(request.getAuditUsuarioCreacion());
                    mapper.insertPersona(persona);
                } else {
                    persona.setNumeroDocumento(request.getNumeroDocumento());
                    persona.setTipoDocumentoId(request.getTipoDocumentoId());
                    persona.setNombre(request.getNombre());
                    persona.setApellidoMaterno(request.getApellidoMaterno());
                    persona.setApellidoPaterno(request.getApellidoPaterno());
                    mapper.update(persona);
                }
                    request.setPersonaNaturalId(persona.getPersonaNaturalId());
                    InstitucionPersona institucionPersona = new InstitucionPersona();
                    institucionPersona.setActivo(1);
                    institucionPersona.setPersonaId(persona.getPersonaNaturalId());
                    institucionPersona.setInstitucionId(request.getInstitucionId());
                    institucionPersona.setPersonaCargoId(Constantes.DIRECTIVO);
                    institucionPersona.setAuditUsuarioCreacion(request.getAuditUsuarioCreacion());
                    mapper.insertInstitutoPersona(institucionPersona);
                    request.setInstitucionPersonaId(institucionPersona.getInstitucionPersonaId());
                    Directivo objSave = mapper.findByIdDirectivo(request);
                    auditoriaService.insertNoTrans(new Auditoria(Constantes.TRS_INSTITUCION_PERSONA,
                        (request.getInstitucionPersonaId() == null) ? null : request.getInstitucionPersonaId().toString(),
                        request.getAuditUsuarioCreacion(),  AuditTipoEnum.I.name(),
                        null, JsonConvert.objectToJsonString(objSave)));
                    response.setData(request);
                    response.setMensaje(Constantes.MSJ_CRUD_REGISTRO);

                    return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_CANTIDAD_DIRECTIVO);
            }
        } else {
            throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_CANTIDAD_DIRECTIVO_GLOBAL);
        }
    }

    @Override
    public ResponseEntity<Respuesta<Directivo>> update(Directivo request) {
        Respuesta<Directivo> response = new Respuesta<>();
        PersonaNatural persona =  findByDocumentPersona(request);
        PersonaNatural existeDirectivoGlobal =  countDirectivoGlobal(request);
        Directivo objAu = getByIdDirectivo(request);
        request.setPersonaNaturalId(objAu.getPersonaNaturalId());
        PersonaNatural personaOriginal =  findByIdPersona(request);

        if(persona == null){
            persona = new PersonaNatural();
            persona.setActivo(1);
            persona.setNombre(request.getNombre());
            persona.setApellidoMaterno(request.getApellidoMaterno());
            persona.setApellidoPaterno(request.getApellidoPaterno());
            persona.setNumeroDocumento(request.getNumeroDocumento());
            persona.setTipoDocumentoId(request.getTipoDocumentoId());
            persona.setAuditUsuarioCreacion(request.getAuditUsuarioCreacion());
            mapper.insertPersona(persona);
        } else {
            //Actualizar el mismo
            if((!request.getNumeroDocumento().equals(personaOriginal.getNumeroDocumento())) && (request.getTipoDocumentoId() != personaOriginal.getTipoDocumentoId())){
                if(existeDirectivoGlobal != null){
                    throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_CANTIDAD_DIRECTIVO_GLOBAL);
                }
            } else if((request.getNumeroDocumento().equals(personaOriginal.getNumeroDocumento())) && (request.getTipoDocumentoId().equals(personaOriginal.getTipoDocumentoId()))){
                persona.setNumeroDocumento(request.getNumeroDocumento());
                persona.setTipoDocumentoId(request.getTipoDocumentoId());
                persona.setNombre(request.getNombre());
                persona.setApellidoMaterno(request.getApellidoMaterno());
                persona.setApellidoPaterno(request.getApellidoPaterno());
                mapper.update(persona);
            } else {
                if(existeDirectivoGlobal != null){
                    throw new EntityValidationCustom(Constantes.MSJ_VALIDACION_CANTIDAD_DIRECTIVO_GLOBAL);
                }
            }
        }
            InstitucionPersona institucionPersona = new InstitucionPersona();
            institucionPersona.setActivo(1);
            institucionPersona.setInstitucionPersonaId(request.getInstitucionPersonaId());
            institucionPersona.setPersonaId(persona.getPersonaNaturalId());
            institucionPersona.setInstitucionId(request.getInstitucionId());
            institucionPersona.setPersonaCargoId(Constantes.DIRECTIVO);
            mapper.updateInstitutoPersona(institucionPersona);
            Directivo objUpdate = getByIdDirectivo(request);
            auditoriaService.insertNoTrans(new Auditoria(Constantes.TRS_INSTITUCION_PERSONA,
                (request.getInstitucionPersonaId() == null) ? null : request.getInstitucionPersonaId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
            response.setData(request);
            response.setMensaje(Constantes.MSJ_CRUD_MODIFICAR);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<Respuesta<PersonaNatural>> deleteById(Directivo request)  {
        Respuesta<PersonaNatural> response = new Respuesta<>();
        Directivo objGet = getByIdDirectivo(request);
        mapper.deleteById(request.getInstitucionPersonaId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TRS_INSTITUCION_PERSONA,
                (objGet.getInstitucionPersonaId() == null) ? null : objGet.getInstitucionPersonaId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.D.name(),
                JsonConvert.objectToJsonString(objGet), null));
        response.setData(null);
        response.setMensaje(Constantes.MSJ_CRUD_ELIMINAR);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    PersonaNatural getById(Integer id){
        PersonaNatural objBusq = mapper.findById(id);
        if(objBusq == null){
            throw new EntityNotFoundException("NO EXISTE EL REGISTRO CON ID: "+id);
        }
        return objBusq;
    }

    Directivo getByIdDirectivo(Directivo request){
        Directivo objBusq = mapper.findByIdDirectivo(request);
        if(objBusq == null){
            throw new EntityNotFoundException("NO EXISTE EL REGISTRO CON ID: ");
        }
        return objBusq;
    }

    PersonaNatural getByDocumentNumber(Directivo request){
        return mapper.findByDocumentNumber(request);
    }

    PersonaNatural countDirectivo(Directivo request){
        return mapper.countDirectivo(request);
    }

    PersonaNatural countDirectivoGlobal(Directivo request){
        return mapper.countDirectivoGlobal(request);
    }

    PersonaNatural findByDocumentPersona(Directivo request){
        return mapper.findByDocumentPersona(request);
    }
    PersonaNatural findByIdPersona(Directivo request){
        return mapper.findByIdPersona(request);
    }
}
