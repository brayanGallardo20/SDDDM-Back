package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.Auditoria;
import pe.gob.minjus.sisarb.model.request.AuditoriaBusquedaRequest;

import java.math.BigDecimal;
import java.util.List;

public interface AuditoriaMapper {

    List<Auditoria> listPaginated(AuditoriaBusquedaRequest request);
    Integer listPaginatedTotal (AuditoriaBusquedaRequest request);

    Integer insert(Auditoria auditoria);

    BigDecimal getMovimiento();
}
