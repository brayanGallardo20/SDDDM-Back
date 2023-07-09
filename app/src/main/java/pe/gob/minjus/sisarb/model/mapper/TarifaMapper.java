package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Tarifa;
import pe.gob.minjus.sisarb.model.request.TarifaBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.TarifaResponse;

import java.util.List;

public interface TarifaMapper {
    List<TarifaResponse> listPaginated(TarifaBusquedaRequest request);
    Integer listPaginatedTotal (TarifaBusquedaRequest request);
    void insert(Tarifa request);
    void update(Tarifa request);
    void delete(int id);
    Tarifa find(Tarifa request);
    Tarifa findByName(Tarifa request);    
    Tarifa findById(Integer id);    
}
