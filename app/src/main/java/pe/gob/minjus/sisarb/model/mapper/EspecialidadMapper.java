package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Especialidad;
import pe.gob.minjus.sisarb.model.request.EspecialidadBusquedaRequest;
import java.util.List;

public interface EspecialidadMapper {

    Especialidad findById(Integer id);
    List<Especialidad> listPaginated(EspecialidadBusquedaRequest request);
    Integer listPaginatedTotal (EspecialidadBusquedaRequest request);
    Integer save(Especialidad model);
    Integer update(Especialidad model);
    Integer deleteById(Integer id);
    Integer countByField(Especialidad model);
    List<Especialidad> listChoose();
}
