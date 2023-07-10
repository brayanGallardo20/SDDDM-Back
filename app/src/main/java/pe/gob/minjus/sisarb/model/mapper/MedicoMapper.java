package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Medico;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import java.util.List;

public interface MedicoMapper {

    Medico findById(Integer id);
    List<Medico> listPaginated(PersonaBusquedaRequest request);
    Integer listPaginatedTotal (PersonaBusquedaRequest request);
    List<Medico> listAll();
    Integer save(Medico model);
    Integer update(Medico model);
    Integer deleteById(Integer id);
    //List<TipoDocumento> listChoose();
}
