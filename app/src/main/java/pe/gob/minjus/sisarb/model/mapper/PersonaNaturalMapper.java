package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.PersonaNatural;

import java.util.List;

public interface PersonaNaturalMapper {
    PersonaNatural findById(Integer id);
    Integer save(PersonaNatural model);
    Integer update(PersonaNatural model);
    PersonaNatural findByDocumentPersona(PersonaNatural request);
    List<PersonaNatural> listByField(PersonaNatural request);
    Integer countByField(PersonaNatural model);

}
