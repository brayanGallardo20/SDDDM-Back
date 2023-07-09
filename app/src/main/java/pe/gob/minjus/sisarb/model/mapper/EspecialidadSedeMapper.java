package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.EspecialidadSede;
import pe.gob.minjus.sisarb.model.request.EspecialidadSedeBusquedaRequest;
import pe.gob.minjus.sisarb.model.response.EspecialidadSedeResponse;
import java.util.List;

public interface EspecialidadSedeMapper {
	  List<EspecialidadSedeResponse> listPaginated(EspecialidadSedeBusquedaRequest request);
	    Integer listPaginatedTotal (EspecialidadSedeBusquedaRequest request);
	    void insert(EspecialidadSede request);
	    void update(EspecialidadSede request);
	    void delete(Long id);
	    EspecialidadSede find(EspecialidadSede request);
	    EspecialidadSede findByName(EspecialidadSede request);    
	    EspecialidadSede findById(Long id); 
}
