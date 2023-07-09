package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Institucion;
import pe.gob.minjus.sisarb.model.domain.Parametro;
import pe.gob.minjus.sisarb.model.request.ParametroBusquedaRequest;

import java.util.List;

public interface ParametroMapper {
    List<Parametro> listPaginated(ParametroBusquedaRequest request);
    Integer listPaginatedTotal (ParametroBusquedaRequest request);
    void insert(Parametro request);
    void update(Parametro request);
    void delete(int id);
    Parametro find(Institucion request);
    Parametro findByName(Parametro request);    
    Parametro findById(Integer id);    
}
