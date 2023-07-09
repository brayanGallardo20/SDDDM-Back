package pe.gob.minjus.sisarb.service;

import pe.gob.minjus.sisarb.model.domain.Arbitro;
import pe.gob.minjus.sisarb.model.domain.ArbitroEspecializacion;

import java.util.List;

public interface ArbitroEspecializacionService {

    List<ArbitroEspecializacion> findByArbitro(Integer arbitroId);
    void saveByArbitro(Arbitro request, String auditUsuarioModifica);
    void deleteAllByArbitroId(Integer arbitroId,String auditUsuarioModifica);
}
