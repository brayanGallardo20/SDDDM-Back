package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.EtapaArbitral;
import pe.gob.minjus.sisarb.model.request.EtapaArbitralBusquedaRequest;

import java.util.List;

public interface EtapaArbitralMapper {

    EtapaArbitral findById(Integer id);
    List<EtapaArbitral> listPaginated(EtapaArbitralBusquedaRequest request);
    Integer listPaginatedTotal (EtapaArbitralBusquedaRequest request);
    Integer save(EtapaArbitral model);
    Integer update(EtapaArbitral model);
    Integer deleteById(Integer id);
    List<EtapaArbitral> listChoose();
    Integer countByField(EtapaArbitral model);
    Integer countSubEtapArbiByEtapArbiId(Integer etapaArbitralId);
}
