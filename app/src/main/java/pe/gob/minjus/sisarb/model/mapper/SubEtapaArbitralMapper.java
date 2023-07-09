package pe.gob.minjus.sisarb.model.mapper;


import pe.gob.minjus.sisarb.model.domain.SubEtapaArbitral;
import pe.gob.minjus.sisarb.model.request.SubEtapaArbitralBusquedaRequest;

import java.util.List;

public interface SubEtapaArbitralMapper {
    SubEtapaArbitral findById(Integer id);
    List<SubEtapaArbitral> listPaginated(SubEtapaArbitralBusquedaRequest request);
    Integer listPaginatedTotal (SubEtapaArbitralBusquedaRequest request);
    Integer save(SubEtapaArbitral model);
    Integer update(SubEtapaArbitral model);
    Integer deleteById(Integer id);
    Integer countByField(SubEtapaArbitral model);
}
