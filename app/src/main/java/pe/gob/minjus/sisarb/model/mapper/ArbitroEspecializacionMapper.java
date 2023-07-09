package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.ArbitroEspecializacion;

import java.util.List;

public interface ArbitroEspecializacionMapper {
    List<ArbitroEspecializacion> findByArbitro(Integer arbitroId);
    Integer save(ArbitroEspecializacion model);
    Integer update(ArbitroEspecializacion model);
    Integer deleteById(Integer arbitroEspecializacionId,String auditUsuarioModifica);
    ArbitroEspecializacion findById(Integer arbitroEspecializacionId);
}
