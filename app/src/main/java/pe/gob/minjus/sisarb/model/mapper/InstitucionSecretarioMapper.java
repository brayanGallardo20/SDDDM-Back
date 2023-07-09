package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.InstitucionSecretario;
import pe.gob.minjus.sisarb.model.request.SecretarioBusquedaRequest;

import java.util.List;

public interface InstitucionSecretarioMapper {
    InstitucionSecretario findById(InstitucionSecretario model);
    List<InstitucionSecretario> listPaginated(SecretarioBusquedaRequest request);
    Integer listPaginatedTotal (SecretarioBusquedaRequest request);
    Integer save(InstitucionSecretario model);
    Integer update(InstitucionSecretario model);
    Integer deleteById(Integer id);
}
