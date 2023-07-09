package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.ParametroConfiguracion;
import pe.gob.minjus.sisarb.model.request.ParametroConfiguracionBusquedaRequest;

import java.util.List;

public interface ParametroConfiguracionMapper {

    ParametroConfiguracion findById(ParametroConfiguracion model);
    List<ParametroConfiguracion> listPaginated(ParametroConfiguracionBusquedaRequest request);
    Integer listPaginatedTotal (ParametroConfiguracionBusquedaRequest request);
    Integer update(ParametroConfiguracion model);
    
}
