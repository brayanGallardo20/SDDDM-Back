package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Sede;
import pe.gob.minjus.sisarb.model.request.SedeBusquedaRequest;

import java.util.List;

public interface SedeMapper {
    Sede findById(Integer id);
    List<Sede> listPaginated(SedeBusquedaRequest request);
    Integer listPaginatedTotal (SedeBusquedaRequest request);
    Integer save(Sede model);
    Integer update(Sede model);
    Integer deleteById(Integer id);
    List<Sede> listChoose();

    Integer countNombreSedeByInstitucion(Integer institucionId, String nombreSede);

    Integer countNombreSedeByInstitucionAndExcludeSede(Integer institucionId,String nombreSede, Integer sedeId);
}
