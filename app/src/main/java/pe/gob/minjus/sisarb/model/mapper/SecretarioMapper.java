package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Secretario;
import pe.gob.minjus.sisarb.model.request.SecretarioBusquedaRequest;

import java.util.List;

public interface SecretarioMapper {
    Secretario findById(Integer id);
    List<Secretario> listPaginated(SecretarioBusquedaRequest request);
    Integer listPaginatedTotal (SecretarioBusquedaRequest request);
    Integer save(Secretario model);
    Integer update(Secretario model);
    Integer deleteById(Integer id);
    List<Secretario> listByField(Secretario request);
}
