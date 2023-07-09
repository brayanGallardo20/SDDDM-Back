package pe.gob.minjus.sisarb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.domain.Secretario;
import pe.gob.minjus.sisarb.model.mapper.PersonaNaturalMapper;
import pe.gob.minjus.sisarb.model.mapper.SecretarioMapper;
import pe.gob.minjus.sisarb.service.SecretarioService;

import java.util.List;

@Service
public class SecretarioServiceImpl implements SecretarioService {

    @Autowired
    SecretarioMapper secretarioMapper;

    @Autowired
    PersonaNaturalMapper personaNaturalMapper;

    @Override
    public Secretario save(Secretario model) {
         //Verificar si existe la persona con el numero de documento y tipo documento.

        return null;
    }

    PersonaNatural getPersNatuByTipoDocAndNumDocu(Integer tipoDocumentoId, String numeroDocumento){
        PersonaNatural personaNatural = new PersonaNatural();
        personaNatural.setNumeroDocumento(numeroDocumento);
        personaNatural.setTipoDocumentoId(tipoDocumentoId);
        List<PersonaNatural> listPerNatuBusqueda =  personaNaturalMapper.listByField(personaNatural);
        if(listPerNatuBusqueda.size()==1){
            return listPerNatuBusqueda.get(0);
        }else{
            return null;
        }
    }

    Secretario getSecretarioByPerNatuId(Integer personaNaturalId){
        Secretario secretario = new Secretario();
        secretario.setPersonaNatural(new PersonaNatural(personaNaturalId));
        List<Secretario> listSecretarioBusqueda =  secretarioMapper.listByField(secretario);
        if(listSecretarioBusqueda.size()==1){
            return listSecretarioBusqueda.get(0);
        }else{
            return null;
        }
    }
}
