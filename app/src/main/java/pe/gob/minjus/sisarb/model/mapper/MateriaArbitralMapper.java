package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.MateriaArbitral;
import pe.gob.minjus.sisarb.model.request.MateriaArbitralBusquedaRequest;
import java.util.List;

public interface MateriaArbitralMapper {

    MateriaArbitral findById(Integer id);
    List<MateriaArbitral> listPaginated(MateriaArbitralBusquedaRequest request);
    Integer listPaginatedTotal (MateriaArbitralBusquedaRequest request);
    Integer save(MateriaArbitral model);
    Integer update(MateriaArbitral model);
    Integer deleteById(Integer id);
    Integer countByField(MateriaArbitral model);
    List<MateriaArbitral> listChoose();
}
