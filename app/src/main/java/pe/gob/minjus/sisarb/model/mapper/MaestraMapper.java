package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Maestra;
import pe.gob.minjus.sisarb.model.request.MaestraBusquedaRequest;
import java.util.List;

public interface MaestraMapper {

	List<Maestra> listPaginated(MaestraBusquedaRequest request);

	Integer listPaginatedTotal(MaestraBusquedaRequest request);

	Maestra findById(Maestra request);
	
	List<Maestra> findByName(Maestra request);

	Integer save(Maestra model);

	Integer update(Maestra model);

	Integer deleteById(Maestra model);

	List<Maestra> listChoose(Maestra request);

}
