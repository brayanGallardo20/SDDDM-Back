package pe.gob.minjus.sisarb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minjus.sisarb.enums.AuditTipoEnum;
import pe.gob.minjus.sisarb.exception.EntityNotFoundException;
import pe.gob.minjus.sisarb.exception.EntityValidationCustom;
import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.domain.SedePersona;
import pe.gob.minjus.sisarb.model.mapper.PersonaNaturalMapper;
import pe.gob.minjus.sisarb.service.AuditoriaService;
import pe.gob.minjus.sisarb.service.ArbitroEspecializacionService;
import pe.gob.minjus.sisarb.service.PersonaService;
import pe.gob.minjus.sisarb.service.SedePersonaService;
import pe.gob.minjus.sisarb.utils.Constantes;
import pe.gob.minjus.sisarb.utils.JsonConvert;
import pe.gob.minjus.sisarb.utils.Validators;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaNaturalMapper mapper;

    @Autowired
    AuditoriaService auditoriaService;

    @Autowired
    SedePersonaService sedePersonaService;

    @Transactional
    @Override
    public PersonaNatural saveOrUpdate(PersonaNatural request) {
        PersonaNatural response = null;
        PersonaNatural pnFind = findByDocument(request);
        if (pnFind == null) {
            //save
            response = save(request);
        } else {
            //update
            if (evaluateForUpdateFieldsPersona(request, pnFind))
                response = update(request, pnFind.getPersonaNaturalId());
        }
        return response;
    }


    public PersonaNatural save(PersonaNatural request) {
        validFieldRequired(request.getAuditUsuarioCreacion(), Constantes.MSJ_VALIDACION_USUARIO_REGISTRO);
        mapper.save(request);
        if (request.getSedePersona()!=null) sedePersonaService.save(request.getSedePersona());
        PersonaNatural objSave = getById(request.getPersonaNaturalId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_PERSONA,
                (request.getPersonaNaturalId() == null) ? null : request.getPersonaNaturalId().toString(),
                request.getAuditUsuarioCreacion(), AuditTipoEnum.I.name(),
                null, JsonConvert.objectToJsonString(objSave)));
        return objSave;
    }


    public PersonaNatural update(PersonaNatural request, Integer personaNaturalId) {
        validFieldRequired(request.getAuditUsuarioModifica(), Constantes.MSJ_VALIDACION_USUARIO_MODIFICA);
        PersonaNatural objAu = getById(personaNaturalId);
        request.setPersonaNaturalId(personaNaturalId);
        mapper.update(request);
        if (request.getSedePersona()!=null) sedePersonaService.update(request.getSedePersona());

        log.info("Se actualizó la persona");

        PersonaNatural objUpdate = getById(request.getPersonaNaturalId());
        auditoriaService.insertNoTrans(new Auditoria(Constantes.TABLE_TRS_PERSONA,
                (request.getPersonaNaturalId() == null) ? null : request.getPersonaNaturalId().toString(),
                request.getAuditUsuarioModifica(),
                AuditTipoEnum.U.name(),
                JsonConvert.objectToJsonString(objAu), JsonConvert.objectToJsonString(objUpdate)));
        return objUpdate;
    }


    /**
     * Evalua si algun atribvuto de persona fue actualizado
     *
     * @param request es el objeto de PersonaNatural
     * @return Si es true, es porque se encontró un cambio
     */
    boolean evaluateForUpdateFieldsPersona(PersonaNatural request, PersonaNatural findInDb) {
        boolean isUpdate = false;
        if (!Validators.compareFieldsStrings(findInDb.getApellidoPaterno(), request.getApellidoPaterno())
                || !Validators.compareFieldsStrings(findInDb.getApellidoMaterno(), request.getApellidoMaterno())
                || !Validators.compareFieldsStrings(findInDb.getNombre(), request.getNombre())
                || !Validators.compareFieldsGeneric(findInDb.getUbigeoId(), request.getUbigeoId())
                || !Validators.compareFieldsStrings(findInDb.getDireccion(), request.getDireccion())
                || !Validators.compareFieldsStrings(findInDb.getTelefono(), request.getTelefono())
                || !Validators.compareFieldsStrings(findInDb.getCorreo(), request.getCorreo())
        ) isUpdate = true;
        return isUpdate;
    }


    /**
     * Valida si el campo que se envía como parametro exista
     *
     * @param campo   es algun atributo de PersonaNatural
     * @param mensaje es el mensaje que se mostrará al enviar la excepción (el usuario creación es obligatorio)
     * @throws EntityValidationCustom en caso el campo sea nulo o vacío
     */
    void validFieldRequired(String campo, String mensaje) {
        if (campo == null || campo.isEmpty()) {
            throw new EntityValidationCustom(mensaje);
        }
    }

    PersonaNatural getById(Integer id) {
        PersonaNatural objBusq = mapper.findById(id);
        if (objBusq == null) {
            throw new EntityNotFoundException(Constantes.MSJ_VALIDACION_NO_EXISTE_REGISTRO_ID + id);
        }
        return objBusq;
    }

    PersonaNatural findByDocument(PersonaNatural request) {
        PersonaNatural response = null;
        response = mapper.findByDocumentPersona(request);
        return response;
    }

    // SedePersona → Debe venir con: sedeId, personaCargoId
    SedePersona saveSedePersona(PersonaNatural personaNatural,SedePersona sedePersona){
        sedePersona.setPersonaId(personaNatural.getPersonaNaturalId());
        return sedePersonaService.save(sedePersona);
    }

    SedePersona updateSedeUpdate(Integer personaNaturalId, SedePersona sedePersona){
        sedePersona.setPersonaId(personaNaturalId);
        return sedePersonaService.save(sedePersona);

    }


}
