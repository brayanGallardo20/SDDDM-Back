package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Paciente;
import pe.gob.minjus.sisarb.model.request.PersonaBusquedaRequest;
import java.util.List;

public interface PacienteMapper {

	Paciente findById(Integer id);
    List<Paciente> listPaginated(PersonaBusquedaRequest request);
    Integer listPaginatedTotal (PersonaBusquedaRequest request);
    List<Paciente> listAll();
    Integer save(Paciente model);
    Integer update(Paciente model);
    Integer deleteById(Integer id);
    //List<TipoDocumento> listChoose();
}
