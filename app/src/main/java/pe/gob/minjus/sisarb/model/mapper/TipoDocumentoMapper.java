package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.TipoDocumento;
import pe.gob.minjus.sisarb.model.request.TipoDocumentoBusquedaRequest;

import java.util.List;

public interface TipoDocumentoMapper {

    TipoDocumento findById(Integer id);
    List<TipoDocumento> listPaginated(TipoDocumentoBusquedaRequest request);
    Integer listPaginatedTotal (TipoDocumentoBusquedaRequest request);
    List<TipoDocumento> listAll();
    Integer save(TipoDocumento model);
    Integer update(TipoDocumento model);
    Integer deleteById(Integer id);
    List<TipoDocumento> listChoose();
    Integer countByField(TipoDocumento model);
    Integer countByPide(TipoDocumento model);
}
