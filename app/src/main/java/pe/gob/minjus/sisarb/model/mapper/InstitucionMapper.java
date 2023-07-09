package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.PersonaNatural;
import pe.gob.minjus.sisarb.model.request.InstitucionBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.InstitucionResponse;
import java.util.List;

public interface InstitucionMapper {
    List<InstitucionResponse> listPaginated(InstitucionBusquedaRequest request);
    Integer listPaginatedTotal (InstitucionBusquedaRequest request);
    void insertInstitution(Institucion request);
    void update(Institucion request);
    void delete(int id);
    Institucion find(Institucion request);
    Institucion findByName(Institucion request); 
    PersonaNatural findByPersonaNatural(Integer personaId); 
    Institucion findById(Integer id);    
}
