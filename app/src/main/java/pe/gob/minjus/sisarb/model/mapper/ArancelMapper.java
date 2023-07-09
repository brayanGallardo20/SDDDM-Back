package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Arancel;
import pe.gob.minjus.sisarb.model.request.ArancelBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.ArancelResponse;
import java.util.List;

public interface ArancelMapper {
    List<ArancelResponse> listPaginated(ArancelBusquedaRequest request);
    Integer listPaginatedTotal (ArancelBusquedaRequest request);
    void insert(Arancel request);
    void update(Arancel request);
    void delete(int id);
    Arancel find(Arancel request);
    Arancel findByName(Arancel request);    
    Arancel findById(Integer id);    
}
