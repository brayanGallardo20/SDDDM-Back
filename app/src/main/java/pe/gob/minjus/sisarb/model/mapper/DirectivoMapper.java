package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Directivo;
import pe.gob.minjus.sisarb.model.domain.InstitucionPersona;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.request.DirectivoBusquedaRequest;

import java.util.List;

public interface DirectivoMapper {


    List<Directivo> listBusqueda(DirectivoBusquedaRequest request);
    Integer listBusquedaTotal (DirectivoBusquedaRequest request);
    PersonaNatural findById(Integer id);
    Directivo findByIdDirectivo(Directivo request);
    Directivo findByIdInstitutePerson(Integer id);
    PersonaNatural findByDocumentNumber(Directivo request);
    PersonaNatural countDirectivo(Directivo request);
    PersonaNatural countDirectivoGlobal(Directivo request);
    PersonaNatural findByDocumentPersona(Directivo request);
    PersonaNatural findByIdPersona(Directivo request);
    Integer insertPersona(PersonaNatural request);
    Integer insertInstitutoPersona(InstitucionPersona request);
    Integer updateInstitutoPersona(InstitucionPersona request);
    Integer update(PersonaNatural request);
    Integer deleteById(Integer id);
}
