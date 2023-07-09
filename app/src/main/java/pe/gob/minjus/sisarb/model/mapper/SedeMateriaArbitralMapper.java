package pe.gob.minjus.sisarb.model.mapper;

import pe.gob.minjus.sisarb.model.domain.SedeMateriaArbitral;

import java.util.List;

public interface SedeMateriaArbitralMapper {
    List<SedeMateriaArbitral> findBySede(Integer sedeId);
    Integer save(SedeMateriaArbitral model);
    Integer update(SedeMateriaArbitral model);
    Integer deleteById(Integer sedeMateriaArbitralId,String auditUsuarioModifica);
    SedeMateriaArbitral findById(Integer sedeMateriaArbitralId);
}
