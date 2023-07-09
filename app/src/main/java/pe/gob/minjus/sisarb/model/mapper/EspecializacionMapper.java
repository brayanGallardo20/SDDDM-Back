package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Especializacion;
import pe.gob.minjus.sisarb.model.request.EspecializacionBusquedaRequest;
import java.util.List;

public interface EspecializacionMapper {

    Especializacion findById(Integer id);
    List<Especializacion> listPaginated(EspecializacionBusquedaRequest request);
    Integer listPaginatedTotal (EspecializacionBusquedaRequest request);
    Integer save(Especializacion model);
    Integer update(Especializacion model);
    Integer deleteById(Integer id);
    Integer countByField(Especializacion model);
}
