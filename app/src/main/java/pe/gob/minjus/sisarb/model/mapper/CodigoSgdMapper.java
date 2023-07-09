package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.CodigoSgd;
import pe.gob.minjus.sisarb.model.request.CodigoSgdBusquedaRequest;

import java.util.List;

public interface CodigoSgdMapper {
    CodigoSgd findById(Integer id);

    List<CodigoSgd> listPaginated(CodigoSgdBusquedaRequest request);

    Integer listPaginatedTotal(CodigoSgdBusquedaRequest request);

    Integer save(CodigoSgd model);

    Integer update(CodigoSgd model);

    Integer deleteById(Integer id);
}
